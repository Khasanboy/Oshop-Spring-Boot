package com.oshop.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "items", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }) })
public class ShoppingCartItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4618880923271952509L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Integer quantity;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinTable(name="item_product",
			joinColumns = @JoinColumn(name ="item_id"),
			inverseJoinColumns = @JoinColumn(name = "product_id"))
	private Product product;

	public ShoppingCartItem() {
		super();
	}

	public ShoppingCartItem(Long id, Integer quantity, Product product) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.product = product;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	
}
