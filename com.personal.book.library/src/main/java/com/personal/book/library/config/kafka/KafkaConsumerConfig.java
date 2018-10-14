package com.personal.book.library.config.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import org.apache.kafka.common.serialization.StringDeserializer;
import com.personal.book.library.servicelayer.model.MailContext;

@EnableKafka
@Configuration
@PropertySource({ "classpath:application.properties" })
public class KafkaConsumerConfig {

	@Autowired
	private Environment environment;
	
 
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, MailContext> mailContextListenerContainerFactory() {
    
        ConcurrentKafkaListenerContainerFactory<String, MailContext> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(mailContextConsumerFactory());
        return factory;
    }
    
    public ConsumerFactory<String, MailContext> mailContextConsumerFactory() {
		
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, environment.getProperty("kafka.bootstrap.address"));
        props.put(ConsumerConfig.GROUP_ID_CONFIG, environment.getProperty("consumer.group.name"));
        
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(MailContext.class));
    }
}
