package com.burane.contact.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Document(collection = "email")
public class Email {

	@Id private ObjectId _id;

	@NotBlank
	@Size(max = 50)
	@Indexed(unique = true)
	private String email;
}