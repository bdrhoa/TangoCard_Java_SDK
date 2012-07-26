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
	 * @throws Exception 
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
			throw new RuntimeException("Failed to get instance of SDK configuration.");
		}
		
		this._base_url = requestObject.getIsProductionMode()
			? appConfig.getConfigValue("tc_sdk_environment_production_url")
			: appConfig.getConfigValue("tc_sdk_environment_integration_url");
		
		this._controller = appConfig.getConfigValue("tc_sdk_controller");
		if ( Helper.isNullOrEmptyString(this._controller)) {
			throw new RuntimeException("SDK configuration missing 'tc_sdk_controller' setting");
		}
		this._action = requestObject.getRequestAction();
		if ( Helper.isNullOrEmptyString(this._action)) {
			throw new RuntimeException("Request missing 'action' name.");
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
		
		try {
			
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
					throw new RuntimeException("Unexpected condition: " + className);
				}
							
				isSuccess = true;
			}
		} catch (Exception ex) {
			throw ex;
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
	 * @param responseJsonEncoded the response json encoded
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	protected String postRequest() throws Exception
	{
		if ( null == this._path ) {
			throw new RuntimeException( "Member variable '_path' is null.");
		}
		
		String responseJsonEncoded = null;
		URL url = null;
		HttpsURLConnection connection = null;

		try {
			url = new URL(this._path);
		} catch(MalformedURLException ex) {
			throw new RuntimeException( "MalformedURLException", ex );
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
				throw new RuntimeException( "IOException", ex );
			}
		}

		return responseJsonEncoded;
	}
	
	/**
	 * Throw on error.
	 *
	 * @param responseJson the response JSON
	 * @throws JSONException the JSON exception
	 * @throws TangoCardServiceException 
	 */
	protected static void throwOnError(JSONObject responseJson) throws JSONException, TangoCardServiceException
	{
		String responseType = null;
		try {
			responseType = responseJson.getString("responseType");
		} catch (JSONException ex) {
			throw new RuntimeException( "JSONException", ex );
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
			throw new RuntimeException("Unexpected 'responseType'.");			
		}
	}	
}
