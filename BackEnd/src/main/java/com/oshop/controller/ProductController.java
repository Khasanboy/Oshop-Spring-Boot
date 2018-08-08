package com.oshop.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.oshop.model.Category;
import com.oshop.model.Product;
import com.oshop.payload.CreateProductRequest;
import com.oshop.payload.ProductResponse;
import com.oshop.payload.UploadFileResponse;
import com.oshop.service.CategoryService;
import com.oshop.service.FileStorageService;
import com.oshop.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired FileStorageService fileStorageService;
	
	@GetMapping("/{id}")
	public ProductResponse getProductById(@PathVariable Long id) {
		Product product = this.productService.getProductById(id).orElse(null);
		
		return new ProductResponse(product.getId(), product.getTitle(), product.getPrice(), product.getCategory().getId(), product.getImgUrl());
	}
	
	@GetMapping("/")
	public List<ProductResponse> getAllProducts(){
		
	 return this.productService.getAllProducts().stream().map(product -> new ProductResponse(product.getId(), product.getTitle(), product.getPrice(), product.getCategory().getId(), product.getImgUrl()))
		.collect(Collectors.toList());
	
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/")
	public void addProduct(@RequestParam("file") MultipartFile file,  @RequestParam("title") String title, @RequestParam("price") BigDecimal price, @RequestParam("categoryId") String categoryId){
		
		Category category = this.categoryService.getCategoryById(categoryId).orElse(null);
		
		String fileName = fileStorageService.storeFile(file);

		Product product = new Product(title, price, category, "http://localhost:8081/api/files/download/"+fileName);

		this.productService.addProduct(product);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public void updateProduct(@PathVariable("id") Long id, @RequestBody CreateProductRequest request) {
		/*
		
		Category category = this.categoryService.getCategoryById(request.getCategory()).orElse(null);
		
		Product product = new Product(request.getTitle(), request.getPrice(), category, request.getImageUrl());
		
		product.setId(id);
		
		this.productService.updateProduct(product);
		*/
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public void deleteProduct(@PathVariable Long id) {
		this.productService.deleteProduct(id);
	}
}
