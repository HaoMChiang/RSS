package eecs40.rssfeed;

import de.vogella.rss.model.Feed;
import de.vogella.rss.model.FeedMessage;
import de.vogella.rss.read.RSSFeedParser;

import java.util.HashSet;
import java.util.Iterator;
import java.util.ArrayList;

public class RSSFeed implements RSSFeedInterface {

    String link;
    String website;
    Feed feed;
    int size;
    int latestUpdateSize;
    HashSet<String> history = new HashSet<>();
    ArrayList<FeedMessage> current = new ArrayList<>();

   public RSSFeed(String link, String website){
       this.link = link;
       this.website = website;
   }

    @Override
    public Feed getFeed() {
       return feed;
    }

    @Override
    public int read() {
       int preSize = size;
       RSSFeedParser parser = new RSSFeedParser(link);
       feed = parser.readFeed();
       for(int i = 0; i<feed.getMessages().size(); i++){
            if(history.contains(feed.getMessages().get(i).getGuid())){
                feed.getMessages().remove(i);
            } else if(!history.contains(feed.getMessages().get(i).getGuid())){
                history.add(feed.getMessages().get(i).getGuid());
                current.add(0,feed.getMessages().get(i));
            }
       }
       int currentSize = size();
       latestUpdateSize = currentSize-preSize;
       size = currentSize;

       return latestUpdateSize;
    }

    @Override
    public int size() {
       return history.size();
    }

    @Override
    public int getLastNumUpdate() {
        return latestUpdateSize;
    }

    @Override
    public Iterator<FeedMessage> iterator() {
       return feed.getMessages().iterator();
    }
}
