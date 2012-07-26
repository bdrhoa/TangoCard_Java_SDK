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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import tangocard.sdk.request.GetAvailableBalanceRequest;
import tangocard.sdk.response.success.GetAvailableBalanceResponse;

import junit.framework.TestCase;

public class UnitTest_GetAvailableBalance extends TestCase {
	
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
    public void testGetAvailableBalance() {
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
		} catch (Exception ex) {
			TestCase.fail("Exception: " + ex.getMessage());
		}
		
		TestCase.assertTrue(isSuccess);
		TestCase.assertNotNull(responseAvailableBalance);
		
		int tango_cents_available_balance = responseAvailableBalance.getAvailableBalance();
		
		TestCase.assertTrue(tango_cents_available_balance >= 100);
    }
}
