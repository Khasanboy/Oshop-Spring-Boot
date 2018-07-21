package com.oshop.payload;

import java.math.BigDecimal;

public class ProductResponse {
	
	private Long id;
	
	private String title;
	
	private BigDecimal price;
	
	private String category;
	
	private String imageUrl;
	

	public ProductResponse(Long id, String title, BigDecimal price, String category, String imageUrl) {
		this.id = id;
		this.title = title;
		this.price = price;
		this.category = category;
		this.imageUrl = imageUrl;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	

}
