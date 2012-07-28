/**
 * ServiceProxy.java
 * TangoCard_Java_SDK
 * 
 * @version  1.0.5
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

package tangocard.sdk.service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

import org.json.JSONException;
import org.json.JSONObject;

import tangocard.sdk.common.*;
import tangocard.sdk.request.*;
import tangocard.sdk.response.ServiceResponseEnum;
import tangocard.sdk.response.success.*;
import tangocard.sdk.response.failure.*;

// TODO: Auto-generated Javadoc
/**
 * The Class ServiceProxy.
 */
public class ServiceProxy {
    
    /** The _base_url. */
    private String _base_url = null;
    
    /** The _controller. */
    private String _controller = null;
    
    /** The _action. */
    private String _action = null;
    
    /** The _path. */
    private String _path = null;
    
    /** The _request_json. */
    private String _request_json = null;
    
    /** The _request object. */
    private BaseRequest _requestObject = null;
    
    /**
     * Instantiates a new service proxy.
     *
     * @param requestObject the request object
     * @throws Exception the exception
     */
    public ServiceProxy( BaseRequest requestObject ) throws Exception
    {
        if (null == requestObject) {
            throw new IllegalArgumentException("Parameter 'requestObject' is not defined.");
        }
        
        SdkConfig appConfig = null;
        try {
            this._requestObject = requestObject;
            
            appConfig = SdkConfig.getInstance();
        } catch ( Exception ex ) {
            throw ex;
        }
        
        if ( null == appConfig ) {
            throw new TangoCardSdkException("Failed to get instance of SDK configuration.");
        }
        
        this._base_url = null;
        if ( requestObject.getTangoCardServiceApi().equals(TangoCardServiceApiEnum.INTEGRATION)) {
            this._base_url = appConfig.getConfigValue("tc_sdk_environment_integration_url");
        } else if ( requestObject.getTangoCardServiceApi().equals(TangoCardServiceApiEnum.PRODUCTION)) {
            this._base_url = appConfig.getConfigValue("tc_sdk_environment_production_url");
        } else {
            throw new TangoCardSdkException("Unexpected Tango Card Service API request: " + requestObject.getTangoCardServiceApi().name() );
        }

        if ( null == this._base_url) {
            throw new TangoCardSdkException("Tango Card Service API was not assigned." );
        }
        
        this._controller = appConfig.getConfigValue("tc_sdk_controller");
        if ( Helper.isNullOrEmptyString(this._controller)) {
            throw new TangoCardSdkException("SDK configuration missing 'tc_sdk_controller' setting");
        }
        this._action = requestObject.getRequestAction();
        if ( Helper.isNullOrEmptyString(this._action)) {
            throw new TangoCardSdkException("Request missing 'action' name.");
        }
        this._path = String.format("%s/%s/%s", this._base_url, this._controller, this._action);
    }

    /**
     * Execute request.
     *
     * @param responseSuccess the response success
     * @return true, if successful
     * @throws Exception the exception
     */
    public boolean executeRequest(SuccessResponse responseSuccess) throws Exception
    {
        boolean isSuccess = false;
        
        String responseJsonEncoded = this.postRequest();
        if ( null != responseJsonEncoded )
        {
            JSONObject responseJson = new JSONObject(responseJsonEncoded);
            
            ServiceProxy.throwOnError(responseJson);
            
            Class<? extends BaseRequest> classType = this._requestObject.getClass();
            String className = classType.getName();
            
            if ( className.equals("tangocard.sdk.request.GetAvailableBalanceRequest") ) {
                ((GetAvailableBalanceResponse)responseSuccess).parseResponseJSON(responseJson);
            } else if ( className.equals("tangocard.sdk.request.PurchaseCardRequest") ) {
                ((PurchaseCardResponse)responseSuccess).parseResponseJSON(responseJson);
            } else {
                throw new TangoCardSdkException("Unexpected condition: " + className);
            }
                        
            isSuccess = true;
        }
            
        return isSuccess;
    }
    
    /**
     * Map request.
     *
     * @return true, if successful
     * @throws Exception the exception
     */
    protected boolean mapRequest() throws Exception
    {
        boolean isSuccess = false;
        try {            
            String responseJsonEncoded = this._requestObject.getJsonEncodedRequest();
            if ( !Helper.isNullOrEmptyString(responseJsonEncoded) ) {
                this._request_json = responseJsonEncoded;
                isSuccess = true;
            }
        } catch (Exception ex) {
            throw ex;
        }
                
        return isSuccess;
    }
    
    /**
     * Post request.
     *
     * @return true, if successful
     * @throws Exception the exception
     */
    protected String postRequest() throws Exception
    {
        if ( null == this._path ) {
            throw new TangoCardSdkException( "Member variable '_path' is null.");
        }
        
        String responseJsonEncoded = null;
        URL url = null;
        HttpsURLConnection connection = null;

        try {
            url = new URL(this._path);
        } catch(MalformedURLException ex) {
            throw new TangoCardSdkException( "MalformedURLException", ex );
        }
        
        if ( this.mapRequest() ) {
            try {
                // connect to the server over HTTPS and submit the payload
                connection = (HttpsURLConnection)url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-length", String.valueOf(this._request_json.length()));
                connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
    
                // open up the output stream of the connection
                DataOutputStream output = new DataOutputStream( connection.getOutputStream() );
                output.write(this._request_json.getBytes());
    
                // now read the input stream until it is closed, line by line adding to the response
                InputStream inputstream = connection.getInputStream();
                InputStreamReader inputstreamreader = new InputStreamReader(inputstream);
                BufferedReader bufferedreader = new BufferedReader(inputstreamreader);
                StringBuffer response = new StringBuffer();
                String line = null;
                while ((line = bufferedreader.readLine()) != null) {
                    response.append(line);
                }
    
                responseJsonEncoded = response.toString();        
            } catch(IOException ex) {
                throw new TangoCardSdkException( "IOException", ex );
            }
        }

        return responseJsonEncoded;
    }
    
    /**
     * Throw on error.
     *
     * @param responseJson the response JSON
     * @throws JSONException the JSON exception
     * @throws TangoCardServiceException the tango card service exception
     * @throws TangoCardSdkException 
     */
    protected static void throwOnError(JSONObject responseJson) throws JSONException, TangoCardServiceException, TangoCardSdkException
    {
        String responseType = null;
        try {
            responseType = responseJson.getString("responseType");
        } catch (JSONException ex) {
            throw new TangoCardSdkException( "JSONException", ex );
        }
        
        if ( responseType.equals("SUCCESS")) {
            return;
        } else if ( responseType.equals("SYS_ERROR")) {
            SystemErrorResponse responseFailure = new SystemErrorResponse(responseJson);
            throw new TangoCardServiceException(ServiceResponseEnum.SYS_ERROR, responseFailure, null);
        } else if ( responseType.equals("INV_INPUT")) {
            InvalidInputResponse responseFailure = new InvalidInputResponse(responseJson);
            throw new TangoCardServiceException(ServiceResponseEnum.INV_INPUT, responseFailure, null);
        } else if ( responseType.equals("INV_CREDENTIAL")) {
            InvalidCredentialsResponse responseFailure = new InvalidCredentialsResponse(responseJson);
            throw new TangoCardServiceException(ServiceResponseEnum.INV_CREDENTIAL, responseFailure, null);
        } else if ( responseType.equals("INS_INV")) {
            InsufficientInventoryResponse responseFailure = new InsufficientInventoryResponse(responseJson);
            throw new TangoCardServiceException(ServiceResponseEnum.INS_INV, responseFailure, null);
        } else if ( responseType.equals("INS_FUNDS")) {
            InsufficientFundsResponse responseFailure = new InsufficientFundsResponse(responseJson);
            throw new TangoCardServiceException(ServiceResponseEnum.INS_FUNDS, responseFailure, null);
        } else {
            throw new TangoCardSdkException("Unexpected 'responseType'.");            
        }
    }    
}
