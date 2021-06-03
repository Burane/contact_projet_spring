package com.burane.contact.service;

import com.burane.contact.model.*;
import com.burane.contact.repository.*;
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
public class CustomUserContactsService {

	@Autowired private ContactRepository contactRepository;
	@Autowired private AddressRepository addressRepository;
	@Autowired private EmailRepository emailRepository;
	@Autowired private CustomUserDetailsService userDetailsService;

	public List<Contact> findByUsername(String username) {
		User user = userDetailsService.findByUsername(username);
		return contactRepository.findAllByUser(user);
	}

	public Contact findByEmails(List<Email> emails) {
		return contactRepository.findByEmails(emails);
	}

	public void deleteContact(Contact contact, String username) {
		User user = userDetailsService.findByUsername(username);
		Contact toDelete = findByEmails(contact.getEmails());
		if (user.getUsername().equals(username))
			contactRepository.delete(toDelete);
	}

	public void saveOrUpdateContact(Contact contact, String username) {
		User user = userDetailsService.findByUsername(username);
		contact.setUser(user);
		emailRepository.saveAll(contact.getEmails());
		addressRepository.saveAll(contact.getAddress());
		contactRepository.save(contact);
	}
}