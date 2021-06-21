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
		Map<String, String> model = new HashMap<>();

		if (user.getUsername().isBlank()) {
			model.put("message", "Username cannot be blank");
			return ResponseEntity.badRequest().body(model);
		}

		if (user.getPassword().isBlank()) {
			model.put("message", "Password cannot be blank");
			return ResponseEntity.badRequest().body(model);
		}

		if (userExists) {
			model.put("message", "User with username : " + user.getUsername() + " already exists");
			return ResponseEntity.badRequest().body(model);
		}

		userService.saveUser(user);
		model.put("message", "User registered successfully");
		return ResponseEntity.ok(model);
	}

}