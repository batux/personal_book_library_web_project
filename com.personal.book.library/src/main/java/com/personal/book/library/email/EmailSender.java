package com.personal.book.library.email;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.personal.book.library.servicelayer.model.MailContext;

@Component
public class EmailSender {

	private static final Logger logger = LoggerFactory.logger(EmailSender.class);
	
	private Session session;
	
	@Autowired
	private Environment environment;
	
	public void send(MailContext mailContext) {
		
		try {
			
			Session session = getSession();
			
			if(session != null) {
				transport(session, mailContext);
			}
			else {
				logger.warn("Email could not be send! To-Emails: " + mailContext.getToEmails());
			}
		} 
		catch (Exception e) {
			logger.error("Email Send Error: " + e.getMessage());
		}
	}
	
	private void transport(Session session, MailContext mailContext) throws MessagingException, UnsupportedEncodingException {
		
		MimeMessage msg = new MimeMessage(session);
		msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
		msg.addHeader("format", "flowed");
		msg.addHeader("Content-Transfer-Encoding", "8bit");

		msg.setFrom(new InternetAddress("no_reply@example.com", "NoReply-JD"));
		msg.setReplyTo(InternetAddress.parse("no_reply@example.com", false));

		msg.setSubject(mailContext.getTitle(), "UTF-8");
		msg.setText(mailContext.getBody(), "UTF-8");
		msg.setSentDate(new Date());
		
		msg.addRecipients(Message.RecipientType.TO, InternetAddress.parse(mailContext.getToEmails()));
		
		Transport.send(msg);
	}
	
	private Session getSession() {
		
		if(session == null) {
			
			final String fromEmail = environment.getProperty("from.email");
			final String password = environment.getProperty("email.password");
			
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			
			Authenticator auth = new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(fromEmail, password);
				}
			};
			
			this.session = Session.getInstance(props, auth);
		}
		
		return session;
	}
}
