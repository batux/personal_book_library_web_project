package com.personal.book.library.kafka;

import java.util.concurrent.CountDownLatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.personal.book.library.email.EmailSender;
import com.personal.book.library.servicelayer.model.MailContext;

@Component
public class MailMessageConsumer {

	private CountDownLatch mailContextLanch = new CountDownLatch(1);
	
	@Autowired
	private EmailSender emailSender;
	
	@KafkaListener(topics = "${mail.topic.name}", group = "${consumer.group.name}", containerFactory = "mailContextListenerContainerFactory")
	public void consumeMailContext(MailContext mailContext) {
		
		emailSender.send(mailContext);
		mailContextLanch.countDown();
	}
}
