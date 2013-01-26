/**
 * Copyright (C) 2013-2013 Evgany Terentiev - All rights reserved.
 */

package org.videoinformer;

import java.io.InputStream;
import java.util.Scanner;
import javax.xml.parsers.ParserConfigurationException;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.DomSerializer;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;

/**
 * Class that get information from resources
 *
 * @author Evgany Terentiev
 */
public class YoutubeClient {

    /**
     * Constant for file name of resources which have to be loaded
     */
    private static final String RESOURCES_NAME = "/watch.htm";

    /**
     * Method create valid XML DOM from local resources
     *
     * @return valid XML DOM
     */
    public org.w3c.dom.Document getDataSource(String url) {
        org.w3c.dom.Document doc = null;
        TagNode tagNode = new HtmlCleaner().clean(readResource());
        try {
            doc = new DomSerializer(new CleanerProperties()).createDOM(tagNode);
        } catch (ParserConfigurationException ex) {
            System.out.println("Can not create XML DOM document");
        }
        return doc;
    }

    /**
     * Read data from resources to string
     *
     * @return string with resources data
     */
    private String readResource() {
        InputStream is = null;
        String inputStreamString = null;
        try {
            is = getClass().getResourceAsStream(RESOURCES_NAME);
            inputStreamString = new Scanner(is, "UTF-8").useDelimiter("\\A").next();
        } catch (NullPointerException e) {
            System.out.println("Incorrect file name or resource path");
            System.exit(0);
        }
        return inputStreamString;
    }
}
