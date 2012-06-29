package com.tangocard;


import junit.framework.TestCase;

public class TCStoreTest extends TestCase {

	private static String testUsername = "third_party_int@tangocard.com";
	private static String testPassword = "integrateme";
	private static String testCompanyIdentifier = "cmh_testing";

	private static String testSKU = "tango-card";
	private static int testCardValue = 1;
	private static int testCardInsufficientFundsValue = 9999; 
	private static boolean testTcSend = false;
	private static String testRecipientName = "test-recipient-name";
	private static String testRecipientEmail = "test-recipient-email";
	private static String testGiftFrom = "test-gift-from";
	private static String testGiftMessage = "test-gift-message";

	public TCStoreTest(String name) {
		super(name);
	}

	public void testTCStoreStaticVars() {

		TCStore testTCStore = new TCStore(false, testUsername, testPassword,
				testCompanyIdentifier);
		
		assertTrue(testTCStore.SUCCESS == "SUCCESS");
		assertTrue(testTCStore.SYS_ERROR == "SYS_ERROR");
		assertTrue(testTCStore.INV_INPUT == "INV_INPUT");
		assertTrue(testTCStore.INV_CREDENTIAL == "INV_CREDENTIAL");
		assertTrue(testTCStore.INS_INV == "INS_INV");
		assertTrue(testTCStore.INS_FUNDS == "INS_FUNDS");
	}

	public void testTCStoreIntegration() {
		
		TCStore testTCStore = new TCStore(false, testUsername, testPassword, testCompanyIdentifier);
		
		assertTrue(testTCStore.userName == testUsername);
		assertTrue(testTCStore.password == testPassword);
		assertTrue(testTCStore.company_identifier == testCompanyIdentifier);
		assertTrue(testTCStore.baseURL == "https://int.tangocard.com/Version2");
	}

	public void testTCStoreProduction() {
		
		TCStore testTCStore = new TCStore(true, testUsername, testPassword,
				testCompanyIdentifier);
		
		assertTrue(testTCStore.userName == testUsername);
		assertTrue(testTCStore.password == testPassword);
		assertTrue(testTCStore.company_identifier == testCompanyIdentifier);
		assertTrue(testTCStore.baseURL == "https://api.tangocard.com/Version2");
	}

	public void testTCStorePurchaseCard() {
		
		TCStore testTCStore = new TCStore(false, testUsername, testPassword, testCompanyIdentifier);
		try {
			testTCStore.purchaseCard(testSKU, testCardValue, testTcSend, testRecipientName, testRecipientEmail, testGiftFrom, testGiftMessage);
		} catch (InvalidParametersException e) {			
			// WW3 = ask -- should I print stack trace?
			e.printStackTrace();
			fail("InvalidParamtersException thrown!" + e.toString());
		} catch (TransactionFailureException e) {
			// WW3 = ask -- should I print stack trace?
			e.printStackTrace();
			fail("TransactionFailureException thrown!" + e.toString());
		}
		
		Transaction lastTransaction = testTCStore.getLastTransaction();
				
		assertTrue(lastTransaction.successful == true);
		assertTrue(lastTransaction.getErrorMessage() == null);
		
		Card lastTransactionCard = lastTransaction.getCard();
		String cardPrimaryIdentifier = lastTransactionCard.getCardPrimaryIdentifier();
		long cardPrimaryIdentifierLong = -1;
		try {
			cardPrimaryIdentifierLong = Long.parseLong(cardPrimaryIdentifier);
		} catch (Exception e) {
			e.printStackTrace();
			fail("NumberFormatException thrown! (card primary identifier is not a valid int) : cardPrimaryIdentifier = " + cardPrimaryIdentifier);
		}
		Long cardPrimaryIdentifierLongObj = new Long(cardPrimaryIdentifierLong);
		assertTrue(cardPrimaryIdentifier.equals(cardPrimaryIdentifierLongObj.toString()));
		assertFalse(cardPrimaryIdentifierLong == -1);
		
		Transaction getLastTransaction = testTCStore.getLastTransaction();
		assertTrue(getLastTransaction == lastTransaction);
	}
	
	// WW3 = ask,... should I call this testInsufficientFunds or what it is?         (and should I test all my exceptions?)    
	public void testTCStorePurchaseCard_InsufficientFunds() {
		
		TCStore testTCStore = new TCStore(false, testUsername, testPassword, testCompanyIdentifier);
		
		boolean insufficientFundsExceptionThrown = false;
		
		try {
			testTCStore.purchaseCard(testSKU, testCardInsufficientFundsValue, testTcSend, testRecipientName, testRecipientEmail, testGiftFrom, testGiftMessage);
		} catch (TransactionFailureException e) {

			// note if the proper error was thrown			
			if (e.errorMessage.equals(testTCStore.INS_FUNDS_MESSAGE)) {
				insufficientFundsExceptionThrown = true;
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			fail("Exception thrown when attempting to purchase card worth $"+testCardInsufficientFundsValue+"!");
		}
		
		assertTrue(insufficientFundsExceptionThrown);
	}
}
