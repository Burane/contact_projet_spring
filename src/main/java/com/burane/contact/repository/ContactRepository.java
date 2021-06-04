package com.burane.contact.repository;

import com.burane.contact.model.Contact;
import com.burane.contact.model.Email;
import com.burane.contact.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ContactRepository extends MongoRepository<Contact, String> {

	List<Contact> findAllByUser(User user);
	Contact findByEmails(List<Email> emails);
	Optional<Contact> findBy_id(String _id);
}