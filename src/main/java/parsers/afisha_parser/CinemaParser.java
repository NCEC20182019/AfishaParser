package parsers.afisha_parser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import parsers.Event;
import parsers.Parser;
import parsers.updates_for_events.Dictionary;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static parsers.updates_for_events.Dictionary.TypeOfEvent.CINEMA;

public class CinemaParser extends Parser {
    private final static String CINEMA_CLASS_NAMES = "list__item-name";
    private final static String CINEMA_CLASS_INFOS = "list__item-info";



    private final static String CINEMA_CLASS_TO_PARSE = "new-list__item movie-item";
    private static Logger logger = LoggerFactory.getLogger(CinemaParser.class);

    @Override
    public ArrayList<Event> getEvents(Document afisha) {
        ArrayList<Event> events = new ArrayList<>();
        String title, description, source_url, image_url;

        Elements elems = afisha.getElementsByAttributeValue("class", CINEMA_CLASS_TO_PARSE);

        try {
            for (Element el : elems) {
                title = el.child(0).child(0).attr("content");
                image_url = el.child(0).child(3).attr("content");
                description = el.child(0).child(el.child(0).children().size() - 1).attr("content");// всегда в последнем child'e, но номер меняется из-за
                source_url = AFISHA_URL + el.child(1).child(0).child(0).attr("href");              //разного количества режиссёров
                events.add(new Event(title, source_url, description, "", CINEMA, image_url, null, null));
                events.get(events.size() - 1).setTags();
            }
            logger.info("Cinemas from afisha.ru were parsed");
        } catch (Exception e) {
            logger.warn("placement of data in html code was changed", e);
        }

        return events;
    }







    @Override
    public Elements[] getElems(Document afisha) {
        Elements[] elms = new Elements[3];
        elms[0] = afisha.getElementsByAttributeValue("class", CINEMA_CLASS_NAMES);
        elms[1] = afisha.getElementsByAttributeValue("class", CINEMA_CLASS_INFOS);
        elms[2] = null;
        return elms;
    }

    @Override
    public String getLocation(Element e) {
        return null;
    }

    @Override
    public LocalDateTime getTime(Event event, Element e) {
        return null;
    }

    @Override
    public Dictionary.TypeOfEvent getTypeOfEvent() {
        return CINEMA;
    }


}
