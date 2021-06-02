package com.burane.contact.service;

import com.burane.contact.model.ERole;
import com.burane.contact.model.Role;
import com.burane.contact.model.User;
import com.burane.contact.repository.RoleRepository;
import com.burane.contact.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired private UserRepository userRepository;
	@Autowired private RoleRepository roleRepository;
	@Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = findByUsername(username);
		List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());

		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				authorities);
	}

	private List<GrantedAuthority> getUserAuthority(List<Role> userRoles) {
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		userRoles.forEach(role -> grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole().name())));
		return grantedAuthorities;
	}

	public User findByUsername(String username) throws NoSuchElementException {
		return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
	}

	public boolean existsByUsername(String username) {
		return userRepository.existsByUsername(username);
	}

	public Role findByRole(ERole role) throws UsernameNotFoundException {
		return roleRepository.findByRole(role).orElseThrow();
	}

	public void saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		Role userRole = findByRole(ERole.User);
		user.setRoles(new ArrayList<>() {{
			add(userRole);
		}});
		userRepository.save(user);
	}

}