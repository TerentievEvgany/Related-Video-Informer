/**
 * Copyright (C) 2013-2013 Evgany Terentiev - All rights reserved.
 */

package org.videoinformer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.commons.validator.routines.UrlValidator;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.DomSerializer;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;

/**
 * Class that get information from youtube.com
 *
 * @author Evgany Terentiev
 */
public class YoutubeClient {

    /**
     * Constant for host name which needs to execute GET request
     */
    private static final String HOST_NAME = "www.youtube.com";

    /**
     * This method create Http client, then send GET request and recive response
     * from server. After read body of response message and create valid XML
     * DOM, that must be returned.
     *
     * @param url for GET request.
     * @return valid XML DOM/
     */
    public org.w3c.dom.Document getDataSource(String url) {

        org.w3c.dom.Document doc = null;
        StringBuilder responsePageBuilder = new StringBuilder();
        DefaultHttpClient client = new DefaultHttpClient();
        try {
            if (url == null) {
                throw new HttpClientException("Empty URL parametr");
            }
        } catch (HttpClientException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        try {
            HttpGet getRequest = new HttpGet(validate(url));
            HttpResponse response = client.execute(getRequest);
            if (response.getStatusLine().getStatusCode() != 200) {
                throw new HttpClientException("Failed : HTTP error code : "
                        + response.getStatusLine().getStatusCode());
            }
            BufferedReader responseReader = new BufferedReader(
                    new InputStreamReader((response.getEntity().getContent())));
            String output;
            while ((output = responseReader.readLine()) != null) {
                responsePageBuilder.append(output).append('\n');
            }
            TagNode tagNode = new HtmlCleaner().clean(responsePageBuilder.toString());
            try {
                doc = new DomSerializer(new CleanerProperties()).createDOM(tagNode);
            } catch (ParserConfigurationException ex) {
                System.out.println("Can not create XML DOM");
            }
        } catch (ClientProtocolException e) {
            System.out.println("HTTP protocol error");
        } catch (IOException e) {
            System.out.println("Can not find input data");
        } catch (HttpClientException e) {
            System.out.println(e.getMessage());
        } finally {
            client.getConnectionManager().shutdown();
        }
        return doc;
    }

    /**
     * Check valid URL syntax or not, if valid, then checks host name which has
     * to be youtube. If validation successful, return URL, else exit from
     * programm.
     *
     * @param url for validation check
     * @return checked URL
     */
    private String validate(String url) {
        UrlValidator urlValidator = new UrlValidator();
        URL validUrl = null;
        try {
            if (urlValidator.isValid(url)) {
                System.out.println("Url is valid");
                try {
                    validUrl = new URL(url);
                } catch (MalformedURLException ex) {
                    System.out.println("No legal protocol could be found in a "
                            + "specification string or the string could not be parsed");
                }
                try {
                    if (!validUrl.getHost().equals(HOST_NAME)) {
                        throw new HttpClientException("Invalid host name");
                    }
                } catch (HttpClientException e) {
                    System.out.println(e.getMessage());
                    System.exit(0);
                }
            } else {
                throw new HttpClientException("Invalid url format");
            }
        } catch (HttpClientException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        return url;
    }
}
