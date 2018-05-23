package com.oshop.service;

import java.util.List;
import java.util.Optional;

import com.oshop.model.Category;

public interface CategoryService {
	
	public List<Category> getAllCategiries();
	
	public Optional<Category> getCategoryById(String id);
	
	public void addCategory(Category category);
	
	public void deleteCategory(String id);

}
