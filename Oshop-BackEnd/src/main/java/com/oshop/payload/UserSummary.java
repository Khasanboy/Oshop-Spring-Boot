package com.oshop.payload;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;

public class UserSummary {

	private Long id;

	private String name;

	private String surname;
	
	private Collection<? extends GrantedAuthority> roles;

	public UserSummary(Long id, String name, String surname, Collection<? extends GrantedAuthority> roles) {
		
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.roles = roles;
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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Collection<? extends GrantedAuthority> getRoles() {
		return roles;
	}

	public void setRoles(Collection<? extends GrantedAuthority> roles) {
		this.roles = roles;
	}
	
	
}
