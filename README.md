TangoCard Java SDK
=================

# Table of Contents #
<ul>
    <li><a href="#introduction">Introduction</a>
        <ul>
            <li><a href="#Incorporate_tango_card">Incorporate Tango Card Gift Cards</a></li>
            <li><a href="#open_account">Open Tango Card Account</a></li>
            <li><a href="#sdk_support">SDK Support</a></li>
        </ul>
    </li>
    <li><a href="#sdk_overview">SDK Overview</a></li>
    <li><a href="#sdk_requirements">SDK Requirements</a></li>
    <li><a href="#tango_card_service_requests">Tango Card Service Requests</a>
        <ul>
            <li><a href="#tango_card_service_api_endpoints">Tango Card Service API Endpoints</a></li>
            <li><a href="#get_available_balance">Get Available Balance</a></li>
            <li><a href="#purchase_card">Purchase Card</a></li>
        </ul>
    </li>
    <li><a href="#tango_card_error_handling">Tango Card Error Handling</a>
        <ul>
            <li><a href="#service_failure_responses">Service Failure Responses</a></li>
            <li><a href="#sdk_error_responses">SDK Error Responses</a></li>
            <li><a href="#handling_errors">Handling Errors</a></li>
        </ul>
    </li>
    <li><a href="#sdk_contents">SDK Contents</a>
        <ul>
            <li><a href="#src">src</a></li>
            <li><a href="#lib">lib</a></li>
            <li><a href="#configuration_files">configuration files</a></li>
            <li><a href="#doc">doc</a></li>
            <li><a href="#examples">examples</a></li>        
            <li><a href="#unittests">unittests</a></li>
        </ul>
    </li>
    <li><a href="#sdk_development_environment">SDK Development Environment</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#production_deployment">Production Deployment</a></li>
</ul>

<a name="introduction"></a>
# Introduction #

<a name="Incorporate_tango_card"></a>
## Incorporate Tango Card Gift Cards  ##
Tango Card’s Extend SDKs allow you to quickly incorporate the innovative Tango Card gift card into your reward, loyalty, and engagement applications. Tango Card is the “exactly what you want” gift card and allows the recipient to use their value exactly how they want – they can select a premier gift card, they can divide their value among Brands, they can use some today and save the rest for another day. They can also donate to a non-profit organization. Tango Card value can be used via the web or from almost any mobile device. There are no fees or expiration dates of any kind. It’s great for the recipient, and even better for you because it is an entire gift card program delivered in one card allowing you to focus on your core business. Tango Card solutions are already used by Microsoft Bing, FedEx, Extole, Plink, beintoo, Lead Valu, Getty Images, and many others. 

<a name="open_account"></a>
## Open Tango Card Account ##
Within minutes of download, our Extend SDKs will allow you to check the balance on your pre-funded Tango Card account, send Tango Card gift cards directly to recipients via email, and return live gift card codes for you to customize and redistribute. With Tango Card and Retailer Brand approval, there is also the ability to order retailer Brand gift cards via the SDK. Simply use the supplied credentials to see how easy it is. When you’re ready to move into production, sign up for an account at https://www.tangocard.com/user/register. Use these credentials in your SDK and you’re done!

<a name="sdk_support"></a>
## SDK Support ##
If you have any questions, please contact us at sdk@tangocard.com.

<a name="sdk_overview"></a>
# SDK Overview #

The Tango Card Java SDK is a wrapper around the Tango Card Service API environments. As such, it has two primary types of objects, Requests and Responses; which are handled by a wrapper class `tangocard.sdk.TangoCardServiceApi`.

The wrapper class `tangocard.sdk.TangoCardServiceApi` currently handles the following static methods:
<dl>
    <dt>bool GetAvailableBalance()</dt>
    <dd>- Gather the currently available balance for provided user within their www.tangocard.com account.</dd>

    <dt>bool PurchaseCard()</dt>
    <dd>- Purchase a gift card using funds from user's www.tangocard.com account.</dd>
</dl>

![Tango Card Service Api](https://github.com/tangocarddev/TangoCard_Java_SDK/raw/dev/doc/images/tangocardserviceapi.png "Tango Card Service Api")

<a name="sdk_requirements"></a>
# SDK Requirements #

* [Java Development Kit 1.6+](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
* [Java Runtime Environment 6 / 7](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
* [JSON in Java](http://www.json.org/java/) - *org.json-20120521.jar*, included within `\lib\` folder of this SDK.
* [Apache ANT](http://ant.apache.org/)


<a name="tango_card_service_requests"></a>
# Tango Card Service Requests #

The Tango Card SDK, every Request has a corresponding success-case Response object.

<a name="tango_card_service_api_endpoints"></a>
## Tango Card Service API Endpoints ##

Available are two endpoints that provide the Tango Card Service API, as defined by `enum tangocard.sdk.service.TangoCardServiceApiEnum` :
* `INTEGRATION` - Expected to be used for development and testing purposes.
* `PRODUCTION` - Performs actual card purchase requests.

Requests are secure HTTP POST using SSL.

<a name="get_available_balance"></a>
## Get Available Balance ##

![Tango Card Service API - GetAvailableBalance()](https://github.com/tangocarddev/TangoCard_Java_SDK/raw/dev/doc/images/tangocardserviceapi_getavailablebalance.png "Tango Card Service API - GetAvailableBalance()")

This request is defined by static method call `tangocard.sdk.TangoCardServiceApi.GetAvailableBalance()`:

```java
    TangoCardServiceApiEnum enumTangoCardServiceApi = TangoCardServiceApiEnum.INTEGRATION;
    String username = "burt@example.com";
    String password = "password";
    
    GetAvailableBalanceResponse response = new GetAvailableBalanceResponse();
    if ( TangoCardServiceApi.GetAvailableBalance(
            enumTangoCardServiceApi, 
            username, 
            password, 
            response
            ) 
            && (null != response) 
    ) {
    {
        System.out.println("\nSuccess - GetAvailableBalance - Initial");
        int tango_cents_available_balance = response.getAvailableBalance();
        double currencyAmount = tango_cents_available_balance/100;
        
        Locale enUSLocale =
            new Locale.Builder().setLanguage("en").setRegion("US").build();

        NumberFormat currencyFormatter = 
            NumberFormat.getCurrencyInstance(enUSLocale);
        System.out.println("\tI have an available balance of " + currencyFormatter.format(currencyAmount) + " dollars.");        
    }
```

Assuming success, the `out` parameter `response` will be an instance of `tangocard.sdk.response.success.GetAvailableBalanceResponse`.

### Method tangocard.sdk.TangoCardServiceApi.GetAvailableBalance() ###

#### Parameters ####
<dl>
  <dt>TangoCardServiceApiEnum enumTangoCardServiceApi</dt>
  <dd>- <code>INTEGRATION</code> or <code>PRODUCTION</code></dd>
  
  <dt>string username</dt>
  <dd>- User email address, and the SDK Integration test username is defined within configuration file <code>app_config.properties</code> in setting <code>app_username</code></dd>

  <dt>string password</dt>
  <dd>- User password, and the SDK Integration test password is defined within configuration file <code>app_config.properties</code> in setting <code>app_password</code></dd>

  <dt>tangocard.sdk.response.success.GetAvailableBalanceResponse response</dt>
  <dd>- This <i>out</i> paramter will provide a valid success response object if this method returns true upon success.</dd>
</dl>

### `tangocard.sdk.response.success.GetAvailableBalanceResponse` Properties ###

<dl>
  <dt>int getAvailableBalance</dt>
  <dd>- Returns available balance of username's account in cents: 100 is $1.00 dollar.</dd>
</dl>

<a name="purchase_card"></a>
## Purchase Card ##

![Tango Card Service API - PurchaseCard()](https://github.com/tangocarddev/TangoCard_Java_SDK/raw/dev/doc/images/tangocardserviceapi_purchasecard.png "Tango Card Service API - PurchaseCard()")

This request is defined by static method call `tangocard.sdk.TangoCardServiceApi.PurchaseCard()`:

```java
    TangoCardServiceApiEnum enumTangoCardServiceApi = TangoCardServiceApiEnum.INTEGRATION;
    String username = "burt@example.com";
    String password = "password";
    String card_sku = "tango-card";
    int cardValueTangoCardCents = 100; // $1.00 dollars
    
    PurchaseCardResponse response = new PurchaseCardResponse();
    if ( TangoCardServiceApi.PurchaseCard(
            enumTangoCardServiceApi,            // API environment
            username,                           // username
            password,                           // password
            card_sku,                           // cardSku
            cardValueTangoCardCents,            // cardValue
            true,                               // tcSend 
            "Sally Example",                    // recipientName
            "sally@example.com",                // recipientEmail
            "Happy Birthday",                   // giftMessage (optional)
            "Bill Example",                     // giftFrom
            null,                               // companyIndentifier (optional)
            response    // response 
            ) 
            && (null != response)
    ) {
    {
        System.out.println( "\nSuccess - PurchaseCard - Delivery\n" );
        System.out.println( "\tReference Order ID: "  + response.getReferenceOrderId() + "");
        System.out.println( "\tCard Token:         "  + response.getCardToken() + "");
        System.out.println( "\tCard Number:        "  + response.getCardNumber() + "");
        System.out.println( "\tCard Pin:           "  + response.getCardPin() + "");
    }
```

Assuming success, the `out` parameter `response` will be an instance of `tangocard.sdk.response.success.PurchaseCardResponse`.

### Method tangocard.sdk.TangoCardServiceApi.PurchaseCard() ###

#### Parameters ###

<dl>
  <dt>TangoCardServiceApiEnum enumTangoCardServiceApi</dt>
  <dd>- <code>INTEGRATION</code> or <code>PRODUCTION</code></dd>

  <dt>string username</dt>
  <dd>- User email address, and a SDK Integration test username is defined within configuration file <code>config\app_config.ini</code> in setting <code>app_username</code></dd>

  <dt>string password</dt>
  <dd>- User password, and a SDK Integration test password is defined within configuration file <code>config\app_config.ini</code> in setting <code>app_password</code></dd>

  <dt>string cardSku</dt>
  <dd>- Card brand request, and the Tango Card brand's card sku *tango-card* is defined in configuration file <code>config\app_config.ini</code> within <code>app_card_sku</code></dd>

  <dt>int cardValue</dt>
  <dd>- Card value in cents; a value of 100 (cent) is $1.00 dollar card. Minimum value is 1 (cent).</dd>

  <dt>boolean tcSend</dt>
  <dd>- Tango Card Service delivers by Email requested card. Set to <code>true</code> for email delivery, and <code>false</code> for no delivery.</dd>

  <dt>string recipientName</dt>
  <dd>- Full name of recipient receiving gift card. Set this parameter with a value of either a string (length minimum 1 character to maximum of 255 characters) if <code>tcSend</code> is <code>true</code>, or <code>null</code> if parameter <code>tcSend</code> is <code>false</code>.</dd>

  <dt>string recipientEmail</dt>
  <dd>- Valid email address of recipient receiving gift card. Set this parameter with a value of either a string (length minimum 1 character to maximum of 255 characters) if <code>tcSend</code> is <code>true</code>, or <code>null</code> if parameter <code>tcSend</code> is <code>false</code>.</dd>

  <dt>string giftMessage</dt>
  <dd>- [Optional] Gift message to be applied to gift card's email. Set this optional parameter with a value of either a string (length minimum 1 character to maximum of 255 characters) or null if <code>tcSend</code> is <code>true</code>, or <code>null</code> if parameter <code>tcSend</code> is <code>false</code>.</dd>

  <dt>string giftFrom</dt>
  <dd>- Full name of giver of gift card. Set this parameter with a value of either a string (length minimum 1 character to maximum of 255 characters) if <code>tcSend</code> is <code>true</code>, or <code>null</code> if parameter <code>tcSend</code> is <code>false</code>.</dd>
  
  <dt>string companyIdentifier</dt>
  <dd>- The name of the parent company providing this gift. Set this optional parameter with a value of either a string (length minimum 1 character to maximum of 255 characters) if <code>tcSend</code> is <code>true</code>, or <code>null</code> if parameter <code>tcSend</code> is <code>false</code>.</dd>

  <dt>tangocard.sdk.response.success.PurchaseCardResponse response</dt>
  <dd>- This <i>out</i> paramter will provide a valid success response object if this method returns true upon success.</dd>
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

<a name="tango_card_error_handling"></a>
# Tango Card Error Handling #

The Tango Card Service API SDK handles its errors by throwing the following exceptions:

* Custom `tangocard.sdk.service.TangoCardServiceException` is thrown when the `Tango Card Service API` return a `Failure Response` for a given `Request`.
* Custom `tangocard.sdk.common.TangoCardSdkException` is thrown when the Tango Card SDK has detected an error within its code, regardless of any given Request.
* Standard `java.lang.IllegalArgumentException` is thrown due to parameter entry errors.

![Tango Card SDK Exceptions](https://github.com/tangocarddev/TangoCard_Java_SDK/raw/dev/doc/images/tangocard_sdk_exceptions.png "Tango Card SDK Exceptions")

<a name="service_failure_responses"></a>
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

<a name="sdk_error_responses"></a>
## SDK Error Responses ##

This SDK throws it own custom exception `tangocard.sdk.common.TangoCardSdkException` when detecting errors that pertain to itself.

![Tango Card SDK Error Detection](https://github.com/tangocarddev/TangoCard_Java_SDK/raw/dev/doc/images/tangocard_sdk_error_detected.png "Tango Card SDK Error Detection")

<a name="handling_errors"></a>
## Handling Errors ##

Wrap every Tango Card request call within a try/catch block, followed by first catching `tangocard.sdk.service.TangoCardServiceException`, then by `tangocard.sdk.common.TangoCardSdkException`, and finally by standard `Exception`.

```java
    try
    {
        TangoCardServiceApiEnum enumTangoCardServiceApi = TangoCardServiceApiEnum.INTEGRATION;
        String username = "burt@example.com";
        String password = "password";
        
        GetAvailableBalanceResponse response = new GetAvailableBalanceResponse();
        if ( TangoCardServiceApi.GetAvailableBalance(
                enumTangoCardServiceApi, 
                username, 
                password, 
                response
                ) 
                && (null != response) 
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

<a name="sdk_contents"></a>
# SDK Contents #
This section details the provided sources of this SDK.

<a name="src"></a>
## src ##
This is the heart of the SDK which contains the sources, and here is a listing of its directories:

* src\tangocard
* src\tangocard\sdk
* src\tangocard\sdk\common
* src\tangocard\sdk\request
* src\tangocard\sdk\response
* src\tangocard\sdk\response\failure
* src\tangocard\sdk\response\success
* src\tangocard\sdk\service

<a name="lib"></a>
## lib ##
The Tango Card Java SDK has one dependency for JSON Library [org.json-20120521.jar](http://code.google.com/p/org-json-java/downloads/detail?name=org.json-20120521.jar&can=2&q=), which is included.

This folder also contains JUnit Library [junit-4.10.jar](https://github.com/KentBeck/junit/downloads) in order to run unittests provides.

## build file ##

Provided is an [Apache ANT](http://ant.apache.org/) *build.xml* file, which was used to create the binary file *TangoCard_Java_SDK.jar*.

<a name="configuration_files"></a>
## configuration files ##

There a several configuration files that are referenced by either the provide application examples, unittests, and SDK itself.

<dl>
    <dt>app_config.properties</dt>
    <dd>- Application configuration file for `\examples` and `\unittests`</dd>
    
    <dt>src/tangocard/sdk/common/tc_sdk_config.properties</dt>
    <dd>- SDK configuration file referenced by `tangocard.sdk.common.SdkConfig.java`. <b>**DO NOT MODIFY**</b></dd>
</dl>

<a name="doc"></a>
## doc ##

The `doc\javadoc\index.html` accesses the up-to-date [javadoc](http://www.oracle.com/technetwork/java/javase/documentation/index-jsp-135444.html) generated documentation for the classes (and functions) that are included in the SDK.

<a name="examples"></a>
## examples ##

The `examples` sub-directory contains full "start to finish" examples of all of the supported methods. This includes catching all of the possible failure modes, etc. 

### TangoCard_Store_Example.java ###

This is a complete example of requesting available balance and purchasing Tango Cards.

1. Request latest available balance
2. Purchase $1.00 Tango Card for Email Delivery
3. Purchase $1.00 Tango Card without Email Delivery
4. Request updated available balance

#### Command Line ####

This example is intended to be run from the command line  (<b>NOTE: Set `<version>` to the current TangoCard_Java_SDK jar label.):

```Text
    > javac -d . -cp ".;TangoCard_Java_SDK.jar;lib\org.json-20120521.jar;" examples\TangoCard_Store_Example.java
    > java -cp ".;TangoCard_Java_SDK.jar;lib\org.json-20120521.jar;" TangoCard_Store_Example
```
or 

```Text
    > ant TangoCard_Store_Example
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

This example is intended to be run from the command line:

```Text
    > javac -d . -cp ".;TangoCard_Java_SDK.jar;lib\org.json-20120521.jar;" examples\TangoCard_Failures_Example.java
    > java -cp ".;TangoCard_Java_SDK.jar;lib\org.json-20120521.jar;" TangoCard_Failures_Example
```

or 

```Text
    > ant TangoCard_Failures_Example
```

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
<a name="unittests"></a>
## unittests ##

The SDK's unittests have been written to use [JUnit](http://www.junit.org/).

* `UnitTest_GetAvailableBalance.java`
* `UnitTest_PurchaseCard.java`

### Running JUnit Tests ###

This SDK sources contains a JUnit jar downloaded from [junit.org downloads](https://github.com/KentBeck/junit/downloads): `\lib\junit-4.10.jar`.

#### JUnit UnitTest_GetAvailableBalance.java ####

This junit test is intended to be run from the command line:

```Text
    > javac -d . -cp ".;TangoCard_Java_SDK.jar;lib\org.json-20120521.jar;lib\junit-4.10.jar;" unittests\UnitTest_GetAvailableBalance.java
    > java -cp ".;TangoCard_Java_SDK.jar;lib\org.json-20120521.jar;lib\junit-4.10.jar;" org.junit.runner.JUnitCore UnitTest_GetAvailableBalance
        JUnit version 4.10
        ..
        Time: 1.466

        OK (2 tests)
```

or 

```Text
    > ant UnitTest_GetAvailableBalance
    Buildfile: \TangoCard_Java_SDK\build.xml

    UnitTest_GetAvailableBalance:
        [junit] Running UnitTest_GetAvailableBalance
        [junit] Tests run: 2, Failures: 0, Errors: 0, Time elapsed: 3.43 sec    
```

#### JUnit UnitTest_PurchaseCard.java ####

This junit test is intended to be run from the command line:

```Text
    > javac -d . -cp ".;TangoCard_Java_SDK.jar;lib\org.json-20120521.jar;lib\junit-4.10.jar;" unittests\UnitTest_PurchaseCard.java

    > java -cp ".;TangoCard_Java_SDK.jar;lib\org.json-20120521.jar;lib\junit-4.10.jar;" org.junit.runner.JUnitCore UnitTest_PurchaseCard
        JUnit version 4.10
        .....
        Time: 2.492

        OK (5 tests)
```

or 

```Text
    > ant UnitTest_PurchaseCard
    Buildfile: \TangoCard_Java_SDK\build.xml

    UnitTest_PurchaseCard:
        [mkdir] \TangoCard_Java_SDK\junit
        [junit] Running UnitTest_PurchaseCard
        [junit] Tests run: 5, Failures: 0, Errors: 0, Time elapsed: 2.932 sec    
```



<a name="sdk_development_environment"></a>
# SDK Development Environment #

This Java SDK was built using:

* [eclipse Ganymede](http://www.eclipse.org/ganymede/)
* jdk1.6.0_27 (32 bit)
* jre7 (32 bit) and jre6 (32 bit) 
* [JAutodoc](http://jautodoc.sourceforge.net/)
* [JUnit](http://www.junit.org/)
* [Apache ANT](http://ant.apache.org/)

<a name="license"></a>
# License #

The Tango Card Java SDK is free to use, given some restrictions. Please see the LICENSE file for details.

<a name="production_deployment"></a>
# Production Deployment #

When you're ready to go live, email [sales@tangocard.com](mailto:sales@tangocard.com). We'll get you set up with a contract and everything else you need, including linking your account so that transactions served via your integration will draw down on your Tango Card account. 
