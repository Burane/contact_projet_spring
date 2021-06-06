package com.burane.contact.service;

import com.burane.contact.Exception.EmailAlreadyExistException;
import com.burane.contact.model.Contact;
import com.burane.contact.model.Email;
import com.burane.contact.model.User;
import com.burane.contact.repository.AddressRepository;
import com.burane.contact.repository.ContactRepository;
import com.burane.contact.repository.EmailRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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

	public Contact findById(ObjectId id) {
		return contactRepository.findById(id.toString())
				.orElseThrow(() -> new NoSuchElementException("Contact not found"));
	}

	public void deleteContact(Contact contact, String username) {
		User user = userDetailsService.findByUsername(username);
		Contact toDelete = findById(contact.get_id());
		if (user.getUsername().equals(username)) {
			emailRepository.deleteAll(toDelete.getEmails());
			addressRepository.deleteAll(toDelete.getAddress());
			contactRepository.delete(toDelete);
		}
	}

	public void saveOrUpdateContact(Contact contact, String username) throws EmailAlreadyExistException {
		User user = userDetailsService.findByUsername(username);
		contact.setUser(user);
		if (contact.getEmails().stream().anyMatch(email -> emailRepository.existsByEmail(email.getEmail())))
			throw new EmailAlreadyExistException("An email is already taken");
		emailRepository.saveAll(contact.getEmails());
		addressRepository.saveAll(contact.getAddress());
		contactRepository.save(contact);
	}

	public void updateContact(Contact contact, String username) throws EmailAlreadyExistException {
		Contact toModify = findById(contact.get_id());
		System.out.println(toModify);
		saveOrUpdateContact(toModify, username);
	}
}