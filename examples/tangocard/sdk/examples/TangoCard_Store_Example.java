/**
 * 
 *  TangoCard_Store_Example.java
 *  TangoCard_Java_SDK
 *  
 *  Example code using Tango Card SDK to get available balance and purchase card.
 * 
 *  © 2012 Tango Card, Inc
 *  All rights reserved.
 * 
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions: 
 * 
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software. 
 * 
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 */ 

package tangocard.sdk.examples;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Properties;

import tangocard.sdk.common.TangoCardSdkException;
import tangocard.sdk.request.*;
import tangocard.sdk.response.success.GetAvailableBalanceResponse;
import tangocard.sdk.response.success.PurchaseCardResponse;
import tangocard.sdk.service.TangoCardServiceException;

public class TangoCard_Store_Example {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws Exception 
	 */
	public static void main(String args[]) throws Exception {

		System.out.println( "\n===============================" );
		System.out.println(   "= Tango Card Java SDK Example =" );
		System.out.println(   "=   for simple store front    =" );
		System.out.println(   "===============================" );
		
		Properties prop = new Properties();		
		try {
			prop.load(new FileInputStream("app_config.properties"));
		} catch ( FileNotFoundException ex ) {
			throw ex;
		} catch ( Exception ex ) {
			throw ex;
		}
		
		try {
			String app_username = prop.getProperty("app_username");
			String app_password = prop.getProperty("app_password");
			String app_production_mode = prop.getProperty("app_production_mode");
			String app_card_sku = prop.getProperty("app_card_sku");
			
			boolean is_production_mode = app_production_mode.equals("true");
			
			GetAvailableBalanceRequest requestAvailableBalance 
				= new GetAvailableBalanceRequest( 
						is_production_mode,
						app_username, 
						app_password
						);
			
			GetAvailableBalanceResponse responseAvailableBalance = new GetAvailableBalanceResponse();
			if ( requestAvailableBalance.execute(responseAvailableBalance) && (null != responseAvailableBalance) )
			{
				System.out.println("\nSuccess - GetAvailableBalance - Initial");
		        int tango_cents_available_balance = responseAvailableBalance.getAvailableBalance();
		        double currencyAmount = tango_cents_available_balance/100;
		        
		        Locale enUSLocale =
		            new Locale.Builder().setLanguage("en").setRegion("US").build();
	
		        NumberFormat currencyFormatter = 
		            NumberFormat.getCurrencyInstance(enUSLocale);
		        System.out.println("\tI have an available balance of " + currencyFormatter.format(currencyAmount) + " dollars.");		
			}
					
		    int cardValueTangoCardCents = 100; // $1.00 dollars
	
		    // set up the request
		    PurchaseCardRequest requestPurchaseCardRequest_Delivery = new PurchaseCardRequest(
					is_production_mode,
					app_username, 
					app_password,
					app_card_sku,              		// cardSku
			        cardValueTangoCardCents,       	// cardValue
			        true,                      		// tcSend 
			        "Sally Test Recipient",         // recipientName
			        "sue_test_recipient@test.com",  // recipientEmail
			        "Happy Birthday",               // giftMessage
			        "Bob Test Giver"                // giftFrom  
		    );
	
		    // make the request
		    PurchaseCardResponse responsePurchaseCard_Delivery = new PurchaseCardResponse();
		    if ( requestPurchaseCardRequest_Delivery.execute(responsePurchaseCard_Delivery) && (null != responsePurchaseCard_Delivery))
		    {
		    	System.out.println( "\nSuccess - PurchaseCard - Delivery\n" );
		    	System.out.println( "\tReference Order ID: "  + responsePurchaseCard_Delivery.getReferenceOrderId() + "");
		    	System.out.println( "\tCard Token:         "  + responsePurchaseCard_Delivery.getCardToken() + "");
		    	System.out.println( "\tCard Number:        "  + responsePurchaseCard_Delivery.getCardNumber() + "");
		    	System.out.println( "\tCard Pin:           "  + responsePurchaseCard_Delivery.getCardPin() + "");
		    }
		    
		    // set up the request
		    PurchaseCardRequest requestPurchaseCardRequest_NoDelivery = new PurchaseCardRequest(
					is_production_mode,
					app_username, 
					app_password,
					app_card_sku,              		// cardSku
			        cardValueTangoCardCents,       	// cardValue
			        false,                      	// tcSend 
			        null,         					// recipientName
			        null,  							// recipientEmail
			        null,               			// giftMessage
			        null                			// giftFrom  
		    );
	
		    // make the request
		    PurchaseCardResponse responsePurchaseCard_NoDelivery = new PurchaseCardResponse();
		    if ( requestPurchaseCardRequest_NoDelivery.execute(responsePurchaseCard_NoDelivery) && (null != responsePurchaseCard_NoDelivery))
		    {
		    	System.out.println( "\nSuccess - PurchaseCard - No Delivery\n" );
		    	System.out.println( "\tReference Order ID: "  + responsePurchaseCard_NoDelivery.getReferenceOrderId() + "");
		    	System.out.println( "\tCard Token:         "  + responsePurchaseCard_NoDelivery.getCardToken() + "");
		    	System.out.println( "\tCard Number:        "  + responsePurchaseCard_NoDelivery.getCardNumber() + "");
		    	System.out.println( "\tCard Pin:           "  + responsePurchaseCard_NoDelivery.getCardPin() + "");
		    }
			
			GetAvailableBalanceRequest requestUpdatedBalance 
			= new GetAvailableBalanceRequest( 
					is_production_mode,
					app_username, 
					app_password
					);
		
			GetAvailableBalanceResponse responseUpdatedBalance = new GetAvailableBalanceResponse();
			if ( requestUpdatedBalance.execute(responseUpdatedBalance) && (null != responseUpdatedBalance) )
			{
				System.out.println("\nSuccess - GetAvailableBalance - Concluding");
		        int tango_cents_available_balance = responseUpdatedBalance.getAvailableBalance();
		        double currencyAmount = tango_cents_available_balance/100;
		        
		        Locale enUSLocale =
		            new Locale.Builder().setLanguage("en").setRegion("US").build();
		
		        NumberFormat currencyFormatter = 
		            NumberFormat.getCurrencyInstance(enUSLocale);
		        System.out.println("\tI have an available balance of " + currencyFormatter.format(currencyAmount) + " dollars.");		
			}
		} catch ( TangoCardServiceException ex ) {
			System.out.println( "TangoCardServiceException: " + ex.getMessage() );
		} catch ( TangoCardSdkException ex ) {
			System.out.println( "TangoCardSdkException: " + ex.getMessage() );
		} catch ( Exception ex ) {
			System.out.println( "Exception: " + ex.getMessage() );
		}
		
		System.out.println(   "===============================" );		
		System.out.println(   "=   The End                   =" );
		System.out.println(   "===============================" );
	}	
}
