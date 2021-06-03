package com.burane.contact.Exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@EqualsAndHashCode(callSuper = true)
@Data
public class EmailAlreadyExistException extends Exception {

	@NonNull private String message;

}