package it.unisalento.distributori.util;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SendMailSSL {
	private Logger logger = LogManager.getLogger(this.getClass().getName());
	
	public int send(String email_To, String oggetto, String testo) {
		
		logger.trace("send()");
		
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("wifidrinksnacks@gmail.com","progettosweng2017");
				}
			});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("wifidrinksnacks@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(email_To));
			message.setSubject(oggetto);
			message.setText(testo);

			Transport.send(message);

			logger.trace("Email inviata a "+email_To);

		} catch (MessagingException e) {
			logger.error("Impossibile inviare la mail con oggetto: " + oggetto + " e testo: " + testo + " a " + email_To,e);
			throw new RuntimeException(e);
		}
		
		return 0;
	}
}