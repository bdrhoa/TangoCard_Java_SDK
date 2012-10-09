/**
 * SdkConfig.java
 * TangoCard_Java_SDK
 * 
 * SDK Configuration file parser.
 * 
 * @version  1.1.0
 * @link     http://www.tangocard.com
 * 
 * Copyright (c) 2012 Tango Card, Inc
 * All rights reserved.
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions: 
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software. 
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * 
 */

package tangocard.sdk.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SdkConfig {

    private static SdkConfig _instanceObject = null;
    private Properties _tc_sdk_config;
    private static Object _syncObject = new Object();

    /**
     * Instantiates a new sdk config.
     *
     * @throws Exception the exception
     */
    private SdkConfig() throws Exception {
        this._tc_sdk_config = new Properties();
        String strFilePath = "tc_sdk_config.properties";
        try {
            InputStream inputStream = this.getClass().getResourceAsStream(strFilePath); 
            this._tc_sdk_config.load(inputStream);
        } catch ( IOException e ) {
            throw new TangoCardSdkException( String.format("IOException: Problems getting SDK configuration resource: '%s', error: '%s'", strFilePath, e.getMessage()), e);
        } catch ( Exception e ) {
            throw e;
        }
    }
    
    /**
     * Gets the single instance of SdkConfig.
     *
     * @return single instance of SdkConfig
     * @throws Exception the exception
     */
    public static SdkConfig getInstance() throws Exception { 
        
        if (null == SdkConfig._instanceObject) {
            synchronized(SdkConfig._syncObject) {
                if (null == SdkConfig._instanceObject) {
                    SdkConfig._instanceObject = new SdkConfig();
                }
            }
        }
        return SdkConfig._instanceObject; 
    }
    
    /**
     * Gets the config value.
     *
     * @param key the key
     * @return the config value
     */
    public String getConfigValue(String key) {
        if (null == this._tc_sdk_config) {
            throw new NullPointerException( "Reference to '_tc_sdk_config' is null.");
        }
        if (Helper.isNullOrEmptyString(key)) {
            throw new IllegalArgumentException( "Parameter 'key' is null.");
        }
        
        return this._tc_sdk_config.getProperty(key);
    }
}
