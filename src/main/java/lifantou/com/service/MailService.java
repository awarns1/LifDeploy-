package lifantou.com.service;

import org.springframework.mail.SimpleMailMessage;

import lifantou.com.entity.AccesApp;

public interface MailService {
	public void sendEmailHtml(String objet, String emailDest, String message);

	public SimpleMailMessage constructActivedAccountEmail(String contextPath, String token, AccesApp accessApp);

	public void constructResetTokenEmail(final String contextPath, final String token, final AccesApp accessApp);

}
