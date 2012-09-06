TangoCard Java SDK
=================

# Table of Contents #
<ul>
    <li><a href="#introduction">Introduction</a>
        <ul>
            <li><a href="#tango_card_sdks">Tango Card SDKs</a>
                <ul>
                    <li><a href="#tango_card_api">Tango Card API</a></li>
                </ul>
            </li>
            <li><a href="#incorporate_tango_card">Incorporate the Tango Card</a></li>
            <li><a href="#open_account">Open Tango Card Account</a>
                <ul>
                    <li><a href="#open_account_register">Register</a></li>
                    <li><a href="#open_account_login">Login</a></li>
                    <li><a href="#open_account_add_funds">Add Funds</a></li>
                </ul>
            </li>
            <li><a href="#start_using">Start Using</a>
                <ul>
                    <li><a href="#start_using_purchase">Purchase and Distribution of Gift Cards</a></li>
                    <li><a href="#start_using_gift_cards">The Tango Card and other Retailer Brand Gift Cards</a></li>
                </ul>
            </li>
            <li><a href="#sdk_support">Tango Card SDKs Support</a></li>
            <li><a href="#contact_us">Contact Us</a></li>
        </ul>
    </li>
    <li><a href="#sdk_overview">Tango Card SDK Overview</a></li>
    <li><a href="#sdk_requirements">Tango Card SDK Requirements</a></li>
    <li><a href="#tango_card_service_requests">Tango Card Service Requests</a>
        <ul>
            <li><a href="#tango_card_service_api_endpoints">Tango Card Service API Endpoints</a></li>
            <li><a href="#tango_card_service_api_security">Tango Card Service API Security</a></li>
        </ul>
    </li>
    <li><a href="#tango_card_sdk_methods">Tango Card SDK Methods</a>
        <ul>
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

<a name="tango_card_sdk"></a>
## Tango Card SDK ##
Tango Card's SDK is flexible, secure, and straightforward. It allows any server to purchase the Tango Card and is intended for users requiring high volume transactions and processes.

<a name="tango_card_api"></a>
### Tango Card API ###
For those developers who wish to develop directly with our Tango Card API endpoints and do not wish to use our available SDKs or need more detail of how our API is defined, the following document is available:
<ul>
    <li><a href="https://github.com/tangocarddev/General/blob/master/TangoCard_API.md" target="_blank">Tango Card API</a></li>
</ul>

<a name="incorporate_tango_card"></a>
## Incorporate the Tango Card ##
Tango Card SDK allows you to incorporate the innovative the Tango Card into your reward, loyalty, and engagement applications. 

Tango Card is the �exactly what you want� gift card and allows the recipient to use their value exactly how they want � they can select a premier gift card, they can divide their value among Brands, they can use some today and save the rest for another day. They can also donate to a non-profit organization. 

Tango Card value can be used via the web or from almost any mobile device. There are no fees or expiration dates of any kind. It�s great for the recipient, and even better for you because it is an entire gift card program delivered in one card allowing you to focus on your core business. 

Tango Card solutions are already used by Microsoft Bing, FedEx, Extole, Plink, beintoo, Lead Valu, Getty Images, and many others.

<a name="open_account"></a>
## Open Tango Card Account ##

In order to use the Tango Card SDKs, it is required to open and fund a Tango Card account on https://www.tangocard.com

<a name="open_account_register"></a>
### Register ###

First, register to open a Tango Card account: <a href="https://www.tangocard.com/user/register" target="_blank">Register</a> 

The provided 'username (email address)' and 'password' will be the same as what will be used for authenticating usage of the Tango Card SDKs' methods.

<a name="open_account_login"></a>
### Login ###

Second, to verify availability of your production account by using login: <a href="https://www.tangocard.com/user/login" target="_blank">Login</a>

<a name="open_account_add_funds"></a>
### Add Funds ###

Third, in order to purchase the Tango Card through the Tango Card SDKs, there must be funds within your Tango Card account.

Fund your account here either by 'wire transfer', 'check', or 'credit card': <a href="https://www.tangocard.com/user/addfunds" target="_blank">Add Funds</a>

<a name="start_using"></a>
## Start Using ##

After opening and funding your Tango Card account, then you are ready to begin using the Tango Card SDK to access your account.

<a name="start_using_purchase"></a>
### Purchase and Distribution of Gift Cards ###
Through the Tango Card SDKs you can purchase Tango Card gift cards with your choice of delivery:
<ul>
    <li>Have Tango Card service send gift cards directly to recipients via email which will include live gift card codes.</li>
    <li>You take the returned live gift card codes for you to customize and redistribute.</li>
</ul>

<a name="start_using_gift_cards"></a>
### The Tango Card and other Retailer Brand Gift Cards ###

The API is optimized for ordering the Tango Card, which has SKU of ```"tango-card"```.

If you have questions about potentially incorporating other brands or digital goods in your program please contact us at general@tangocard.com.

<a name="sdk_support"></a>
## Tnngo Card SDKs Support ##
If you have any questions with the Tango Card SDKs, please contact us at sdk@tangocard.com.

<a name="contact_us"></a>
## Contact Us ##
To learn more about Tango Card integration solutions, call 1.877.55.TANGO.

<a name="sdk_overview"></a>
# Tango Card SDK Overview #

The Tango Card Java SDK is a wrapper around the <a href="https://github.com/tangocarddev/General/blob/master/TangoCard_API.md" target="_blank">Tango Card API</a>.

As such, it has two primary types of objects, Requests and Responses; which are handled by a wrapper class `tangocard.sdk.TangoCardServiceApi`.

The wrapper class `tangocard.sdk.TangoCardServiceApi` currently handles the following static methods:
<dl>
    <dt>bool GetAvailableBalance()</dt>
    <dd>- Gather the currently available balance for provided user within their www.tangocard.com account.</dd>

    <dt>bool PurchaseCard()</dt>
    <dd>- Purchase a gift card using funds from user's www.tangocard.com account.</dd>
</dl>

![Tango Card Service Api](https://github.com/tangocarddev/TangoCard_Java_SDK/raw/master/doc/images/tangocardserviceapi.png "Tango Card Service Api")

<a name="sdk_requirements"></a>
# Tango Card SDK Requirements #

* [Java Development Kit 1.6+](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
* [Java Runtime Environment 6 / 7](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
* [JSON in Java](http://www.json.org/java/) - *org.json-20120521.jar*, included within `\lib\` folder of this SDK.
* [Apache ANT](http://ant.apache.org/)


<a name="tango_card_service_requests"></a>
# Tango Card Service Requests #

The Tango Card SDK, every Request has a corresponding success-case Response object.

<a name="tango_card_service_api_endpoints"></a>
## Tango Card Service API Endpoints ##

Available are two endpoints that provide the <a href="https://github.com/tangocarddev/General/blob/master/TangoCard_API.md" target="_blank">Tango Card API</a>, as defined by `enum tangocard.sdk.service.TangoCardServiceApiEnum` :
<dl>
    <dt>INTEGRATION</dt> 
    <dd>
        <ul>
            <li>Expected to be used for development and testing purposes.</li>
            <li><b>Important:</b> Purchases from this endpoint will: 
                <ul>
                    <li>Use funds from our test account.</li>
                    <li>Send real emails (with fake codes), so only use recipient email addresses you control for testing purposes.</li>
                </ul>
            </li>
            <li>Endpoint URL: https://int.tangocard.com/Version2/[method]</li>
            <li>Login to use our testing account through this endpoint is:
                <dl>
                    <dt>Username:</dt>
                    <dd>third_party_int@tangocard.com</dd>
                    <dt>Password:</dt>
                    <dd>integrateme</dd>
                </dl>
            </li>
        </ul>
    </dd>
    <dt>PRODUCTION</dt>
    <dd>
        <ul>
            <li>Performs actual card purchase requests.</li>
            <li><b>Important:</b> Purchases from this endpoint will: 
                <ul>
                    <li>Use funds from <b>your Tango Card account</b>!</li>
                    <li>Send real emails (with live codes), only use recipient email addresses you wish to deliver to.</li>
                </ul>
            </li>
            <li>Endpoint URL: https://api.tangocard.com/Version2/[method]</li>
            <li>Login to use your production account through this endpoint is:
                <dl>
                    <dt>Username:</dt>
                    <dd>Your Tango Card account's username (email address)</dd>
                    <dt>Password:</dt>
                    <dd>Your Tango Card account's password</dd>
                </dl>
            </li>
        </ul>
    </dd>
</dl>

<a name="tango_card_service_api_security"></a>
## Tango Card Service API Security ##

Requests are secure HTTP POST using SSL.

All calls are made via <a href="http://technet.microsoft.com/en-us/library/cc784450(v=ws.10).aspx">"TLS/SSL"</a>.

<a name="tango_card_sdk_methods"></a>
# Tango Card SDK Methods #

<a name="get_available_balance"></a>
## Get Available Balance ##

![Tango Card Service API - GetAvailableBalance()](https://github.com/tangocarddev/TangoCard_Java_SDK/raw/master/doc/images/tangocardserviceapi_getavailablebalance.png "Tango Card Service API - GetAvailableBalance()")

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
  <dd>- INTEGRATION and PRODUCTION</dd>
  
  <dt>string username</dt>
  <dd>- User email address, and the SDK Integration test username is defined within configuration file *app_config.properties* in setting *app_username*</dd>

  <dt>string password</dt>
  <dd>- User password, and the SDK Integration test password is defined within configuration file *app_config.properties* in setting *app_password*</dd>

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

![Tango Card Service API - PurchaseCard()](https://github.com/tangocarddev/TangoCard_Java_SDK/raw/master/doc/images/tangocardserviceapi_purchasecard.png "Tango Card Service API - PurchaseCard()")

This request is defined by static method call `tangocard.sdk.TangoCardServiceApi.PurchaseCard()`:

```java
    TangoCardServiceApiEnum enumTangoCardServiceApi = TangoCardServiceApiEnum.INTEGRATION;
    String username = "burt@example.com";
    String password = "password";
    String card_sku = "tango-card";
    int cardValueTangoCardCents = 100; // $1.00 dollars
    
    PurchaseCardResponse response = new PurchaseCardResponse();
    if ( TangoCardServiceApi.PurchaseCard(
            enumTangoCardServiceApi,        // API environment
            username,                         // username
            password,                        // password
            card_sku,                        // cardSku
            cardValueTangoCardCents,        // cardValue
            true,                            // tcSend 
            "Sally Example",                // recipientName
            "sally@example.com",            // recipientEmail
            "Happy Birthday",                // giftMessage
            "Bill Example",                    // giftFrom  
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
  <dd>- INTEGRATION or PRODUCTION</dd>

  <dt>string username</dt>
  <dd>- User email address, and a SDK Integration test username is defined within configuration file *app_config.properties* in setting *app_username*</dd>

  <dt>string password</dt>
  <dd>- User password, and a SDK Integration test password is defined within configuration file *app_config.properties* in setting *app_password*</dd>

  <dt>string cardSku</dt>
  <dd>- Card brand request, and the Tango Card brand's card sku *tango-card* is defined within configuration file *app_config.properties* in setting *app_card_sku*</dd>

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

![Tango Card SDK Exceptions](https://github.com/tangocarddev/TangoCard_Java_SDK/raw/master/doc/images/tangocard_sdk_exceptions.png "Tango Card SDK Exceptions")

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

![Tango Card SDK Service Response Failures](https://github.com/tangocarddev/TangoCard_Java_SDK/raw/master/doc/images/tangocard_sdk_service_failure_response.png "Tango Card SDK Service Response Failures")

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

![Tango Card SDK Error Detection](https://github.com/tangocarddev/TangoCard_Java_SDK/raw/master/doc/images/tangocard_sdk_error_detected.png "Tango Card SDK Error Detection")

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
