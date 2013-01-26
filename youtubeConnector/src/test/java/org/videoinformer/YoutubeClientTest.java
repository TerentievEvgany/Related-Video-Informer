/**
 * Copyright (C) 2013-2013 Evgany Terentiev - All rights reserved.
 */

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.videoinformer;

import junit.framework.TestCase;
import org.w3c.dom.Document;

/**
 *
 * @author pirocrew
 */
public class YoutubeClientTest extends TestCase {

    public YoutubeClientTest(String testName) {
        super(testName);
    }

    /**
     * Test of getDataSource method, of class YoutubeClient.
     */
    public void testGetDataSource() {
        System.out.println("getDataSource");
        String url = "http://www.youtube.com/watch?v=1YK3KVBajgY";
        YoutubeClient instance = new YoutubeClient();
        Document result = instance.getDataSource(url);
        assertNotNull(result);
    }
}
