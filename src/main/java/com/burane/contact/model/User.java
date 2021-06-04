package com.burane.contact.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Document(collection = "user")
public class User {

	@JsonSerialize(using = ToStringSerializer.class)
	@Id private ObjectId _id;

	@NotBlank
	@NotEmpty
	@NotNull
	@Size(max = 50)
	private String username;

	@NotBlank
	@NotEmpty
	@NotNull
	@Size(max = 50)
	private String password;

	@DBRef private List<Role> roles;

//	@DBRef private List<Contact> contacts;
}