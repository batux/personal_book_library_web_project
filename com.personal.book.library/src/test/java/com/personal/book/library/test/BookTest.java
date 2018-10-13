package com.personal.book.library.test;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

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
import org.springframework.util.CollectionUtils;

import com.personal.book.library.datalayer.config.jpa.RepositorySpringConfiguration;
import com.personal.book.library.datalayer.entity.Book;
import com.personal.book.library.datalayer.entity.Category;
import com.personal.book.library.datalayer.entity.User;
import com.personal.book.library.datalayer.model.LikeDegree;
import com.personal.book.library.datalayer.repository.jpa.BookRepository;
import com.personal.book.library.datalayer.repository.jpa.CategoryRepository;
import com.personal.book.library.datalayer.repository.jpa.UserRepository;
import com.personal.book.library.util.CryptoUtil;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RepositorySpringConfiguration.class)
@TestPropertySource({ "classpath:application.properties" })
public class BookTest {

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Test
	@Transactional
	@Commit
	public void createBook() {
		
		Book book1 = prepareBook();
		book1 = bookRepository.save(book1);
		
		Assert.assertNotNull(book1.getId());
	}
	
	@Test
	public void findBookById() {
		
		Iterator<Book> bookIterator = bookRepository.findAll().iterator();
		if(bookIterator.hasNext()) {
			Book book = bookIterator.next();
			book = bookRepository.findById(book.getId());
			Assert.assertNotNull(book.getId());
		}
		else {
			Assert.fail();
		}
	}
	
	@Test
	@Transactional
	@Commit
	public void listBooksByUserId() {
		
		Book book1 = prepareBook();
		book1 = bookRepository.save(book1);
		
		Long userId = book1.getUser().getId();
		List<Book> booksOfUser = bookRepository.findByUser(userId);
		
		Assert.assertTrue(!CollectionUtils.isEmpty(booksOfUser));
	}
	
	
	private Book prepareBook() {
		
		Book book1 = new Book();
		book1.setName("Effective Java");
		book1.setAuthorFullName("Joshua Bloch");
		book1.setCreatedDate(new Date());
		book1.setLikeDegree(LikeDegree.LIKED_TOO_MUCH);
		
		Category category = null;
		Iterable<Category> categories = categoryRepository.findAll();
		Iterator<Category> categoryIterator = categories.iterator();
		if(!categoryIterator.hasNext()) {
			category = new Category();
			category.setName("Bilgisayar Kitapları");
			category = categoryRepository.save(category);
		}
		else {
			category =categoryIterator.next();
		}
		
		
		User user = null;
		Iterable<User> users = userRepository.findAll();
		Iterator<User> userIterator = users.iterator();
		
		if(!userIterator.hasNext()) {
			user = new User();
			user.setName("batuhan");
			user.setSurname("düzgün");
			user.setNickName("batux");
			user.setPassword(CryptoUtil.cryptPassword("12345"));
			user.setCreateDate(new Date());
			user = userRepository.save(user);
		}
		else {
			user = userIterator.next();
		}
		
		book1.setCategory(category);
		book1.setUser(user);
		return book1;
	}
	
}
