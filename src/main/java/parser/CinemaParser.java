package parser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ListIterator;

public class CinemaParser extends Parser {
    private final static String CINEMA_CLASS_NAMES = "list__item-name";
    private final static String CINEMA_CLASS_INFOS = "list__item-info";

    private final static String TYPE_OF_EVENT_CINEMA = "cinema";

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
                description = el.child(0).child(5).attr("content");
                source_url = AFISHA_URL + el.child(1).child(0).child(0).attr("href");
                events.add(new Event(title, source_url, description, "", TYPE_OF_EVENT_CINEMA, image_url, null, null));
            }
            logger.info("Cinemas from afisha.ru were parsed");
        }catch (Exception e){
        logger.warn("placement of data in html code was changed");
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
    public String getTypeOfEvent() {
        return TYPE_OF_EVENT_CINEMA;
    }




}
