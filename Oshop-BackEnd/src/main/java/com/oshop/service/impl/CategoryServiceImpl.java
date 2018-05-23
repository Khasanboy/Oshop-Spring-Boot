package com.oshop.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oshop.model.Category;
import com.oshop.repository.CategoryRepository;
import com.oshop.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<Category> getAllCategiries() {
		return this.categoryRepository.findAll();
	}

	@Override
	public Optional<Category> getCategoryById(String id) {
		return this.categoryRepository.findById(id);
	}

	@Override
	public void addCategory(Category category) {
		this.categoryRepository.save(category);
	}

	@Override
	public void deleteCategory(String id) {
		this.categoryRepository.deleteById(id);
	}
	
	

}
