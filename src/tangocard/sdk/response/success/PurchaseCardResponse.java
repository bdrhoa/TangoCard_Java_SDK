/**
 * PurchaseCardResponse.java
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

package tangocard.sdk.response.success;

import org.json.JSONException;
import org.json.JSONObject;

import tangocard.sdk.common.*;

/**
 * The Class PurchaseCardResponse.
 */
public class PurchaseCardResponse extends SuccessResponse {
    
    /** The _reference order id. */
    private String _referenceOrderId;
    
    /**
     * Gets the reference order id.
     *
     * @return the reference order id
     */
    public String getReferenceOrderId()
    {
        return this._referenceOrderId;
    }
    
    /** The _card token. */
    private String _cardToken;
    
    /**
     * Gets the card token.
     *
     * @return the card token
     */
    public String getCardToken()
    {
        return this._cardToken;
    }
    
    /** The _card number. */
    private String _cardNumber;
    
    /**
     * Gets the card number.
     *
     * @return the card number
     */
    public String getCardNumber()
    {
        return this._cardNumber;
    }
    
    /** The _card pin. */
    private String _cardPin;
    
    /**
     * Gets the card pin.
     *
     * @return the card pin
     */
    public String getCardPin()
    {
        return this._cardPin;
    }
    
    
    /** The claim URL. */
    private String _claimUrl;
    
    /**
     * Gets the claim URL.
     *
     * @return the claim URL
     */
    public String getClaimUrl()
    {
        return this._claimUrl;
    }
    
    
    /** The challenge Key. */
    private String _challengeKey;
    
    /**
     * Gets the claim URL.
     *
     * @return the challenge Key
     */
    public String getChallengeKey()
    {
        return this._challengeKey;
    }
 
    /**
     * Instantiates a new purchase card response.
     */
    public PurchaseCardResponse()
    {
    }
    
    /**
     * Instantiates a new purchase card response.
     *
     * @param responseJson the response JSON
     * @throws TangoCardSdkException 
     */
    public PurchaseCardResponse(JSONObject responseJson) throws TangoCardSdkException
    {
        this.parseResponseJSON(responseJson);
    }
    
    /* (non-Javadoc)
     * @see tangocard.sdk.response.success.SuccessResponse#parseResponseJSON(org.json.JSONObject)
     */
    public boolean parseResponseJSON( JSONObject responseJson ) throws TangoCardSdkException
    {
        boolean isSuccess = false;
        
        try {
            JSONObject response = responseJson.getJSONObject("response");

            if ( null != response ) {
                if ( response.has("referenceOrderId") && !response.isNull("referenceOrderId")) {
                    this._referenceOrderId = response.getString("referenceOrderId");
                }
                if ( response.has("cardToken") && !response.isNull("cardToken")) {
                    this._cardToken = response.getString("cardToken");
                }
                if ( response.has("cardNumber") && !response.isNull("cardNumber")) {
                    this._cardNumber = response.getString("cardNumber");
                }
                if ( response.has("cardPin") && !response.isNull("cardPin")) {
                    this._cardPin = response.getString("cardPin");
                }
                if ( response.has("claimUrl") && !response.isNull("claimUrl")) {
                    this._claimUrl = response.getString("claimUrl");
                }
                if ( response.has("challengeKey") && !response.isNull("challengeKey")) {
                    this._challengeKey = response.getString("challengeKey");
                }
                isSuccess = true;
            }
        } catch (JSONException e) {
            throw new TangoCardSdkException( "JSONException", e );
        }
        
        return isSuccess;
    }
}
