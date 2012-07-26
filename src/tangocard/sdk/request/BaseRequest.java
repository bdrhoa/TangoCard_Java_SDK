/**
 * BaseRequest.java
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

package tangocard.sdk.request;

import tangocard.sdk.common.*;
import tangocard.sdk.response.success.SuccessResponse;
import tangocard.sdk.service.*;

public abstract class BaseRequest {
	
	/** The _username. */
	private String _username = null;
	
	/** The _password. */
	private String _password = null;
	
	/** The _endpoint. */
	private boolean _is_production_mode = false;
	
	/**
	 * Instantiates a new base request.
	 *
	 * @param isProductionMode the is production mode
	 * @param username the username
	 * @param password the password
	 */
	public BaseRequest(
			boolean isProductionMode,
			String username,
			String password
	){
        // -----------------------------------------------------------------
        // validate inputs
        // -----------------------------------------------------------------
        // username
        if ( Helper.isNullOrEmptyString(username) ) {
            throw new IllegalArgumentException("Parameter 'username' is not defined.");
        }
        // password
        if ( Helper.isNullOrEmptyString(password) ) {
            throw new IllegalArgumentException("Parameter 'password' is not defined.");
        }
        
		this._username = username;
		this._password = password;
		this._is_production_mode = isProductionMode;
	}
	
	/**
	 * Gets the request action.
	 *
	 * @return the request action
	 */
	abstract public String getRequestAction();
	
	/**
	 * Gets the json encoded request.
	 *
	 * @return the json encoded request
	 */
	abstract public String getJsonEncodedRequest();
	
    /**
     * Gets the username.
     *
     * @return the username
     */
    public String getUsername() {
        return this._username;
    }
    
    /**
     * Sets the username.
     *
     * @param username the new username
     */
    public void setUsername(String username) {
    	this._username = username;
    }
    
    /**
     * Gets the password.
     *
     * @return the password
     */
    public String getPassword() {
    	return this._password;
    }
    
    /**
     * Sets the password.
     *
     * @param password the new password
     */
    public void setPassword(String password) {
    	this._password = password;
    }
    
    /**
     * Determine if currently requested service endpoint type is for production.
     *
     * @return the checks if is production mode
     */
    public boolean getIsProductionMode()
    {
    	return this._is_production_mode;
    }
    
    /**
     * Set service endpoint type based upon if request is for production.
     *
     * @param is_production_mode the new checks if is production mode
     */
    public void setIsProductionMode(boolean is_production_mode)
    {
    	this._is_production_mode = is_production_mode;
    }
    
    /**
     * Execute.
     *
     * @param <T> the generic type
     * @param response the response
     * @return true, if successful
     * @throws Exception the exception
     */
    public <T extends SuccessResponse> boolean execute(T response) throws Exception
    {
    	ServiceProxy proxy = new ServiceProxy(this);
    	return proxy.executeRequest(response);
    }
}