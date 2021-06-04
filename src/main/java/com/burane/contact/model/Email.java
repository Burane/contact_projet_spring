package com.burane.contact.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@Document(collection = "email")
public class Email {

	@JsonSerialize(using = ToStringSerializer.class)
	@Id private ObjectId _id;

	@NotBlank
	@Size(max = 50)
	@Indexed(name = "email_index_unique", unique = true)
	private String email;
}