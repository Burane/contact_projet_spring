package com.burane.contact.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Document(collection = "address")
public class Address {

	@Id private ObjectId _id;

	@NotBlank private int postalCode;

	@NotBlank
	@Size(max = 50)
	private String city;

	@NotBlank
	@Size(max = 50)
	private String rue;

}