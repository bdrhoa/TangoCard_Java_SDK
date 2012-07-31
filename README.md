TangoCard Java SDK
=================

# Overview #
The Tango Card Java SDK is a wrapper around the Tango Card Service API environments. As such, it has two primary types of objects, Requests and Responses; which are handled by a wrapper class `tangocard.sdk.TangoCardServiceApi`.

The wrapper class `tangocard.sdk.TangoCardServiceApi` currently handles the following static methods:
<dl>
	<dt>bool GetAvailableBalance()</dt>
	<dd>- Gather the currently available balance for provided user within their www.tangocard.com account.</dd>

	<dt>bool PurchaseCard()</dt>
	<dd>- Purchase a gift card using funds from user's www.tangocard.com account.</dd>
</dl>

![Tango Card Service Api](https://github.com/tangocarddev/TangoCard_Java_SDK/raw/dev/doc/images/tangocardserviceapi.png "Tango Card Service Api")

# Tango Card Service Requests #

The Tango Card SDK, every Request has a corresponding success-case Response object.

## Tango Card Service API ##

Available are two endpoints that provide the Tango Card Service API, as defined by `enum tangocard.sdk.service.TangoCardServiceApiEnum` :
* `INTEGRATION` - Expected to be used for development and testing purposes.
* `PRODUCTION` - Performs actual card purchase requests.

Requests are secure HTTP POST using SSL.

## Get Available Balance ##

![Tango Card Service API - GetAvailableBalance()](https://github.com/tangocarddev/TangoCard_Java_SDK/raw/dev/doc/images/tangocardserviceapi_getavailablebalance.png "Tango Card Service API - GetAvailableBalance()")

This request is defined by static method call `tangocard.sdk.TangoCardServiceApi.GetAvailableBalance()`:

```java
	TangoCardServiceApiEnum enumTangoCardServiceApi = TangoCardServiceApiEnum.INTEGRATION;
	
	GetAvailableBalanceResponse responseGetAvailableBalance = new GetAvailableBalanceResponse();
	if ( TangoCardServiceApi.GetAvailableBalance(
			enumTangoCardServiceApi, 
			username, 
			password, 
			responseGetAvailableBalance
			) 
			&& (null != responseGetAvailableBalance) 
	) {
	{
		System.out.println("\nSuccess - GetAvailableBalance - Initial");
		int tango_cents_available_balance = responseGetAvailableBalance.getAvailableBalance();
		double currencyAmount = tango_cents_available_balance/100;
		
		Locale enUSLocale =
			new Locale.Builder().setLanguage("en").setRegion("US").build();

		NumberFormat currencyFormatter = 
			NumberFormat.getCurrencyInstance(enUSLocale);
		System.out.println("\tI have an available balance of " + currencyFormatter.format(currencyAmount) + " dollars.");		
	}
```

Its response `$responseGetAvailableBalance` will now be (assuming success) a `tangocard.sdk.response.success.GetAvailableBalanceResponse` type object.

### `tangocard.sdk.TangoCardServiceApi.GetAvailableBalance()` Method ###

#### Parameters ####
<dl>
  <dt>TangoCardServiceApiEnum enumTangoCardServiceApi</dt>
  <dd>- INTEGRATION and PRODUCTION</dd>
  
  <dt>string username</dt>
  <dd>- User email address, and the SDK Integration test username is defined in application configuration file *app_config.properties* within *app_username*</dd>

  <dt>string password</dt>
  <dd>- User password, and the SDK Integration test password is defined in application configuration file *app_config.properties* within *app_password*</dd>

  <dt>tangocard.sdk.response.success.GetAvailableBalanceResponse response</dt>
  <dd>- This out paramter will provide a valid success response object if this method returns true upon success.</dd>
</dl>

### `tangocard.sdk.response.success.GetAvailableBalanceResponse` Properties ###

<dl>
  <dt>int getAvailableBalance</dt>
  <dd>- Returns available balance of username's account in cents: 100 is $1.00 dollar.</dd>
</dl>

## Purchase Card ##

![Tango Card Service API - PurchaseCard()](https://github.com/tangocarddev/TangoCard_Java_SDK/raw/dev/doc/images/tangocardserviceapi_purchasecard.png "Tango Card Service API - PurchaseCard()")

This request is defined by static method call `tangocard.sdk.TangoCardServiceApi.PurchaseCard()`:

```java
	int cardValueTangoCardCents = 100; // $1.00 dollars
	TangoCardServiceApiEnum enumTangoCardServiceApi = TangoCardServiceApiEnum.INTEGRATION;

	PurchaseCardResponse responsePurchaseCard_Delivery = new PurchaseCardResponse();
	if ( TangoCardServiceApi.PurchaseCard(
			enumTangoCardServiceApi,		// API environment
			username, 					// username
			password,					// password
			card_sku,					// cardSku
			cardValueTangoCardCents,		// cardValue
			true,							// tcSend 
			"Sally Example",				// recipientName
			"sally@example.com",			// recipientEmail
			"Happy Birthday",				// giftMessage
			"Bill Example",					// giftFrom  
			responsePurchaseCard_Delivery	// response 
			) 
			&& (null != responsePurchaseCard_Delivery)
	) {
	{
		System.out.println( "\nSuccess - PurchaseCard - Delivery\n" );
		System.out.println( "\tReference Order ID: "  + responsePurchaseCard_Delivery.getReferenceOrderId() + "");
		System.out.println( "\tCard Token:         "  + responsePurchaseCard_Delivery.getCardToken() + "");
		System.out.println( "\tCard Number:        "  + responsePurchaseCard_Delivery.getCardNumber() + "");
		System.out.println( "\tCard Pin:           "  + responsePurchaseCard_Delivery.getCardPin() + "");
	}
```

Its response `$requestPurchaseCardRequest_Delivery` will now be (assuming success) a `tangocard.sdk.response.success.PurchaseCardResponse` type object.

### `tangocard.sdk.TangoCardServiceApi.PurchaseCard()` Method ###

#### Parameters ###
<dl>
  <dt>TangoCardServiceApiEnum enumTangoCardServiceApi</dt>
  <dd>- INTEGRATION and PRODUCTION</dd>

  <dt>string username</dt>
  <dd>- User email address, and a SDK Integration test username is defined in application configuration file *app_config.properties* within *app_username*</dd>

  <dt>string password</dt>
  <dd>- User password, and a SDK Integration test password is defined in application configuration file *app_config.properties* within *app_password*</dd>

  <dt>string cardSku</dt>
  <dd>- Card brand request, and the Tango Card brand's card sku *tango-card* is defined in application configuration file *app_config.properties* within *app_card_sku*</dd>

  <dt>int cardValue</dt>
  <dd>- Card value in cents; a value of 100 (cent) is $1.00 dollar card. Minimum value is 1 (cent).</dd>

  <dt>boolean tcSend</dt>
  <dd>- Tango Card Service delivers by Email requested card. Set to true for email delivery, and false for no delivery.</dd>

  <dt>string recipientName</dt>
  <dd>- Full name of recipient receiving gift card. Set this value with either a string (length minumum 1 character to maximum of 255 characters) if `tcSend` is true, or null if parameter `tcSend` is false.</dd>

  <dt>string recipientEmail</dt>
  <dd>- Valid email address of recipient receiving gift card. Set this value with either a string (length minumum 1 character to maximum of 255 characters) if `tcSend` is true, or null if parameter `tcSend` is false.</dd>

  <dt>string giftMessage</dt>
  <dd>- [Optional] Gift message to be applied to gift card's email. Set this value with either a string (length minumum 1 character to maximum of 255 characters) or null if `tcSend` is true, or null if parameter `tcSend` is false.</dd>

  <dt>string giftFrom</dt>
  <dd>- Full name of giver of gift card. Set this value with either a string (length minumum 1 character to maximum of 255 characters) if `tcSend` is true, or null if parameter `tcSend` is false.</dd>

  <dt>tangocard.sdk.response.success.PurchaseCardResponse response</dt>
  <dd>- This out paramter will provide a valid success response object if this method returns true upon success.</dd>
</dl>

### `tangocard.sdk.response.success.PurchaseCardResponse` Properties ###

<dl>
  <dt>String getReferenceOrderId</dt>
  <dd>- Confirmation number of purchase.</dd>
  
  <dt>String getCardToken</dt>
  <dd>- Card reference to the aforementioned purchase.</dd>
  
  <dt>String getCardNumber</dt>
  <dd>- Card number provided to the recipient to be used at redemption upon the www.tangocard.com site.</dd>
  
  <dt>String getCardPin</dt>
  <dd>- Card pin provided to the recipient used to validate provided Card number a redemption upon the www.tangocard.com site.</dd>
</dl>

# Tango Card Error Handling #

The Tango Card Service API SDK handles its errors by throwing the following exceptions:

* Custom `tangocard.sdk.service.TangoCardServiceException` is thrown when the `Tango Card Service API` return a `Failure Response` for a given `Request`.
* Custom `tangocard.sdk.common.TangoCardSdkException` is thrown when the Tango Card SDK has detected an error within its code, regardless of any given Request.
* Standard `java.lang.IllegalArgumentException` is thrown due to parameter entry errors.

![Tango Card SDK Exceptions](https://github.com/tangocarddev/TangoCard_Java_SDK/raw/dev/doc/images/tangocard_sdk_exceptions.png "Tango Card SDK Exceptions")

## Service Failure Responses ##

A service will return the following failure responses as enumerated by `tangocard.sdk.response.ServiceResponseEnum`:

<table>
	<tr><th>Failure</th><th>Failure Reponse Type</th><th>Failure Response Object</th></tr>
	<tr><td>Insufficient Funds</td><td>INS_FUNDS</td><td>`tangocard.sdk.response.failure.InsufficientFundsResponse`</td></tr>
	<tr><td>Insufficient Inventory</td><td>INS_INV</td><td>`tangocard.sdk.response.failure.InsufficientInventoryResponse`</td></tr> 
	<tr><td>Invalid Credentials</td><td>INV_CREDENTIAL</td><td>`tangocard.sdk.response.failure.InvalidCredentialsResponse`</td></tr> 
	<tr><td>Invalid Input</td><td>INV_INPUT</td><td>`tangocard.sdk.response.failure.InvalidInputResponse`</td></tr>
	<tr><td>System Failure</td><td>SYS_ERROR</td><td>`tangocard.sdk.response.failure.SystemFailureResponse`</td></tr>
</table>

Each of the aforementioned `Failure Responses` contains details as to the reason that the `Tango Card Service API` did not perform provided `Request`.

![Tango Card SDK Service Response Failures](https://github.com/tangocarddev/TangoCard_Java_SDK/raw/dev/doc/images/tangocard_sdk_service_failure_response.png "Tango Card SDK Service Response Failures")

The details of these service failure responses are embedded and thrown within `tangocard.sdk.service.TangoCardServiceException`

### Expected Failure Responses for Specific Requests ###

Each Request will have the following possible Failure Responses as a property value within `tangocard.sdk.service.TangoCardServiceException.getResponse()`:

<table>
	<tr><th>Request</th><th>Possible Failure Responses</th></tr>
	<tr>
		<td>`GetAvailableBalanceRequest`</td>
		<td>
			<table>
				<tr><th>Failure Reponse Type</th><th>Failure Response</th></tr>
				<tr><td>INV_CREDENTIAL</td><td>`InvalidCredentialsResponse`</td></tr> 
				<tr><td>SYS_ERROR</td><td>`SystemFailureResponse`</td></tr>
			</table>
		</td>
	</tr>
	<tr>
		<td>`PurchaseCardRequest`</td>
		<td>
			<table>
				<tr><th>Failure Reponse Type</th><th>Failure Response</th></tr>
				<tr><td>INS_FUNDS</td><td>`InsufficientFundsResponse`</td></tr>
				<tr><td>INS_INV</td><td>`InsufficientInventoryResponse`</td></tr> 
				<tr><td>INV_CREDENTIAL</td><td>`InvalidCredentialsResponse`</td></tr> 
				<tr><td>INV_INPUT</td><td>`InvalidInputResponse`</td></tr>
				<tr><td>SYS_ERROR</td><td>`SystemFailureResponse`</td></tr>
			</table>
		</td>
	</tr>
</table>

## SDK Error Responses ##

This SDK throws it own custom exception `tangocard.sdk.common.TangoCardSdkException` when detecting errors that pertain to itself.

![Tango Card SDK Error Detection](https://github.com/tangocarddev/TangoCard_Java_SDK/raw/dev/doc/images/tangocard_sdk_error_detected.png "Tango Card SDK Error Detection")

## Handling Errors ##

Wrap every Tango Card request call within a try/catch block, followed by first catching `tangocard.sdk.service.TangoCardServiceException`, then by `tangocard.sdk.common.TangoCardSdkException`, and finally by standard `Exception`.
```java
	try
	{
		TangoCardServiceApiEnum enumTangoCardServiceApi = TangoCardServiceApiEnum.INTEGRATION;
		
		GetAvailableBalanceResponse responseGetAvailableBalance = new GetAvailableBalanceResponse();
		if ( TangoCardServiceApi.GetAvailableBalance(
				enumTangoCardServiceApi, 
				username, 
				password, 
				responseGetAvailableBalance
				) 
				&& (null != responseGetAvailableBalance) 
		) {
            // Do Stuff ... //
		}
	}
	catch (TangoCardServiceException ex)
	{           
		System.out.println("=== Tango Card Service Failure ===");
		System.out.println( String.format("Failure response type: %s", ex.getResponseType()) );
		System.out.println( String.format("Failure response:      %s", ex.getMessage()));
		
	}
	catch (TangoCardSdkException ex)
	{
		
		System.out.println("=== Tango Card SDK Failure ===");
		System.out.println( String.format("%s :: %s", ex.getClass().toString(), ex.getMessage()));
		
	}
	catch (Exception ex)
	{
		
		System.out.println("=== Unexpected Error ===");
		System.out.println( String.format("%s :: %s", ex.getClass().toString(), ex.getMessage()));            
	}
```

# Java SDK Development Environment #

This Java SDK project was built using:

* [eclipse Ganymede](http://www.eclipse.org/ganymede/)
* jdk1.6.0_27 (32 bit)
* jre7 (32 bit) and jre6 (32 bit) 
* [JAutodoc](http://jautodoc.sourceforge.net/)
* [JUnit](http://www.junit.org/)

# SDK Structure #
There are four directories in the SDK: `doc`, `examples`, `unittests`, `lib`, `src`, and key `configuration files`.

## configuration files ##

There a several configuration files that are referenced by either the provide application examples, unittests, and SDK itself.

<dl>
	<dt>app_config.properties</dt>
	<dd>- Application configuration file for `\examples` and `\unittests`</dd>
	
	<dt>tc_sdk_config.properties</dt>
	<dd>- SDK configuration file referenced by `tangocard\sdk\common\SdkConfig.java`. <b>**DO NOT MODIFY**</b></dd>
</dl>

## doc ##
The docs sub-directory maintains the up-to-date [javadoc](http://www.oracle.com/technetwork/java/javase/documentation/index-jsp-135444.html) documentation for the classes (and functions) that are included in the SDK.

## examples ##
The examples sub-directory contains full "start to finish" examples of all of the supported methods. This includes catching all of the possible failure modes, etc. 

### TangoCard_Store_Example.java ###

This is a complete example of requesting available balance and purchasing Tango Cards.

1. Request latest available balance
2. Purchase $1.00 Tango Card for Email Delivery
3. Purchase $1.00 Tango Card without Email Delivery
4. Request updated available balance

#### Command Line ####

This example is intended to be run from the command line  (<b>NOTE: Set `<version>` to the current TangoCard_Java_SDK jar label.):

```Text
    $ javac -d . -cp ".;TangoCard_Java_SDK-<version>.jar;lib\org.json-20120521.jar;" examples\TangoCard_Store_Example.java

	$ java -cp ".;TangoCard_Java_SDK-<version>.jar;lib\org.json-20120521.jar;" TangoCard_Store_Example
```

#### Example Command Line Run ####

```Text
	===============================
	= Tango Card Java SDK Example =
	=   for simple store front    =
	===============================

	Success - GetAvailableBalance - Initial
			I have an available balance of $8,755,654.00 dollars.

	Success - PurchaseCard - Delivery

			Reference Order ID: 112-07212466-28
			Card Token:         501385bb04f608.38922035
			Card Number:        7001-8040-0176-7850-913
			Card Pin:           368525

	Success - PurchaseCard - No Delivery

			Reference Order ID: 112-07212467-28
			Card Token:         501385bb84a0f7.11849672
			Card Number:        7001-5040-0168-6066-118
			Card Pin:           701172

	Success - GetAvailableBalance - Concluding
			I have an available balance of $8,755,652.00 dollars.
			
	===============================
	=   The End                   =
	===============================
```

### TangoCard_Failures_Example.java ###

Example of how the SDK handles various failure responses, such as:
* Insufficient Funds
* Invalid Credentials
* Invalid Input

#### Command Line ####

This example is intended to be run from the command line (<b>NOTE: set `<version>` to the current TangoCard_Java_SDK jar label.):

    > javac -d . -cp ".;TangoCard_Java_SDK-<version>.jar;lib\org.json-20120521.jar;" examples\TangoCard_Failures_Example.java

	> java -cp ".;TangoCard_Java_SDK-<version>.jar;lib\org.json-20120521.jar;" TangoCard_Failures_Example

#### Example Command Line Run ####

```Text
	===============================
	= Tango Card Java SDK Example =
	=   with Failures             =
	===============================
	
	======== Get Available Balance ========
	=== Tango Card Service Failure ===
	Failure response type: INV_CREDENTIAL
	Failure response:      Provided user credentials are not valid.
	===== End Get Available Balance ====

	======== Purchase Card ========
	=== Tango Card Service Failure ===
	Failure response type: INS_FUNDS
	Failure response:      Available Balance: 0, Order Cost: 100
	AvailableBalance:      0
	OrderCost:             100
	===== End Get Available Balance ====

	===============================
	=   The End                   =
	===============================
```

## unittests ##

The SDK's unittests have been written to use [JUnit](http://www.junit.org/).

* `UnitTest_GetAvailableBalance.java`
* `UnitTest_PurchaseCard.java`

### Running JUnit Tests ###

This SDK sources contains a JUnit jar downloaded from [junit.org downloads](https://github.com/KentBeck/junit/downloads): `\lib\junit-4.10.jar`.

#### JUnit UnitTest_GetAvailableBalance.java ####

This junit test is intended to be run from the command line (<b>NOTE: set `<version>` to the current TangoCard_Java_SDK jar label.):

```Text
	> javac -d . -cp ".;TangoCard_Java_SDK-<version>.jar;lib\org.json-20120521.jar;lib\junit-4.10.jar;" unittests\UnitTest_GetAvailableBalance.java

	> java -cp ".;TangoCard_Java_SDK-<version>.jar;lib\org.json-20120521.jar;lib\junit-4.10.jar;" org.junit.runner.JUnitCore UnitTest_GetAvailableBalance
		JUnit version 4.10
		..
		Time: 1.466

		OK (2 tests)
```

#### JUnit UnitTest_PurchaseCard.java ####

This junit test is intended to be run from the command line (<b>NOTE: set `<version>` to the current TangoCard_Java_SDK jar label.):

```Text
	> javac -d . -cp ".;TangoCard_Java_SDK-<version>.jar;lib\org.json-20120521.jar;lib\junit-4.10.jar;" unittests\UnitTest_PurchaseCard.java

	> java -cp ".;TangoCard_Java_SDK-<version>.jar;lib\org.json-20120521.jar;lib\junit-4.10.jar;" org.junit.runner.JUnitCore UnitTest_PurchaseCard
		JUnit version 4.10
		.....
		Time: 2.492

		OK (5 tests)
```

## lib ##
The Tango Card Java SDK has one dependency for JSON Library [org.json-20120521.jar](http://code.google.com/p/org-json-java/downloads/detail?name=org.json-20120521.jar&can=2&q=), which is included.

## src ##
This is the heart of the SDK... the src sub-directory is where all of the code lies. 

# Requirements #
* [Java Development Kit 1.6+](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
* [Java Runtime Environment 6 / 7](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
* [JSON in Java](http://www.json.org/java/) - *org.json-20120521.jar*

# License #
The Tango Card Java SDK is free to use, given some restrictions. Please see the LICENSE file for details.

# Integration #
When you're ready to go live, email [sales@tangocard.com](mailto:sales@tangocard.com). We'll get you set up with a contract and everything else you need, including linking your account so that transactions served via your integration will draw down on your Tango Card account. 
