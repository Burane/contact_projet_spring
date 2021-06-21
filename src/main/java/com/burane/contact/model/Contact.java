package com.burane.contact.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@Document(collection = "contact")
public class Contact {

	@JsonSerialize(using = ToStringSerializer.class)
	@Id
	private ObjectId _id;

	private String firstName;

	private String lastName;

	@JsonIgnore
	@DBRef
	private User user;

	@DBRef
	private List<Address> address;

	@DBRef
	private List<Email> emails;

}