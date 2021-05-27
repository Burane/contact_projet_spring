package com.burane.contact;

import com.burane.contact.model.ERole;
import com.burane.contact.model.Role;
import com.burane.contact.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ContactApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContactApplication.class, args);
	}

	@Bean
	CommandLineRunner init(RoleRepository roleRepository) {

		return args -> {

			boolean adminRoleExist = roleRepository.existsByRole(ERole.User);
			if (!adminRoleExist) {
				Role newAdminRole = new Role();
				newAdminRole.setRole(ERole.User);
				roleRepository.save(newAdminRole);
			}

			boolean userRoleExist = roleRepository.existsByRole(ERole.Admin);
			if (!userRoleExist) {
				Role newUserRole = new Role();
				newUserRole.setRole(ERole.Admin);
				roleRepository.save(newUserRole);
			}
		};

	}

}