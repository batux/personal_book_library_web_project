package com.personal.book.library.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.personal.book.library.servicelayer.model.MailContext;

@Component
public class MailMessageProducer {

	@Autowired
	private Environment environment;
	
	@Autowired
	private KafkaTemplate<String, MailContext> mailContextKafkaTemplate;
	
	public void send(MailContext mailContext) {
		this.mailContextKafkaTemplate.send(environment.getProperty("mail.topic.name"), mailContext);
	}
}
