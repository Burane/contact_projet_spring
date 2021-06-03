package com.burane.contact.controller;

import com.burane.contact.model.Contact;
import com.burane.contact.service.CustomUserContactsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/contact")
public class ContactController {

	@Autowired private CustomUserContactsService customUserContactsService;

	@PostMapping("/all")
	public List<Contact> getAllContacts() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		return customUserContactsService.findByUsername(username);
	}

	@PostMapping("/add")
	public String addOrUpdateContact(@Valid @RequestBody Contact contact) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		customUserContactsService.saveOrUpdateContact(contact, username);
		return "User Content.";
	}

	@PostMapping("/remove")
	public String userAccess(@Valid @RequestBody Contact contact) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		customUserContactsService.deleteContact(contact, username);
		return "User Content.";
	}
}