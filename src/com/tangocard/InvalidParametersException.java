package com.tangocard;

public class InvalidParametersException extends Exception {

	/**
	 * An invalid parameters exception is thrown when the use of 
	 * an invalid value is attempted.
	 *
	 * @author WEW
	 * @version 1.0
	 */

	private static final long serialVersionUID = -8118639617644300827L;

	public InvalidParametersException(String errorMessage) {
		
		super(errorMessage);
	}
}
