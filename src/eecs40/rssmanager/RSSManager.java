package eecs40.rssmanager;

import eecs40.observer.RSSFeedObserver;
import eecs40.rssfeed.RSSFeedInterface;

import java.util.List;
import java.util.ArrayList;

public class RSSManager extends AbstractRSSManager {

    List<RSSFeedInterface> feedInterfaceList = new ArrayList<>();
    List<RSSFeedObserver> feedObserverList = new ArrayList<>();
    long interval;

    @Override
    public void addFeed(RSSFeedInterface f) {
        feedInterfaceList.add(f);
    }

    @Override
    public void removeFeed(RSSFeedInterface f) {
        feedInterfaceList.remove(f);
    }

    @Override
    public List<RSSFeedInterface> getFeedList() {
        return feedInterfaceList;
    }

    @Override
    public void addObserver(RSSFeedObserver ob) {
        feedObserverList.add(ob);
    }

    @Override
    public void removeObserver(RSSFeedObserver ob) {
        feedObserverList.remove(ob);
    }

    @Override
    public List<RSSFeedObserver> getObserverList() {
        return feedObserverList;
    }

    @Override
    public void setInterval(long interval) {
        this.interval = interval;
    }

    @Override
    public long getInterval() {
        return interval;
    }
}
