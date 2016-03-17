package mail.send;

import java.security.Security;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {
	private String mailhost = "smtp.gmail.com";

	public synchronized void sendMail(String subject, String body, final String sender, String recipients)
			throws Exception {

		Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.host", mailhost);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.quitwait", "false");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				//TODO -- Password need to update
				return new PasswordAuthentication(sender, "XXXX_password");
			}
		});

		MimeMessage message = new MimeMessage(session);
		message.setSender(new InternetAddress(sender));
		message.setSubject(subject);
		message.setContent(body, "text/plain");
		if (recipients.indexOf(',') > 0) {
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipients));
		} else {
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipients));
		}

		Transport.send(message);

	}

	public static void main(String args[]) throws Exception {
		SendMail mailutils = new SendMail();
		mailutils.sendMail("test", "test", "sunnymahajan89@gmail.com", "vmahajanjkt@gmail.com");

	}

	// public static void main(String[] args) {
	//
	// final String username = "sunnymahajan89@gmail.com";
	// final String password = "gshriram5457";
	//
	// Properties props = new Properties();
	// props.put("mail.smtp.auth", "true");
	// props.put("mail.smtp.starttls.enable", "true");
	// props.put("mail.smtp.host", "smtp.gmail.com");
	// props.put("mail.smtp.port", "587");
	//
	// Session session = Session.getInstance(props,
	// new javax.mail.Authenticator() {
	// protected PasswordAuthentication getPasswordAuthentication() {
	// return new PasswordAuthentication(username, password);
	// }
	// });
	//
	// try {
	//
	// Message message = new MimeMessage(session);
	// message.setFrom(new InternetAddress("from-email@gmail.com"));
	// message.setRecipients(Message.RecipientType.TO,
	// InternetAddress.parse("vmahajanjkt@gmail.com"));
	// message.setSubject("Testing Subject");
	// message.setText("Dear Mail Crawler,"
	// + "\n\n No spam to my email, please!");
	//
	// Transport.send(message);
	//
	// System.out.println("Done");
	//
	// } catch (MessagingException e) {
	// throw new RuntimeException(e);
	// }
	// }

}
