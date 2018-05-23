package com.oshop.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oshop.model.Product;
import com.oshop.repository.ProductRepository;
import com.oshop.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Product> getAllProducts() {
		return this.productRepository.findAll();
	}

	@Override
	public Optional<Product> getProductById(Long id) {
		return this.productRepository.findById(id);
	}

	@Override
	public void addProduct(Product product) {
		this.productRepository.save(product);
	}

	@Override
	public void deleteProduct(Long id) {
		this.productRepository.deleteById(id);
	}

	@Override
	public void updateProduct(Product product) {
		this.productRepository.save(product);
	}

}
