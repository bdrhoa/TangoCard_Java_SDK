package com.tangocard;

import java.util.Date;

import junit.framework.TestCase;

public class TransactionTest extends TestCase {

	String testOrderID = "abc.123";
	String testPrimaryIdentifier = "test_primary_identifier";
	String testSecondaryIdentifier = "test_secondary_identifier";
	
	String testErrorMessage = "This is a test error message!_";
	Exception testErrorException = new Exception("This is a test error exception!_");
		
	public TransactionTest(String name) {
		super(name);
	}

	public void testTransaction() {

		Card testCard = new Card(testPrimaryIdentifier, testSecondaryIdentifier);
		Transaction testTransaction = new Transaction(testOrderID, testCard);

		assertTrue(testCard == testTransaction.getCard());
		assertTrue(testTransaction.getOrderId() == testOrderID);
		
		// check that "rounded" version of the current date and the transaction date are equal
		Date currentDate = new Date();
		long currentDateInt = Math.round(currentDate.getTime() / 1000000);
		long transactionDateInt = (testTransaction.getDatetime().getTime() / 1000000);
		
		assertTrue(currentDateInt == transactionDateInt);		
	}
	
	public void testGetCard() {
		Card testCard = new Card(testPrimaryIdentifier, testSecondaryIdentifier);
		Transaction testTransaction = new Transaction(testOrderID, testCard);
		assert(testTransaction.getCard() == testCard);
	}
	
	public void testDefaultSuccessful() {
		Transaction newTransaction = new Transaction();
		assert(newTransaction.getSuccess() == false);
	}
	
	public void testSetSuccessful() {
		Transaction newTransaction = new Transaction();
		newTransaction.setSuccess(true);
		assert(newTransaction.getSuccess() == true);
	}

	public void testErrorMessage() {
		Transaction testTransaction = new Transaction();
		testTransaction.setError(testErrorMessage, testErrorException);
		assert(testErrorMessage == testTransaction.getErrorMessage());
	}
	
	public void testErrorException() {
		Transaction testTransaction = new Transaction();
		testTransaction.setError(testErrorMessage, testErrorException);
		assert(testErrorException == testTransaction.getErrorException());
	}
}
