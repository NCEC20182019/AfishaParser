package rest_service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import parsers.Event;
import parsers.updates_for_events.EventUpdate;

import java.util.ArrayList;

public class PostUpdateToEventService {
    private static final String TEST_UPDATE_SERVICE_URL    =  "http://localhost:8094/parser/update";
    private static final String REAL_UPDATE_SERVICE_URL    =  "http://localhost:8092/update/create/";
    private static final String SERVER_UPDATE_SERVICE_URL  =  "http://lemmeknow.tk:8092/update/create/";
    private static final String SERVER1_UPDATE_SERVICE_URL =  "/update/create";

    private PostUpdateToEventService(){};

    public static void  postUpdate(EventUpdate update){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate rt = new RestTemplate();
        // Data attached to the request.
        HttpEntity<EventUpdate> requestBody = new HttpEntity<>(update, headers);
        // Send request with POST method.
        EventUpdate e = rt.postForObject(REAL_UPDATE_SERVICE_URL, requestBody, EventUpdate.class);
//для теста
    }

    public static void postAll(ArrayList<EventUpdate> updates){
        for(EventUpdate eu : updates){
            PostUpdateToEventService.postUpdate(eu);
        }
    }


}
