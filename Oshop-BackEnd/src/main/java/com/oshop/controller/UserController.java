package com.oshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oshop.payload.UserSummary;
import com.oshop.repository.UserRepository;
import com.oshop.security.CurrentUser;
import com.oshop.security.UserPrincipal;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/me")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
		
		System.out.println(currentUser.getId());
		
		UserSummary userSummary = new UserSummary(currentUser.getId(), currentUser.getName(), currentUser.getSurname(), currentUser.getAuthorities());
		
		return userSummary;
		
	}

}
