package com.tangocard;

public class TransactionFailureException extends Exception {

	/**
	 * A TransactionFailedException is thrown when the server has reported  
	 * that a transaction has failed.  
	 *
	 * @author WEW
	 * @version 1.0
	 */

	private static final long serialVersionUID = -7143462704533286476L;

	public TransactionFailureException(String errorMessage) {
		super(errorMessage);
	}

}
