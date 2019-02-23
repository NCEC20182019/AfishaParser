package parser;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ListIterator;

public class TheatreParser extends Parser {
    private final static String THEATRE_CLASS_NAMES = "list__item-name";
    private final static String THEATRE_CLASS_INFOS = "list__item-desc-list";
    private final static String THEATRE_CLASS_TIMES = "list__item-desc-time";

    @Override
    public Elements[] getElems(Document afisha) {
        Elements[] elms = new Elements[3];
        elms[0] = afisha.getElementsByAttributeValue("class", THEATRE_CLASS_NAMES);
        elms[1] = afisha.getElementsByAttributeValue("class", THEATRE_CLASS_INFOS);
        elms[2] = afisha.getElementsByAttributeValue("class", THEATRE_CLASS_TIMES);
        return elms;
    }

    @Override
    public String getLocation(Elements classInfo, int index) {
        return classInfo.get(index).child(0).text();// Если с театром проблема, то она здесь. Чекай html. Скорее всего поменялось местоположение инфы о локации
    }

    @Override
    public void setTime(Event event, Elements classTime, int index) {

        try {
            if(classTime.get(index).text().length() == 12 || classTime.get(index).text().length() == 13)
                event.setDate_start(new TimeParser().toDate( classTime.get(index).text()));//следствие уникального
            else event.setDate_start(new TimeParser().toDate(classTime.get(index).text().substring(0, 12)));//В МАРТЕ ВСЁ ИЗМЕНИТСЯ!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("shit happens with " + classTime.get(index).text());
        }
    }

}
