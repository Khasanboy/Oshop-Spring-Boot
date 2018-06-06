package com.oshop.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "cartItems", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }) })
public class ShoppingCartItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4618880923271952509L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long productId;

	private Integer quantity;

	public ShoppingCartItem() {
		super();
	}

	public ShoppingCartItem(Long productId, Integer quantity) {
		super();
		this.productId = productId;
		this.quantity = quantity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
