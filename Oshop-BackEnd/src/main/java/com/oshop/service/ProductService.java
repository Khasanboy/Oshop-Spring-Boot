package com.oshop.service;

import java.util.List;
import java.util.Optional;

import com.oshop.model.Product;

public interface ProductService {
	
	public List<Product> getAllProducts();
	
	public Optional<Product> getProductById(Long id);
	
	public void addProduct(Product product);
	
	public void deleteProduct(Long id);
	
	public void updateProduct(Product product);
	

}
