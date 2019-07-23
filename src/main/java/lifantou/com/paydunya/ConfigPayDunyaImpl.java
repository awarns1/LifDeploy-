package lifantou.com.paydunya;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lifantou.com.entity.FaireUnDon;
import lifantou.com.service.AdminService;

@Service
public class ConfigPayDunyaImpl implements IConfigPayDunya {
	PaydunyaSetup setup = new PaydunyaSetup();
	PaydunyaCheckoutStore store = new PaydunyaCheckoutStore();
	@Autowired
	private AdminService adminService;

	@Override
	public String baseConfig(FaireUnDon faireUnDon, HttpServletRequest request) {
		final String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort();
		setup.setMasterKey("yTBJjJRr-HpSV-N2dz-kjpS-usf16JihnBSs");// free key :yTBJjJRr-HpSV-N2dz-kjpS-usf16JihnBSs //
		setup.setPrivateKey("live_private_AKSzcSepPIxgFEIijNWWj0Vphdf"); // test_private_1hQOJ2huWiNwB90wzgPGG4wG2PW
		setup.setPublicKey("live_public_MrtsfBLEwm2qs1sTytkiKkrSBiZ"); // free key :
																		// test_public_frnXPQJVbb0WVsbrDdWnbJrKbX5
		setup.setToken("9BTR767G0LutGgguzCMI"); // free token : osSBviQcmF6h9KepMmcL
		setup.setMode("live"); // mode test
		// setup.setMode("live"); // Optionnel. Utilisez cette option pour les paiements
		// tests.

		store.setName("Lifantou"); // Seul le nom est requis
		store.setTagline("Chaque graine est une lumière!");
		store.setPhoneNumber("+221 78-374-93-49");
		store.setPostalAddress("27 C Cité Soprim");
		store.setWebsiteUrl("" + appUrl + "/");
		store.setLogoUrl("https://i.imgur.com/K6BQy4W.png?1");

		// Configuration de l'IPN (Instant Payment Notification)
		store.setCallbackUrl(appUrl + "/Admin/paynotif");

		// Configuration d'une URL de redirection après annulation de paiement
		store.setCancelUrl(appUrl + "/faire-un-don");

		// Configuration d'une URL de redirection après confirmation de paiement
		store.setReturnUrl(appUrl + "/verification-etat-paiement");

		PaydunyaCheckoutInvoice invoice = new PaydunyaCheckoutInvoice(setup, store);
		invoice.setTotalAmount(faireUnDon.getMontant());
		invoice.setDescription("Montant donation: " + faireUnDon.getMontant() + " FCFA");
		invoice.create();
		if (invoice.create()) {
			faireUnDon.setToken(invoice.getToken());
			adminService.saveFaireUnDon(faireUnDon);
			/*
			 * System.out.println("1 "+invoice.getStatus());
			 * System.out.println("2 "+invoice.getResponseText());
			 * System.out.println("3 "+invoice.getInvoiceUrl());
			 */
			return invoice.getInvoiceUrl();
		} else {
			// erreur on doit rediriger sa
			// System.out.println("5 " + invoice.getResponseCode());
			return store.getCancelUrl();
		}
	}

	@Override
	public void verifEtatPaiement(String token) {
		PaydunyaCheckoutInvoice invoice = new PaydunyaCheckoutInvoice(setup, store);
		if (invoice.confirm(token)) {

			// Récupérer le statut du paiement
			// Le statut du paiement peut être soit completed, pending, cancelled
			// System.out.println("7 " + invoice.getStatus());
			// System.out.println("8 " + invoice.getResponseText());

			// Récupérer l'URL du reçu PDF électronique pour téléchargement
			// System.out.println(invoice.getReceiptUrl());

			// Vous pouvez aussi récupérer le montant total spécifié précédemment
			System.out.println(invoice.getTotalAmount());

		} else {
			adminService.deleteDonationByToken(token);
			// System.out.println("11 " + invoice.getStatus());
			/*
			 * System.out.println("12 " + invoice.getResponseText());
			 * System.out.println("13 " + invoice.getResponseCode());
			 */
		}
	}

}
