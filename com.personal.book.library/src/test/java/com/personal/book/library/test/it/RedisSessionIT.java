package com.personal.book.library.test.it;

import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.personal.book.library.config.jpa.RepositorySpringConfiguration;
import com.personal.book.library.config.kafka.KafkaConsumerConfig;
import com.personal.book.library.config.kafka.KafkaProducerConfig;
import com.personal.book.library.config.mongo.MongoSpringConfiguration;
import com.personal.book.library.config.redis.RedisSessionConfiguration;
import redis.clients.jedis.Jedis;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RepositorySpringConfiguration.class, 
		MongoSpringConfiguration.class, 
		KafkaConsumerConfig.class, 
		KafkaProducerConfig.class,
		RedisSessionConfiguration.class})
@TestPropertySource({ "classpath:application.properties" })
public class RedisSessionIT {

	private Jedis jedis;
	
	@Autowired
	private Environment environment;
	
	@Test
	public void beSureRedisSessionStoreEmpty() {
		
		jedis = new Jedis(environment.getProperty("redis.host"), Integer.parseInt(environment.getProperty("redis.port")));
		jedis.flushAll();
		
		Set<String> redisResult = jedis.keys("*");
        Assert.assertTrue(redisResult.size() == 0);
	}
	
	// Integration Test for Redis Connection
	@Test
	public void testRedisConnection() {
		
		jedis = new Jedis(environment.getProperty("redis.host"), Integer.parseInt(environment.getProperty("redis.port")));
		
		Set<String> redisResult = jedis.keys("*");
		System.out.println(redisResult);
        Assert.assertTrue(redisResult.size() > 0);
	}
}
