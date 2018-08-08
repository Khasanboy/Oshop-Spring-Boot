package com.oshop.payload;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;


public class CreateProductRequest {
	
	@NotBlank
	private String title;
	
	@NotBlank
	private BigDecimal price;
	
	@NotBlank
	private String category;

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

}
