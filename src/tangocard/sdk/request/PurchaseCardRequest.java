/**
 * PurchaseCardRequest.java
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

package tangocard.sdk.request;

import org.json.JSONException;
import org.json.JSONObject;

import tangocard.sdk.common.*;
import tangocard.sdk.response.success.*;
import tangocard.sdk.service.TangoCardServiceApiEnum;
    
public class PurchaseCardRequest extends BaseRequest {
    
    public String         _cardSku = null;
    public int            _cardValue = -1;
    public boolean        _tcSend = false;
    public String        _recipientName = null;
    public String        _recipientEmail = null;
    public String        _giftMessage = null;
    public String        _giftFrom = null;
    public String        _companyIdentifier = null;

    /**
     * Instantiates a new purchase card request.
     *
     * @param enumTangoCardServiceApi The enum Tango Card service api
     * @param username The username to access User's registered Tango Card account
     * @param password The password to access User's registered Tango Card account
     * @param cardSku The card sku
     * @param cardValue The card value
     * @param tcSend Determines if Tango Card Service will send an email with gift card information to recipient
     * @param recipientName The recipient name
     * @param recipientEmail The recipient email
     * @param giftMessage The gift message
     * @param giftFrom The gift from
     * @param companyIdentifier The Company identifier for which Email Template when sending Gift Card.  
     */
    public PurchaseCardRequest(
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
            String companyIdentifier
    ) {
        // parent construct
        super(enumTangoCardServiceApi, username, password);
        
        // -----------------------------------------------------------------
        // validate inputs
        // ----------------------------------------------------------------- 
        
        // enumTangoCardServiceApi
        if ( enumTangoCardServiceApi.equals(TangoCardServiceApiEnum.UNDEFINED) ) {
            throw new IllegalArgumentException( "Parameter 'enumTangoCardServiceApi' must be defined.");
        }
        
        // cardSku
        if (Helper.isNullOrEmptyString(cardSku))
        {
            throw new IllegalArgumentException( "Parameter 'cardSku' must be a string.");
        }
        if (cardSku.length() < 1)
        {
            throw new IllegalArgumentException( "Parameter 'cardSku' must have a length greater than zero.");
        }
        if (cardSku.length() > 255)
        {
            throw new IllegalArgumentException( "Parameter 'cardSku' must have a length less than 255.");
        }
        
        // cardValue
        if (cardValue < 1)
        {
            throw new IllegalArgumentException( "Parameter 'cardValue' must have a value which is greater than or equal to 1.");
        }
        
        if (tcSend) {
            // recipientName
            if (Helper.isNullOrEmptyString(recipientName))
            {
                throw new IllegalArgumentException( "Parameter 'recipientName' must be present (not null) if tcSend is set to true.");
            }
            if (recipientName.length() < 1)
            {
                throw new IllegalArgumentException( "Parameter 'recipientName' must have a length greater than zero.");
            }
            if (recipientName.length() > 255)
            {
                throw new IllegalArgumentException( "Parameter 'recipientName' must have a length less than 256.");
            }   
            
            // recipientEmail
            if (Helper.isNullOrEmptyString(recipientEmail))
            {
                throw new IllegalArgumentException( "Parameter 'recipientEmail' must be present (not null) if tcSend is set to true.");
            }
            if (recipientEmail.length() < 3)
            {
                throw new IllegalArgumentException( "Parameter 'recipientEmail' must have a length greater than two.");
            }
            if (recipientEmail.length() > 255)
            {
                throw new IllegalArgumentException( "Parameter 'recipientEmail' must have a length less than 256.");
            }
            
            // giftFrom
            if (Helper.isNullOrEmptyString(giftFrom))
            {
                throw new IllegalArgumentException( "Parameter 'giftFrom' must be present (not null) if tcSend is set to true.");
            }
            if (giftFrom.length() < 1)
            {
                throw new IllegalArgumentException( "Parameter 'giftFrom' must have a length greater than zero.");
            }
            if (giftFrom.length() > 255)
            {
                throw new IllegalArgumentException( "Parameter 'giftFrom' must have a length less than 256.");
            }
            
            // giftMessage
            if ( !Helper.isNullOrEmptyString(giftMessage) ) {
                if (giftMessage.length() > 255)
                {
                    throw new IllegalArgumentException( "Parameter 'giftMessage' must have a length less than 256.");
                }
            }

            // companyIdentifier
            if ( !Helper.isNullOrEmptyString(companyIdentifier) ) {
                if (companyIdentifier.length() > 255)
                {
                    throw new IllegalArgumentException( "Parameter 'companyIdentifier' must have a length less than 256.");
                }
            }
        }

        // -----------------------------------------------------------------
        // save inputs
        // -----------------------------------------------------------------

        this._cardSku   = cardSku;
        this._cardValue = cardValue;
        this._tcSend    = tcSend;
        if (tcSend) {
            this._recipientName  = recipientName;
            this._recipientEmail = recipientEmail;
            this._giftFrom       = giftFrom;
            if ( !Helper.isNullOrEmptyString(giftMessage) ) {
                this._giftMessage    = giftMessage;
            }
            if ( !Helper.isNullOrEmptyString(companyIdentifier) ) {
                this._companyIdentifier    = companyIdentifier;
            }
        }
    }


    /**
     * Execute.
     *
     * @param response the response
     * @return true, if successful
     * @throws Exception the exception
     */
    public boolean execute(PurchaseCardResponse response) throws Exception {
        return super.execute(response);
    }

    /* (non-Javadoc)
     * @see tangocard.sdk.request.BaseRequest#getJsonEncodedRequest()
     */
    @Override
    public String getJsonEncodedRequest() throws TangoCardSdkException {
        
        JSONObject requestJson = new JSONObject();
        try {
            requestJson.put("username",     super.getUsername());
            requestJson.put("password",     super.getPassword());
            requestJson.put("giver_name",     this._giftFrom);
            requestJson.put("cardSku",         this._cardSku);
            requestJson.put("cardValue",     this._cardValue);
            requestJson.put("tcSend",         this._tcSend);
            if (this._tcSend) {
                requestJson.put("recipientName",     this._recipientName);
                requestJson.put("recipientEmail",     this._recipientEmail);
                requestJson.put("giftFrom",         this._giftFrom);
                if ( !Helper.isNullOrEmptyString(this._giftMessage) ) {
                    requestJson.put("giftMessage",     this._giftMessage);
                }
            if ( !Helper.isNullOrEmptyString(           this._companyIdentifier) ) {
                requestJson.put("companyIdentifier",    this._companyIdentifier);
            }
            }
        } catch (JSONException e) {
            throw new TangoCardSdkException( "JSONException", e );
        }
        
        return requestJson.toString();
    }

    /* (non-Javadoc)
     * @see tangocard.sdk.request.BaseRequest#getRequestAction()
     */
    @Override
    public String getRequestAction() {
        return "PurchaseCard";
    }
}