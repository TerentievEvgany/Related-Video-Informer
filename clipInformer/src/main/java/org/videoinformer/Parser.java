/**
 * Copyright (C) 2013-2013 Evgany Terentiev - All rights reserved.
 */

package org.videoinformer;

import java.util.ArrayList;
import java.util.List;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

/**
 * Class that parse valid XML DOM with Xpath
 *
 * @author Evgany Terentiev
 */
public class Parser {

    /**
     * Parse XML DOM with Xpath, creates list of videos and add it.Then call
     * method to print result.
     *
     * @param document valid XML DOM.
     */
    public void parse(org.w3c.dom.Document document) {
        ArrayList<ClipInfo> videos = new ArrayList<ClipInfo>();
        XPath xpath = XPathFactory.newInstance().newXPath();
        String listSize = null;
        String link = null;
        String name = null;
        String viewingscount = null;
        try {
            listSize = (String) xpath.evaluate("count(.//*[@id='watch-related']/li)", document, XPathConstants.STRING);
            for (int j = 1; j < Integer.parseInt(listSize); j++) {
                ClipInfo clip = new ClipInfo();
                link = (String) xpath.evaluate(".//*[@id='watch-related']/li[" + j + "]/a/@href", document, XPathConstants.STRING);
                name = (String) xpath.evaluate(".//*[@id='watch-related']/li[" + j + "]/a/span[2]/@title", document, XPathConstants.STRING);
                viewingscount = (String) xpath.evaluate(".//*[@id='watch-related']/li[" + j + "]/a/span[4]", document, XPathConstants.STRING);
                clip.setLink(link);
                clip.setName(name);
                clip.setViewscount(viewingscount);
                videos.add(clip);
            }
        } catch (XPathExpressionException e) {
            System.out.println("Xpath expression error");
        }
        printClips(videos);
    }

    /**
     * Print videos info
     *
     * @param videos list of videos
     */
    private void printClips(List<ClipInfo> videos) {
        String urlYoutube = "http://www.youtube.com";
        for (ClipInfo clip : videos) {
            if (clip.getLink().contains(urlYoutube)) {
                System.out.println(clip.getLink() + " " + clip.getName() + " " + clip.getViewscount()+" ");
            } else {
                System.out.println(urlYoutube + clip.getLink() + " " + clip.getName() + " " + clip.getViewscount()+" ");
            }
        }
    }
}
