package parser;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Parser{
    protected final static String AFISHA_URL = "https://www.afisha.ru";
    private static Logger logger = LoggerFactory.getLogger(Parser.class);

    public abstract ArrayList<Event> getEvents(Document afisha);

    //Основной метод для парсинга afisha.ru
    public ArrayList<Event> parseUsingHtmlAttributes(Document afisha){
        return getEvents(afisha);
    }


    public static Document getDocument(String url){

        Connection con = Jsoup.connect(url);
        Document afisha = null;
        try {
            afisha = con.get();
            logger.info("html uploaded");
        } catch (IOException e) {
            logger.warn("can't upload html from" + url);
            e.printStackTrace();
        }
        return afisha;
    }

    @Deprecated
    public abstract Elements[] getElems(Document afisha);
    /**
     *
     * @param e элемент, в котором описывается местоположение
     *
     * @return возвращает имя локации
     */
    @Deprecated
    public abstract String getLocation(Element e);
    @Deprecated
    public abstract LocalDateTime getTime(Event event, Element e);
    @Deprecated
    public abstract String getTypeOfEvent();

    /**
     * Парсер, основанный на html class'ах
     * Необходим метод для получения image_url
     * @param afisha
     * @return
     */
    @Deprecated
    public ArrayList<Event> parseUsingHtmlClasses(Document afisha){
        ArrayList<Event> events = new ArrayList<>();
        String eventUrl, eventName, location, description, date_start, date_end;
        String type_of_event = getTypeOfEvent();
        Elements[] elems = getElems(afisha);

        //elems[0] содержит имена и урл
        //elms[1] содержит описание и местоположение
        //elms[2] содержит информацию о времени проведения
        for (int i = 0; i < elems[0].size(); i++ ){

            if(!type_of_event.equals("theatre")) eventUrl = AFISHA_URL + elems[0].get(i).attr("href");
                else eventUrl = AFISHA_URL  + elems[0].get(i).child(0).attr("href");
            eventName = elems[0].get(i).text();
            if(type_of_event.equals("cinema")) {
                description = elems[1].get(i).child(0).text();
                location = "";
            }
                else {
                    location = getLocation(elems[1].get(i));
                    description = "";
                }

             events.add(new Event(eventName, eventUrl, description, location, type_of_event, "", null, null));
            if(! type_of_event.equals("cinema")){
                if(! type_of_event.equals("exhibition")) events.get(i).setDate_start(getTime(events.get(i),elems[2].get(i)));
                    else getTime(events.get(i) ,elems[2].get(i));//как быть тут?
            }
        }

        return events;
    }


}
