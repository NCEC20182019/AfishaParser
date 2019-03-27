package parsers.vk_parser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import parsers.Event;
import parsers.Parser;
import parsers.afisha_parser.TimeParser;
import parsers.updates_for_events.Dictionary;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static parsers.updates_for_events.Dictionary.TypeOfEvent.VK_EVENT;

public class VkEventsParser extends Parser {
    private final static String VK_EVENT_CLASS_TO_PARSE = "event_item";
    private static final Logger logger = LoggerFactory.getLogger(VkEventsParser.class);

    @Override
    public ArrayList<Event> getEvents(Document afisha) {
        ArrayList<Event> events = new ArrayList<>();
        String title, source_url, image_url, location,description;
        LocalDateTime date_start;


        Elements titlesAndUrls = afisha.getElementsByAttributeValue("class", "event_link");
        Elements descriptions = afisha.getElementsByAttributeValue("class", "info_line event_status");
        Elements dates = afisha.getElementsByAttributeValue("class", "info_line datetime_string");
        Elements images = afisha.getElementsByAttributeValue("class", "event_image");
        try {
            for(int i = 0; i < descriptions.size(); i++){
                title = titlesAndUrls.get(i).text();
                source_url = titlesAndUrls.get(i).attr("href");
                description = descriptions.get(i).text();
                image_url = images.get(i).attr("src");
                //location = locations.get(i).text();// координаты "51.674591, 39.201684". Есть траблы с некоторыми ивентами.
                date_start = new TimeParser().toDate(dates.get(i).text());//добавить распознавание даты типа "28 марта 2019 в 19:00 (МСК) "
                events.add(new Event(title, source_url, description, null, VK_EVENT, image_url, date_start, null));
            }
        }catch (Exception e){
            logger.error("something wrong", e);
        }

        return events;
    }







    @Override
    public Elements[] getElems(Document afisha) {
        return new Elements[0];
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
        return null;
    }
}
