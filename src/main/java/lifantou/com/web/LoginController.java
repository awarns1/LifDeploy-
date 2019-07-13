package lifantou.com.web;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lifantou.com.dao.PasswordResetTokenRepository;
import lifantou.com.dao.RegionRepository;
import lifantou.com.entity.AccesApp;
import lifantou.com.entity.ChoixProducteur;
import lifantou.com.entity.Donation;
import lifantou.com.entity.LigneCommande;
import lifantou.com.entity.NousContactez;
import lifantou.com.entity.PasswordResetToken;
import lifantou.com.entity.User;
import lifantou.com.paydunya.IConfigPayDunya;
import lifantou.com.service.AccountService;
import lifantou.com.service.AdminService;
import lifantou.com.service.LifantouService;
import lifantou.com.utils.UserForm;

@Controller
public class LoginController {
	Calendar c = Calendar.getInstance();
	int year = c.get(Calendar.YEAR);
	@Autowired
	private RegionRepository regionRepository;
	@Autowired
	private AccountService accountService;
	@Autowired
	private AdminService adminService;
	@Autowired
	private PasswordResetTokenRepository passwordTokenRepository;
	@Autowired
	private IConfigPayDunya configPayDunyaServ;

	@Autowired
	private LifantouService lifantouService;

	@GetMapping("/")
	public String index(ModelMap map) {
		map.put("contact", new NousContactez());
		return "views/index";
	}

	// redirection vers le form de connexion
	@GetMapping("/login")
	public String logon(ModelMap map) {
		try {
			map.put("year", year);
			map.put("formLogin", true);
			return "views/login";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// redirection vers le recover pwd
	@GetMapping("/recover")
	public String recover(ModelMap map) {
		try {
			map.put("year", year);
			map.put("formRecover", true);
			map.put("ShowformRecover", true);
			return "views/login";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// redirection vers le form donation
	@GetMapping("/donation")
	public String donation(ModelMap map) {
		try {
			map.put("year", year);
			map.put("formDonation", true);
			map.put("donation", new Donation());
			return "views/login";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@PostMapping("/donation")
	public String donationPost(Donation donation, ModelMap map, HttpServletRequest request) {
		try {
			String url = configPayDunyaServ.baseConfig(donation, request);
			return "redirect:" + url;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// verifier etat paiement
	@GetMapping("/verification-etat-paiement")
	public String verificationEtatPaiement(@RequestParam("token") String token, ModelMap map) {
		configPayDunyaServ.verifEtatPaiement(token);
		return "redirect:/";
	}

	// si client bien authentifié
	@GetMapping("/dashboard")
	public String red(Authentication auth, HttpSession session, ModelMap map) {
		try {
			if (auth != null) {
				map.put("year", year);
				AccesApp accesApp = accountService.verificationCompteUsername(auth.getName());
				session.setAttribute("Nom", accesApp.getUsername());
				session.setAttribute("RoleName", accesApp.getIdRole().getType());
				if (accesApp.getIdRole().getType().equals("ADMIN")) {
					map.put("getDefaultContAdmin", "active");
					// getNbStatAdmin(map);
					return "views/home";
				} else {
					String role = (String) session.getAttribute("RoleName");
					map.put("role", role);
					session.setAttribute("idSessionUser", accesApp.getIdUser().getId());
					getNbStatAutres(map, session, role);
					if (role.equals("PARTENAIRE")) {
						return "redirect:/Gestions/Ecoles";
					} else {
						map.put("getDefaultContentHoriz", "actived");
					}
					return "views/dashboard_h";
				}
			} else {
				return "redirect:/login";
			}
		} catch (Exception e) {
			e.printStackTrace();
			// return "redirect:/";
			return null;
		}
	}

	// redirection vers le form de creation de copmte
	@GetMapping("/register")
	public String register(ModelMap map) {
		map.put("year", year);
		map.put("userForm", new UserForm());
		map.put("regionList", regionRepository.findAll());
		return "views/register";
	}

	@PostMapping("/register")
	public String Registration(@Valid UserForm userForm, BindingResult result, HttpServletRequest request,
			ModelMap map) {
		try {
			map.put("regionList", regionRepository.findAll());
			if (result.hasErrors()) {
				return "views/register";
			} else if (userForm.getType() == null) {
				map.put("selectType", "Selectionne type de compte obligatoire !!!");
				return "views/register";
			} else if (!userForm.getPassword().equals(userForm.getConfirmPassword())) {
				map.put("erreurPwd", "Mot de passe non identique");
				return "views/register";
				// accountService.verificationCompteUser(userForm.getEmail()) != null
			} else if (accountService.verificationCompteUsername(userForm.getUsername()) != null) {
				map.put("compteOrEmailExist", "Le nom d'utilisateur existe déjà !!!");
				return "views/register";
			} else {
				if ((userForm.getEmail() == null || userForm.getEmail().equals(""))
						&& userForm.getType().toLowerCase().equals("producteur"))
					userForm.setEmail("contact@lifantou.com");
				final String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort()
						+ request.getContextPath();
				accountService.registration(userForm, appUrl);
				map.put("registrationOk",
						"Votre compte a été créé avec succès, Merci de bien vouloir patienter pour l'activation du compte qui sera effective sous 48h. !!!");
				return logon(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// validation compte registration
	@GetMapping(value = "/activation-compte")
	public String validationInscription(final HttpServletRequest request, final ModelMap map,
			@RequestParam("id") final long id, @RequestParam("token") final String token) {
		final AccesApp passToken = accountService.getAccessAppToken(token);
		if ((passToken == null)) {
			map.put("invalidToken", "clé d'activation incorrecte !!!");
			return "views/login";
		} else if ((passToken.getIdAccesApp() != id)) {
			map.put("invalidToken", "clé d'activation incorrecte !!!");
			return "views/login";
		} else {
			map.put("registrationOk", "Compte activé avec succès !!!");
			final String newToken = UUID.randomUUID().toString();
			passToken.setActived(1);
			passToken.setToken(newToken);
			accountService.activedCompte(passToken);
			return "views/login";
		}
	}

	// recover pwd post
	@PostMapping("/recover")
	public String resetPwsdPost(final HttpServletRequest request, @RequestParam("email") final String userEmail,
			ModelMap map) {
		try {
			User user = accountService.verificationCompteUser(userEmail);
			if (user != null) {
				accountService.recoverPwd(request, user);
				map.put("confirmOk", "email ok");
			} else {
				map.put("errorEmail", "Erreur email ou inexistant");
			}
			return recover(map);
		} catch (MailException e) {
			return "redirect:/recover";
		}
	}

	// verification token send by email
	@GetMapping(value = "/changePassword")
	public String changePassword(final HttpServletRequest request, final ModelMap map,
			@RequestParam("id") final long id, @RequestParam("token") final String token) {
		try {
			final PasswordResetToken passToken = accountService.getPasswordResetToken(token);
			if ((passToken == null)) {
				map.put("invalidToken", "invalidToken token");
				map.put("formLogin", true);
				return "views/login";
			}
			final AccesApp accesApp = passToken.getAccesApp();
			if ((accesApp.getIdAccesApp() != id)) {
				map.put("invalidToken", "invalidToken user");
				map.put("formLogin", true);
				return "views/login";
			}
			final Calendar cal = Calendar.getInstance();
			if ((passToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
				map.put("SessionExpired", "token expire");// retour reset password
				passwordTokenRepository.delete(passToken);
				map.put("formLogin", true);
				return "views/login";
			}
			map.put("formRecover", true);
			map.put("ShowformReset", true);
			map.put("idAc", id);
			return "views/login";
		} catch (Exception e) {
			return "redirect:/login";
		}
	}

	@PostMapping(value = "/changePassword")
	public String savePassword(final HttpServletRequest request, final ModelMap map,
			@RequestParam("idAccesApp") long idAccesApp, @RequestParam("password") final String password,
			@RequestParam("confirmPassword") final String confirmPassword) {
		try {
			if (password.equals(confirmPassword)) {
				accountService.saveNewPassword(idAccesApp, password);
				map.put("registrationOk", "Mot de passe bien reinitialiser");
				map.put("formLogin", true);
				return "views/login";
			} else {
				map.put("PwdNonIdent", "Mot de passe saisi non identiques");
				map.put("formRecover", true);
				map.put("ShowformReset", true);
				map.put("idAc", idAccesApp);
				return "views/login";
			}
		} catch (Exception e) {
			return "redirect:/login";
		}
	}

	// Access denied
	@GetMapping(value = "/Access_Denied/")
	public String access_Denied(ModelMap map) {
		map.put("ERROR", "YOU AR NOT AUTHORIZE FOR THIS PAGE");
		map.put("getFormAccessDenied", "ok");
		return "views/account";
	}

	@GetMapping(value = "/logout")
	public String logoutPage(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		session = request.getSession();
		session.invalidate();
		session = request.getSession(false);
		return "redirect:/login";
	}

	@PostMapping(value = "/Contactez-nous/")
	public String contactezNousPost(NousContactez contact, BindingResult result, ModelMap map) {
		try {
			accountService.nousContactez(contact);
			return "redirect:/";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/";
		}
	}

	public void getNbStatAdmin(ModelMap map) {
		map.put("NbProdBase", adminService.getProduits().size());
		map.put("NbCmdEnCours", adminService.getAllLigneCommandesEncours().size());
		map.put("MntTotalDonation", adminService.getAllDonation().size());
	}

	public void getNbStatAutres(ModelMap map, HttpSession session, String role) {
		Long idUser = (Long) session.getAttribute("idSessionUser");
		if (role.equals("ECOLE")) {
			map.put("ListCmdEncours", adminService.getAllLigneCommandesEcoleEncours(idUser));
			// map.put("NbCmdTerminer",
			// adminService.getAllLigneCommandesByEcoleN(idUser).size());
			map.put("NbCmdTerminer", adminService.getAllLigneCommandesByEcole(idUser).size());
			map.put("NbPaieEffect", adminService.getAllPaiementsEffectueByEcole(idUser).size());
			map.put("NbPaieRest", montantPaieRestEcole(idUser));
			map.put("NbPartenaire", adminService.getAllPartenaireByEcole(idUser).size());

		} else if (role.equals("PRODUCTEUR")) {
			map.put("NbProdProduct", adminService.getAllProduitProdByProducteur(idUser).size());
			map.put("NbCmdProd", adminService.getAllCommandesRecuByProducteur(idUser).size());
			map.put("NbALlPaieProd", montantPaieRestProd(idUser));
			map.put("NbCmdProdEncours", adminService.getAllCommandesProducteurEncours(idUser));
		} else {
			map.put("NbEcolePart", adminService.getAllEcoleByPartenaire(idUser).size());

		}

	}

	public int montantPaieRestEcole(Long idUser) {
		List<LigneCommande> lisPai = adminService.getAllPaiementsRestantByEcole(idUser);
		int mnt = 0;
		for (LigneCommande l : lisPai) {
			mnt = mnt + l.getMontantTotal();
		}
		return mnt;
	}

	public int montantPaieRestProd(Long idUser) {
		List<ChoixProducteur> listChProd = adminService.getAllPaiementsRestantByProducteur(idUser);
		int mnt2 = 0;
		for (ChoixProducteur ch : listChProd) {
			mnt2 = mnt2 + (ch.getQuantite() * ch.getLigneCommande().getProduit().getPrixUnitaire());
		}
		return mnt2;
	}

	@GetMapping("/initApp")
	public String initApp(HttpServletRequest request) {
		try {
			lifantouService.initAPP(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/";
	}

	@GetMapping("/activeAllAccounts")
	public String activeAllAccounts() {
		try {
			lifantouService.activeAllAccount();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/";
	}
}
