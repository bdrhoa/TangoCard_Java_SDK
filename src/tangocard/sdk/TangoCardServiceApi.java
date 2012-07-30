/**
 * TangoCardServiceApi.java
 * TangoCard_Java_SDK
 * 
 * @version  1.0.6
 * @link     http://www.tangocard.com
 * 
 * © 2012 Tango Card, Inc
 * All rights reserved.
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions: 
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software. 
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * 
 */

package tangocard.sdk;

import tangocard.sdk.request.*;
import tangocard.sdk.response.success.*;
import tangocard.sdk.service.TangoCardServiceApiEnum;

public final class TangoCardServiceApi {
	
	/**
	 * Constructor that prevents a default instance of this class from being created.
	 */
	private TangoCardServiceApi() {}
	
	
	/**
	 * Gets the available balance.
	 *
	 * @param enumTangoCardServiceApi the enum Tango Card service api
	 * @param username the username
	 * @param password the password
	 * @param response the response
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	public static boolean GetAvailableBalance (
            TangoCardServiceApiEnum enumTangoCardServiceApi,
            String username, 
            String password,
            GetAvailableBalanceResponse response
    ) throws Exception {
		
		// set up the request
        GetAvailableBalanceRequest request 
	        = new GetAvailableBalanceRequest( 
	                enumTangoCardServiceApi,
	                username, 
	                password
	                );
        
        return request.execute(response);
	}
	
    /**
     * Purchase card.
     *
     * @param enumTangoCardServiceApi the enum tango card service api
     * @param username the username
     * @param password the password
     * @param cardSku the card sku
     * @param cardValue the card value
     * @param tcSend the tc send
     * @param recipientName the recipient name
     * @param recipientEmail the recipient email
     * @param giftMessage the gift message
     * @param giftFrom the gift from
     * @param response the response
     * @return true, if successful
     * @throws Exception the exception
     */
    public static boolean PurchaseCard(
            TangoCardServiceApiEnum enumTangoCardServiceApi,
            String username, 
            String password,    
            String cardSku, 
            int cardValue, 
            boolean tcSend, 
            String recipientName, 
            String recipientEmail, 
            String giftMessage, 
            String giftFrom,
            PurchaseCardResponse response
    ) throws Exception {
    	
    	// set up the request
        PurchaseCardRequest requestPurchaseCard = new PurchaseCardRequest(
                enumTangoCardServiceApi,
                username, 
                password,
                cardSku,					// cardSku
                cardValue,					// cardValue
                tcSend,						// tcSend 
                recipientName,				// recipientName
                recipientEmail,				// recipientEmail
                giftMessage,				// giftMessage
                giftFrom					// giftFrom  
        );
        
        return requestPurchaseCard.execute(response);
	}

}
