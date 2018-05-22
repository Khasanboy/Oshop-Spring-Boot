package com.oshop.security;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.oshop.model.User;

public class UserPrincipal implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8890638164681993350L;

	private Long id;
	
	private String name;
	
	private String surname;
	
	@JsonIgnore
	private String email;
	
	@JsonIgnore
	private String password;
	
	private Collection<? extends GrantedAuthority> authorities;

	public UserPrincipal(Long id, String name, String surname, String email, String password,
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}
	
	public static UserPrincipal create(User user) {
		List<GrantedAuthority> authorities = user.getRoles().stream().map(role ->
			new SimpleGrantedAuthority(role.getName().name())
		).collect(Collectors.toList());
		
		return new UserPrincipal(user.getId(), user.getName(), user.getSurname(), user.getEmail(), user.getPassword(), authorities);
	}
	
	

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getEmail() {
		return email;
	}


	@Override
	public String getPassword() {
		return password;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		
		UserPrincipal that = (UserPrincipal) obj;
		
		return Objects.equals(id,  that.id);
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	
	
	//Not used
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

}
