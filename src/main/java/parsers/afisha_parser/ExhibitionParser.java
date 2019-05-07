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

import static parsers.updates_for_events.Dictionary.TypeOfEvent.EXHIBITION;

public class ExhibitionParser extends Parser {
    private final static String EXHIBITION_CLASS_NAMES = "list__item-name";
    private final static String EXHIBITION_CLASS_INFOS = "list__item-info";
    private final static String EXHIBITION_CLASS_TIMES = "list__item-desc-time";

    private final static String EXHIBITION_CLASS_TO_PARSE = "new-list__item exhibition-item";

    private static Logger logger = LoggerFactory.getLogger(ExhibitionParser.class);

    @Override
    public ArrayList<Event> getEvents(Document afisha) {
        ArrayList<Event> events = new ArrayList<>();
        String title, source_url, image_url, location,
                description;//description есть в html, но ничего кроме <meta itemProp="description" content=""> замечено не было
        LocalDateTime date_start, date_end;

        Elements elems = afisha.getElementsByAttributeValue("class", EXHIBITION_CLASS_TO_PARSE);
        try {
            for (Element el : elems) {
                title = el.child(0).child(0).attr("content");
                date_start = LocalDateTime.parse(el.child(0).child(2).attr("content"));
                date_end = LocalDateTime.parse(el.child(0).child(3).attr("content"));
                location = el.child(0).child(4).child(0).attr("content");
                image_url = el.child(0).child(6).attr("content");
                description = el.child(0).child(7).attr("content");
                source_url = AFISHA_URL + el.child(1).child(0).child(0).attr("href");

                events.add(new Event(title, source_url, description, location, EXHIBITION, image_url, date_start, date_end));
            }
            logger.info("Exhibitions from afisha.ru were parsed");
        }catch (Exception e){
            logger.warn("placement of data in html code was changed");
        }

        return events;
    }






    @Override
    public Elements[] getElems(Document afisha) {
        Elements[] elms = new Elements[3];
        elms[0] = afisha.getElementsByAttributeValue("class", EXHIBITION_CLASS_NAMES);
        elms[1] = afisha.getElementsByAttributeValue("class", EXHIBITION_CLASS_INFOS);
        elms[2] = afisha.getElementsByAttributeValue("class", EXHIBITION_CLASS_TIMES);
        return elms;
    }

    @Override
    public String getLocation(Element e) {
        return e.child(1).child(0).text();
    }

    @Override
    public LocalDateTime getTime(Event event, Element e) {

        try {
            //if(! e.text().substring(0, 2).equals("с ")) event.setDate_end(new TimeParser().toDate(e.text()));
             //   else event.setDate_start(new TimeParser().toDate(e.text().substring(2)));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Dictionary.TypeOfEvent getTypeOfEvent() {
        return EXHIBITION;
    }

}
