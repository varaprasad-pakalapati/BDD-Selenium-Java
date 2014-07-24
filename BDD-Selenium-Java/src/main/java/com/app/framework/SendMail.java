package com.app.framework;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendMail {
	
	private Properties props;
	private Message message;
	private Session session;
	Config config;
	
	SendMail() {
		props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		createSession();
	}
	
	private void createSession() {
		session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication("varaprasad.pakalapati@gmail.com","prasadpaka2");
					}
				});
	}

	public void transport(String testDetails, String reportFilePath) {
		
		Multipart multipart = new MimeMultipart();
		BodyPart messageBodyPart = new MimeBodyPart();
        DataSource source = new FileDataSource(reportFilePath);
        try {
        	Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("varaprasad.pakalapati@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(config.getInstance().getDistributionList()));
			message.setSubject(testDetails);
			message.setText("Dear Mail Crawler," +
					"\n\n No spam to my email, please!");
			message.setFileName("Automation test results.");
			
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(reportFilePath);
			multipart.addBodyPart(messageBodyPart);
			message.setContent(multipart );
			Transport.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}		
	}

}
