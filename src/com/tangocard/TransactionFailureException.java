package com.tangocard;

/**
 * A TransactionFailedException is thrown when the server has reported  
 * that a transaction has failed.  
 *
 * @author Winston E. Williams
 * @version 1.0
 */
public class TransactionFailureException extends Exception {

	private static final long serialVersionUID = -7143462704533286476L;
	
	protected String errorMessage;

	public TransactionFailureException(String errorMessage) {
		super(errorMessage);
		
		this.errorMessage = errorMessage;
	}
	
	public String getErrorMessage() {
		return(this.errorMessage);
	}

}
