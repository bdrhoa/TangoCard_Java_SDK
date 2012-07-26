/**
 * GetAvailableBalanceResponse.java
 * TangoCard_Java_SDK
 * 
 * @version  1.0.2
 * @link     http://www.tangocard.com
 * @since 	 07/23/2012
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

package tangocard.sdk.response.success;

import org.json.JSONException;
import org.json.JSONObject;

// TODO: Auto-generated Javadoc
/**
 * The Class GetAvailableBalanceResponse.
 */
public class GetAvailableBalanceResponse extends SuccessResponse {
    
    /** The available balance. */
    private int _availableBalance = 0;
    
    /**
     * Gets the available balance.
     *
     * @return the available balance
     */
    public int getAvailableBalance()
    {
    	return this._availableBalance;
    }
    
    /**
     * Instantiates a new gets the available balance response.
     */
    public GetAvailableBalanceResponse() 
    {

    }
    
    /**
     * Instantiates a new gets the available balance response.
     *
     * @param responseJson the response JSON
     * @throws JSONException 
     */
    public GetAvailableBalanceResponse(JSONObject responseJson) 
    {
		this.parseResponseJSON(responseJson);
    }
    
    /**
     * Parses the response JSON.
     *
     * @param responseJson the response JSON
     * @return true, if successful
     */
    public boolean parseResponseJSON( JSONObject responseJson )
    {
    	boolean isSuccess = false;
		try {
			this._availableBalance = responseJson.getJSONObject("response").getInt("availableBalance");
			isSuccess = true;
		} catch (JSONException ex) {
			throw new RuntimeException( "JSONException", ex );
		}
		
		return isSuccess;
    }
}
