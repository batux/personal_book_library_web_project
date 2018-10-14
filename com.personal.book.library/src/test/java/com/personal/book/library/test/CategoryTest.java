package com.personal.book.library.test;

import java.util.Iterator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.personal.book.library.datalayer.entity.Category;
import com.personal.book.library.datalayer.repository.jpa.CategoryRepository;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RepositorySpringConfiguration.class, 
		MongoSpringConfiguration.class, 
		KafkaConsumerConfig.class, 
		KafkaProducerConfig.class})
@TestPropertySource({ "classpath:application.properties" })
public class CategoryTest {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Test
	@Transactional
	@Commit
	public void createCategory() {
		
		Category category = new Category();
		category.setName("Edebiyat");
		category = categoryRepository.save(category);
		
		Assert.assertNotNull(category.getId());
	}
	
	
	@Test
	public void getAllCategories() {
		
		Iterator<Category> categoryIterator = categoryRepository.findAll().iterator();
		Assert.assertTrue(categoryIterator.hasNext());
	}
	
}
