package main;

import de.vogella.rss.model.FeedMessage;
import eecs40.observer.RSSFeedObserver;
import eecs40.rssfeed.RSSFeed;
import eecs40.rssfeed.RSSFeedInterface;
import eecs40.rssmanager.AbstractRSSManager;
import eecs40.rssmanager.RSSManager;
import eecs40.rssmanager.RSSManagerInterface;

public class Main implements RSSFeedObserver{
    public static void main(String[] args){
        Main m = new Main();

        RSSFeedInterface f1 = new RSSFeed("http://rss.cnn.com/rss/edition.rss", "CNN News");
        RSSFeedInterface f2 = new RSSFeed("http://feeds.foxnews.com/foxnews/latest", "Fox News");
        RSSFeedInterface f3 = new RSSFeed("http://lorem-rss.herokuapp.com/feed","News");
        // Create RSSManager

        RSSManagerInterface rm = new RSSManager();
        // Add current class's object as a listener
        rm.addObserver(m);

        // set fetch internal
        rm.setInterval(10000); // 10000ms, 10 sec

        // Add feed
        rm.addFeed(f1);
        rm.addFeed(f2);
        rm.addFeed(f3);

        // run RSSManager in background
        new Thread(rm).start();
    }

    @Override
    public void newFeedArrived(RSSFeedInterface f){
        System.out.println(f.getFeed().getTitle() + " has update: " + f.getLastNumUpdate());
        int count = 0;
        for(FeedMessage fm : f.getFeed().getMessages()){
            System.out.println(fm);
            if(++count == f.getLastNumUpdate()){
                break;
            }
        }
    }

}
