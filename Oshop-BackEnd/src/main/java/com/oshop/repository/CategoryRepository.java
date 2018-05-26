package com.oshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oshop.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String>{
	
}
