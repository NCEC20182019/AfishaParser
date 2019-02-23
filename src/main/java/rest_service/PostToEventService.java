package rest_service;

import parser.Event;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

public class PostToEventService {
//Выставить необходимый урл
    private static final String TEST_EVENTS_SERVICE_URL = "http://localhost:8082/Event";
    private static final String REAL_EVENTS_SERVICE_URL = "http://localhost:8081/event/create/";

    private PostToEventService(){};

    public static void  post(Event event){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate rt = new RestTemplate();
        // Data attached to the request.
        HttpEntity<Event> requestBody = new HttpEntity<>(event, headers);
        // Send request with POST method.
        Event e = rt.postForObject(TEST_EVENTS_SERVICE_URL, requestBody, Event.class);
//для теста
    }

    public static void postAll(ArrayList<Event> events){
        for(Event e : events){
            PostToEventService.post(e);
        }
    }

}
