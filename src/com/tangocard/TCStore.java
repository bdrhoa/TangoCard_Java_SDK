package com.tangocard;

import org.json.JSONException;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.*;
import java.util.Date;

/**
 * This class is used to purchase TangoCards or Store cards and to get available Balance per account
 *
 * @author CMH
 * @author WEW
 * @version 1.0
 */

public class TCStore {
	
	static boolean ENVIRONMENT_INTEGRATION = false;
	static boolean ENVIRONMENT_PRODUCTION = true;

	private String baseURL;
	private String userName;
	private String password;
	private String company_identifier;

	/**
	 * This response type indicates that the transaction was successful.
	 */
	private final String SUCCESS = "SUCCESS";
	
    /**     
     * This response type indicates that there was an error internal to the 
     * TangoCard service.
     */  
	private final String SYS_ERROR = "SYS_ERROR";
	
    /**
     * This response type indicates that there was a problem with the inputs.  
     */
	private final String INV_INPUT = "INV_INPUT";
	
    /**
     * This response type indicates that the supplied login credentials were 
     * invalid.
     */
	private final String INV_CREDENTIAL = "INV_CREDENTIAL";
	
    /**
     * This response type indicates that the item was not available for purchase 
     * due to there not being enough available inventory.
     */
	private final String INS_INV = "INS_INV";
	
    /**
     * This response type indicates that the authenticated account did not have 
     * sufficient funds available to make the purchase.
     */
	private final String INS_FUNDS = "INS_FUNDS";
	

	// ---------------------------------------------------------------------------------------------------------------
	/**
	 * Full constructor that sets up the store and makes it ready for use
	 *
	 * @param production Set to true to connect to production, otherwise false
	 * @param userName The username portion of the credentials
	 * @param password The password portion of the credentials
	 * @param company_identifier identifier for reporting purposes, free text
	 */
	public TCStore(Boolean production, String userName, String password, String company_identifier) {

		this.userName = userName;
		this.password = password;
		this.company_identifier = company_identifier;

		baseURL = production ? "https://api.tangocard.com/Version2" : "https://int.tangocard.com/Version2";
	}

	// ----------------------------------------------------------------------------------------------------------------
	/**
	 * Purchase a tangocard for a specific amount. On success will always return a filled in Transaction object.
	 * If the transaction failed, the returned object will be null.
	 *
	 * The flag send is true if TangoCard is to send the gift card directly to the receiver. If false the responsibility
	 * to communicate the card info to the receiver falls to the initiator of the call.
	 *
	 * If send is true, then receiverName, receiverEmail, from and giftMsg are mandatory, otherwise they should be
	 * set to null
	 *
	 * The value for sku is "tango-card" for the TangoCard, please contact your account manager for a list of other
	 * cards that might be available to you
	 *
	 * @param sku The sku of the card to send
	 * @param value  The value of the card being purchased
	 * @param tcSend Flag to indicate if TangoCard should send the card to the recipient or not
	 * @param recipientName The name of the person to receive the gift (mandatory if send == true)
	 * @param recipientEmail The email of the person to receive the gift (mandatory if send == true)
	 * @param giftFrom The entity (free form) that is sending the gift (mandatory if send == true)
	 * @param giftMsg The gift message to attach to the gift (mandatory if send == true)
	 *
	 * @return An instance of the transaction class
	 * @throws InvalidParametersException 
	 */
	public Transaction purchaseCard(String sku, int value, boolean tcSend, 
									String recipientName, String recipientEmail, 
									String giftFrom, String giftMessage) throws InvalidParametersException, TransactionFailureException {
		
		if (value <= 0 || value >= 10000) {
			throw new InvalidParametersException("You must supply a card value that is between 1 and 10,000 dollars.");
		}
		
		JSONObject request = new JSONObject();
		
		Transaction returnTransaction = new Transaction();
		
		try {
			request.put("username", userName);
			request.put("password", password);
			request.put("company_identifier", company_identifier);
			request.put("giver_name", giftFrom);
			request.put("cardSku", sku);
			request.put("cardValue", (value * 100));
			request.put("tcSend", tcSend);
			if (tcSend) {
				request.put("recipientName", recipientName);
				request.put("recipientEmail", recipientEmail);
				request.put("giftFrom", giftFrom);
				request.put("giftMessage", giftMessage);
			}
			
			JSONObject responseObject = new JSONObject(submitToServer(request.toString(), baseURL + "/PurchaseCard"));
			String responseType = responseObject.getString("responseType");
			
			if (responseType.equals(SUCCESS)) {
				
				JSONObject response = responseObject.getJSONObject("response");
				
				String primary = response.getString("cardNumber");
				String secondary = response.getString("cardPin");
				String orderId = response.getString("referenceOrderId");
				Card theCard = new Card(primary, secondary);
				
				returnTransaction = new Transaction(orderId, theCard);
				returnTransaction.setDatetime(new Date());
				returnTransaction.setSuccess(true);
				
			} else if (responseType.equals(SYS_ERROR)) {
				throw new TransactionFailureException("System error");
				
			} else if (responseType.equals(INV_INPUT)) {
				throw new TransactionFailureException("Invalid input");
				
			} else if (responseType.equals(INV_CREDENTIAL)) {
				throw new TransactionFailureException("Invalid credentials");
				
			} else if (responseType.equals(INS_INV)) {
				throw new TransactionFailureException("Insufficient inventory");
				
			} else if (responseType.equals(INS_FUNDS)) {
				throw new TransactionFailureException("Insufficient funds");

			} else {
				throw new TransactionFailureException("Error while performing transaction!");
				
			}
			
		} catch(JSONException jse) {
			returnTransaction.setError("There was an error processing the response from the server.", jse);
			jse.printStackTrace();
			return null;
		}
		
		return returnTransaction;
	}

	// ---------------------------------------------------------------------------------------------------------------
	/**
	 * Return the balance available to use for purchasing cards
	 * @return  The available balance for your account in dollars
	 */
	public int getAvailableBalance() {

		String returnString = null;
		int returnValue = -1;

		JSONObject request = new JSONObject();

		try {
			request.put("username", userName);
			request.put("password", password);
		} catch (JSONException joe) {
		}

		returnString = submitToServer(request.toString(), baseURL + "/GetAvailableBalance");

		try {

			JSONObject responseObject = new JSONObject(returnString);
			// check the content of the "responseType" for "SUCCESS"
			String responseType = responseObject.getString("responseType");

			if (responseType.equals(SUCCESS)) {
				JSONObject response = responseObject.getJSONObject("response");

				returnValue = response.getInt("availableBalance") / 100;
			}

		} catch (JSONException joe) {
			joe.printStackTrace();
		}
		
		return  returnValue;
	}

	/**
	 * Submits the JSON payload in String format to the server and returns the result. 
	 * Note this method does not do any JSON encoding or decoding
	 *
	 * @param payload The string-ified submission payload
	 * @return the string-ified response
	 */
	private String submitToServer(String payload, String endPoint) {

		String returnString = null;
		URL url = null;
		HttpsURLConnection connection = null;

		try {
			url = new URL(endPoint);
		} catch(MalformedURLException mue) {
			mue.printStackTrace();
			return null;
		}

		try {
			// connect to the server over HTTPS and submit the payload
			connection = (HttpsURLConnection)url.openConnection();
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-length", String.valueOf(payload.length()));
			connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");

			// open up the output stream of the connection
			DataOutputStream output = new DataOutputStream( connection.getOutputStream() );
			output.write(payload.getBytes());

			// now read the input stream until it is closed, line by line adding to the response
			InputStream inputstream = connection.getInputStream();
			InputStreamReader inputstreamreader = new InputStreamReader(inputstream);
			BufferedReader bufferedreader = new BufferedReader(inputstreamreader);
			StringBuffer response = new StringBuffer();
			String line = null;
			while ((line = bufferedreader.readLine()) != null) {
				response.append(line);
			}

			returnString = response.toString();
			
		} catch(IOException ioe) {
			ioe.printStackTrace();
			return null;
		}

		return returnString;
	}
	
}
