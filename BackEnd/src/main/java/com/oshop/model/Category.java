package com.oshop.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="categories",
	uniqueConstraints = {
			@UniqueConstraint(columnNames= {
					"id"
			})
	}
)
public class Category implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1794877852791453688L;

	@Id
	private String id;
	
	@NotBlank
	private String name;

	public Category() {
		super();
	}

	public Category(String id, @NotBlank String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
		

}
