package com.tangocard;

import java.util.Date;

/**
 * This class encapsulates a transaction, which constitutes an order id
 * and a Card instance. In the future a transaction could possibly hold
 * multiple cards, but for now it is limited to one
 *
 * @author CMH
 * @author Winston E Williams
 * @version 1.0
 */
 
public class Transaction {
	
	protected boolean successful = false;
	
	private Date datetime; // the date and time when the transaction occurred
	
	private String orderID;
	private Card cardInstance;
	
	private String errorMessage;
	private Exception errorException;

	// -----------------------------------------------------------------------
	/**
	 * Constructs a new Transaction with no settings
	 */
	protected Transaction() {
	}
	
	// -----------------------------------------------------------------------
	/**
	 * Constructs a new Transaction , should not be used by SDK users
	 *
	 * @param orderID
	 * @param cardInstance
	 */
	protected Transaction(String orderID, Card cardInstance) {
	 
		this.orderID = orderID;
		this.cardInstance = cardInstance;
		this.successful = false;
		this.datetime = new Date();
	}
	
	// -----------------------------------------------------------------------
	/**
	 * Return the order id for the transaction represented by this instance
	 * @return the Order ID
	 */
	public String getOrderId() {

		return orderID;
	}
	
	// -----------------------------------------------------------------------
	/**
	 * Return the card that is associated with this transaction
	 * @return The card instance
	 */
	public Card getCard() {
		return cardInstance;
	}

	// -----------------------------------------------------------------------
	/**
	 * Set the date and time when the transaction occured
	 * @return Nothing
	 */	
	public Date getDatetime() {
		return this.datetime;
	}
	
	// -----------------------------------------------------------------------
	/**
	 * Set the date and time when the transaction occured
	 * @return Nothing
	 */	
	public void setDatetime(Date newDatetime) {
		this.datetime = newDatetime;
	}
	
	// -----------------------------------------------------------------------
	/**
	 * Set whether the transaction was successful
	 * @return Nothing
	 */	
	public void setSuccess(boolean success) {
		this.successful = success;
	}
	
	// -----------------------------------------------------------------------
	/**
	 * Save the error which occurred when trying to process this transaction
	 * @return Nothing
	 */	
	public void setError(String message, Exception exception) {
		this.errorMessage = message;
		this.errorException = exception;
	}
	
	// -----------------------------------------------------------------------
	/**
	 * Get the error message returned when trying to process this transaction
	 * @return Nothing
	 */
	public String getErrorMessage() {
		return this.errorMessage;
	}
	
	// -----------------------------------------------------------------------
	/**
	 * Get the exception which occurred when trying to process the transaction
	 * @return Nothing
	 */
	public Exception getErrorException() {
		return this.errorException;
	}
	
	// -----------------------------------------------------------------------
	/**
	 * Get transaction success
	 * @return Nothing
	 */	
	public boolean getSuccess() {
		return this.successful;
	}
}
