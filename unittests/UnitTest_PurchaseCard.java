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

import tangocard.sdk.TangoCardServiceApi;
import tangocard.sdk.common.TangoCardSdkException;
import tangocard.sdk.request.PurchaseCardRequest;
import tangocard.sdk.response.ServiceResponseEnum;
import tangocard.sdk.response.success.PurchaseCardResponse;
import tangocard.sdk.service.TangoCardServiceApiEnum;
import tangocard.sdk.service.TangoCardServiceException;

import junit.framework.TestCase;

/**
 * The Class UnitTest_PurchaseCard.
 */
public class UnitTest_PurchaseCard extends TestCase {
    
    /** The _app_username. */
    private String _app_username = null;
    
    /** The _app_password. */
    private String _app_password = null;
    
    /** The _app_card_sku. */
    private String _app_card_sku = null;
    
    /** The _enum Tango Card service api. */
    private TangoCardServiceApiEnum _enumTangoCardServiceApi = TangoCardServiceApiEnum.UNDEFINED; 

    /* (non-Javadoc)
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() {
        Properties prop = new Properties();
        
        try {
            prop.load(new FileInputStream("app_config.properties"));
        } catch ( FileNotFoundException ex ) {
            TestCase.fail("FileNotFoundException: " + ex.getMessage());
        } catch ( Exception ex ) {
            TestCase.fail("Exception: " + ex.getMessage());
        }
        
        this._app_username = prop.getProperty("app_username");
        this._app_password = prop.getProperty("app_password");
        this._app_card_sku = prop.getProperty("app_card_sku");
        
        String app_tango_card_service_api = prop.getProperty("app_tango_card_service_api");
        this._enumTangoCardServiceApi = TangoCardServiceApiEnum.valueOf(app_tango_card_service_api);
    }
    
    /**
     * Instantiates a new unit test_ purchase card.
     *
     * @param method the method
     */
    public UnitTest_PurchaseCard(String method) {
        super(method);
    }
    
    /**
     * Test purchase card_ delivery.
     */
    public void test_PurchaseCard_Delivery() {
        int cardValueTangoCardCents = 100; // $1.00 dollars

        // make the request
        boolean isSuccess = false;
        PurchaseCardResponse responsePurchaseCard_Delivery = new PurchaseCardResponse();
        try {
            isSuccess = TangoCardServiceApi.PurchaseCard(
                    this._enumTangoCardServiceApi,
                    this._app_username, 
                    this._app_password,
                    this._app_card_sku,             // cardSku
                    cardValueTangoCardCents,        // cardValue
                    true,                           // tcSend 
                    "Sally Example",                // recipientName
                    "sally@example.com",            // recipientEmail
                    "Happy Birthday",               // giftMessage
                    "Bill Example",                 // giftFrom  
            		responsePurchaseCard_Delivery	// response
            		);
        } catch (TangoCardServiceException ex) {
            TestCase.fail("TangoCardServiceException: " + ex.getMessage());
        } catch (TangoCardSdkException ex) {
            TestCase.fail("TangoCardSdkException: " + ex.getMessage());
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
    

    /**
     * Test purchase card_ no delivery.
     */
    public void test_PurchaseCard_NoDelivery() {
        int cardValueTangoCardCents = 100; // $1.00 dollars

        // make the request
        boolean isSuccess = false;
        PurchaseCardResponse responsePurchaseCard_NoDelivery = new PurchaseCardResponse();
        try {
            isSuccess = TangoCardServiceApi.PurchaseCard(
                    this._enumTangoCardServiceApi,
                    this._app_username, 
                    this._app_password,
                    this._app_card_sku,             // cardSku
                    cardValueTangoCardCents,        // cardValue
                    false,                          // tcSend 
                    null,                           // recipientName
                    null,                           // recipientEmail
                    null,                           // giftMessage
                    null,                            // giftFrom  
                    responsePurchaseCard_NoDelivery	// response
            		);
        } catch (TangoCardServiceException ex) {
            TestCase.fail("TangoCardServiceException: " + ex.getMessage());
        } catch (TangoCardSdkException ex) {
            TestCase.fail("TangoCardSdkException: " + ex.getMessage());
        } catch (Exception ex) {
            TestCase.fail("Exception: " + ex.getMessage());
        }
        
        TestCase.assertTrue(isSuccess);
        TestCase.assertNotNull(responsePurchaseCard_NoDelivery);
        TestCase.assertNotNull(responsePurchaseCard_NoDelivery.getCardNumber());
        TestCase.assertNotNull(responsePurchaseCard_NoDelivery.getCardPin());
        TestCase.assertNotNull(responsePurchaseCard_NoDelivery.getCardToken());
        TestCase.assertNotNull(responsePurchaseCard_NoDelivery.getReferenceOrderId());
    }
    
    /**
     * Test_ purchase card_ invalid credentials.
     */
    public void test_PurchaseCard_InvalidCredentials() {
        int cardValueTangoCardCents = 100; // $1.00 dollars
        
        String username = "burt@example.com";
        String password = "password";  

        boolean isSuccess = false;
        PurchaseCardResponse responsePurchaseCard_NoDelivery = new PurchaseCardResponse();
        try {
            isSuccess = TangoCardServiceApi.PurchaseCard(
                    this._enumTangoCardServiceApi,
                    username, 
                    password,
                    this._app_card_sku,             	// cardSku
                    cardValueTangoCardCents,           	// cardValue
                    false,                          	// tcSend 
                    null,                             	// recipientName
                    null,                              	// recipientEmail
                    null,                           	// giftMessage
                    null,                            	// giftFrom  
                    responsePurchaseCard_NoDelivery		// response
            		);
            
            TestCase.fail("Expected 'TangoCardServiceException' to be thrown");
        } catch (TangoCardServiceException ex) {
            TestCase.assertTrue(  ex.getResponseType().equals(ServiceResponseEnum.INV_CREDENTIAL.toString()) );
            String message = ex.getMessage();
            TestCase.assertNotNull(message);
        } catch (TangoCardSdkException ex) {
            TestCase.fail("TangoCardSdkException: " + ex.getMessage());
        } catch (Exception ex) {
            TestCase.fail("Exception: " + ex.getMessage());
        }
        
        TestCase.assertFalse(isSuccess);
        TestCase.assertNotNull(responsePurchaseCard_NoDelivery);
        TestCase.assertNull(responsePurchaseCard_NoDelivery.getCardNumber());
        TestCase.assertNull(responsePurchaseCard_NoDelivery.getCardPin());
        TestCase.assertNull(responsePurchaseCard_NoDelivery.getCardToken());
        TestCase.assertNull(responsePurchaseCard_NoDelivery.getReferenceOrderId());
    }
    
    /**
     * Test_ purchase card_ insufficient funds.
     */
    public void test_PurchaseCard_InsufficientFunds() {
        int cardValueTangoCardCents = 100; // $1.00 dollars
        
        String username = "empty@tangocard.com";
        String password = "password";  

        // make the request
        boolean isSuccess = false;
        PurchaseCardResponse responsePurchaseCard_NoDelivery = new PurchaseCardResponse();
        try {
            isSuccess = TangoCardServiceApi.PurchaseCard(
                    this._enumTangoCardServiceApi,
                    username, 
                    password,
                    this._app_card_sku,             	// cardSku
                    cardValueTangoCardCents,           	// cardValue
                    false,                          	// tcSend 
                    null,                             	// recipientName
                    null,                              	// recipientEmail
                    null,                           	// giftMessage
                    null,                            	// giftFrom  
                    responsePurchaseCard_NoDelivery		// response
            		);
            
            TestCase.fail("Expected 'TangoCardServiceException' to be thrown");
        } catch (TangoCardServiceException ex) {
            TestCase.assertTrue(  ex.getResponseType().equals(ServiceResponseEnum.INS_FUNDS.toString()) );
            String message = ex.getMessage();
            TestCase.assertNotNull(message);
        } catch (TangoCardSdkException ex) {
            TestCase.fail("TangoCardSdkException: " + ex.getMessage());
        } catch (Exception ex) {
            TestCase.fail("Exception: " + ex.getMessage());
        }
        
        TestCase.assertFalse(isSuccess);
        TestCase.assertNotNull(responsePurchaseCard_NoDelivery);
        TestCase.assertNull(responsePurchaseCard_NoDelivery.getCardNumber());
        TestCase.assertNull(responsePurchaseCard_NoDelivery.getCardPin());
        TestCase.assertNull(responsePurchaseCard_NoDelivery.getCardToken());
        TestCase.assertNull(responsePurchaseCard_NoDelivery.getReferenceOrderId());
    }
    
    /**
     * Test_ purchase card_ insufficient funds.
     */
    public void test_PurchaseCard_InvalidInput_Sku() {
        int cardValueTangoCardCents = 100; // $1.00 dollars 

        // make the request
        boolean isSuccess = false;
        PurchaseCardResponse responsePurchaseCard_NoDelivery = new PurchaseCardResponse();
        try {
            isSuccess = TangoCardServiceApi.PurchaseCard(
                    this._enumTangoCardServiceApi,
                    this._app_username, 
                    this._app_password,
                    "mango-card",                   	// cardSku
                    cardValueTangoCardCents,           	// cardValue
                    false,                          	// tcSend 
                    null,                             	// recipientName
                    null,                              	// recipientEmail
                    null,                           	// giftMessage
                    null,                            	// giftFrom  
                    responsePurchaseCard_NoDelivery		// response
            		);
            
            TestCase.fail("Expected 'TangoCardServiceException' to be thrown");
        } catch (TangoCardServiceException ex) {
            TestCase.assertTrue(  ex.getResponseType().equals(ServiceResponseEnum.INV_INPUT.toString()) );
            String message = ex.getMessage();
            TestCase.assertNotNull(message);
        } catch (TangoCardSdkException ex) {
            TestCase.fail("TangoCardSdkException: " + ex.getMessage());
        } catch (Exception ex) {
            TestCase.fail("Exception: " + ex.getMessage());
        }
        
        TestCase.assertFalse(isSuccess);
        

        TestCase.assertNotNull(responsePurchaseCard_NoDelivery);
        TestCase.assertNull(responsePurchaseCard_NoDelivery.getCardNumber());
        TestCase.assertNull(responsePurchaseCard_NoDelivery.getCardPin());
        TestCase.assertNull(responsePurchaseCard_NoDelivery.getCardToken());
        TestCase.assertNull(responsePurchaseCard_NoDelivery.getReferenceOrderId());
    }
}
