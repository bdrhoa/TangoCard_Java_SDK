package com.tangocard;

/**
 * This class performs a test run of the TCStore, logging in as a test   
 * user, getting the available balance, and purchasing a single card 
 * using only $1.
 *
 * @author CMH
 * @author WEW
 * @version 1.0
 */

public class TestStore {

	TCStore theStore;
	
	String loginUsername = "third_party_int@tangocard.com";
	String loginPassword = "integrateme";
	String companyIdentifier = "cmh_testing";
	
	public TestStore() {
		
		// Instantiate TCStore in non-production mode, providing our e-mail, password, and company identifier
		theStore = new TCStore(TCStore.ENVIRONMENT_INTEGRATION, loginUsername, loginPassword, companyIdentifier);

		// check the available balance...
		System.out.println("I have : " + theStore.getAvailableBalance() + " dollars available...");
		
		// now purchase a new TangoCard
		System.out.println("Now attempting to buy a $1 TangoCard:");
		buyCard(1);
	}
	
	// purchase a card, returning true on success and false on error 
	public boolean buyCard(int value) {
		
		// Purchase a single card using TCStore, which will return a Transaction object or null on failure
		Transaction myTransaction;
		try {
			myTransaction = theStore.purchaseCard("tango-card", value, false, null, null, null, null);
		} catch (InvalidParametersException ipe) {
			ipe.printStackTrace();
			return false;
		} catch (TransactionFailureException tfe) {
			tfe.printStackTrace();
			return false;
		}

		// check if the transaction has failed
		if (myTransaction.successful) {
						
			// the Order ID number has been provided in the Transaction object
			System.out.println("Order Number      : " + myTransaction.getOrderId());

			// the Card object provided by the purhcase
			Card theCard = myTransaction.getCard();
			System.out.println("Primary Card ID   : " + theCard.getCardPrimaryIdentifier());
			System.out.println("Secondary Card ID : " + theCard.getCardSecondaryIdentifier());
			
		} else {

			// if the transaction has failed, show the error
			String transactionError = myTransaction.getErrorMessage();
			System.out.println("Transaction failed: " + transactionError);
			return false;
			
		}

		return true;
	}
	
	public static void main(String args[]) {
		new TestStore();
	}
	
}
