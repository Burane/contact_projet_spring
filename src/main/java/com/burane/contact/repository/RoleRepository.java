package com.burane.contact.repository;

import com.burane.contact.model.ERole;
import com.burane.contact.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {
	Optional<Role> findByRole(ERole role);
	boolean existsByRole(ERole role);

}