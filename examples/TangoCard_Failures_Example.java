/**
 * 
 *  TangoCard_Failures_Example.java
 *  TangoCard_Java_SDK
 *  
 *  Example code using Tango Card SDK and how to handle Service Failures.
 * 
 *  © 2012 Tango Card, Inc
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

import tangocard.sdk.common.TangoCardSdkException;
import tangocard.sdk.request.GetAvailableBalanceRequest;
import tangocard.sdk.request.PurchaseCardRequest;
import tangocard.sdk.response.ServiceResponseEnum;
import tangocard.sdk.response.failure.InsufficientFundsResponse;
import tangocard.sdk.response.success.GetAvailableBalanceResponse;
import tangocard.sdk.response.success.PurchaseCardResponse;
import tangocard.sdk.service.TangoCardServiceException;

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
            prop.load(new FileInputStream("app_config.properties"));
        } catch ( FileNotFoundException ex ) {
            throw ex;
        } catch ( Exception ex ) {
            throw ex;
        }
        
        String app_production_mode = prop.getProperty("app_production_mode");
        
        boolean is_production_mode = app_production_mode.equals("true");
        

       String username = "test@test.com";
       String password = "password";
        
        try
        {
            System.out.println("======== Get Available Balance ========");

            GetAvailableBalanceRequest request = new GetAvailableBalanceRequest
            (
                is_production_mode,
                username,
                password
            );
            GetAvailableBalanceResponse response = new GetAvailableBalanceResponse();
            if (request.execute(response) && (null != response))
            {
                
                System.out.println("=== Expected failure ===");
                
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

        System.out.println("===== End Get Available Balance ====\n\n\n");
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
            prop.load(new FileInputStream("app_config.properties"));
        } catch ( FileNotFoundException ex ) {
            throw ex;
        } catch ( Exception ex ) {
            throw ex;
        }

        String app_production_mode = prop.getProperty("app_production_mode");        
        boolean is_production_mode = app_production_mode.equals("true");

        String username = "empty@tangocard.com";
        String password = "password";
      
        try
        {
            System.out.println("======== Purchase Card ========");

            PurchaseCardRequest request = new PurchaseCardRequest
            (
                is_production_mode,
                username,
                password,
                "tango-card",
                100,    // $1.00 value
                false, 
                null, 
                null, 
                null, 
                null
            );

            PurchaseCardResponse response = null;
            if (request.execute(response) && (null != response))
            {              
                System.out.println("=== Expected failure ===");               
            }
        }
        catch (TangoCardServiceException ex)
        {        
            System.out.println("=== Tango Card Service Failure ===");
            System.out.println("Failure response type: " + ex.getResponseType().toString());
            System.out.println("Failure response:      " + ex.getMessage());
            if ( ex.getResponseType().equals( ServiceResponseEnum.INS_FUNDS.toString() ) ) {
                System.out.println("AvailableBalance:      " + ((InsufficientFundsResponse)ex.getResponse()).getAvailableBalance());
                System.out.println("OrderCost:             " + ((InsufficientFundsResponse)ex.getResponse()).getOrderCost());
            }          
        }
        catch (TangoCardSdkException ex)
        {
            System.out.println("=== Tango Card SDK Failure ===");
            System.out.println( String.format("%s :: %s", ex.getCause().getClass().toString(), ex.getMessage())); 

            System.out.println(TangoCard_Failures_Example.getStackTrace(ex));
        }
        catch (Exception ex)
        {          
            System.out.println("=== Unexpected Error ===");
            System.out.println( String.format("%s :: %s", ex.getCause().getClass().toString(), ex.getMessage()));   

            System.out.println(TangoCard_Failures_Example.getStackTrace(ex));
        }

        System.out.println("===== End Get Available Balance ====\n\n\n");
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
