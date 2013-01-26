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
public class ParserTest extends TestCase {

    public ParserTest(String testName) {
        super(testName);
    }

    /**
     * Test of parse method, of class Parser.
     */
    public void testParse() throws ParserConfigurationException {
        System.out.println("parse");
        TagNode tagNode = new HtmlCleaner().clean("/watch.htm");
        Document document = new DomSerializer(new CleanerProperties()).createDOM(tagNode);
        Parser instance = new Parser();
        instance.parse(document);
        
        assertNotNull(document);

    }
}
