<h1>Tango Card Java SDK</h1>
<h3>Incorporate the innovative Tango Card directly into your reward, loyalty, and engagement applications.</h3>
<h4>Update: 2012-10-01</h4>
===

# Table of Contents #
<ul>
    <li><a href="#introduction">Introduction</a>
        <ul>
            <li><a href="#tango_card_sdks">Tango Card SDKs</a></li>
            <li><a href="#tango_card_service_api">Tango Card Service API</a></li>
            <li><a href="#incorporate_tango_card">Incorporate the Tango Card</a></li>
            <li><a href="#open_account">Open Tango Card Account</a>
                <ul>
                    <li><a href="#open_account_register">Register</a></li>
                    <li><a href="#open_account_login">Login</a></li>
                    <li><a href="#open_account_add_funds">Add Funds</a></li>
                </ul>
            </li>
        </ul>
    </li>
    <li><a href="#puchasing_options">Understanding Gift Card Purchasing Options</a>
        <ul>
            <li><a href="#puchasing_options_distribution">Distribution of Gift Cards</a></li>
            <li><a href="#puchasing_options_skus">The Tango Card and other Retailer Brand Gift Cards</a></li>
            <li><a href="#puchasing_options_denominations">Gift Card Denominations</a></li>
            <li><a href="#puchasing_options_templates">The Tango Card and custom Company Email Templates</a></li>
        </ul>
    </li>
    <li><a href="#sdk_support">Tango Card Service API Support</a>
        <ul>
            <li><a href="#sdk_support_contact">Contact Us</a></li>
            <li><a href="#sdk_support_resolve">Resolving Issues using Fiddler 2</a></li>
        </ul>
    </li>
    <li><a href="#sdk_overview">Tango Card Java SDK Overview</a></li>
    <li><a href="#sdk_requirements">Tango Card SDK Requirements</a></li>
    <li><a href="#tango_card_service_api_requests">Tango Card Service API Requests</a>
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

<a name="tango_card_service_api"></a>
## Tango Card Service API ##
For those developers who wish to develop directly with our Tango Card API endpoints and do not wish to use our available SDKs or need more detail of how our API is defined, the following document is available:
<ul>
    <li><a href="https://github.com/tangocarddev/General/blob/master/Tango_Card_Service_API.md" target="_blank">Tango Card Service API</a></li>
</ul>

<a name="incorporate_tango_card"></a>
## Incorporate the Tango Card ##
The Tango Card SDKs, through the <a href="https://github.com/tangocarddev/General/blob/master/Tango_Card_Service_API.md" target="_blank">Tango Card Service API</a>, allows you to incorporate the innovative Tango Card directly into your reward, loyalty, and engagement applications. 

Tango Card is the "exactly what you want" gift card and allows the recipient to use their value exactly how they want – they can select a premier gift card, they can divide their value among Brands, they can use some today and save the rest for another day. They can also donate to a non-profit organization. 

Tango Card value can be used via the web or from almost any mobile device. There are no fees or expiration dates of any kind. It’s great for the recipient, and even better for you because it is an entire gift card program delivered in one card allowing you to focus on your core business. 

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

<a name="puchasing_options"></a>
# Understanding Gift Card Purchasing Options #

After opening and funding your Tango Card account, then you are ready to begin using the Tango Card Service API to access your account for getting available balance and for purchasing gift cards.

When you are ready to purchase a card, the Tango Card Service API has several options:

<dl>
    <dt>
    <a name="puchasing_options_distribution"></a>
    Distribution of Digital Gift Cards - parameter <code>tcSend</code> - boolean - <b>required</b></dt>
    <dd>
        Through the Tango Card Service API you can purchase Tango Card gift cards with your choice of delivery:
        <ul>
            <li><code>tcSend = true</code> - Have Tango Card service send gift cards directly to recipients via email which will include live gift card codes.</li>
            <li><code>tcSend = false</code> - You take the returned live gift card codes for you to customize and redistribute.</li>
        </ul>
    </dd>
    
    <dt>
    <a name="puchasing_options_skus"></a>
    The Tango Card and other Retailer Brand Gift Cards SKUs - parameter <code>cardSKU</code> - string - <b>required</b></dt>
    <dd>The API is optimized for ordering the Tango Card, which is SKU <code>"tango-card"</code>.

    <br>If you have questions about potentially incorporating other brands or digital goods in your program, then please do contact us at <a href="mailto:sdk@tangocard.com?Subject=Tango Card Service API Question">sdk@tangocard.com</a>.
    </dd>
    
    <dt>
    <a name="puchasing_options_denominations"></a>
    Gift Card Denominations - parameter <code>cardValue</code> - integer - <b>required</b></dt>
    <dd>Each gift card SKU has it own allowed set of denominations that can to assigned to parameter <code>cardValue</code>.
    <br/>For SKU <code>"tango-card"</code>, the available denomination in cents is between <code>1</code> ($0.01) to <code>100000</code> ($1000.00).
    <br/>To find out about other available denominations for potentially incorporating other SKUs that can be assigned to parameter <code>cardValue</code>, then please do contact us at <a href="mailto:sdk@tangocard.com?Subject=Tango Card Service API Question">sdk@tangocard.com</a>.
    </dd>
    
    <dt>
    <a name="puchasing_options_templates"></a>
    The Tango Card and custom Company Email Templates - parameter <code>companyIdentifier</code> - string - <b>optional</b></dt>
    <dd>If you choose to have the Tango Card Service API send digital gift cards by setting <code>tcSend</code> to <code>true</code>, then by default the gift card information within a Tango Card email template.
    <br>If you prefer to have the Tango Card Service API send the gift card information with a custom email template (with your own branding), then please do contact us at <a href="mailto:sdk@tangocard.com?Subject=Tango Card Service API Question">sdk@tangocard.com</a>.
    </dd>
</dl>

<a name="sdk_support"></a>
# Tango Card Java SDK and Service API Support #
If you have any questions with the Tango Card Java SDK or our Service API, please contact us at sdk@tangocard.com.

<a name="sdk_support_contact"></a>
## Contact Us ##
To learn more about Tango Card integration solutions, call 1.877.55.TANGO.

<a name="sdk_support_resolve"></a>
## Resolving Issues using Fiddler 2 ##

The best way to resolve any issues that pertain to using our Tango Card Java SDK or our Tango Card Service API is by using this freely available tool <a href="http://www.fiddler2.com/fiddler2/" target="_blank">`Fiddler 2 - Web Debugging Proxy`</a>, and providing us with the raw request and response bodies using its `Inspectors` tab feature.

Using `Fiddler 2` will provide us with the most complete detail and the fastest response from Tango Card by understanding if there is an issue on how a request was presented to our service, or if it is an issue with our service on how we replied to your request.

### Fiddler 2 Example - Raw Request from Client - Get Available Balance ###

```Text
POST https://int.tangocard.com/Version2/GetAvailableBalance HTTP/1.1
Accept: application/json, text/javascript, */*; q=0.01
Accept-Language: en-us
Content-Type: application/json; charset=UTF-8
Accept-Encoding: gzip, deflate
User-Agent: Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)
Host: int.tangocard.com
Content-Length: 69
Connection: Keep-Alive
Cache-Control: no-cache
 
{"username":"third_party_int@tangocard.com","password":"integrateme"}
```
 
### Fiddler 2 Example - Raw Response from Service - Get Available Balance ###

```Text
HTTP/1.1 200 OK
Date: Wed, 26 Sep 2012 04:30:36 GMT
Server: Apache/2.2.22 (Ubuntu)
X-Powered-By: PHP/5.3.10-1ubuntu3.3
Access-Control-Allow-Origin: *
Content-Length: 68
Connection: close
Content-Type: application/json
 
{"responseType":"SUCCESS","response":{"availableBalance":873431432}}
```

<a name="sdk_overview"></a>
# Tango Card Java SDK Overview #

The Tango Card Java SDK is a wrapper around the <a href="https://github.com/tangocarddev/General/blob/master/Tango_Card_Service_API.md" target="_blank">Tango Card Service API</a>.

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

<a name="tango_card_service_api_requests"></a>
# Tango Card Service API Requests #

With the <a href="https://github.com/tangocarddev/General/blob/master/Tango_Card_Service_API.md" target="_blank">Tango Card Service API</a>, every request has a corresponding success-case response object. There are also several failure-case response objects which are shared between calls. The specifics of the request and response objects will be described in <a href="#tango_card_sdk_methods">Tango Card SDK Methods</a>.

<a name="tango_card_service_api_endpoints"></a>
## Tango Card Service API Endpoints ##

Available are two endpoints that provide the <a href="https://github.com/tangocarddev/General/blob/master/Tango_Card_Service_API.md" target="_blank">Tango Card Service API</a>, as defined by `enum tangocard.sdk.service.TangoCardServiceApiEnum` :
<dl>
    <dt><code>INTEGRATION</code></dt> 
    <dd>
        <ul>
            <li>Expected to be used for development and testing purposes.</li>
            <li><b>Important:</b> Purchases from this endpoint will: 
                <ul>
                    <li>Use funds from our test account.</li>
                    <li>Send real emails (with fake codes), so only use recipient email addresses you control for testing purposes.</li>
                </ul>
            </li>
            <li>Secure Endpoint URL: <code>https://int.tangocard.com/Version2</code></li>
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
    <dt><code>PRODUCTION</code></dt>
    <dd>
        <ul>
            <li>Performs actual card purchase requests.</li>
            <li><b>Important:</b> Purchases from this endpoint will: 
                <ul>
                    <li>Use funds from <b>your Tango Card account</b>!</li>
                    <li>Send real emails (with live codes), only use recipient email addresses you wish to deliver to.</li>
                </ul>
            </li>
            <li>Endpoint URL: <code>https://api.tangocard.com/Version2</code></li>
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

<a href="https://github.com/tangocarddev/General/blob/master/Tango_Card_Service_API.md" target="_blank">Tango Card Service API</a> Requests are performed using secure HTTP POST via <a href="http://en.wikipedia.org/wiki/Transport_Layer_Security" target="_blank">"TLS/SSL"</a>.

The use of SSL allows for securely transmitting data and prevents <a href="http://en.wikipedia.org/wiki/Man-in-the-middle_attack" target="_blank">man-in-the-middle attacks</a>.

The lack of sessions and the inability to communicate with the API over HTTP prevents <a href="http://en.wikipedia.org/wiki/Session_hijacking" target="_blank">session hijacking</a> and <a href="http://en.wikipedia.org/wiki/Cross-site_request_forgery" target="_blank">cross-site request forgery</a>.

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
    <dt>* enumTangoCardServiceApi</dt>
    <dd><code>TangoCardServiceApiEnum</code> - <b>required</b> - <code>INTEGRATION</code> or <code>PRODUCTION</code></dd>
    <dt>* username</dt>
    <dd>string - <b>required</b> - user account's username registered within Tango Card production website (https://www.tangocard.com).</dd>
    <dt>* password</dt>
    <dd>string - <b>required</b> - user account's password registered within Tango Card production website (https://www.tangocard.com)</dd>
    <dt>tangocard.sdk.response.success.GetAvailableBalanceResponse response</dt>
    <dd>- This <i>out</i> paramter will provide a valid success response object if this method returns <code>true</code> upon success.</dd>
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
            enumTangoCardServiceApi,            // API environment
            username,                           // username
            password,                           // password
            card_sku,                           // cardSku
            cardValueTangoCardCents,            // cardValue
            true,                               // tcSend 
            "Sally Example",                    // recipientName
            "sally@example.com",                // recipientEmail
            "Happy Birthday",                   // giftMessage
            "Bill Example",                     // giftFrom
            null,                               // companyIdentifier (default Tango Card email template
            response                            // response 
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
    <dt>* enumTangoCardServiceApi</dt>
    <dd><code>TangoCardServiceApiEnum</code> - <b>required</b> - <code>INTEGRATION</code> or <code>PRODUCTION</code></dd>
    <dt>* username</dt>
    <dd>string - <b>required</b> - user account's username registered within Tango Card production website (https://www.tangocard.com).</dd>
    <dt>* password</dt>
    <dd>string - <b>required</b> - user account's password registered within Tango Card production website (https://www.tangocard.com)</dd>
    <dt>* cardSku</dt>
    <dd>string - <b>required</b> - The SKU of the card to purchase. The SKU for the Tango Card is "tango-card". See: <a href="#puchasing_options_skus">Purchase Option of Gift Card Brands</a></dd>
    <dt>* cardValue</dt>
    <dd>integer - <b>required</b> - The value of the card to purchase in cents (100 = $1.00). See: <a href="#puchasing_options_denominations">Purchase Option for Denominations</a></dd>
    <dt>* tcSend</dt>
    <dd>boolean - <b>required</b> - Whether Tango Card will send the email to the user. See: <a href="#puchasing_options_distribution">Purchase Option for Distribution</a>.</dd>
    <dt>* recipientName</dt>
    <dd>string (length 1 - 255) or null - <b>required</b> if tcSend=true else ignored - The name of the person receiving the card.</dd>
    <dt>* recipientEmail</dt>
    <dd>string (length 3 - 255) or null - <b>required</b> if tcSend=true else ignored - The email address of the person receiving the card.</dd>
    <dt>* giftMessage</dt>
    <dd>string (length 1 - 255) or null - <b>required</b> if tcSend=true else ignored - A message from the sender of the card to the recipient. May be null, but must exist if tcSend = true.</dd>
    <dt>giftFrom</dt>
    <dd>string (length 1 - 255) or null - <b>optional</b> if tcSend=true else ignored - The name of the person sending the card.</dd>
    <dt>companyIdentifer</dt>
    <dd>string (length 1 - 255) or null - <b>optional</b> if tcSend=true else ignored - The email-template identifier. Ignored or value <code>null</code> will use the Tango Card Email Template. See: <a href="#puchasing_options_templates">Purchase Option for Email Templates</a>.</dd>

  <dt>tangocard.sdk.response.success.PurchaseCardResponse response</dt>
  <dd>- This <i>out</i> parameter will provide a valid success response object if this method returns <code>true</code> upon success.</dd>
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
    <tr><td>Insufficient Funds</td><td><code>INS_FUNDS</code></td><td><code>tangocard.sdk.response.failure.InsufficientFundsResponse</code></td></tr>
    <tr><td>Insufficient Inventory</td><td><code>INS_INV</code></td><td><code>tangocard.sdk.response.failure.InsufficientInventoryResponse</code></td></tr> 
    <tr><td>Invalid Credentials</td><td><code>INV_CREDENTIAL</code></td><td><code>tangocard.sdk.response.failure.InvalidCredentialsResponse</code></td></tr> 
    <tr><td>Invalid Input</td><td><code>INV_INPUT</code></td><td><code>tangocard.sdk.response.failure.InvalidInputResponse</code></td></tr>
    <tr><td>System Failure</td><td><code>SYS_ERROR</code></td><td><code>tangocard.sdk.response.failure.SystemFailureResponse</code></td></tr>
</table>

Each of the aforementioned `Failure Responses` contains details as to the reason that the `Tango Card Service API` did not perform provided `Request`.

![Tango Card SDK Service Response Failures](https://github.com/tangocarddev/TangoCard_Java_SDK/raw/master/doc/images/tangocard_sdk_service_failure_response.png "Tango Card SDK Service Response Failures")

The details of these service failure responses are embedded and thrown within `tangocard.sdk.service.TangoCardServiceException`

### Expected Failure Responses for Specific Requests ###

Each Request will have the following possible Failure Responses as a property value within `tangocard.sdk.service.TangoCardServiceException.getResponse()`:
Using this SDK, when a request fails because of failure response from the Tango Card Service API, then <code>tangocard.sdk.service.TangoCardServiceException</code> is thrown.

The exception has two properties that define what was failure response from the Tango Card Service API:
* `Failure Response Type` held within this property <code>tangocard.sdk.service.TangoCardServiceException.getResponseType()</code>.
* `failure Response Object` held within this property <code>tangocard.sdk.service.TangoCardServiceException.getResponse()</code> whose parent class is <code>tangocard.sdk.response.failure.FailureResponse</code>.

<table>
    <tr><th>Request</th><th>Possible Failure Responses</th></tr>
    <tr>
        <td><code>GetAvailableBalanceRequest</code></td>
        <td>
            <table>
                <tr><th>Failure Reponse Type</th><th>Failure Response</th></tr>
                <tr><td><code>INV_CREDENTIAL</code></td><td><code>InvalidCredentialsResponse</code></td></tr> 
                <tr><td><code>SYS_ERROR</code></td><td><code>SystemFailureResponse</code></td></tr>
            </table>
        </td>
    </tr>
    <tr>
        <td><code>PurchaseCardRequest</code></td>
        <td>
            <table>
                <tr><th>Failure Reponse Type</th><th>Failure Response</th></tr>
                <tr><td><code>INS_FUNDS</code></td><td><code>InsufficientFundsResponse</code></td></tr>
                <tr><td><code>INS_INV</code></td><td><code>InsufficientInventoryResponse</code></td></tr> 
                <tr><td><code>INV_CREDENTIAL</code></td><td><code>InvalidCredentialsResponse</code></td></tr> 
                <tr><td><code>INV_INPUT</code></td><td><code>InvalidInputResponse</code></td></tr>
                <tr><td><code>SYS_ERROR</code></td><td><code>SystemFailureResponse</code></td></tr>
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
This is the heart of the SDK which contains the <code>src</code., and here is a listing of its contents:

* src\tangocard\config\tc_sdk_config.properties
* src\tangocard\sdk\TangoCardServiceApi.java
* src\tangocard\sdk\common\Helper.java
* src\tangocard\sdk\common\SdkConfig.java
* src\tangocard\sdk\common\TangoCardSdkException.java
* src\tangocard\sdk\request\BaseRequest.java
* src\tangocard\sdk\request\GetAvailableBalanceRequest.java
* src\tangocard\sdk\request\PurchaseCardRequest.java
* src\tangocard\sdk\response\BaseResponse.java
* src\tangocard\sdk\response\ServiceResponseEnum.java
* src\tangocard\sdk\response\failure\FailureResponse.java
* src\tangocard\sdk\response\failure\InsufficientFundsResponse.java
* src\tangocard\sdk\response\failure\InsufficientInventoryResponse.java
* src\tangocard\sdk\response\failure\InvalidCredentialsResponse.java
* src\tangocard\sdk\response\failure\InvalidInputResponse.java
* src\tangocard\sdk\response\failure\SystemErrorResponse.java
* src\tangocard\sdk\response\success\GetAvailableBalanceResponse.java
* src\tangocard\sdk\response\success\PurchaseCardResponse.java
* src\tangocard\sdk\response\success\SuccessResponse.java
* src\tangocard\sdk\service\ServiceProxy.java
* src\tangocard\sdk\service\TangoCardServiceApiEnum.java
* src\tangocard\sdk\service\TangoCardServiceException.java

<a name="lib"></a>
## lib ##
The Tango Card Java SDK has one dependency for JSON Library [org.json-20120521.jar](http://code.google.com/p/org-json-java/downloads/detail?name=org.json-20120521.jar&can=2&q=), which is included.

This folder also contains JUnit Library [junit-4.10.jar](https://github.com/KentBeck/junit/downloads) in order to run unittests provides.

## build file ##

Provided is an [Apache ANT](http://ant.apache.org/) `build.xml` file and reading build attributes from `build.properties` which was used to create `dest\TangoCard_Java_SDK-{major}.{minor}.{revision}.jar` file by using `ant`:

```Text
    > ant build
```

<a name="configuration_files"></a>
## configuration files ##

There a several configuration files that are referenced by either the provide application examples, unittests, and SDK itself.

<dl>
    <dt>config\app_config.properties</dt>
    <dd>- An example application configuration file for <code>\examples<\code> and <code>\unittests<\code></dd>
    
    <dt>src\tangocard\config\tc_sdk_config.properties</dt>
    <dd>- SDK configuration file referenced by `tangocard.sdk.common.SdkConfig.java`. <b>**DO NOT MODIFY**</b></dd>
</dl>

<a name="doc"></a>
## doc ##

The <code>doc\javadoc\index.html<\code> accesses the up-to-date [javadoc](http://www.oracle.com/technetwork/java/javase/documentation/index-jsp-135444.html) generated documentation for the classes (and functions) that are included in the SDK.

<a name="examples"></a>
## examples ##

The <code>\examples<\code> sub-directory contains full "start to finish" examples of all of the supported methods. This includes catching all of the possible failure modes, etc. 

### TangoCard_Store_Example.java ###

This is a complete example of requesting available balance and purchasing Tango Cards.

1. Request latest available balance
2. Purchase $1.00 Tango Card for Email Delivery
3. Purchase $1.00 Tango Card without Email Delivery
4. Request updated available balance

#### Command Line ####

This example is intended to be run from the command line by using `ant`:

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

This example is intended to be run from the command line by using `ant`:

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

The SDK's <code>\unittests<\code> have been written to use [JUnit](http://www.junit.org/).

* `UnitTest_GetAvailableBalance.java`
* `UnitTest_PurchaseCard.java`

### Running JUnit Tests ###

This SDK sources contains a JUnit jar downloaded from [junit.org downloads](https://github.com/KentBeck/junit/downloads): `\lib\junit-4.10.jar`.

#### JUnit UnitTest_GetAvailableBalance.java ####

This `junit` test is intended to be run from the command line by using `ant`:

```Text
    > ant UnitTest_GetAvailableBalance
    Buildfile: \TangoCard_Java_SDK\build.xml

    UnitTest_GetAvailableBalance:
        [junit] Running UnitTest_GetAvailableBalance
        [junit] Tests run: 2, Failures: 0, Errors: 0, Time elapsed: 3.43 sec    
```

#### JUnit UnitTest_PurchaseCard.java ####

This `junit` test is intended to be run from the command line by using `ant`:

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
