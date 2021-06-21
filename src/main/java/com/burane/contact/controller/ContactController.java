package com.burane.contact.controller;

import com.burane.contact.Exception.EmailAlreadyExistException;
import com.burane.contact.model.Address;
import com.burane.contact.model.Contact;
import com.burane.contact.model.Email;
import com.burane.contact.model.User;
import com.burane.contact.service.CustomUserContactsService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/contact")
public class ContactController {

	@Autowired private CustomUserContactsService customUserContactsService;

	record addressBody(ObjectId _id, int postalCode, String city, String street) {
	}

	record emailBody(ObjectId _id, String email) {
	}

	record contactBody(ObjectId _id, String firstName, String lastName, User user, List<addressBody> address,
					   List<emailBody> emails) {
	}

	@PostMapping("/all")
	public List<Contact> getAllContacts() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		return customUserContactsService.findByUsername(username);
	}

	@PostMapping("/add")
	public String addContact(@Valid @RequestBody Contact contact) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		System.out.println(contact);
		try {
			customUserContactsService.saveOrUpdateContact(contact, username);
		} catch (EmailAlreadyExistException e) {
			return e.getMessage();
		}
		return "Done";
	}

	@PostMapping("/update")
	public String addContact(@Valid @RequestBody contactBody contact) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Contact update = new Contact(contact._id(), contact.firstName, contact.lastName, null, new ArrayList<>(
				contact.address().stream()
						.map(addressBody -> new Address(addressBody._id(), addressBody.postalCode(), addressBody.city(),
								addressBody.street())).toList()), new ArrayList<>(
				contact.emails().stream().map(emailBody -> new Email(emailBody._id(), emailBody.email())).toList()));
		try {
			customUserContactsService.saveOrUpdateContact(update, username);
		} catch (EmailAlreadyExistException e) {
			return e.getMessage();
		}
		return "Done";
	}

	@PostMapping("/remove")
	public String userAccess(@Valid @RequestBody Contact contact) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		customUserContactsService.deleteContact(contact, username);
		return "Done";
	}

}