package com.tangocard;

/**
 * This class simply holds the card information returned from TangoCard
 * on a successful transaction. 
 * The primary identifier is the card number, or URL and the secondary
 * indentifier is the PIN or challenge code; depending on the card type
 * purchased.
 *
 * @author CMH
 * @version 1.0
 */

public class Card {
  
	private String cardPrimaryIdentifier;
	private String cardSecondaryIdentifier;
	
	// ------------------------------------------------------------------------
	/**
	 * Should never by used by SDK users
	 * @param primary
	 * @param secondary
	 */
	public Card(String primary, String secondary) { 
		cardPrimaryIdentifier = primary;
		cardSecondaryIdentifier = secondary;
	}
	
	// ------------------------------------------------------------------------
	/**
	 * Return the primary card identifier, such as card number or challenge URL
	 * @return Primary identifier
	 */
	public String getCardPrimaryIdentifier() {
		return cardPrimaryIdentifier;
	}
	
	// ------------------------------------------------------------------------
	/**
	 * Returns the secondary card identifier, such as a PIN or challenge code
	 * Note it is fully legal for this field to be null.
	 * @return Secondary identifier
	 */
	public String getCardSecondaryIdentifier() {
		return cardSecondaryIdentifier;
	}

}
