package com.burane.contact.configuration.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.burane.contact.model.Role;
import com.burane.contact.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.Set;

@Component
public class JwtTokenProvider {

	@Value("${contact.app.jwtSecret}") private String jwtSecret;

	@Value("${contact.app.jwtExpirationMs}") private long jwtExpirationMs;

	@Autowired private CustomUserDetailsService customUserDetailsService;

	@PostConstruct
	protected void init() {
		jwtSecret = Base64.getEncoder().encodeToString(jwtSecret.getBytes());
	}

	public String createToken(String username, Set<Role> set) {
		Instant now = Instant.now();
		return JWT.create().withSubject(username).withClaim("roles", set.stream().map(Role::toString).toList())
				.withExpiresAt(Date.from(now.plusMillis(jwtExpirationMs))).withIssuedAt(Date.from(now))
				.sign(Algorithm.HMAC256(jwtSecret));
	}

	public String getUsername(String token) {
		return JWT.require(Algorithm.HMAC256(jwtSecret)).build().verify(token).getSubject();
	}

	public Authentication getAuthentication(String token) {
		UserDetails userDetails = customUserDetailsService.loadUserByUsername(getUsername(token));
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}

	public String resolveToken(HttpServletRequest req) {
		String bearerToken = req.getHeader("Authorization");
		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7);
		}
		return null;
	}

	public boolean validateToken(String token) {
		try {
			JWT.require(Algorithm.HMAC256(jwtSecret)).build().verify(token);
			return true;
		} catch (JWTVerificationException | IllegalArgumentException e) {
			return false;
		}
	}

}