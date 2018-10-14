package com.personal.book.library.test;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.personal.book.library.config.jpa.RepositorySpringConfiguration;
import com.personal.book.library.config.kafka.KafkaConsumerConfig;
import com.personal.book.library.config.kafka.KafkaProducerConfig;
import com.personal.book.library.config.mongo.MongoSpringConfiguration;
import com.personal.book.library.datalayer.entity.User;
import com.personal.book.library.datalayer.repository.jpa.UserRepository;
import com.personal.book.library.util.CryptoUtil;


@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RepositorySpringConfiguration.class, 
		MongoSpringConfiguration.class, 
		KafkaConsumerConfig.class, 
		KafkaProducerConfig.class})
@TestPropertySource({ "classpath:application.properties" })
public class UserTest {

	@Autowired
	private UserRepository userRepository;
	
	@Test
	@Transactional
	@Commit
	public void createUser() {
		
		User user = new User();
		user.setName("batuhan");
		user.setSurname("düzgün");
		user.setNickName("batux");
		user.setPassword(CryptoUtil.cryptPassword("12345"));
		user.setCreateDate(new Date());

		user = userRepository.save(user);
		
		Assert.assertNotNull(user.getId());
	}
	
	@Test
	public void findByUserName() {
		
		Iterator<User> userIterator = userRepository.findAll().iterator();
		
		if(userIterator.hasNext()) {
			User user = userIterator.next();
			List<User> users = userRepository.findByUserName(user.getNickName(), new PageRequest(0, 1));
			Assert.assertNotNull(users.get(0).getId());
		}
		else {
			Assert.fail();
		}
	}
	
	@Test
	public void findUserById() {
		
		Iterable<User> users = userRepository.findAll();
		Iterator<User> usersIterator = users.iterator();
		
		if(usersIterator != null && usersIterator.hasNext()) {
			User user = usersIterator.next();
			user = userRepository.findById(user.getId());
			Assert.assertNotNull(user.getId());
		}
		else {
			Assert.fail();
		}
	}
	
}
