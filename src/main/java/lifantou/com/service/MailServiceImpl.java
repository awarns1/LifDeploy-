package lifantou.com.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import lifantou.com.entity.AccesApp;

@Service("mailService")
public class MailServiceImpl implements MailService {
	@Autowired
	JavaMailSender mailSender;

	@Override
	public void sendEmailHtml(String objet, String emailDest, String message) {
		MimeMessage messages = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(messages);
		try {
			String htmlMsg = "<div>" + "<div>"
					+ "<img src='https://i.imgur.com/vbasWDy.png' style=\"margin-top: 5px; margin-left: 35px;\"><hr>"
					+ "</div>" + "<div>" + "" + message + "" + "</div><div>Cordialement,</div>"
					+ "<div><b>L'équipe Lifantou<b/></div>" + "</div>";
			messages.setContent(htmlMsg, "text/html");
			helper.setTo(emailDest);
			helper.setSubject(objet);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		mailSender.send(messages);
	}

	@Override
	public SimpleMailMessage constructActivedAccountEmail(String contextPath, String token, AccesApp accessApp) {
		final String url = contextPath + "/activation-compte?id=" + accessApp.getIdAccesApp() + "&token=" + token;
		final String message = "Bonjour "
				+ " \r\nMerci d'avoir créé un compte sur notre plateforme Lifantou. Vous pouvez maintenant"
				+ " activer votre compte en cliquant sur le lien ci-dessous";
		final SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(accessApp.getIdUser().getEmail());
		email.setSubject("Activation compte utilisateur");
		email.setText(message + " \r\n" + url + " \r\n\n " + "Cordialement,\n Mail: From Lifantou :)");
		mailSender.send(email);
		return email;
	}

	@Override
	public void constructResetTokenEmail(String contextPath, String token, AccesApp accessApp) {
		MimeMessage messages = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(messages);
		final String url = contextPath + "/changePassword?id=" + accessApp.getIdAccesApp() + "&token=" + token;
		final String message = "Vous avez demandé à réinitialiser votre mot de passe. Vous pouvez maintenant"
				+ " valider cette demande en cliquant sur le lien ci-dessous";
		try {
			String htmlMsg = "<div>" + "<div>"
					+ "<img src='https://i.imgur.com/vbasWDy.png' style=\"margin-top: 5px; margin-left: 35px;\"><hr>"
					+ "</div>" + "<div>" + "" + message + "" + "</div><div>" + "" + url + "" + "</div>"
					+ "<br/><div><b>L'équipe Lifantou<b/></div>" + "</div>";
			messages.setContent(htmlMsg, "text/html");
			helper.setTo(accessApp.getIdUser().getEmail());
			helper.setSubject("Reset Password");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		mailSender.send(messages);
	}

}