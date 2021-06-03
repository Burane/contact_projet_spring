package com.burane.contact.controller;

import com.burane.contact.configuration.jwt.JwtTokenProvider;
import com.burane.contact.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

	@Autowired private JwtTokenProvider jwtTokenProvider;
	@Autowired private CustomUserDetailsService userService;
	@Autowired private AuthenticationManager authenticationManager;


	record AuthBody(String username, String password) {
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody AuthBody data) {
		Map<String, String> model = new HashMap<>();
		try {
			String username = data.username;
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.password));
			String token = jwtTokenProvider.createToken(username, userService.findByUsername(username).getRoles());
			model.put("username", username);
			model.put("token", token);
			return ResponseEntity.ok(model);
		} catch (AuthenticationException | NoSuchElementException e) {
			model.put("message","Username or password incorrect");
			return ResponseEntity.badRequest().body(model);
		}
	}

}