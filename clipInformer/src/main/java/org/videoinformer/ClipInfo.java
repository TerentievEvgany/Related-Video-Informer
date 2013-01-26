/**
 * Copyright (C) 2013-2013 Evgany Terentiev - All rights reserved.
 */

package org.videoinformer;

/**
 * Class of video description
 *
 * @author Evgany Terentiev
 */
public class ClipInfo {

    /**
     * Name of video
     */
    private String name;
    /**
     * URL of video
     */
    private String link;
    /**
     * Views count of video
     */
    private String viewscount;

    /**
     * Get name of video
     *
     * @return name of video
     */
    public String getName() {
        return name;
    }

    /**
     * Get URL of video
     *
     * @return URL of video
     */
    public String getLink() {
        return link;
    }

    /**
     * Get views count of video
     *
     * @return views count of video
     */
    public String getViewscount() {
        return viewscount;
    }

    /**
     * Set name for video
     *
     * @param name name for video
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set URL for video
     *
     * @param link URL for video
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * Set views count for video
     *
     * @param viewscount views count for video
     */
    public void setViewscount(String viewscount) {
        this.viewscount = viewscount;
    }
}
