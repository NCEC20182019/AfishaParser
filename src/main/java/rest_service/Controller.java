package rest_service;

import parser.*;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.util.ArrayList;

import static parser.Parser.*;

@RestController
public class Controller {
    private final static String AFISHA_CINEMA_URL = "https://www.afisha.ru/voronezh/schedule_cinema/?view=list";
    private final static String AFISHA_EXHIBITION_URL = "https://www.afisha.ru/voronezh/schedule_exhibition/?view=list";
    private final static String AFISHA_THEATRE_URL = "https://www.afisha.ru/voronezh/schedule_theatre/?view=list";
    private final static String AFISHA_CONCERT_URL = "https://www.afisha.ru/voronezh/schedule_concert/?view=list";






    //теперь в основном для тестирования т.к. есть POST в EventService
    @RequestMapping(value = "/Events", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<Event> getEvents(@RequestParam(value = "type_of_event", required = true, defaultValue = "cinema") String typeOfEvent) throws IOException {
        Parser p = ParserFactory.getParser(typeOfEvent);
        switch (typeOfEvent) {
            case "cinema" : {
                return p.parseUsingHtmlAttributes(Parser.getDocument(AFISHA_CINEMA_URL));
            }
            case "exhibition" : {
                return p.parseUsingHtmlAttributes(Parser.getDocument(AFISHA_EXHIBITION_URL));
            }

            case "theatre" : {
                return p.parseUsingHtmlAttributes(Parser.getDocument(AFISHA_THEATRE_URL));
            }

            case "concert" : {
                return p.parseUsingHtmlAttributes(Parser.getDocument(AFISHA_CONCERT_URL));
            }
        }

        return new ArrayList<Event>();
    }
//Для тестирования работоспособности postEvents
    @RequestMapping(value = "/Event", method = RequestMethod.POST)
    public Event testPostEvent(@RequestBody Event e){
        e.show();
        return e;
    }

//заставляет отправить обновление в EventService
    @RequestMapping(value = "/parse_all", method = RequestMethod.POST)
    public String postEvents(@RequestBody String urlWhatToParse) throws IOException {
        if(urlWhatToParse.equals("https://www.afisha.ru")) {
            try {
                PostToEventService.postAll(ParserFactory.getParser("cinema").parseUsingHtmlAttributes(Parser.getDocument(AFISHA_CINEMA_URL)));
                PostToEventService.postAll(ParserFactory.getParser("exhibition").parseUsingHtmlAttributes(Parser.getDocument(AFISHA_EXHIBITION_URL)));
                PostToEventService.postAll(ParserFactory.getParser("theatre").parseUsingHtmlAttributes(Parser.getDocument(AFISHA_THEATRE_URL)));
                PostToEventService.postAll(ParserFactory.getParser("concert").parseUsingHtmlAttributes(Parser.getDocument(AFISHA_CONCERT_URL)));
                return "OK";
            }
            catch (Exception e){
                return "Someting gone wrong";
            }

        }
        return "error";
    }


}
