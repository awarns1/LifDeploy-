package lifantou.com.service;

import javax.servlet.http.HttpServletRequest;

import lifantou.com.entity.AccesApp;
import lifantou.com.entity.NousContactez;
import lifantou.com.entity.PasswordResetToken;
import lifantou.com.entity.Role;
import lifantou.com.entity.User;
import lifantou.com.utils.UserForm;

public interface AccountService {
	void registration(UserForm userForm, String contextPath);

	Role SaveRole(Role role);

	AccesApp SaveAccesApp(AccesApp accesApp);

	String generateIdentifiant(String sigle, String type);

	AccesApp getAccessAppToken(String token);

	AccesApp activedCompte(AccesApp accesApp);

	User verificationCompteUser(String email);

	AccesApp verificationCompteUsername(String username);

	AccesApp getAccessAppByUser(Long idUser);

	void createPasswordResetTokenForAccessApp(AccesApp accesApp, String token);

	PasswordResetToken getPasswordResetToken(String token);

	void recoverPwd(final HttpServletRequest request,User user);
	
	void saveNewPassword(Long idAccesApp, String newPassword);
	
	void nousContactez(NousContactez nousContactez);

}
