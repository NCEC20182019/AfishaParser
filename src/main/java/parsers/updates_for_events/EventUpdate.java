package parsers.updates_for_events;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import parsers.Event;
import parsers.EventDTO;
import parsers.Parser;
import parsers.TwitterParser.TwitterParser;
import parsers.afisha_parser.CinemaParser;

import java.util.ArrayList;
import java.util.Date;

public class EventUpdate {
    private final static String URL_FOR_BATCH_FROM_EVENT_SERVICE = "http://localhost/updates/batch";
    private final static String REAL_URL_FOR_BATCH_FROM_EVENT_SERVICE = "http://lemmeknow.tk/updates/batch";


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

    public ArrayList<EventUpdate> update(ArrayList<EventDTO> events){
        for(EventDTO e : events){
            e.createTags();
            findUpdatesForEventInTwitter(e);
        }
        return new ArrayList<>();
    }



    public ArrayList<EventUpdate> findUpdatesForEventInTwitter(EventDTO event){
        //ArrayList<EventUpdate> updates = new ArrayList<>();

        TwitterParser tp = new TwitterParser();
        for(String tag : event.getTags()){
            TweetsSearcher.search(tag);
            tp.getEventUpdates(event);
        }
        return new ArrayList<>();
    }


    /* public static void main(String[] args) {

         ArrayList<EventDTO> eventDTOS = new ArrayList<>();
         ArrayList<Event> events = new CinemaParser().getEvents(Parser.getDocument("https://www.afisha.ru/voronezh/schedule_cinema/?view=list"));
         //for (Event e : new CinemaParser().getEvents(Parser.getDocument("https://www.afisha.ru/voronezh/schedule_cinema/?view=list")))
             //eventDTOS.add(new EventDTO().EventToDTO(e));
         eventDTOS.add(new EventDTO().EventToDTO(events.get(0)));
         eventDTOS.add(new EventDTO().EventToDTO(events.get(1)));

         for (EventDTO dto : eventDTOS)
             for(EventUpdate eu : new EventUpdate().findUpdatesForEventInTwitter(dto))
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
