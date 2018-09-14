package com.oshop.service;

import java.util.List;
import java.util.Optional;

import com.oshop.model.Category;

public interface CategoryService {
	
	public List<Category> getAllCategories();
	
	@SuppressWarnings("unused")
	public Optional<Category> getCategoryById(String id);
	
	@SuppressWarnings("unused")
	public void addCategory(Category category);
	
	@SuppressWarnings("unused")
	public void deleteCategory(String id);

}
