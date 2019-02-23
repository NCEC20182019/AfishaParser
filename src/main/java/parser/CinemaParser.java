package parser;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.ListIterator;

public class CinemaParser extends Parser {
    private final static String CINEMA_CLASS_NAMES = "list__item-name";
    private final static String CINEMA_CLASS_INFOS = "list__item-info";


    @Override
    public Elements[] getElems(Document afisha) {
        Elements[] elms = new Elements[3];
        elms[0] = afisha.getElementsByAttributeValue("class", CINEMA_CLASS_NAMES);
        elms[1] = afisha.getElementsByAttributeValue("class", CINEMA_CLASS_INFOS);
        elms[2] = null;
        return elms;
    }

    @Override
    public String getLocation(Elements classInfo, int index) {
        return null;
    }

    @Override
    public void setTime(Event event, Elements classTime, int index) {

    }


}
