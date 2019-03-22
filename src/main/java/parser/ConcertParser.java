package parser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.*;


public class ConcertParser extends Parser {
    private final static String CONCERT_CLASS_NAMES = "list__item-name";
    private final static String CONCERT_CLASS_INFOS = "list__item-desc-list";
    private final static String CONCERT_CLASS_TIMES = "list__item-desc-time";

    private final static String TYPE_OF_EVENT_CONCERT = "concert";
    private final static String CONCERT_CLASS_TO_PARSE = "new-list__item concert-item";

    private static Logger logger = LoggerFactory.getLogger(ConcertParser.class);


    @Override
    public ArrayList<Event> getEvents(Document afisha) {
        ArrayList<Event> events = new ArrayList<>();
        String title, source_url, image_url, location;
        LocalDateTime date_start;

        Elements elems = afisha.getElementsByAttributeValue("class", CONCERT_CLASS_TO_PARSE);


        try {
            for (Element el : elems) {
                title = el.child(0).child(0).attr("content");
                date_start = LocalDateTime.parse(el.child(0).child(2).attr("content"));
                location = el.child(0).child(3).child(0).attr("content");
                image_url = el.child(0).child(5).attr("content");
                source_url = AFISHA_URL + el.child(1).child(0).child(0).attr("href");
                events.add(new Event(title, source_url, "", location, TYPE_OF_EVENT_CONCERT, image_url, date_start, null));
            }
            logger.info("Concerts from afisha.ru were parsed");
        } catch (Exception e){
            logger.warn("placement of data in html code was changed");
        }

        return events;
    }









    @Override
    public Elements[] getElems(Document afisha) {
        Elements[] elms = new Elements[3];
        elms[0] = afisha.getElementsByAttributeValue("class", CONCERT_CLASS_NAMES);
        elms[1] = afisha.getElementsByAttributeValue("class", CONCERT_CLASS_INFOS);
        elms[2] = afisha.getElementsByAttributeValue("class", CONCERT_CLASS_TIMES);
        return elms;
    }

    @Override
    public String getLocation(Element e) {
        return e.text();
    }



    @Override
    public String getTypeOfEvent() {
        return TYPE_OF_EVENT_CONCERT;
    }

    @Override
    public LocalDateTime getTime(Event event, Element e) {
        try {
             return new TimeParser().toDate(e.text());
        } catch (ParseException ex) {
            ex.printStackTrace();
            return null;
        }
    }


}
