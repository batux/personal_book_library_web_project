package com.personal.book.library.datalayer.repository.mongo;

import java.math.BigInteger;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.personal.book.library.datalayer.model.Book;


@Repository
public interface BookDraftRepository extends MongoRepository<Book, BigInteger>{

	public Book findByUserId(BigInteger userId);
	
	@Query(value="{ 'userId' : ?0 }", delete = true)
	public Book deleteByUserId(BigInteger userId);
}
