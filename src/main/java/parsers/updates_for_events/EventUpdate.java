package parsers.updates_for_events;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import parsers.Event;
import parsers.EventDTO;
import parsers.Parser;
import parsers.TwitterParser.TwitterParser;
import rest_service.PostUpdateToEventService;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

public class EventUpdate {
    private final static String URL_FOR_BATCH_FROM_EVENT_SERVICE = "http://localhost:8092/update/batch";
    private final static String REAL_URL_FOR_BATCH_FROM_EVENT_SERVICE = "http://lemmeknow.tk:8092/update/batch";


    private String url_to_tweet;
    private String text_from_tweet;
    private String url_to_pic_from_tweet;
    private int event_id;
    private Date last_update_date;

    public Date getLast_update_date() {
        return last_update_date;
    }

    public void setLast_update_date(Date last_update_date) {
        this.last_update_date = last_update_date;
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }



    public ArrayList<EventDTO> getEventFromEventService(){
        RestTemplate rt = new RestTemplate();
        ResponseEntity<ArrayList<EventDTO>> response = rt.exchange(URL_FOR_BATCH_FROM_EVENT_SERVICE, HttpMethod.GET, null
                , new ParameterizedTypeReference<ArrayList<EventDTO>>() {});
        return response.getBody();
    }

    public void update(){
        ArrayList<EventUpdate> eventUpdates = new ArrayList<>();
        for(EventDTO e : getEventFromEventService()){
            System.out.println(e);
            e.setTags();
            eventUpdates.addAll(findUpdatesForEventInTwitter(e));
        }
        PostUpdateToEventService.postAll(eventUpdates);
    }

    public static void main(String[] args) {
        new EventUpdate().update();
    }

    public ArrayList<EventUpdate> findUpdatesForEventInTwitter(EventDTO event){
        ArrayList<EventUpdate> updates = new ArrayList<>();
        ArrayList<String> tags = event.getTags();
        TwitterParser tp = new TwitterParser();
        for(String tag : tags){
            TweetsSearcher.search(tag);
            updates.addAll(tp.getEventUpdates(event));
        }
        return updates;
    }

    /*public static void main(String[] args) {
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
            eu.show();
    }*/

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

    public EventUpdate(String url_to_tweet, String text_from_tweet, String url_to_pic_from_tweet, int event_id, Date last_update_date) {
        this.url_to_tweet = url_to_tweet;
        this.text_from_tweet = text_from_tweet;
        this.url_to_pic_from_tweet = url_to_pic_from_tweet;
        this.event_id = event_id;
        this.last_update_date = last_update_date;
    }
    public void show(){
        System.out.println("=================================================");
        System.out.println(event_id);
        System.out.println(url_to_tweet);
        System.out.println(text_from_tweet);
        System.out.println(url_to_pic_from_tweet);
    }
}