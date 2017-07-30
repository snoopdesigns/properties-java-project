package org.snoopdesigns.props.crawler;

public class CrawlParameters {

    private boolean singleRoom;
    private boolean doublRoom;

    public CrawlParameters(boolean singleRoom, boolean doublRoom) {
        this.singleRoom = singleRoom;
        this.doublRoom = doublRoom;
    }

    public boolean isSingleRoom() {
        return singleRoom;
    }

    public boolean isDoublRoom() {
        return doublRoom;
    }
}
