package rest_service;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;


public class Test {
    //JUnit
    //Mockito
    //Postman

    private static final String URL_EVENTS_CINEMA = "http://localhost:8080/Event?type_of_event=cinema";
    private static final String URL_EVENTS_THEATRE = "http://localhost:8080/Event?type_of_event=theatre";
    private static final String URL_EVENTS_EXHIBITION = "http://localhost:8080/Event?type_of_event=exhibition";
    private static final String URL_EVENTS_CONCERT = "http://localhost:8080/Event?type_of_event=concert";

    public static void callYourselfSimple() {
        RestTemplate rt = new RestTemplate();

        System.out.println(rt.getForObject(URL_EVENTS_THEATRE, String.class));
        System.out.println(rt.getForObject(URL_EVENTS_EXHIBITION, String.class));
        System.out.println(rt.getForObject(URL_EVENTS_CINEMA, String.class));
        System.out.println(rt.getForObject(URL_EVENTS_CONCERT, String.class));
    }

    public static void main(String[] args) {
        Test.callYourself();
        Test.callYourselfSimple();
    }

    public static void callYourself(){
        // HttpHeaders
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        // HttpEntity<Employee[]>: To get result as Employee[].
        HttpEntity<?> entity = new HttpEntity<>(headers);
        // RestTemplate
        RestTemplate restTemplate = new RestTemplate();
        // Send request with GET method, and Headers.
        ResponseEntity<String> response = restTemplate.exchange(URL_EVENTS_CINEMA, HttpMethod.GET, entity, String.class);
        HttpStatus statusCode = response.getStatusCode();
        System.out.println("Response Satus Code: " + statusCode);
        // Status Code: 200
        if (statusCode == HttpStatus.OK)
            System.out.println(response);
    }
}
