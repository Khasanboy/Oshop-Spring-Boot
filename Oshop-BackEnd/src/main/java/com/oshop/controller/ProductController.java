package com.oshop.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oshop.model.Category;
import com.oshop.model.Product;
import com.oshop.payload.CreateProductRequest;
import com.oshop.payload.ProductResponse;
import com.oshop.service.CategoryService;
import com.oshop.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/{id}")
	public ProductResponse getProductById(@PathVariable Long id) {
		Product product = this.productService.getProductById(id).orElse(null);
		
		return new ProductResponse(product.getId(), product.getTitle(), product.getPrice(), product.getCategory().getId(), product.getImgUrl());
	}
	
	@GetMapping("/")
	public List<Product> getAllProducts(){
		
		return this.productService.getAllProducts();
	
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/")
	public void addProduct(@RequestBody CreateProductRequest request) {
		
		System.out.println(request.toString());
		Category category = this.categoryService.getAllCategiries().get(0);
				
		Category cat = this.categoryService.getCategoryById(request.getCategory()).orElse(category);
		
		Product product = new Product(request.getTitle(), request.getPrice(), cat, request.getImageUrl());
		
		this.productService.addProduct(product);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public void updateProduct(@PathVariable("id") Long id, @RequestBody CreateProductRequest request) {
		
		System.out.println("Update product");
		
		Category category = this.categoryService.getAllCategiries().get(0);
		
		Category cat = this.categoryService.getCategoryById(request.getCategory()).orElse(category);
		
		Product product = new Product(request.getTitle(), request.getPrice(), cat, request.getImageUrl());
		
		product.setId(id);
		
		this.productService.updateProduct(product);
	}
	
	@DeleteMapping("/{id}")
	public void deleteProduct(@PathVariable Long id) {
		this.productService.deleteProduct(id);
	}
}
