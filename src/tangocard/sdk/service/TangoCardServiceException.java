/**
 * © 2012 Tango Card, Inc
 *  All rights reserved.
 * 
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions: 
 * 
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software. 
 * 
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 */ 

package tangocard.sdk.service;

import tangocard.sdk.response.*;
import tangocard.sdk.response.failure.*;

/**
 * The Class TangoCardServiceException.
 */
public class TangoCardServiceException extends Exception { 

	private static final long serialVersionUID = 3461168669128080352L;
	private String _responseType = "UNDEFINED";
	private FailureResponse _response = null;
	
	public String getResponseType() {
		return this._responseType;
	}
	
	public Object getResponse() {
		return this._response;
	}
	
	/**
	 * Instantiates a new service exception.
	 *
	 * @param responseType the response type
	 * @param responseJson the response json
	 * @param message the message
	 */
	public TangoCardServiceException(ServiceResponseEnum responseType, FailureResponse responseJson, String message) { 
		super(message); 
		this._responseType = responseType.name();
		this._response = responseJson;
	} 	
} 
