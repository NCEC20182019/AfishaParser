package parser;

import com.sun.deploy.security.ValidationState;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ListIterator;

public class TheatreParser extends Parser {
    private final static String THEATRE_CLASS_NAMES = "list__item-name";
    private final static String THEATRE_CLASS_INFOS = "list__item-desc-list";
    private final static String THEATRE_CLASS_TIMES = "list__item-desc-time";

    private final static String TYPE_OF_EVENT_THEATRE = "theatre";

    private final static String THEATRE_CLASS_TO_PARSE = "new-list__item theatre-item";


    //у афиши внезапно исчезли представления из интерфейса
    //https://www.afisha.ru/voronezh/schedule_theatre/?view=list нет спектаклей, но страница есть
    @Override
    public ArrayList<Event> getEvents(Document afisha) {
        ArrayList<Event> events = new ArrayList<>();

        return events;
    }


    public static void main(String[] args) throws IOException {
        for( Event e : new TheatreParser().parseUsingHtmlAttributes(Parser.getDocument("https://www.afisha.ru/voronezh/schedule_theatre/?view=list")))
            e.show();
    }









    @Override
    public Elements[] getElems(Document afisha) {
        Elements[] elms = new Elements[3];
        elms[0] = afisha.getElementsByAttributeValue("class", THEATRE_CLASS_NAMES);
        elms[1] = afisha.getElementsByAttributeValue("class", THEATRE_CLASS_INFOS);
        elms[2] = afisha.getElementsByAttributeValue("class", THEATRE_CLASS_TIMES);
        return elms;
    }

    @Override
    public String getLocation(Element e) {
        return e.child(0).text();// Если с театром проблема, то она здесь. Чекай html. Скорее всего поменялось местоположение инфы о локации
    }

    @Override
    public LocalDateTime getTime(Event event, Element e) {

        try {
            if(e.text().length() == 12 || e.text().length() == 13)
                return new TimeParser().toDate( e.text());//следствие уникального
            else return new TimeParser().toDate(e.text().substring(0, 12));//В МАРТЕ ВСЁ ИЗМЕНИТСЯ!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        } catch (ParseException ex) {
            ex.printStackTrace();
            System.out.println("shit happens with " + e.text());
            return null;
        }
    }

    @Override
    public String getTypeOfEvent() {
        return TYPE_OF_EVENT_THEATRE;
    }

}
