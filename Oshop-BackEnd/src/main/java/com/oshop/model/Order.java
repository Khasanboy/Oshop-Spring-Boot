package com.oshop.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
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
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="orders", uniqueConstraints = {
		@UniqueConstraint(columnNames = {
				"id"
		})
})
public class Order implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2340503318380200055L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private Date datePlaced;
	
	@OneToMany(fetch=FetchType.EAGER)
	@JoinTable(name="order_items",
			joinColumns = @JoinColumn(name ="order_id"),
			inverseJoinColumns = @JoinColumn(name = "item_id"))
	private Set<ShoppingCartItem> items = new HashSet<>();

	public Order() {
		super();
	}

	public Order(Long id, Date datePlaced, Set<ShoppingCartItem> items) {
		super();
		this.id = id;
		this.datePlaced = datePlaced;
		this.items = items;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDatePlaced() {
		return datePlaced;
	}

	public void setDatePlaced(Date datePlaced) {
		this.datePlaced = datePlaced;
	}

	public Set<ShoppingCartItem> getItems() {
		return items;
	}

	public void setItems(Set<ShoppingCartItem> items) {
		this.items = items;
	}	

}
