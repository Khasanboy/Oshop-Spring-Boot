package com.oshop.controller;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.oshop.model.Category;
import com.oshop.model.Product;
import com.oshop.payload.ProductResponse;
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

	@Autowired
	FileStorageService fileStorageService;

	@GetMapping("/{id}")
	public ProductResponse getProductById(@PathVariable Long id) {
		Product product = this.productService.getProductById(id).orElse(null);

		return new ProductResponse(product.getId(), product.getTitle(), product.getPrice(),
				product.getCategory().getId(), product.getImgUrl());
	}

	@GetMapping("/")
	public List<ProductResponse> getAllProducts() {

		return this.productService
				.getAllProducts().stream().map(product -> new ProductResponse(product.getId(), product.getTitle(),
						product.getPrice(), product.getCategory().getId(), product.getImgUrl()))
				.collect(Collectors.toList());

	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/")
	public void addProduct(@RequestParam("file") MultipartFile file, @RequestParam("title") String title,
			@RequestParam("price") BigDecimal price, @RequestParam("categoryId") String categoryId) {

		Category category = this.categoryService.getCategoryById(categoryId).orElse(null);

		String fileName = fileStorageService.storeFile(file);

		Product product = new Product(title, price, category, "http://localhost:8081/api/files/download/" + fileName);

		this.productService.addProduct(product);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public void updateProduct(@PathVariable("id") Long id,
			@RequestParam(value = "file", required = false) MultipartFile file, @RequestParam("title") String title,
			@RequestParam("price") BigDecimal price, @RequestParam("categoryId") String categoryId)
			throws MalformedURLException {

		Category category = this.categoryService.getCategoryById(categoryId).orElse(null);

		Product oldProduct = this.productService.getProductById(id).orElse(null);
		String fileName = "";

		if (file != null) {
			fileName = fileStorageService.storeFile(file);
			String url = oldProduct.getImgUrl();
			String name = url.substring(url.lastIndexOf('/') + 1, url.length());
			fileStorageService.deleteFile(name);
		}

		Product product = new Product(title, price, category,
				file != null ? "http://localhost:8081/api/files/download/" + fileName : oldProduct.getImgUrl());

		product.setId(id);

		this.productService.updateProduct(product);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public void deleteProduct(@PathVariable Long id) {
		Product product = productService.getProductById(id).orElse(null);
		if (product != null) {
			String url = product.getImgUrl();
			String name = url.substring(url.lastIndexOf('/') + 1, url.length());
			fileStorageService.deleteFile(name);
			this.productService.deleteProduct(id);
		}
	}
}
