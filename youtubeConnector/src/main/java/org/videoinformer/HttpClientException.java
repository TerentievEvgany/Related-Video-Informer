/**
 * Copyright (C) 2013-2013 Evgany Terentiev - All rights reserved.
 */

package org.videoinformer;

/**
 *
 * @author Evgany Terentiev
 */
public class HttpClientException extends Exception{

    public HttpClientException(String message) {
        super(message);
    }

    public HttpClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpClientException(Throwable cause) {
        super(cause);
    }
    
}
