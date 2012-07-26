/**
 * 
 *  UnitTest_GetAvailableBalance.java
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

package tangocard.sdk.unittests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import tangocard.sdk.common.TangoCardSdkException;
import tangocard.sdk.request.GetAvailableBalanceRequest;
import tangocard.sdk.response.ServiceResponseEnum;
import tangocard.sdk.response.success.GetAvailableBalanceResponse;
import tangocard.sdk.service.TangoCardServiceException;

import junit.framework.TestCase;

/**
 * The Class UnitTest_GetAvailableBalance.
 */
public class UnitTest_GetAvailableBalance extends TestCase {
	
	/** The _app_username. */
	private String _app_username = null;
	
	/** The _app_password. */
	private String _app_password = null;
	
	/** The _is_production_mode. */
	private boolean _is_production_mode = false;	

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
		String app_production_mode = prop.getProperty("app_production_mode");
		
		this._is_production_mode = app_production_mode.equals("true");        
    }
    
    /**
     * Instantiates a new unit test_ get available balance.
     *
     * @param method the method
     */
    public UnitTest_GetAvailableBalance(String method) {
        super(method);
    }
    
    /**
     * Test get available balance.
     */
    public void test_GetAvailableBalance() {
		GetAvailableBalanceRequest requestAvailableBalance 
		= new GetAvailableBalanceRequest( 
				this._is_production_mode,
				this._app_username, 
				this._app_password
				);
	
		boolean isSuccess = false;
		GetAvailableBalanceResponse responseAvailableBalance = new GetAvailableBalanceResponse();
		try {			
			isSuccess = requestAvailableBalance.execute(responseAvailableBalance);
		} catch (TangoCardServiceException ex) {
			TestCase.fail("TangoCardServiceException: " + ex.getMessage());
		} catch (TangoCardSdkException ex) {
			TestCase.fail("TangoCardSdkException: " + ex.getMessage());
		} catch (Exception ex) {
			TestCase.fail("Exception: " + ex.getMessage());
		}
		
		TestCase.assertTrue(isSuccess);
		TestCase.assertNotNull(responseAvailableBalance);
		
		int tango_cents_available_balance = responseAvailableBalance.getAvailableBalance();
		
		TestCase.assertTrue(tango_cents_available_balance >= 100);
    }
    
    /**
     * Test_ get available balance_ invalid credentials.
     */
    public void test_GetAvailableBalance_InvalidCredentials() {
    	
        String username = "test@test.com";
        String password = "password";   
        
		GetAvailableBalanceRequest requestAvailableBalance 
		= new GetAvailableBalanceRequest( 
				this._is_production_mode,
				username, 
				password
				);
	
		boolean isSuccess = false;
		GetAvailableBalanceResponse responseAvailableBalance = new GetAvailableBalanceResponse();
		try {			
			isSuccess = requestAvailableBalance.execute(responseAvailableBalance);
			
			TestCase.fail("Expected 'TangoCardServiceException' to be thrown");
		} catch (TangoCardServiceException ex) {
			TestCase.assertTrue(  ex.getResponseType().equals(ServiceResponseEnum.INV_CREDENTIAL.toString()) );
		} catch (TangoCardSdkException ex) {
			TestCase.fail("TangoCardSdkException: " + ex.getMessage());
		} catch (Exception ex) {
			TestCase.fail("Exception: " + ex.getMessage());
		}
		
		TestCase.assertFalse(isSuccess);
    }
}
