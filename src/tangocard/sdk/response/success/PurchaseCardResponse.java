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
            this._referenceOrderId         = responseJson.getJSONObject("response").getString("referenceOrderId");
            this._cardToken             = responseJson.getJSONObject("response").getString("cardToken");
            if ( responseJson.getJSONObject("response").has("cardNumber")) {
                this._cardNumber         = responseJson.getJSONObject("response").getString("cardNumber");
            }
            if ( responseJson.getJSONObject("response").has("cardPin")) {
                this._cardPin             = responseJson.getJSONObject("response").getString("cardPin");
            }
            isSuccess = true;
        } catch (JSONException e) {
            throw new TangoCardSdkException( "JSONException", e );
        }
        
        return isSuccess;
    }
}
