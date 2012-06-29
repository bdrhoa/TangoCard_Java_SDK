package com.tangocard;


import junit.framework.TestCase;

public class CardTest extends TestCase {

	static String primaryIdentifier = "primary_identifier";
	static String secondaryIdentifier = "secondary_identifier";
	
	public CardTest(String name) {
		super(name);
	}
	
	public void testCardSettings() {
		Card testCard = new Card(primaryIdentifier, secondaryIdentifier);
		assertTrue(testCard.getCardPrimaryIdentifier() == primaryIdentifier);
		assertTrue(testCard.getCardSecondaryIdentifier() == secondaryIdentifier);
	}
}
