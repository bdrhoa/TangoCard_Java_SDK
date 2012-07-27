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
				app_username, 
				app_password
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

## Purchase Tango Card ##

This request is defined by `class TangoCard\Sdk\Request\PurchaseCardRequest`
```java
	int cardValueTangoCardCents = 100; // $1.00 dollars

	// set up the request
	PurchaseCardRequest requestPurchaseCardRequest_Delivery = new PurchaseCardRequest(
			is_production_mode,
			app_username, 
			app_password,
			app_card_sku,              		// cardSku
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

# Tango Card Error Handling #

There are also failure-case response objects. Each Request will explain (in the documentation) what type of possible failure-case response objects can be expected.

![Tango Card SDK Exceptions](https://github.com/tangocarddev/TangoCard_Java_SDK/raw/dev/doc/images/tangocard_sdk_exceptions.png "Tango Card SDK Exceptions")

## Service Failure Responses ##

A service will return the following failure responses as enumerated by `TangoCard\Sdk\Response\ServiceResponseEnum`:

<table>
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

## doc ##
The docs sub-directory maintains the up-to-date (javadoc) documentation for the classes (and functions) that are included in the SDK.

## examples ##
The examples sub-directory contains full "start to finish" examples of all of the supported methods. This includes catching all of the possible failure modes, etc. 

The examples are intended to be run from the command line, like:

    $ php examples/TangoCard_Store_Example.php
	
### TangoCard_Store_Example.php ###

This is a complete example of requesting available balance and purchasing Tango Cards.

1. Request latest available balance
2. Purchase $1.00 Tango Card for Email Delivery
3. Purchase $1.00 Tango Card without Email Delivery
4. Request updated available balance

### TangoCard_Failures_Example.php ###

Example of how the SDK handles various failure responses, such as:
* Insufficient Funds
* Invalid Credentials
* Invalid Input

## unittests ##

The SDK's unittests have been written to use [JavaUnit](http://www.phpunit.de).

* `UnitTest_GetAvailableBalance`
* `UnitTest_PurchaseCard`

## lib ##
The Tango Card Java SDK has one dependency for JSON Library [org.json-20120521.jar], which is included.

## src ##
This is the heart of the SDK... the src sub-directory is where all of the code lies. 

# Requirements #

# License #
The Tango Card Java SDK is free to use, given some restrictions. Please see the LICENSE file for details.

# Integration #
When you're ready to go live, email [sales@tangocard.com](mailto:sales@tangocard.com). We'll get you set up with a contract and everything else you need, including linking your account so that transactions served via your integration will draw down on your Tango Card account. 
