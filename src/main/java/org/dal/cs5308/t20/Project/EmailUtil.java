package org.dal.cs5308.t20.Project;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtil {

	public void sendEmail(String toAddress, String subject, String content) {
		Properties props = new Properties();
		props.put("mail.smtp.host", AppProperties.properties.getProperty("mail.smtp.host"));
		props.put("mail.smtp.port", AppProperties.properties.getProperty("mail.smtp.port"));
		props.put("mail.smtp.auth", "true");
		if ("true".equals(AppProperties.properties.getProperty("mail.usetls"))) {
			props.put("mail.smtp.socketFactory.port", AppProperties.properties.getProperty("mail.smtp.port"));
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.starttls.enable", "true");
		}

		// get Session
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(AppProperties.properties.getProperty("mail.username"),
						AppProperties.properties.getProperty("mail.password"));
			}
		});
		// compose message
		try {
			MimeMessage message = new MimeMessage(session);
			message.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(toAddress));
			message.setSubject(subject);
			message.setText(content);

			// send message
			Transport.send(message);
			System.out.println("Email sent successfully to - " + toAddress);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
