package org.snoopdesigns.props.crawler;

public class CrawlParameters {

    private Integer maxPages;
    private boolean singleRoom;
    private boolean doubleRoom;

    public CrawlParameters(Integer maxPages, boolean singleRoom, boolean doubleRoom) {
        this.maxPages = maxPages;
        this.singleRoom = singleRoom;
        this.doubleRoom = doubleRoom;
    }

    public Integer getMaxPages() {
        return maxPages;
    }

    public boolean isSingleRoom() {
        return singleRoom;
    }

    public boolean isDoubleRoom() {
        return doubleRoom;
    }
}
