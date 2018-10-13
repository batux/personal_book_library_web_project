package com.personal.book.library.datalayer.repository.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.personal.book.library.datalayer.entity.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer>{
	
	public Category findById(Long id);

}
