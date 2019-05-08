package rest_service;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import parsers.*;
import org.springframework.web.bind.annotation.*;
import parsers.updates_for_events.Dictionary;
import parsers.updates_for_events.EventUpdate;
import parsers.vk_parser.VkApiParser.VkEventsApiParser;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static parsers.updates_for_events.Dictionary.TypeOfEvent.*;

@RestController
@RequestMapping(value = "parser", produces = MediaType.APPLICATION_JSON_VALUE)
public class Controller {
    private final static String AFISHA_CINEMA_URL = "https://www.afisha.ru/voronezh/schedule_cinema/?view=list";
    private final static String AFISHA_EXHIBITION_URL = "https://www.afisha.ru/voronezh/schedule_exhibition/?view=list";
    private final static String AFISHA_THEATRE_URL = "https://www.afisha.ru/voronezh/schedule_theatre/?view=list";
    private final static String AFISHA_CONCERT_URL = "https://www.afisha.ru/voronezh/schedule_concert/?view=list";
    private final static String VK_EVENT_URL = "https://vkevent.ru/city42/popular/";

    private Logger logger = LoggerFactory.getLogger(Controller.class);






    //теперь в основном для тестирования т.к. есть POST в EventService
    @RequestMapping(value = "/events", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<Event> getEvents(@RequestParam(value = "type_of_event", required = true, defaultValue = "cinema") String typeOfEvent) throws IOException {
        Parser p = ParserFactory.getParser(Dictionary.getTypeOfEventByName(typeOfEvent));
        switch (typeOfEvent) {
            case "cinema" : {
                PostEventToEventService.postAll(ParserFactory.getParser(CINEMA)
                        .parseUsingHtmlAttributes(Parser.getDocument(AFISHA_CINEMA_URL)));
                return p.parseUsingHtmlAttributes(Parser.getDocument(AFISHA_CINEMA_URL));
            }
            case "exhibition" : {
                //PostEventToEventService.postAll(ParserFactory.getParser(EXHIBITION)
                  //      .parseUsingHtmlAttributes(Parser.getDocument(AFISHA_EXHIBITION_URL)));
                return p.parseUsingHtmlAttributes(Parser.getDocument(AFISHA_EXHIBITION_URL));
            }

            case "theatre" : {
                //PostEventToEventService.postAll(ParserFactory.getParser(THEATRE)
                  //      .parseUsingHtmlAttributes(Parser.getDocument(AFISHA_THEATRE_URL)));
                return p.parseUsingHtmlAttributes(Parser.getDocument(AFISHA_THEATRE_URL));
            }

            case "concert" : {
                //PostEventToEventService.postAll(ParserFactory.getParser(CONCERT)
                 //       .parseUsingHtmlAttributes(Parser.getDocument(AFISHA_CONCERT_URL)));
                return p.parseUsingHtmlAttributes(Parser.getDocument(AFISHA_CONCERT_URL));
            }
            case "vk_event" : {
                try {
                    PostEventToEventService.postAll(new VkEventsApiParser().getEvents());
                    return new VkEventsApiParser().getEvents();

                } catch (ClientException e) {
                    e.printStackTrace();
                } catch (ApiException e) {

                }
            }
        }

        return new ArrayList<Event>();
    }
//Для тестирования работоспособности postEvents
    @RequestMapping(value = "/event", method = RequestMethod.POST)
    public Event testPostEvent(@RequestBody Event e){
        e.show();
        return e;
    }
    //Для тестирования работоспособности postUpdates
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public EventUpdate testPostEvent(@RequestBody EventUpdate eu){
        eu.show();
        return eu;
    }

//заставляет отправить обновление в EventService
    @RequestMapping(value = "/parse", method = RequestMethod.POST)
    public String postEvents(@RequestBody String urlWhatToParse) throws IOException {
        try {
        switch (urlWhatToParse) {
            case AFISHA_CINEMA_URL : {
                PostEventToEventService.postAll(ParserFactory.getParser(CINEMA)
                        .parseUsingHtmlAttributes(Parser.getDocument(AFISHA_CINEMA_URL)));
                logger.info(urlWhatToParse + " parsed");
                return "OK";
            }
                case AFISHA_EXHIBITION_URL : {
                    PostEventToEventService.postAll(ParserFactory.getParser(EXHIBITION)
                            .parseUsingHtmlAttributes(Parser.getDocument(AFISHA_EXHIBITION_URL)));
                    logger.info(urlWhatToParse + " parsed");
                    return "OK";
                }
                case AFISHA_THEATRE_URL : {
                    PostEventToEventService.postAll(ParserFactory.getParser(THEATRE)
                            .parseUsingHtmlAttributes(Parser.getDocument(AFISHA_THEATRE_URL)));
                    logger.info(urlWhatToParse + " parsed");
                    return "OK";
                }
                case AFISHA_CONCERT_URL : {
                    PostEventToEventService.postAll(ParserFactory.getParser(CONCERT)
                            .parseUsingHtmlAttributes(Parser.getDocument(AFISHA_CONCERT_URL)));
                    logger.info(urlWhatToParse + " parsed");
                    return "OK";
                }
                case VK_EVENT_URL : {
                    PostEventToEventService.postAll(new VkEventsApiParser().getEvents());
                    logger.info(urlWhatToParse + " parsed");
                    return "OK";
                }
                case "TWITTER" : {
                    new EventUpdate().update();
                    logger.info(urlWhatToParse + " parsed");
                    return "OK";
                }

        }
            return "Wrong parameter";

        }catch (Exception e){
            logger.error("couldn't parse " + urlWhatToParse, e);
            return "Someting gone wrong";
        }
    }




}
