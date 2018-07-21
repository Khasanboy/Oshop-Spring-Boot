package com.oshop.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="shippings")
public class Shipping implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8754435734884168757L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String line1;
	
	private String line2;
	
	private String city;

	public Shipping() {
		super();
	}

	public Shipping(String name, String line1, String line2, String city) {
		super();
		this.name = name;
		this.line1 = line1;
		this.line2 = line2;
		this.city = city;
	}

	public Shipping(Long id, String name, String line1, String line2, String city) {
		super();
		this.id = id;
		this.name = name;
		this.line1 = line1;
		this.line2 = line2;
		this.city = city;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLine1() {
		return line1;
	}

	public void setLine1(String line1) {
		this.line1 = line1;
	}

	public String getLine2() {
		return line2;
	}

	public void setLine2(String line2) {
		this.line2 = line2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	

}
