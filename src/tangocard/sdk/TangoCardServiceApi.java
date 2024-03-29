/**
 * TangoCardServiceApi.java
 * TangoCard_Java_SDK
 * 
 * @version  1.1.0
 * @link     http://www.tangocard.com
 * 
 * Copyright (c) 2012 Tango Card, Inc
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

import tangocard.sdk.common.*;
import tangocard.sdk.request.*;
import tangocard.sdk.response.success.*;
import tangocard.sdk.service.TangoCardServiceApiEnum;

public final class TangoCardServiceApi {
    
    /**
     * Constructor that prevents a default instance of this class from being created.
     */
    private TangoCardServiceApi() {}
    
    /**
     * Get SDK Version from config file.
     * @return String SDK version
     */
    public static String GetVersion() {
        SdkConfig sdkConfig;
        try {
            sdkConfig = SdkConfig.getInstance();
            return sdkConfig.getConfigValue("tc_sdk_version");
        } catch (Exception e) {
            return "error";
        }
    }
    
    
    /**
     * Gets the available balance.
     *
     * @param enumTangoCardServiceApi the enum Tango Card service api
     * @param username The username to access User's registered Tango Card account
     * @param password The password to access User's registered Tango Card account
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
        if (Helper.isNullOrEmptyString(username)) {
            throw new IllegalArgumentException("Parameter 'username' is not defined.");
        }
        if (Helper.isNullOrEmptyString(password)) {
            throw new IllegalArgumentException("Parameter 'password' is not defined.");
        }
        if ( null == response) {
            throw new IllegalArgumentException("Parameter 'response' is not defined.");
        }
        
        // set up the request
        GetAvailableBalanceRequest request 
            = new GetAvailableBalanceRequest( 
                    enumTangoCardServiceApi,
                    username.trim(), 
                    password
                    );
        
        return request.execute(response);
    }
    
    /**
     * Purchase card.
     *
     * @param enumTangoCardServiceApi the enum tango card service api
     * @param username The username to access User's registered Tango Card account
     * @param password The password to access User's registered Tango Card account
     * @param cardSku The card sku
     * @param cardValue The card value
     * @param tcSend Determines if Tango Card Service will send an email with gift card information to recipient.
     * @param recipientName The recipient name
     * @param recipientEmail The recipient email
     * @param giftMessage The gift message
     * @param giftFrom The gift from
     * @param companyIdentifier The Company identifier for which Email Template to use when sending Gift Card. 
     * @param response The response
     *
     * @return true, if successful
     *
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
            String companyIdentifier,
            PurchaseCardResponse response
    ) throws Exception {
        if (Helper.isNullOrEmptyString(username)) {
            throw new IllegalArgumentException("Parameter 'username' is not defined.");
        }
        if (Helper.isNullOrEmptyString(password)) {
            throw new IllegalArgumentException("Parameter 'password' is not defined.");
        }
        if (Helper.isNullOrEmptyString(cardSku)) {
            throw new IllegalArgumentException("Parameter 'cardSku' is not defined.");
        }
        if (0 >= cardValue) {
            throw new IllegalArgumentException("Parameter 'cardValue' is not defined.");
        }
        if ( null == response ) {
            throw new IllegalArgumentException("Parameter 'response' is not defined.");
        }
        
        // set up the request
        PurchaseCardRequest requestPurchaseCard = new PurchaseCardRequest(
                enumTangoCardServiceApi,
                username.trim(), 
                password,
                cardSku.trim(),                                                                         // cardSku
                cardValue,                                                                              // cardValue
                tcSend,                                                                                 // tcSend 
                Helper.isNullOrEmptyString(recipientName)       ? null : recipientName.trim(),          // recipientName
                Helper.isNullOrEmptyString(recipientEmail)      ? null : recipientEmail.trim(),         // recipientEmail
                Helper.isNullOrEmptyString(giftMessage)         ? null : giftMessage.trim().replaceAll("(\r\n|\n\r|\r|\n)", "<br>") ,        // giftMessage
                Helper.isNullOrEmptyString(giftFrom)            ? null : giftFrom.trim(),                // giftFrom
                Helper.isNullOrEmptyString(companyIdentifier)   ? null : companyIdentifier.trim()        // companyIdentifier
        );
        
        return requestPurchaseCard.execute(response);
    }

}
