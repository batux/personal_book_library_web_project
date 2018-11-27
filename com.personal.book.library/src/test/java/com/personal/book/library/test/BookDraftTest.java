package com.personal.book.library.test;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.CollectionUtils;

import com.personal.book.library.config.mongo.MongoSpringConfiguration;
import com.personal.book.library.datalayer.model.BookDraft;
import com.personal.book.library.datalayer.repository.mongo.BookDraftRepository;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MongoSpringConfiguration.class)
@TestPropertySource({ "classpath:application.properties" })
public class BookDraftTest {

	@Autowired
	private MongoTemplate mongoDbTemplate;
	
	@Autowired
	private BookDraftRepository bookDraftRepository;
	
	@Test
	public void saveBookAsDraft() {
		
		BookDraft draftBook = new BookDraft();
		draftBook.setName("batuhan");
		draftBook.setUserId(new BigInteger("45"));
		draftBook.setCreatedDate(new Date());
		
		bookDraftRepository.save(draftBook);

		Query query = new Query().addCriteria(Criteria.where("name").is("batuhan"));
		List<BookDraft> books = mongoDbTemplate.find(query, BookDraft.class);
		
		Assert.assertTrue(!CollectionUtils.isEmpty(books));
		
		
		query = new Query().addCriteria(Criteria.where("userId").is("45"));
		books = mongoDbTemplate.find(query, BookDraft.class);
		
		Assert.assertTrue(!CollectionUtils.isEmpty(books));

		for(BookDraft bookDraft : books)
		{
			Assert.assertEquals(bookDraft.getId(),45);
		}
	}
}
