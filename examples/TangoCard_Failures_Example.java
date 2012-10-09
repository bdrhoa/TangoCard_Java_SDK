/**
 * 
 *  TangoCard_Failures_Example.java
 *  TangoCard_Java_SDK
 *  
 *  Example code using Tango Card SDK and how to handle Service Failures.
 * 
 *  Copyright (c) 2012 Tango Card, Inc
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


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Properties;

import tangocard.sdk.TangoCardServiceApi;
import tangocard.sdk.common.TangoCardSdkException;
import tangocard.sdk.response.ServiceResponseEnum;
import tangocard.sdk.response.failure.*;
import tangocard.sdk.response.success.*;
import tangocard.sdk.service.*;

public class TangoCard_Failures_Example {

    /**
     * @param args
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception {
        
        System.out.println( "\n===============================" );
        System.out.println(   "= Tango Card Java SDK Example =" );
        System.out.println(   "=   with Failures             =" );
        System.out.println(   "===============================" );
        
        System.out.println(   "\nSDK Version: " + TangoCardServiceApi.GetVersion() + "\n" );
        
        TangoCard_Failures_Example.Example_GetAvailableBalance_InvalidCredentials();
        TangoCard_Failures_Example.Example_PurchaseCard_InsufficientFunds();

        System.out.println(   "===============================" );        
        System.out.println(   "=   The End                   =" );
        System.out.println(   "===============================" );
    }
    
    /**
     * Example_ get available balance_ invalid credentials.
     *
     * @throws Exception the exception
     */
    static public void Example_GetAvailableBalance_InvalidCredentials() throws Exception
    {
        Properties prop = new Properties();        
        try {
            prop.load(new FileInputStream("config/app_config.properties"));
        } catch ( FileNotFoundException e ) {
            throw e;
        } catch ( Exception e ) {
            throw e;
        }

        String app_tango_card_service_api = prop.getProperty("app_tango_card_service_api");
        TangoCardServiceApiEnum enumTangoCardServiceApi = TangoCardServiceApiEnum.valueOf(app_tango_card_service_api);

        String username = "burt@example.com";
        String password = "password";

        try
        {
            System.out.println("\n======== Get Available Balance with Invalid Credentials ========");

            GetAvailableBalanceResponse response = new GetAvailableBalanceResponse();
            if (TangoCardServiceApi.GetAvailableBalance(
                    enumTangoCardServiceApi, 
                    username, 
                    password, 
                    response
                    ) 
                    && (null != response)
            ) {
                System.out.println("=== Expected failure ===");
            }
        }
        catch (TangoCardServiceException e)
        {           
            System.out.println("=== Tango Card Service Failure ===");
            System.out.println( String.format("Failure response type: %s", e.getResponseType()) );
            System.out.println( String.format("Failure response:      %s", e.getMessage()));
            
        }
        catch (TangoCardSdkException e)
        {
            
            System.out.println("=== Tango Card SDK Failure ===");
            System.out.println( String.format("%s :: %s", e.getClass().toString(), e.getMessage()));
            
        }
        catch (Exception e)
        {
            
            System.out.println("=== Unexpected Error ===");
            System.out.println( String.format("%s :: %s", e.getClass().toString(), e.getMessage()));            
        }

        System.out.println("===== End Get Available Balance with Invalid Credentials ====\n\n\n");
    }

    /**
     * Example_ purchase card_ insufficient funds.
     *
     * @throws Exception the exception
     */
    static public void Example_PurchaseCard_InsufficientFunds() throws Exception
    {
        Properties prop = new Properties();        
        try {
            prop.load(new FileInputStream("config/app_config.properties"));
        } catch ( FileNotFoundException e ) {
            throw e;
        } catch ( Exception e ) {
            throw e;
        }

        String app_tango_card_service_api = prop.getProperty("app_tango_card_service_api");
        TangoCardServiceApiEnum enumTangoCardServiceApi = TangoCardServiceApiEnum.valueOf(app_tango_card_service_api);

        String username = "empty@tangocard.com";
        String password = "password";
      
        try
        {
            System.out.println("\n======== Purchase Card with Insufficient Funds ========");

            PurchaseCardResponse response = new PurchaseCardResponse();
            if ( TangoCardServiceApi.PurchaseCard(
                    enumTangoCardServiceApi,       // API environment
                    username,                        // username
                    password,                       // password
                    "tango-card",                   // cardSku
                    100,                           // cardValue = $1.00 value
                    false,                           // tcSend 
                    null,                           // recipientName
                    null,                           // recipientEmail
                    null,                           // giftMessage
                    null,                           // giftFrom
                    null,                           // companyIdentifier
                    response                       // response
                    ) 
                    && (null != response)
            ) {       
                System.out.println("=== Expected failure ===");               
            }
        }
        catch (TangoCardServiceException e)
        {        
            System.out.println("=== Tango Card Service Failure ===");
            System.out.println("Failure response type: " + e.getResponseType().toString());
            System.out.println("Failure response:      " + e.getMessage());
            if ( e.getResponseType().equals( ServiceResponseEnum.INS_FUNDS.toString() ) ) {
                System.out.println("AvailableBalance:      " + ((InsufficientFundsResponse) e.getResponse()).getAvailableBalance());
                System.out.println("OrderCost:             " + ((InsufficientFundsResponse) e.getResponse()).getOrderCost());
            }          
        }
        catch (TangoCardSdkException e)
        {
            System.out.println("=== Tango Card SDK Failure ===");
            System.out.println( String.format("%s :: %s", e.getCause().getClass().toString(), e.getMessage())); 

            System.out.println(TangoCard_Failures_Example.getStackTrace(e));
        }
        catch (Exception e)
        {          
            System.out.println("=== Unexpected Error ===");
            System.out.println( String.format("%s :: %s", e.getCause().getClass().toString(), e.getMessage()));   

            System.out.println(TangoCard_Failures_Example.getStackTrace(e));
        }

        System.out.println("===== End Purchase Card with Insufficient Funds ====\n\n\n");
    }
    
    /**
     * Gets the stack trace.
     *
     * @param aThrowable the a throwable
     * @return the stack trace
     */
    public static String getStackTrace(Throwable aThrowable) {
        final Writer result = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(result);
        aThrowable.printStackTrace(printWriter);
        return result.toString();
    }
}
