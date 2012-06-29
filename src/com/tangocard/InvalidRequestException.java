package com.tangocard;

public class InvalidRequestException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4855144069549691297L;

	public InvalidRequestException(String errorMessage) {
		
		super(errorMessage);
	}
}
