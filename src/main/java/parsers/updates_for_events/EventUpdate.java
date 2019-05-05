package parsers.updates_for_events;

import parsers.Event;
import parsers.Parser;
import parsers.TwitterParser.TwitterParser;

import java.util.ArrayList;

public class EventUpdate {
    private String url_to_tweet;
    private String text_from_tweet;
    private String url_to_pic_from_tweet;
    private Event event;
    private int event_id;

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public static ArrayList<EventUpdate> findUpdatesForEventInTwitter(Event event){
        ArrayList<EventUpdate> updates = new ArrayList<>();
        ArrayList<String> tags = event.getTags();
        TwitterParser tp = new TwitterParser();
        for(String tag : tags){
            TweetsSearcher.search(tag);
            updates.addAll(tp.getEventUpdates(event));
        }

        return updates;
    }

    public static void main(String[] args) {
        ArrayList<EventUpdate> allUpdates = new ArrayList<>();
        int i = 0;
        ArrayList<Event> events = Parser.parseEveryting();

                //new CinemaParser().getEvents(Parser.getDocument("https://www.afisha.ru/voronezh/schedule_cinema/?view=list"));
                //Parser.parseEveryting();
        System.out.println("Events size=" + events.size());
        for(Event e : events){
            System.out.println("Event #" + i++);
            for(EventUpdate eu : EventUpdate.findUpdatesForEventInTwitter(e))
                eu.show();
        }
        //101 апдейт на 7 тегов!!! Как такое может быть????????
        /*System.out.println("allUpdates size=" + allUpdates.size());
        for( EventUpdate eu : allUpdates)
            eu.show();*/
    }

    public EventUpdate() {
    }

    public String getUrl_to_tweet() {
        return url_to_tweet;
    }

    public void setUrl_to_tweet(String url_to_tweet) {
        this.url_to_tweet = url_to_tweet;
    }

    public String getText_from_tweet() {
        return text_from_tweet;
    }

    public void setText_from_tweet(String text_from_tweet) {
        this.text_from_tweet = text_from_tweet;
    }

    public String getUrl_to_pic_from_tweet() {
        return url_to_pic_from_tweet;
    }

    public void setUrl_to_pic_from_tweet(String url_to_pic_from_tweet) {
        this.url_to_pic_from_tweet = url_to_pic_from_tweet;
    }

    public EventUpdate(String url_to_tweet, String text_from_tweet, String url_to_pic_from_tweet, Event event, int event_id) {
        this.event = event;
        this.url_to_tweet = url_to_tweet;
        this.text_from_tweet = text_from_tweet;
        this.url_to_pic_from_tweet = url_to_pic_from_tweet;
        this.event_id = event_id;
    }
    public void show(){
        System.out.println("=================================================");
        System.out.println(event.getTitle());
        System.out.println(event_id);
        System.out.println(url_to_tweet);
        System.out.println(text_from_tweet);
        System.out.println(url_to_pic_from_tweet);
    }
}
