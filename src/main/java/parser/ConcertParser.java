package parser;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.text.ParseException;
import java.util.*;


public class ConcertParser extends Parser {
    private final static String CONCERT_CLASS_NAMES = "list__item-name";
    private final static String CONCERT_CLASS_INFOS = "list__item-desc-list";
    private final static String CONCERT_CLASS_TIMES = "list__item-desc-time";


    @Override
    public Elements[] getElems(Document afisha) {
        Elements[] elms = new Elements[3];
        elms[0] = afisha.getElementsByAttributeValue("class", CONCERT_CLASS_NAMES);
        elms[1] = afisha.getElementsByAttributeValue("class", CONCERT_CLASS_INFOS);
        elms[2] = afisha.getElementsByAttributeValue("class", CONCERT_CLASS_TIMES);
        return elms;
    }

    @Override
    public String getLocation(Elements classInfo, int index) {
        return classInfo.get(index).text();
    }

    @Override
    public void setTime(Event event, Elements classTime, int index) {
        try {
            event.setDate_start(new TimeParser().toDate(classTime.get(index).text()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }


}
