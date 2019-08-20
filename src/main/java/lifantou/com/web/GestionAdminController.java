package lifantou.com.web;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lifantou.com.dao.ProduitRepository;
import lifantou.com.dao.UserRepository;
import lifantou.com.entity.AccesApp;
import lifantou.com.entity.Ecole;
import lifantou.com.entity.LigneCommande;
import lifantou.com.entity.Partenaire;
import lifantou.com.entity.Producteur;
import lifantou.com.entity.Produit;
import lifantou.com.entity.ProduitProducteur;
import lifantou.com.entity.User;
import lifantou.com.service.AdminService;
import lifantou.com.service.MailService;
import lifantou.com.utils.MapLocation;

@Controller
public class GestionAdminController {
	@Autowired
	private AdminService adminService;
	@Autowired
	private ProduitRepository _produitRepository;
	@Autowired
	private MailService mailService;
	@Autowired
	private UserRepository usersRepository;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	Integer seuilStockGl = 10;

	@GetMapping("/Admin/{name}")
	public String link(@PathVariable String name, ModelMap map) {
		try {
			switch (name) {
			case "Ecoles-List":
				map.put("getEcolesList", "active");
				map.put("getEcolesListNot", "not");
				map.put("ListEcoles", adminService.getEcoles());
				map.put("title", "Liste des Ecoles");
				break;
			case "Producteurs-List":
				map.put("getProducteursList", "active");
				map.put("getProducteursListNot", "not");
				map.put("ListProduct", adminService.getProducteurs());
				map.put("title", "Liste des Producteurs");
				break;
			case "Partenaires-List":
				map.put("getPartenairesList", "active");
				map.put("getPartenairesListNot", "not");
				map.put("title", "Liste des Partenaires");
				map.put("ListPart", adminService.getPartenaires());
				break;
			case "Produit-Base":
				map.put("getCategorieProd", "active");
				map.put("getCategorieProdNot", "not");
				map.put("ListCatProd", adminService.getProduits());
				map.put("produit", new Produit());
				map.put("title", "Produits de base");
				break;
			case "Produit-Disponible":
				map.put("getProdProducteur", "active");
				map.put("ProdDispoList", "not null");
				map.put("ListProdProduct", adminService.getAllProduitProducteurByStock());
				break;
			case "Place-Marche":
				adminService.misajourStockGlobale();
				map.put("getPlaceMarcher", "active");
				map.put("getActive", "active");
				map.put("ListCatProd", adminService.getProduitMarche(seuilStockGl));
				map.put("ListCmdEnCours", adminService.getAllLigneCommandesEncours());
				break;
			case "Commandes":
				map.put("ListeCmdEtPaiementEtDonation", "Liste des commandes");
				map.put("getCommandes", "active");
				map.put("CtlCmd", "not");
				map.put("ListCommande", adminService.getAllLigneCommandes());
				break;
			case "Paiements":
				map.put("ListeCmdEtPaiementEtDonation", "Liste des paiements");
				map.put("getPaiements", "active");
				map.put("getActive", "active");
				map.put("ListPaiements", adminService.getAllPaiements());
				map.put("ListPaiementsProd", adminService.getAllChoixProducteur());
				break;
			case "Donations":
				map.put("ListeCmdEtPaiementEtDonation", "Liste des donations");
				map.put("getDonations", "active");
				map.put("ListDonations", adminService.getAllDonation());
				map.put("ListFaireUnDons", adminService.getAllFaireUnDon());
				break;
			case "Commentaires":
				map.put("ListeCommentaires", "Liste des commentaires");
				map.put("getCommentaires", "active");
				map.put("title", "Les commentaires");
				map.put("ListCommentaires", adminService.getAllCommentaires());
				break;
			case "Comptes-Users":
				map.put("ListeCmdEtPaiementEtDonation", "Accès des utilisateurs");
				map.put("getComptesUser", "active");
				map.put("getComptesUserNot", "not");
				map.put("ListAccessApp", adminService.getAllAccessApp());
				break;
			case "Cartographie-Ecole":
				map.put("Cartographie", "Cartographie des Ecoles");
				map.put("mapForEcole", true);
				map.put("getCartographie", "active");
				map.put("mapMarkersLocations", getMapMarkerLocations(true));
				map.put("mapMarkersLabels", getMapMarkerLables(true));
				map.put("mapCenterPositionIndice", getMapMarkerCenterPosIndice(true));
				break;
			case "Cartographie-Producteur":
				map.put("Cartographie", "Cartographie des Producteurs");
				map.put("mapForProductor", true);
				map.put("getCartographie", "active");
				map.put("mapMarkersLocations", getMapMarkerLocations(false)); //
				map.put("mapMarkersLabels", getMapMarkerLables(false)); //
				map.put("mapCenterPositionIndice", getMapMarkerCenterPosIndice(false));
				break;
			}
			map.put("NbCmdEnCours", adminService.getAllLigneCommandesEncours().size());
			return "views/home";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// mis a jour global
	@GetMapping("/Admin/misajourstock/")
	public String misAjourStock(ModelMap map) {
		adminService.misajourStockGlobale();
		map.put("MisAjouSk", "success");
		return link("Produit-Base", map);
	}

	// add produit de base
	@PostMapping("/Admin/Produit-Base/new")
	public String addProdBase(Produit produit, @RequestParam("file") MultipartFile mfile, ModelMap map)
			throws IOException {
		adminService.SaveProduit(produit, mfile);
		map.put("addProdBase", "success");
		return link("Produit-Base", map);
	}

	// edi produit de base
	@PostMapping("/Admin/Produit-Base/edit")
	public String editProdBase(Produit produit, @RequestParam("file") MultipartFile mfile, ModelMap map)
			throws IOException {
		adminService.editProduit(produit, mfile);
		map.put("editProdBase", "success");
		return link("Produit-Base", map);
	}

	@PostMapping("/Admin/Coordonnee/edit/")
	public String editCoordonneeUser(@RequestParam("longitud") String longitud, @RequestParam("latitud") String latitud,
			@RequestParam("type") String type, @RequestParam("idUser") Long idUser, ModelMap map) {
		switch (type) {
		case "ecol":
			Ecole ec = adminService.getEcole(idUser);
			ec.setLongitudeEcole(Double.parseDouble(longitud));
			ec.setLatitudeEcole(Double.parseDouble(latitud));
			usersRepository.save(ec);
			return link("Ecoles-List", map);
		case "prod":
			Producteur pr = adminService.getProducteur(idUser);
			pr.setLongitudeProduct(Double.parseDouble(longitud));
			pr.setLatitudeProduct(Double.parseDouble(latitud));
			usersRepository.save(pr);
			return link("Producteurs-List", map);
		}
		return "views/home";
	}

	// add produit producteur
	@GetMapping("/Admin/Produit-Disponible/{id}-{vals}/")
	public String addProdProducteur(@PathVariable Long id, @PathVariable String vals, ModelMap map) {
		map.put("getProdProducteur", "active");
		map.put("addProduitProduct", "not null");
		switch (vals) {
		case "new":
			map.put("id", id);
			map.put("idPP", 0);
			map.put("prodBaseListe", adminService.getProduits());
			break;
		case "edit":
			ProduitProducteur pp = adminService.getProduitProducteurById(id);
			map.put("prodBas", adminService.getProduit(pp.getProduit().getId()));
			map.put("idPP", id);
			map.put("id", pp.getProducteur().getId());
			map.put("qte", pp.getQuantiteStock());
			map.put("edit", "not null");
			break;
		}
		return "views/home";
	}

	//
	@GetMapping("/Admin/{vales}/{id}/")
	public String linkById(@PathVariable Long id, @PathVariable String vales, ModelMap map,
			HttpServletRequest request) {
		try {
			final String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort();
			switch (vales) {
			case "Passer_commande":
				Ecole e = adminService.getEcole(id);
				map.put("idEcole", e.getId());
				map.put("Ident", e.getIdentifiant());
				map.put("text", ": Commande pour l'ecole: " + e.getIdentifiant());
				return link("Place-Marche", map);
			case "Payer_commande":
				adminService.changeEtatPaiementLigneCmd(id, 1);
				return link("Commandes", map);
			case "Livraison_commande":
				adminService.changeEtatLigneCommande(id, 2);
				return link("Commandes", map);
			case "Commande_livree":
				adminService.changeEtatLigneCommande(id, 3);
				return link("Commandes", map);
			case "Annuler_commande":
				LigneCommande ligCmd = adminService.changeEtatLigneCommande(id, 4);
				String msg1 = "<p>Votre commande à été annulée en raison de rupture de stock pour ce produit.</p>"
						+ "<p>Reference commande: " + ligCmd.getRef() + "<p>" + "<br/><p>Lien plateforme: <b>" + appUrl
						+ "</b><p>";
				mailService.sendEmailHtml("Annulation commande", ligCmd.getEcole().getEmail(), msg1);
				return link("Commandes", map);
			case "Anuler_paiement_cmd":
				adminService.changeEtatPaiementLigneCmd(id, 0);
				return link("Paiements", map);
			case "Payer_producteur":
				adminService.changeEtatPaiementChoixProducteur(id, 1);
				return link("Paiements", map);
			case "Annuler_paiement_prod":
				adminService.changeEtatPaiementChoixProducteur(id, 0);
				return link("Paiements", map);
			case "Repartition_commande":
				map.put("getRepartiCmd", "active");
				link("Place-Marche", map);
				map.put("getActive", "v");
				LigneCommande lcmd = adminService.getLigneCommandeById(id);
				map.put("cmd", lcmd);
				map.put("prodProductLis", adminService.getAllProduitProducteurByProduit(lcmd.getProduit().getId()));
				break;
			case "Details_commande":
				map.put("ListeCmdEtPaiementEtDonation", "Détails de la commande");
				map.put("getCommandes", "active");
				map.put("getDetailCmd", "not");
				LigneCommande lCmd = adminService.getLigneCommandeById(id);
				map.put("lcmd", lCmd);
				map.put("choixProdLis", adminService.getAllChoixProducteurByLigneCmd(id));
				break;
			case "Activer_compte":
				AccesApp acc = adminService.getAccesAppActived(id, 1);
				return link("Comptes-Users", map);
			case "Desactiver_compte":
				adminService.getAccesAppActived(id, 0);
				return link("Comptes-Users", map);
			case "Bloquer_compte":
				adminService.getAccesAppActived(id, 2);
				return link("Comptes-Users", map);
			case "Supprimer_compte":
				adminService.deleteAccesApp(id);
				return link("Comptes-Users", map);
			case "Supprimer_cmnt":
				adminService.deleteCommentaire(id);
				return link("Commentaires", map);
			case "Ecoles_partenaire":
				map.put("getPartenairesList", "active");
				map.put("getEcolePart", "not");
				Partenaire pa = adminService.getPartenaire(id);
				map.put("title", "Liste des Ecoles pour le Partenaire: " + pa.getIdentifiant());
				map.put("ListEcolePart", adminService.getAllEcoleByPartenaire(id));
				break;
			case "Details_informations":
				map.put("ListeCmdEtPaiementEtDonation", "Accès des utilisateurs");
				map.put("getComptesUser", "active");
				map.put("getDetailInfosUser", "not");
				map.put("AccessApp", adminService.getAccesApp(id));
				User u = usersRepository.findOne(adminService.getAccesApp(id).getIdUser().getId());
				if (u instanceof Producteur) {
					map.put("prod", (Producteur) u);
				} else if (u instanceof Ecole) {
					map.put("ecol", (Ecole) u);
				} else if (u instanceof Partenaire) {
					map.put("parte", (Partenaire) u);
				}
				break;
			case "Affecter_ecole":
				map.put("partenaire", adminService.getPartenaire(id));
				map.put("title",
						"Affecter Ecole pour le partenaire: " + adminService.getPartenaire(id).getIdentifiant());
				map.put("getPartenairesList", "active");
				map.put("getAddEcoleForPart", "not");
				map.put("ListEcoleExistant", adminService.getAllEcoleByPartenaires(id));
				map.put("ListEcoleNonAffect",
						adminService.getAllEcoleNonAffecterToPart(adminService.getAllEcoleByPartenaire(id)));
				break;
			case "Modifier_produit":
				map.put("getCategorieProd", "active");
				map.put("editProduitBase", "not");
				Produit prd = adminService.getProduit(id);
				map.put("title", "Modification du produit: " + prd.getNomProduit());
				map.put("produit", new Produit());
				map.put("prodBase", prd);
				break;
			case "Supprimer_produit":
				if (adminService.deleteProduitBase(id)) {
					map.put("suppProdOK", "Produit de base bien supprimé. !!!");
				} else {
					map.put("suppProdKO", "Produit de base non supprimé, il contient déjà des produits.!!!");
				}
				return link("Produit-Base", map);
			}
			return "views/home";
		} catch (Exception e) {
			return link("Place-Marche", map);
		}
	}

	// edit profil user
	@GetMapping("/Admin/{vals}/{id}/edit")
	public String linkById2(@PathVariable Long id, @PathVariable String vals, ModelMap map) {
		try {
			map.put("editAdmin", "admin");
			switch (vals) {
			case "Ecole":
				map.put("getEcolesList", "active");
				map.put("getEcolesCoord", "not");
				map.put("datas", adminService.getUserForm(adminService.getEcole(id), "ECOLE"));
				map.put("role", "ECOLE");
				map.put("title", "Mise à jour informations école");
				break;
			case "Producteur":
				map.put("getProducteursList", "active");
				map.put("getProdCoord", "not");
				map.put("datas", adminService.getUserForm(adminService.getProducteur(id), "PRODUCTEUR"));
				map.put("role", "PRODUCTEUR");
				map.put("title", "Mise à jour informations producteur");
				break;
			case "Partenaire":
				map.put("getPartenairesList", "active");
				map.put("getEditInfoPart", "not");
				map.put("datas", adminService.getUserForm(adminService.getPartenaire(id), "PARTENAIRE"));
				map.put("role", "PARTENAIRE");
				map.put("title", "Mise à jour informations partenaire");
				break;
			}
			map.put("regionList", adminService.getAllRegion());
			return "views/home";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@PostMapping("/Admin/Passer-commande-ecole/")
	public String addCmdAdmin(@RequestParam("selectCh") String selectCh[], @RequestParam("qteCmd") Integer qteCmd[],
			@RequestParam("idEcole") Long idEcole, @RequestParam("pu") Integer pu[],
			@RequestParam("dateLiv") String dateLiv[], ModelMap map) {
		try {
			for (int i = 0; i < selectCh.length; i++) {
				String val[] = selectCh[i].split("/");
				Long idProd = Long.parseLong(val[0]);
				Integer position = Integer.parseInt(val[1]);
				Produit pr = adminService.getProduit(idProd);
				if (qteCmd[position - 1] != null && qteCmd[position - 1] <= pr.getStockGlobal()) {
					Integer mnt = pu[position - 1] * qteCmd[position - 1];
					adminService.saveLigneCommande(idProd, idEcole, qteCmd[position - 1], mnt,
							sdf.parse(dateLiv[position - 1]));
				}
			}
			return "redirect:/Admin/Place-Marche";
		} catch (ParseException e) {
			e.printStackTrace();
			return "redirect:/Admin/Place-Marche";
		}
	}

	@PostMapping("/Admin/Repartition/commande")
	public String repartitionCmdPost(@RequestParam("idProdProduct") String idProdProduct[],
			@RequestParam("qteProd") Integer qteProd[], @RequestParam("idLigneCmd") Long idLigneCmd, ModelMap map,
			HttpServletRequest request) {
		int qteTotal = 0;
		for (int i = 0; i < idProdProduct.length; i++) {
			String val[] = idProdProduct[i].split("/");
			Long idProdProd = Long.parseLong(val[0]);
			Integer position = Integer.parseInt(val[1]);
			qteTotal = qteTotal + qteProd[position - 1];
		}
		LigneCommande lg = adminService.getLigneCommandeById(idLigneCmd);
		if (lg.getQuantite() > lg.getProduit().getStockGlobal()) {
			map.put("errokQte", "Quantitée stock produit est insuffisante pour satisfaire la commande");
			return linkById(idLigneCmd, "Repartition_commande", map, request);
		} else if (qteTotal < lg.getQuantite()) {
			map.put("errokQte", "Quantitée saissie est inférieur à la quantitée commandée");
			return linkById(idLigneCmd, "Repartition_commande", map, request);
		} else if (qteTotal > lg.getQuantite()) {
			map.put("errokQte", "Quantitée saissie est supérieur à la quantitée commandée");
			return linkById(idLigneCmd, "Repartition_commande", map, request);
		} else {
			boolean resp = false;
			for (int i = 0; i < idProdProduct.length; i++) {
				String val[] = idProdProduct[i].split("/");
				Long idProdProd = Long.parseLong(val[0]);
				Integer position = Integer.parseInt(val[1]);
				ProduitProducteur pp = adminService.getProduitProducteurById(idProdProd);
				if (qteProd[position - 1] != null && qteProd[position - 1] > 0) {
					if (qteProd[position - 1] > pp.getQuantiteStock()) {
						resp = true;
						break;
					}
				} else {
					map.put("errokQte", "Quantite saissie non valide");
					return linkById(idLigneCmd, "Repartition_commande", map, request);
				}
			}
			if (resp) {
				map.put("errokQte", "Quantitée saissie non disponible chez le producteur");
				return linkById(idLigneCmd, "Repartition_commande", map, request);
			} else {
				for (int i = 0; i < idProdProduct.length; i++) {
					String val[] = idProdProduct[i].split("/");
					Long idProdProd2 = Long.parseLong(val[0]);
					Integer position2 = Integer.parseInt(val[1]);
					adminService.valideLigneCommande(idLigneCmd, idProdProd2, qteProd[position2 - 1]);
				}
			}
		}
		return "redirect:/Admin/Commandes";
	}

	@PostMapping("/Admin/AddProdToPanier/")
	public String card(@RequestParam("selectCh") Long selectCh[], @RequestParam("qteCmd") Integer qteCmd[],
			@RequestParam("idEcole") Long idEcole, ModelMap map) {
		for (int i = 0; i < qteCmd.length; i++) {
			if (qteCmd[i] != null) {
				adminService.saveCart(idEcole, selectCh[i], qteCmd[i]);
			}
		}
		return link("Place-Marche", map);
	}

	// add ecole to partenaire
	@PostMapping("/Admin/AddEcoleToPartenaire/")
	public String addEcoleToPartenaire(@RequestParam("idPart") Long idPart, @RequestParam("idEcole") Long idEcole[],
			ModelMap map, HttpServletRequest request) {
		try {
			for (int i = 0; i < idEcole.length; i++) {
				adminService.addEcoleForPartenaire(idPart, idEcole[i]);
			}
			return linkById(idPart, "Affecter_ecole", map, request);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// delete ecole to affectation partenaire
	@GetMapping("/Admin/{id}/DeleteEcoleToPartenaire/{idPart}/")
	public String deleteEcoleToPartenaire(@PathVariable("id") Long id, @PathVariable("idPart") Long idPart,
			ModelMap map, HttpServletRequest request) {
		try {
			adminService.deleteEcoleToPart(id);
			return linkById(idPart, "Affecter_ecole", map, request);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// Return photos
	@GetMapping(value = "/getPhoto/{val}/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getPhoto(@PathVariable("id") Long id, @PathVariable("val") String vals) throws IOException {
		switch (vals) {
		case "ProdBaseImg":
			Produit prod = adminService.getProduit(id);
			return IOUtils.toByteArray(new ByteArrayInputStream(prod.getPhotoBytes()));
		}
		return null;
	}

	@GetMapping("/Admin/findProdBase")
	@ResponseBody
	public Produit getProduiBse(Long id) {
		return _produitRepository.findOne(id);
	}

	@GetMapping("/Admin/deleteProdBase")
	public String getDeleteProdBase(Long id, ModelMap map) {
		if (adminService.deleteProduitBase(id)) {
			map.put("suppProdOK", "Produit de base bien supprimé. !!!");
		} else {
			map.put("suppProdKO", "Produit de base non supprimé, il contient déjà des produits.!!!");
		}
		return link("Produit-Base", map);
		/*
		 * _produitRepository.delete(id); return "redirect:/Admin/Produit-Base/";
		 */
	}

	// return geolocal positions for ecoles or proctors
	private List<MapLocation> getMapMarkerLocations(boolean ecoleLocation) {
		List<MapLocation> locations = new ArrayList<>();
		try {
			if (ecoleLocation) {
				List<Ecole> ecoles = adminService.getEcoles();
				for (Ecole ecole : ecoles) {
					locations.add(new MapLocation(ecole.getLatitudeEcole(), ecole.getLongitudeEcole()));
				}
				System.out.println(locations.size() + " ******* location");
			} else {
				List<Producteur> prods = adminService.getProducteurs();
				for (Producteur prod : prods) {
					locations.add(new MapLocation(prod.getLatitudeProduct(), prod.getLongitudeProduct()));
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// return jsondata;
		return locations;
	}

	private List<String> getMapMarkerLables(boolean ecoleLabeles) {
		List<String> labels = new ArrayList<>();
		try {
			if (ecoleLabeles) {
				List<Ecole> ecoles = adminService.getEcoles();
				for (Ecole ecole : ecoles) {
					labels.add(ecole.getNomEcole());
					System.out.println("label " + ecole.getNomEcole());
				}
				System.out.println(labels.size() + " ******* location");
			} else {
				List<Producteur> prods = adminService.getProducteurs();
				for (Producteur prod : prods) {
					labels.add(prod.getPrenomProduct() + " " + prod.getNomProduct());
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// return jsondata;
		return labels;
	}

	private int getMapMarkerCenterPosIndice(boolean ecoleLocation) {
		int max = 0, random = 0;
		try {
			max = (ecoleLocation) ? adminService.getEcoles().size() : adminService.getProducteurs().size();
			random = new Random().nextInt(max);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return random;
	}

}
