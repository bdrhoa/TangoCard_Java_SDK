TangoCard Java SDK
=================

# Overview #
The Tango Card Java SDK is a wrapper around the Tango Card Extend API. As such, it has two primary types of objects: Requests and Responses.

# Tango Card Service Requests #

The Tango Card SDK, every Request has a corresponding success-case Response object. 

![Tango Card SDK Requests](https://github.com/tangocarddev/TangoCard_Java_SDK/raw/dev/doc/images/tangocard_sdk_service_success_response.png "Tango Card SDK Requests")

## Get Available Balance ##

This request is defined by `class TangoCard\Sdk\Request\GetAvailableBalanceRequest`
```java
	// set up the request
	GetAvailableBalanceRequest requestAvailableBalance 
		= new GetAvailableBalanceRequest( 
				is_production_mode,
				username, 
				password
				);
	
	// make the request
	GetAvailableBalanceResponse responseAvailableBalance = new GetAvailableBalanceResponse();
	if ( requestAvailableBalance.execute(responseAvailableBalance) && (null != responseAvailableBalance) )
	{
		System.out.println("\nSuccess - GetAvailableBalance - Initial");
		int tango_cents_available_balance = responseAvailableBalance.getAvailableBalance();
		double currencyAmount = tango_cents_available_balance/100;
		
		Locale enUSLocale =
			new Locale.Builder().setLanguage("en").setRegion("US").build();

		NumberFormat currencyFormatter = 
			NumberFormat.getCurrencyInstance(enUSLocale);
		System.out.println("\tI have an available balance of " + currencyFormatter.format(currencyAmount) + " dollars.");		
	}
```

Its response `$responseAvailableBalance` will now be (assuming success) a `TangoCard\Sdk\Response\Success\GetAvailableBalanceResponse` type object.

### `GetAvailableBalanceRequest` Constructor Parameters ###

<dl>
  <dt>boolean is_production_mode</dt>
  <dd>- True for accessing Tango Card Production API service, and false for accessing Tango Card Integration API service</dd>
  <dt>string username</dt>
  <dd>- User email address, and the SDK Integration test username is defined in application configuration file _app_config.properties_ within *app_username*</dd>
  <dt>string password</dt>
  <dd>- User password, and the SDK Integration test password is defined in application configuration file _app_config.properties_ within *app_password*</dd>
</dl>

## Purchase Tango Card ##

This request is defined by `class TangoCard\Sdk\Request\PurchaseCardRequest`
```java
	int cardValueTangoCardCents = 100; // $1.00 dollars

	// set up the request
	PurchaseCardRequest requestPurchaseCardRequest_Delivery = new PurchaseCardRequest(
			is_production_mode,				// isProductionMode
			username, 						// username
			password,						// password
			card_sku,              			// cardSku
			cardValueTangoCardCents,       	// cardValue
			true,                      		// tcSend 
			"Sally Test Recipient",         // recipientName
			"sue_test_recipient@test.com",  // recipientEmail
			"Happy Birthday",               // giftMessage
			"Bob Test Giver"                // giftFrom  
	);

	// make the request
	PurchaseCardResponse responsePurchaseCard_Delivery = new PurchaseCardResponse();
	if ( requestPurchaseCardRequest_Delivery.execute(responsePurchaseCard_Delivery) && (null != responsePurchaseCard_Delivery))
	{
		System.out.println( "\nSuccess - PurchaseCard - Delivery\n" );
		System.out.println( "\tReference Order ID: "  + responsePurchaseCard_Delivery.getReferenceOrderId() + "");
		System.out.println( "\tCard Token:         "  + responsePurchaseCard_Delivery.getCardToken() + "");
		System.out.println( "\tCard Number:        "  + responsePurchaseCard_Delivery.getCardNumber() + "");
		System.out.println( "\tCard Pin:           "  + responsePurchaseCard_Delivery.getCardPin() + "");
	}
```

Its response `$requestPurchaseCardRequest_Delivery` will now be (assuming success) a `TangoCard\Sdk\Response\Success\PurchaseCardResponse` type object.

### `PurchaseCardRequest` Constructor Parameters ###

<dl>
  <dt>boolean is_production_mode</dt>
  <dd>- Selecting which Tango Card Service to make requests. Set to true for accessing Tango Card Production API service, and false for accessing Tango Card Integration API service</dd>
  <dt>string username</dt>
  <dd>- User email address, and a SDK Integration test username is defined in application configuration file _app_config.properties_ within *app_username*</dd>
  <dt>string password</dt>
  <dd>- User password, and a SDK Integration test password is defined in application configuration file _app_config.properties_ within *app_password*</dd>
  <dt>string cardSku</dt>
  <dd>- Card brand request, and the Tango Card brand's card sku *tango-card* is defined in application configuration file _app_config.properties_ within *app_card_sku*</dd>
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
</dl>

# Tango Card Error Handling #

There are also failure-case response objects. Each Request will explain (in the documentation) what type of possible failure-case response objects can be expected.

![Tango Card SDK Exceptions](https://github.com/tangocarddev/TangoCard_Java_SDK/raw/dev/doc/images/tangocard_sdk_exceptions.png "Tango Card SDK Exceptions")

## Service Failure Responses ##

A service will return the following failure responses as enumerated by `TangoCard\Sdk\Response\ServiceResponseEnum`:

<table>
	<tr><th>Failure</th><th>Reponse Type</th><th>Response</th></tr>
	<tr><td>Insufficient Funds</td><td>INS_FUNDS</td><td>`TangoCard\Sdk\Response\Failure\InsufficientFundsResponse`</td></tr>
	<tr><td>Insufficient Inventory</td><td>INS_INV</td><td>`TangoCard\Sdk\Response\Failure\InsufficientInventoryResponse`</td></tr> 
	<tr><td>Invalid Credentials</td><td>INV_CREDENTIAL</td><td>`TangoCard\Sdk\Response\Failure\InvalidCredentialsResponse`</td></tr> 
	<tr><td>Invalid Input</td><td>INV_INPUT</td><td>`TangoCard\Sdk\Response\Failure\InvalidInputResponse`</td></tr>
	<tr><td>System Failure</td><td>SYS_ERROR</td><td>`TangoCard\Sdk\Response\Failure\SystemFailureResponse`</td></tr>
</table>

![Tango Card SDK Service Response Failures](https://github.com/tangocarddev/TangoCard_Java_SDK/raw/dev/doc/images/tangocard_sdk_service_failure_response.png "Tango Card SDK Service Response Failures")

The details of these service failure responses are embedded and thrown within `TangoCard\Sdk\ServiceTangoCardServiceException`

## SDK Error Responses ##

Along with standard `InvalidArgumentException` for catching parameter entry errors, the SDK throws it own exception when detecting errors that pertain to itself `TangoCard\Sdk\Common\TangoCardSdkException`.

![Tango Card SDK Error Detection](https://github.com/tangocarddev/TangoCard_Java_SDK/raw/dev/doc/images/tangocard_sdk_sdk_error_detected.png "Tango Card SDK Error Detection")

## Handling Errors ##

Wrap every Tango Card request call within a try/catch block, followed by first catching `TangoCard\Sdk\Service\TangoCardServiceException`, then by `\TangoCard\Sdk\Common\TangoCardSdkException`, and finally by standard `Exception`.
```java
	try
	{
		// set up the request
		GetAvailableBalanceRequest request = new GetAvailableBalanceRequest
		(
			is_production_mode,
			username,
			password
		);
		
		// make the request
		GetAvailableBalanceResponse response = new GetAvailableBalanceResponse();
		if (request.execute(response) && (null != response))
		{
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

# SDK Structure #
There are four directories in the SDK: `doc`, `examples`, `unittests`, `lib`, and `src`.

## Java IDE ##

This Java SDK project was built using:
* **eclipse Ganymede**
* jdk1.6.0_27 (32 bit)
* jre7 (32 bit) and jre6 (32 bit) 
* JAutodoc

## config ##

There a several configuration files that are referenced by either the provide application examples, unittests, and SDK itself.

<dl>
	<dt>config\app_config.ini</dt>
	<dd>- Application configuration file for `\examples` and `\unittests`</dd>
	<dt>lib\config\tc_sdk_config.ini</dt>
	<dd>- SDK configuration file referenced by `TangoCard\Sdk\Common\SdkConfig.php`. **DO NOT MODIFY**</dd>
</dl>

## doc ##
The docs sub-directory maintains the up-to-date (javadoc) documentation for the classes (and functions) that are included in the SDK.

## examples ##
The examples sub-directory contains full "start to finish" examples of all of the supported methods. This includes catching all of the possible failure modes, etc. 
	
### TangoCard_Store_Example.java ###

This is a complete example of requesting available balance and purchasing Tango Cards.

1. Request latest available balance
2. Purchase $1.00 Tango Card for Email Delivery
3. Purchase $1.00 Tango Card without Email Delivery
4. Request updated available balance

#### Command Line ####

This example is intended to be run from the command line:

    $ javac -d . -cp ".;TangoCard_Java_SDK.jar;lib\org.json-20120521.jar;" examples\TangoCard_Store_Example.java

	$ java -cp ".;TangoCard_Java_SDK.jar;lib\org.json-20120521.jar;" TangoCard_Store_Example

### TangoCard_Failures_Example.java ###

Example of how the SDK handles various failure responses, such as:
* Insufficient Funds
* Invalid Credentials
* Invalid Input

#### Command Line ####

This example is intended to be run from the command line:

    $ javac -d . -cp ".;TangoCard_Java_SDK.jar;lib\org.json-20120521.jar;" examples\TangoCard_Store_Example.java

	$ java -cp ".;TangoCard_Java_SDK.jar;lib\org.json-20120521.jar;" TangoCard_Store_Example

## unittests ##

The SDK's unittests have been written to use [JavaUnit](http://www.phpunit.de).

* `UnitTest_GetAvailableBalance`
* `UnitTest_PurchaseCard`

### Running JUnit Test ###

These unit tests are executable from IDE eclipse Ganymede by righ-click selecting a UnitTest_ file, then *Debug As... > JUnit Test*

## lib ##
The Tango Card Java SDK has one dependency for JSON Library [org.json-20120521.jar], which is included.

## src ##
This is the heart of the SDK... the src sub-directory is where all of the code lies. 

# Requirements #
[Java Development Kit 1.6+]
[Java Runtime Environment 6 / 7]

# License #
The Tango Card Java SDK is free to use, given some restrictions. Please see the LICENSE file for details.

# Integration #
When you're ready to go live, email [sales@tangocard.com](mailto:sales@tangocard.com). We'll get you set up with a contract and everything else you need, including linking your account so that transactions served via your integration will draw down on your Tango Card account. 
