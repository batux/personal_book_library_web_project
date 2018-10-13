package com.personal.book.library.servicelayer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personal.book.library.datalayer.entity.Category;
import com.personal.book.library.datalayer.repository.jpa.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<Category> prepareCategories() {
		
		Iterator<Category> categoryIterator = categoryRepository.findAll().iterator();
		List<Category> categories = new ArrayList<Category>();
		
		while(categoryIterator.hasNext()) {
			Category category = categoryIterator.next();
			categories.add(category);
		}
		
		return categories;
	}
}
