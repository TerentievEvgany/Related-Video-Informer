/**
 * Copyright (C) 2013-2013 Evgany Terentiev - All rights reserved.
 */

package org.videoinformer;

public class VideoInformer {

    public static void main(String[] args) {
        String urlConnection = null;
        if (args.length > 0) {
            urlConnection = args[0];
        }
        YoutubeClient client = new YoutubeClient();
        org.w3c.dom.Document document = client.getDataSource(urlConnection);
        Parser pageParser = new Parser();
        pageParser.parse(document);
    }
}
