/**
 * GetAvailableBalanceRequest.java
 * TangoCard_Java_SDK
 * 
 * @version  1.1.0
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

package tangocard.sdk.request;

import org.json.JSONException;
import org.json.JSONObject;

import tangocard.sdk.common.*;
import tangocard.sdk.response.success.*;
import tangocard.sdk.service.TangoCardServiceApiEnum;

/**
 * The Class GetAvailableBalanceRequest.
 */
public class GetAvailableBalanceRequest extends BaseRequest {
    
    /**
     * Instantiates a new gets the available balance request.
     *
     * @param enumTangoCardServiceApi the enum Tango Card service api
     * @param username the username
     * @param password the password
     */
    public GetAvailableBalanceRequest(
            TangoCardServiceApiEnum enumTangoCardServiceApi,
            String username, 
            String password
    ) {
        super(enumTangoCardServiceApi, username, password);
    }

    /**
     * Execute.
     *
     * @return the gets the available balance response
     * @throws Exception 
     */
    public boolean execute(GetAvailableBalanceResponse response) throws Exception {
        return super.execute(response);
    }

    /* (non-Javadoc)
     * @see tangocard.sdk.request.BaseRequest#getJsonEncodedRequest()
     */
    @Override
    public String getJsonEncodedRequest() throws TangoCardSdkException {
        
        JSONObject requestJson = new JSONObject();

        try {
            requestJson.put("username", super.getUsername());
            requestJson.put("password", super.getPassword());
        } catch (JSONException ex) {
            throw new TangoCardSdkException( "JSONException", ex );
        }
        
        return requestJson.toString();
    }

    /* (non-Javadoc)
     * @see tangocard.sdk.request.BaseRequest#getRequestAction()
     */
    @Override
    public String getRequestAction() {
        return "GetAvailableBalance";
    }
}