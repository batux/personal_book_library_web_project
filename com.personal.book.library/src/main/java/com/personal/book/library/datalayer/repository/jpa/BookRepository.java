package com.personal.book.library.datalayer.repository.jpa;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.personal.book.library.datalayer.entity.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, BigInteger>{
	
	public Book findById(BigInteger id);
	public List<Book> findByUser(BigInteger userId);

	@Query(value = "FROM Book b WHERE b.user.id = :id")
	public List<Book> findByUser(@Param("id") Long userId);
}
