package com.oshop.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
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
	
	@OneToMany(fetch=FetchType.EAGER)
	@JoinTable(name="shoppingCart_items",
			joinColumns = @JoinColumn(name ="shoppingCart_id"),
			inverseJoinColumns = @JoinColumn(name = "item_id"))
	private Set<ShoppingCartItem> items = new HashSet<>();

	public ShoppingCart() {
		super();
	}

	public ShoppingCart(Long id, Set<ShoppingCartItem> items) {
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

	public Set<ShoppingCartItem> getItems() {
		return items;
	}

	public void setItems(Set<ShoppingCartItem> items) {
		this.items = items;
	}
	
	public void addItem(ShoppingCartItem item) {
		this.items.add(item);
	}
	
	public void removeItem(Long id) {
		Iterator<ShoppingCartItem> items = this.items.iterator();
		
		while(items.hasNext()) {
			
			if(items.next().getId().equals(id)) {
				items.remove();
			}
		}
		
	}
		
}
