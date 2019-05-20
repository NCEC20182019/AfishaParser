package rest_service;

import parsers.Event;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

public class PostEventToEventService {
//Выставить необходимый урл
    private static final String TEST_EVENT_SERVICE_URL    =  "http://localhost:8094/parser/event";
    private static final String REAL_EVENT_SERVICE_URL    =  "http://localhost:8092/events/create/";
    private static final String SERVER_EVENT_SERVICE_URL  =  "http://lemmeknow.tk:8092/events/create/";
    private static final String SERVER1_EVENT_SERVICE_URL =  "/events/create";

    private PostEventToEventService(){};

    public static void  postEvent(Event event){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate rt = new RestTemplate();
        // Data attached to the request.
        HttpEntity<Event> requestBody = new HttpEntity<>(event, headers);
        // Send request with POST method.
        Event e = rt.postForObject(REAL_EVENT_SERVICE_URL, requestBody, Event.class);
//для теста
    }

    public static void postAll(ArrayList<Event> events){
        for(Event e : events){
            PostEventToEventService.postEvent(e);
        }
    }

}
