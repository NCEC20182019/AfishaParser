package parsers.updates_for_events;

import parsers.Event;
import parsers.Parser;
import parsers.TwitterParser.TwitterParser;
import parsers.afisha_parser.CinemaParser;

import java.util.ArrayList;

public class EventUpdates {
    private String urlToTweet;
    private String textFromTweet;
    private String urlToPicFromTweet;
    private Event event;

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public static ArrayList<EventUpdates> findUpdatesForEventInTwitter(Event event){
        ArrayList<EventUpdates> updates = new ArrayList<>();
        ArrayList<String> tags = event.getTags();
        TwitterParser tp = new TwitterParser();
        for(String tag : tags){
            TweetsSearcher.search(tag);
            updates.addAll(tp.getEventUpdates(event));
        }

        return updates;
    }

    public static void main(String[] args) {
        ArrayList<EventUpdates> allUpdates = new ArrayList<>();
        int i = 0;
        ArrayList<Event> events = new CinemaParser().getEvents(Parser.getDocument("https://www.afisha.ru/voronezh/schedule_cinema/?view=list"));
                //Parser.parseEveryting();
        System.out.println("Events size=" + events.size());
        //for(Event e : events){
        //for(int j = 0; j < 3; j++){
            System.out.println("Event #" + i++);
            allUpdates.addAll(EventUpdates.findUpdatesForEventInTwitter(events.get(0)));
        //}
        //101 апдейт на 7 тегов!!! Как такое может быть????????
        System.out.println("allUpdates size=" + allUpdates.size());
        for( EventUpdates eu : allUpdates)
            eu.show();
    }

    public String getUrlToTweet() {
        return urlToTweet;
    }

    public void setUrlToTweet(String urlToTweet) {
        this.urlToTweet = urlToTweet;
    }

    public String getTextFromTweet() {
        return textFromTweet;
    }

    public void setTextFromTweet(String textFromTweet) {
        this.textFromTweet = textFromTweet;
    }

    public String getUrlToPicFromTweet() {
        return urlToPicFromTweet;
    }

    public void setUrlToPicFromTweet(String urlToPicFromTweet) {
        this.urlToPicFromTweet = urlToPicFromTweet;
    }

    public EventUpdates(String urlToTweet, String textFromTweet, String urlToPicFromTweet, Event event) {
        this.event = event;
        this.urlToTweet = urlToTweet;
        this.textFromTweet = textFromTweet;
        this.urlToPicFromTweet = urlToPicFromTweet;
    }
    public void show(){
        System.out.println("=================================================");
        event.show();
        System.out.println(urlToTweet);
        System.out.println(textFromTweet);
        System.out.println(urlToPicFromTweet);
    }
}
