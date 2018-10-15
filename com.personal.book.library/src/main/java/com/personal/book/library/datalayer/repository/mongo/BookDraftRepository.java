package com.personal.book.library.datalayer.repository.mongo;

import java.math.BigInteger;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.personal.book.library.datalayer.model.BookDraft;


@Repository
public interface BookDraftRepository extends MongoRepository<BookDraft, BigInteger>{
	
	@Query(value="{ 'userId' : ?0 }")
	public BookDraft findWithUserId(BigInteger userId);
	
	@Query(value="{ 'userId' : ?0 }", delete = true)
	public BookDraft deleteByUserId(BigInteger userId);
}
