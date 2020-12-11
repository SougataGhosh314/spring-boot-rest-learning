package com.sougata.rest.webservices.restfulwebservices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserAlreadyExistsException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public UserAlreadyExistsException(String message) {
		super(message);
		
	}
	
}