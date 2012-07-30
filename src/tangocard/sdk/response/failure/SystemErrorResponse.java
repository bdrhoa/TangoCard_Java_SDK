/**
 * SystemErrorResponse.java
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

package tangocard.sdk.response.failure;

import org.json.JSONException;
import org.json.JSONObject;

import tangocard.sdk.common.TangoCardSdkException;

/**
 * The Class SystemErrorResponse.
 */
public class SystemErrorResponse extends FailureResponse {
    
    /** The error code. */
    private String _errorCode;
    
    /**
     * Gets the error code.
     *
     * @return the error code
     */
    public String getErrorCode() {
        return this._errorCode;
    }
    
    /**
     * Instantiates a new system error response.
     *
     * @param responseJson the response JSON
     * @throws TangoCardSdkException 
     */
    public SystemErrorResponse( JSONObject responseJson ) throws TangoCardSdkException
    {
        this.parseResponseJSON(responseJson);
    }
    
    /* (non-Javadoc)
     * @see tangocard.sdk.response.BaseResponse#parseResponseJSON(org.json.JSONObject)
     */
    public boolean parseResponseJSON( JSONObject responseJson ) throws TangoCardSdkException
    {
        boolean isSuccess = false;
        try {
            this._errorCode     = responseJson.getJSONObject("response").getString("errorCode");
            isSuccess = true;
        } catch (JSONException ex) {
            throw new TangoCardSdkException( "JSONException", ex );
        }
        
        return isSuccess;
    }
    
    /* (non-Javadoc)
     * @see tangocard.sdk.response.failure.FailureResponse#getMessage()
     */
    public String getMessage()
    {
        return String.format("ErrorCode: %s", this._errorCode);
    }
}
