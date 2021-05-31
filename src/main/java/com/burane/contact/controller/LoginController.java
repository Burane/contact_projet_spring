package com.burane.contact.controller;

import com.burane.contact.configuration.jwt.JwtTokenProvider;
import com.burane.contact.model.User;
import com.burane.contact.repository.UserRepository;
import com.burane.contact.service.CustomUserDetailsService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
	public ResponseEntity<Map<String, String>> login(@RequestBody @Valid AuthBody data) {
		try {
			String username = data.username;
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.password));
			String token = jwtTokenProvider.createToken(username, userService.findByUsername(username).getRoles());
			Map<String, String> model = new HashMap<>();
			model.put("username", username);
			model.put("token", token);
			return ResponseEntity.ok(model);
		} catch (AuthenticationException | NoSuchElementException e) {
//			return ResponseEntity.badRequest()..build();
			throw new BadCredentialsException("Invalid username or password");
		}
	}

}