package com.burane.contact.repository;

import com.burane.contact.model.Email;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmailRepository extends MongoRepository<Email, String> {

	boolean existsByEmail(String email);

}