package lifantou.com.service;

import lifantou.com.dao.AccesAppRepository;
import lifantou.com.dao.PasswordResetTokenRepository;
import lifantou.com.dao.RegionRepository;
import lifantou.com.dao.RoleRepository;
import lifantou.com.dao.UserRepository;
import lifantou.com.entity.AccesApp;
import lifantou.com.entity.Ecole;
import lifantou.com.entity.NousContactez;
import lifantou.com.entity.Partenaire;
import lifantou.com.entity.PasswordResetToken;
import lifantou.com.entity.Producteur;
import lifantou.com.entity.Region;
import lifantou.com.entity.Role;
import lifantou.com.entity.User;
import lifantou.com.utils.UserForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	AccesAppRepository accesAppRepository;
	@Autowired
	private RegionRepository regionRepository;
	@Autowired
	private AccountService accountService;
	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	@Autowired
	private PasswordResetTokenRepository passwordTokenRepository;
	@Autowired
	private MailService mailService;
	// String emailDest = "lifantou.workgroup@gmail.com";
	String emailDest = "awa.thiam@lifantou.com";

	// ajouter un user
	@Override
	public void registration(UserForm userForm, String contextPath) {
		if (userForm == null)
			throw new RuntimeException("aucune donne envoyer ");
		User user = new User();
		user.setEmail(userForm.getEmail());
		user.setTel(userForm.getTel());
		user.setAdresse(userForm.getAdresse());
		Region region = regionRepository.getOne(userForm.getIdRegion());
		AccesApp accesApp = new AccesApp();
		accesApp.setUsername(userForm.getUsername());
		accesApp.setPassword(passwordEncoder.encode(userForm.getPassword()));
		accesApp.setToken(UUID.randomUUID().toString());
		if (userForm.getUsername().contains("@admin")) {
			user.setRegion(region);
			userRepository.save(user);
			accesApp.setIdUser(user);
			accesApp.setIdRole(roleRepository.findRoleByType("ADMIN"));
			accesApp.setActived(1);
			SaveAccesApp(accesApp);
		} else {
			accesApp.setActived(0);
			if (userForm.getType().equals("Ecole")) {
				Ecole ecole = new Ecole(accountService.generateIdentifiant(region.getSigle(), "RS"), user.getEmail(),
						user.getTel(), user.getAdresse(), region);
				ecole.setNomEcole(userForm.getNomEcole());
				ecole.setNomGest(userForm.getNomGest());
				ecole.setPrenomGest(userForm.getPrenomGest());
				ecole.setBudgetAnnuel(0);
				userRepository.save(ecole);
				accesApp.setIdUser(ecole);
				accesApp.setIdRole(roleRepository.findRoleByType("ECOLE"));
				SaveAccesApp(accesApp);

			} else if (userForm.getType().equals("Producteur")) {
				Producteur p = new Producteur(accountService.generateIdentifiant(region.getSigle(), "PA"),
						user.getEmail(), user.getTel(), user.getAdresse(), region);
				p.setPrenomProduct(userForm.getPrenomProduct());
				p.setNomProduct(userForm.getNomProduct());
				userRepository.save(p);
				accesApp.setIdUser(p);
				accesApp.setIdRole(roleRepository.findRoleByType("PRODUCTEUR"));
				SaveAccesApp(accesApp);

			} else if (userForm.getType().equals("Partenaire")) {
				Partenaire parte = new Partenaire(accountService.generateIdentifiant(region.getSigle(), "PE"),
						user.getEmail(), user.getTel(), user.getAdresse(), region);
				parte.setDomaineActivite(userForm.getDomaineActivite());
				parte.setNomStructure(userForm.getNomStructure());
				userRepository.save(parte);
				accesApp.setIdUser(parte);
				accesApp.setIdRole(roleRepository.findRoleByType("PARTENAIRE"));
				SaveAccesApp(accesApp);
			}
		}
		String msg = "<p>Votre compte utilisateur avec l'adresse e-mail " + accesApp.getIdUser().getEmail()
				+ " a été créé.</p>"
				+ "<p>Merci de bien vouloir patienter pour l'activation du compte qui sera effective sous 48h.<p>";
		// mailService.sendEmailHtml("Création de compte",
		// accesApp.getIdUser().getEmail(), msg);
	}

	@Override
	public AccesApp SaveAccesApp(AccesApp accesApp) {
		return accesAppRepository.saveAndFlush(accesApp);
	}

	@Override
	public Role SaveRole(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public String generateIdentifiant(String sigle, String type) {
		int valeurMin = 100;
		int valeurMax = 10000;
		Random r = new Random();
		int valeur = valeurMin + r.nextInt(valeurMax - valeurMin);
		String ident = sigle + "-" + type + "-" + valeur;
		return ident;
	}

	@Override
	public AccesApp getAccessAppToken(String token) {
		return accesAppRepository.findByToken(token);
	}

	@Override
	public AccesApp activedCompte(AccesApp accesApp) {
		return accesAppRepository.save(accesApp);
	}

	@Override
	public User verificationCompteUser(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public AccesApp verificationCompteUsername(String username) {
		return accesAppRepository.findByUsername(username);
	}

	@Override
	public AccesApp getAccessAppByUser(Long idUser) {
		return accesAppRepository.findByIdUserId(idUser);
	}

	@Override
	public void createPasswordResetTokenForAccessApp(AccesApp accesApp, String token) {
		final PasswordResetToken myToken = new PasswordResetToken(token, accesApp);
		passwordTokenRepository.save(myToken);
	}

	@Override
	public PasswordResetToken getPasswordResetToken(String token) {
		return passwordTokenRepository.findByToken(token);
	}

	@Override
	public void recoverPwd(final HttpServletRequest request, User user) {
		AccesApp accesApp = accountService.getAccessAppByUser(user.getId());
		final String token = UUID.randomUUID().toString();
		accountService.createPasswordResetTokenForAccessApp(accesApp, token);
		final String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath();
		mailService.constructResetTokenEmail(appUrl, token, accesApp);
	}

	@Override
	public void saveNewPassword(Long idAccesApp, String newPassword) {
		AccesApp accesApp = accesAppRepository.getOne(idAccesApp);
		accesApp.setPassword(passwordEncoder.encode(newPassword));
		accesAppRepository.save(accesApp);
		PasswordResetToken p = passwordTokenRepository.findByAccesApp(accesApp);
		passwordTokenRepository.delete(p);
	}

	@Override
	public void nousContactez(NousContactez contact) {
		String htmlMsg = "<p>Nom complet: " + contact.getNomComplet() + "</p>" + "<p>Email Exp :" + contact.getEmail()
				+ "</p>" + "<p>" + contact.getMessage() + "</p>";
		mailService.sendEmailHtml(contact.getObjt(), emailDest, htmlMsg);

	}
}
