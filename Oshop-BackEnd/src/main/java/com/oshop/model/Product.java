package com.oshop.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="products")
public class Product implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1182540649589295498L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String title;
	
	private BigDecimal price;
	
	@JsonIgnore
	@OneToOne(fetch=FetchType.LAZY)
	@JoinTable(name="product_category",
			joinColumns=@JoinColumn(name="product_id"),
			inverseJoinColumns = @JoinColumn(name="category_id")
			)
	private Category category;
	
	@NotBlank
	private String imgUrl;

	public Product() {
		super();
	}

	public Product(@NotBlank String title, BigDecimal price, Category category, @NotBlank String imgUrl) {
		this.title = title;
		this.price = price;
		this.category = category;
		this.imgUrl = imgUrl;
	}
	

	public Product(Long id, @NotBlank String title, BigDecimal price, Category category, @NotBlank String imgUrl) {
		this.id = id;
		this.title = title;
		this.price = price;
		this.category = category;
		this.imgUrl = imgUrl;
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
}
