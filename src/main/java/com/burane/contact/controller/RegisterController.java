package com.burane.contact.controller;

import com.burane.contact.model.User;
import com.burane.contact.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class RegisterController {

	@Autowired private CustomUserDetailsService userService;

	@PostMapping("/register")
	public ResponseEntity<Map<String, String>> register(@RequestBody @Valid User user) {
		boolean userExists = userService.existsByUsername(user.getUsername());

		if (userExists)
			throw new BadCredentialsException("User with username: " + user.getUsername() + " already exists");

		userService.saveUser(user);
		Map<String, String> model = new HashMap<>();
		model.put("message", "User registered successfully");

		return ResponseEntity.ok(model);
	}

}