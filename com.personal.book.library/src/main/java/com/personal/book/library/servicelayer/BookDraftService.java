package com.personal.book.library.servicelayer;

import java.math.BigInteger;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personal.book.library.datalayer.model.Book;
import com.personal.book.library.datalayer.repository.mongo.BookDraftRepository;
import com.personal.book.library.util.HttpSessionUtil;

@Service
public class BookDraftService {

	@Autowired
	private BookDraftRepository bookDraftRepository;
	
	@Autowired
	private HttpSession httpSession;
	
	
	public boolean saveBookAsDraft(Book book) {
		
		Long userId = HttpSessionUtil.getUserId(httpSession);
		BigInteger userIdAsBigInt = new BigInteger(String.valueOf(userId));
		book.setUserId(userIdAsBigInt);
		book.setCreatedDate(new Date());
		
		Book originalDraft = bookDraftRepository.findByUserId(userIdAsBigInt);
		
		if(originalDraft != null) {
			book.setId(originalDraft.getId());
			bookDraftRepository.save(originalDraft);
		}
		
		bookDraftRepository.save(book);
		
		return true;
	}
	
	
	public Book findDraftBook() {
		
		Long userId = HttpSessionUtil.getUserId(httpSession);
		Book book = bookDraftRepository.findByUserId(new BigInteger(String.valueOf(userId)));
		return book;
	}
	
}
