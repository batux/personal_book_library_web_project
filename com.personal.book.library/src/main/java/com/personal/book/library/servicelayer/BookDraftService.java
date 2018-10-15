package com.personal.book.library.servicelayer;

import java.math.BigInteger;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.personal.book.library.datalayer.model.BookDraft;
import com.personal.book.library.datalayer.repository.mongo.BookDraftRepository;

@Service
public class BookDraftService {

	@Autowired
	private BookDraftRepository bookDraftRepository;
	
	
	@Transactional
	public boolean saveBookAsDraft(BookDraft book, Long userId) {
		
		BigInteger userIdAsBigInt = new BigInteger(String.valueOf(userId));
		book.setUserId(userIdAsBigInt);
		book.setCreatedDate(new Date());
		
		BookDraft originalDraft = bookDraftRepository.findWithUserId(userIdAsBigInt);
		
		if(originalDraft != null) {
			originalDraft.setAuthorFullName(book.getAuthorFullName());
			originalDraft.setCategory(book.getCategory());
			originalDraft.setCreatedDate(new Date());
			originalDraft.setLikeDegree(book.getLikeDegree());
			originalDraft.setName(book.getName());
			originalDraft.setUserId(userIdAsBigInt);
		}
		else {
			originalDraft = book;
		}
		
		originalDraft = bookDraftRepository.save(originalDraft);
		
		return true;
	}
	
	
	public BookDraft findDraftBook(Long userId) {
		
		BookDraft book = bookDraftRepository.findWithUserId(new BigInteger(String.valueOf(userId)));
		return book;
	}
	
}
