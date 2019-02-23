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
                return p.parse(Parser.getDocument(AFISHA_CINEMA_URL), TYPE_OF_EVENT_CINEMA);
            }
            case "exhibition" : {
                return p.parse(Parser.getDocument(AFISHA_EXHIBITION_URL), TYPE_OF_EVENT_EXHIBITION);
            }

            case "theatre" : {
                return p.parse(Parser.getDocument(AFISHA_THEATRE_URL), TYPE_OF_EVENT_THEATRE);
            }

            case "concert" : {
                return p.parse(Parser.getDocument(AFISHA_CONCERT_URL), TYPE_OF_EVENT_CONCERT);
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
    @RequestMapping(value = "/parse_all")
    public void postEvents() throws IOException {
        PostToEventService.postAll(ParserFactory.getParser(TYPE_OF_EVENT_CINEMA).parse(Parser.getDocument(AFISHA_CINEMA_URL), TYPE_OF_EVENT_CINEMA));
        PostToEventService.postAll(ParserFactory.getParser(TYPE_OF_EVENT_EXHIBITION).parse(Parser.getDocument(AFISHA_EXHIBITION_URL), TYPE_OF_EVENT_EXHIBITION));
        PostToEventService.postAll(ParserFactory.getParser(TYPE_OF_EVENT_THEATRE).parse(Parser.getDocument(AFISHA_THEATRE_URL), TYPE_OF_EVENT_THEATRE));
        PostToEventService.postAll(ParserFactory.getParser(TYPE_OF_EVENT_CONCERT).parse(Parser.getDocument(AFISHA_CONCERT_URL), TYPE_OF_EVENT_CONCERT));
    }


}
