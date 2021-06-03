package com.burane.contact.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "contact")
public class Contact {

	@Id private ObjectId _id;

	@JsonIgnore
	@DBRef
	private User user;

	@DBRef private List<Address> address;

	@DBRef private List<Email> emails;

}