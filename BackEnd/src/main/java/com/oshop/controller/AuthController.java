package com.oshop.controller;

import java.net.URI;
import java.util.Collections;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.oshop.exception.AppException;
import com.oshop.model.Role;
import com.oshop.model.RoleName;
import com.oshop.model.User;
import com.oshop.payload.ApiResponse;
import com.oshop.payload.JwtAuthenticationResponse;
import com.oshop.payload.LoginRequest;
import com.oshop.payload.SignUpRequest;
import com.oshop.repository.RoleRepository;
import com.oshop.repository.UserRepository;
import com.oshop.security.JwtTokenProvider;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	JwtTokenProvider tokenProvider;
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest){
		
		System.out.println("I am coming to login");
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						loginRequest.getEmail(),
						loginRequest.getPassword()
						)
				);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String jtw = tokenProvider.generateToken(authentication);
		
		return ResponseEntity.ok(new JwtAuthenticationResponse(jtw));
		
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("/signup")
	public  ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest){
		
		System.out.println("came "+ signUpRequest);
		
		
		if(userRepository.existsByEmail(signUpRequest.getEmail())) {
			return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"), HttpStatus.BAD_REQUEST);
		}
		
		// Create user
		
		User user = new User(signUpRequest.getName(), signUpRequest.getSurname(), signUpRequest.getEmail(), signUpRequest.getPassword());
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
				.orElseThrow(()-> new AppException("User Role not set"));
		
		user.setRoles(Collections.singleton(userRole));
		
		User result = userRepository.save(user);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentContextPath().path("/user/{username}")
				.buildAndExpand(result.getName()).toUri();
		
		return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
		
	}

}
