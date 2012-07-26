/**
 * 
 *  UnitTest_PurchaseCard.java
 *  TangoCard_Java_SDK
 *
 *  Tango Card SDK JUnit Test
 * 
 * © 2012 Tango Card, Inc
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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import tangocard.sdk.request.PurchaseCardRequest;
import tangocard.sdk.response.success.PurchaseCardResponse;

import junit.framework.TestCase;

public class UnitTest_PurchaseCard extends TestCase {
	
	private String _app_username = null;
	private String _app_password = null;
	private String _app_card_sku = null;
	private boolean _is_production_mode = false;	

    protected void setUp() {
		Properties prop = new Properties();
		
		try {
			prop.load(new FileInputStream("app_config.properties"));
		} catch ( FileNotFoundException ex ) {
			ex.printStackTrace();
			TestCase.fail("FileNotFoundException: " + ex.getMessage());
		} catch ( Exception ex ) {
			ex.printStackTrace();
			TestCase.fail("Exception: " + ex.getMessage());
		}
		
		this._app_username = prop.getProperty("app_username");
		this._app_password = prop.getProperty("app_password");
		String app_production_mode = prop.getProperty("app_production_mode");
		this._app_card_sku = prop.getProperty("app_card_sku");
		
		this._is_production_mode = app_production_mode.equals("true");        
    }
    
    public UnitTest_PurchaseCard(String method) {
        super(method);
    }
    
    public void testPurchaseCard_Delivery() {
	    int cardValueTangoCardCents = 100; // $1.00 dollars

	    // set up the request
	    PurchaseCardRequest requestPurchaseCardRequest_Delivery = new PurchaseCardRequest(
				this._is_production_mode,
				this._app_username, 
				this._app_password,
				this._app_card_sku,             // cardSku
		        cardValueTangoCardCents,       	// cardValue
		        true,                      		// tcSend 
		        "Sally Test Recipient",         // recipientName
		        "sue_test_recipient@test.com",  // recipientEmail
		        "Happy Birthday",               // giftMessage
		        "Bob Test Giver"                // giftFrom  
	    );

	    // make the request
	    boolean isSuccess = false;
	    PurchaseCardResponse responsePurchaseCard_Delivery = new PurchaseCardResponse();
	    try {
	    	isSuccess = requestPurchaseCardRequest_Delivery.execute(responsePurchaseCard_Delivery);
		} catch (Exception ex) {
			TestCase.fail("Exception: " + ex.getMessage());
		}
		
		TestCase.assertTrue(isSuccess);
		TestCase.assertNotNull(responsePurchaseCard_Delivery);
		TestCase.assertNotNull(responsePurchaseCard_Delivery.getCardNumber());
		TestCase.assertNotNull(responsePurchaseCard_Delivery.getCardPin());
		TestCase.assertNotNull(responsePurchaseCard_Delivery.getCardToken());
		TestCase.assertNotNull(responsePurchaseCard_Delivery.getReferenceOrderId());
    }
}
