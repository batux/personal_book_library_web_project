package com.personal.book.library.servicelayer;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import com.personal.book.library.datalayer.entity.Book;
import com.personal.book.library.datalayer.entity.User;
import com.personal.book.library.datalayer.repository.jpa.BookRepository;
import com.personal.book.library.datalayer.repository.jpa.UserRepository;
import com.personal.book.library.kafka.MailMessageProducer;
import com.personal.book.library.servicelayer.exception.ServiceLayerException;
import com.personal.book.library.util.HttpSessionUtil;
import com.personal.book.library.util.MailContextUtil;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MailMessageProducer mailMessageProducer;
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private HttpSession httpSession;
	
	
	@Transactional(rollbackFor = { SQLException.class, ServiceLayerException.class })
	public Long createBook(Book book) {
		
		if(book == null) {
			throw new ServiceLayerException("EMPTY-OBJECT-ERROR", "Book object can not be null!");
		}
		
		Long userId = HttpSessionUtil.getUserId(httpSession);
		User user = userRepository.findById(userId);
		
		if(user == null) {
			throw new ServiceLayerException("SESSION-NOT-FOUND", "Session was killed!");
		}
		
		book.setUser(user);
		book.setCreatedDate(new Date());
		book = bookRepository.save(book);
		
		mailMessageProducer.send(MailContextUtil.createMailContext(book, environment.getProperty("to.emails")));
		
		return book.getId().longValue();
	}
	
	@Transactional(rollbackFor = { SQLException.class, ServiceLayerException.class })
	@Commit
	public List<Book> prepareBooksOfUser() {
		
		Long userId = HttpSessionUtil.getUserId(httpSession);
		
		if(userId < 0) {
			throw new ServiceLayerException("INVALID-SESSION-ERROR", "Session is not valid!");
		}
		
		List<Book> books = bookRepository.findByUser(userId);
		return books;
	}
}
