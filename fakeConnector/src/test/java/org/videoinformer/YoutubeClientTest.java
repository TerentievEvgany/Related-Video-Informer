/**
 * Copyright (C) 2013-2013 Evgany Terentiev - All rights reserved.
 */

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.videoinformer;

import javax.xml.parsers.ParserConfigurationException;
import junit.framework.TestCase;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.DomSerializer;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
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
    public void testGetDataSource() throws ParserConfigurationException {
        System.out.println("getDataSource");
        String url = "";
        YoutubeClient instance = new YoutubeClient();
        TagNode tagNode = new HtmlCleaner().clean("/watch.htm");
        Document expResult = new DomSerializer(new CleanerProperties()).createDOM(tagNode);
        System.out.println(expResult.toString());
        Document result = instance.getDataSource(url);
        System.out.println(result.toString());
        assertFalse(result.equals(expResult));
    }
}
