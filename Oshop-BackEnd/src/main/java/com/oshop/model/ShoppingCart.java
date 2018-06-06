package com.oshop.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.oshop.model.audit.DateAudit;

@Entity
@Table(name = "carts")
public class ShoppingCart extends DateAudit {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7597186240952951687L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinTable(name="shoppingCart_items",
			joinColumns = @JoinColumn(name ="shoppingCart_id"),
			inverseJoinColumns = @JoinColumn(name = "item_id"))
	private Set<Role> items = new HashSet<>();

	public ShoppingCart() {
		super();
	}

	public ShoppingCart(Long id, Set<Role> items) {
		super();
		this.id = id;
		this.items = items;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Role> getItems() {
		return items;
	}

	public void setItems(Set<Role> items) {
		this.items = items;
	}
		
}
