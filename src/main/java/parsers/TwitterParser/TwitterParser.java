package parsers.TwitterParser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import parsers.Event;
import parsers.EventDTO;
import parsers.updates_for_events.EventUpdate;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class TwitterParser {

    private static final String TWITTER_URL = "https://twitter.com";
    private static final String TWITTER_SEARCH_RESULT_FILENAME = "TwitterSearchResult.html";
    private static final Logger logger = LoggerFactory.getLogger(TwitterParser.class);
    private static final String CLASS_WITH_TWEETS = "content";
    private static final String CLASS_WITH_TEXT = "js-tweet-text-container";
    private static final String CLASS_WITH_TWEET_URL = "tweet-timestamp js-permalink js-nav js-tooltip";//href
    //Картинка из видео (Нужна ли?)
    //<div class="PlayableMedia-player" data-playable-media-url="" data-use-react-player="" data-use-b-version-of-react-player="" data-use-player-precache="" data-border-top-left-radius="" data-border-top-right-radius="" data-border-bottom-left-radius="" data-border-bottom-right-radius=""
    // style="padding-bottom: 100.0%; background-image:url('https://pbs.twimg.com/amplify_video_thumb/1097582278912499714/img/UKrmG6hIZdppdPzT.jpg')">
    private static final String CLASS_WITH_VIDEO_PREVIEW_PIC = "PlayableMedia-player\n" +
            "        \n" +
            "        ";//child(0) от этого класса ибо название этого чайлда слишком безумно
    //Просто картинка
    //<div class="AdaptiveMedia-photoContainer js-adaptive-photo " data-image-url="https://pbs.twimg.com/media/DzcJF0kXcAAfQcU.jpg" data-element-context="platform_photo_card" style="background-color:rgba(64,36,38,1.0);" data-dominant-color="[64,36,38]">
    private static final String CLASS_WITH_PIC = "AdaptiveMedia-photoContainer js-adaptive-photo ";
    private static final String ATTR_NAME_FOR_CLASS_WITH_PIC = "data-image-url";

    public Document getDocument(String htmlFileName) throws IOException {
        File in = new File(TWITTER_SEARCH_RESULT_FILENAME);
        return Jsoup.parse(in, "UTF-8");
    }
//class="js-tweet-text-container"
//class="content"
    public ArrayList<EventUpdate> getEventUpdates(EventDTO event){
        Document doc = null;
        try {
            doc = getDocument(TWITTER_SEARCH_RESULT_FILENAME);
            logger.info("Loading " + TWITTER_SEARCH_RESULT_FILENAME + " is successful");
        } catch (IOException e) {
            logger.error("There is no " + TWITTER_SEARCH_RESULT_FILENAME, e);
            throw new RuntimeException();
        }
        ArrayList<EventUpdate> upadates = new ArrayList<>();
        String urlToTweet, textFromTweet, urlToPicFromTweet;
        Elements tweets = doc.getElementsByClass(CLASS_WITH_TWEETS);
        //Вместо n должен быть event_id
        int n = 0;
        for (Element e : tweets) {
            urlToTweet = TWITTER_URL + e.getElementsByClass(CLASS_WITH_TWEET_URL).attr("href");
            System.out.println(urlToTweet);
            textFromTweet = e.getElementsByClass(CLASS_WITH_TEXT).text();
            System.out.println(textFromTweet);
            try {
                urlToPicFromTweet = e.getElementsByClass(CLASS_WITH_PIC).attr(ATTR_NAME_FOR_CLASS_WITH_PIC);
            } catch (Exception ex) {
                urlToPicFromTweet = "";
            }
            System.out.println(urlToPicFromTweet);
            System.out.println("=================================================================");
            upadates.add(new EventUpdate(urlToTweet, textFromTweet, urlToPicFromTweet, event.getEvent_id(), new Date()));
        }



        logger.info("Parsing " + TWITTER_SEARCH_RESULT_FILENAME + " is over");
        return upadates;
    }

    /*public static void main(String[] args) throws IOException {
        TwitterParser tp = new TwitterParser();
        tp.getEventUpdates(new Event());
    }*/
}
