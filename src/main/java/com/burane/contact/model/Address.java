package com.burane.contact.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@Document(collection = "address")
public class Address {

	@JsonSerialize(using = ToStringSerializer.class)
	@Id private ObjectId _id;

	@NotBlank private int postalCode;

	@NotBlank
	@Size(max = 50)
	private String city;

	@NotBlank
	@Size(max = 50)
	private String street;

}