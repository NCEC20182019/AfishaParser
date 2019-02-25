package parser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.ListIterator;

public class ExhibitionParser extends Parser {
    private final static String EXHIBITION_CLASS_NAMES = "list__item-name";
    private final static String EXHIBITION_CLASS_INFOS = "list__item-info";
    private final static String EXHIBITION_CLASS_TIMES = "list__item-desc-time";
    private final static String TYPE_OF_EVENT_EXHIBITION = "exhibition";

    @Override
    public Elements[] getElems(Document afisha) {
        Elements[] elms = new Elements[3];
        elms[0] = afisha.getElementsByAttributeValue("class", EXHIBITION_CLASS_NAMES);
        elms[1] = afisha.getElementsByAttributeValue("class", EXHIBITION_CLASS_INFOS);
        elms[2] = afisha.getElementsByAttributeValue("class", EXHIBITION_CLASS_TIMES);
        return elms;
    }

    @Override
    public String getLocation(Elements classInfo, int index) {
        return classInfo.get(index).child(1).child(0).text();
    }

    @Override
    public void setTime(Event event, Element e) {

        try {
            if(! e.text().substring(0, 2).equals("с ")) event.setDate_end(new TimeParser().toDate(e.text()));
                else event.setDate_start(new TimeParser().toDate(e.text().substring(2)));
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public String getTypeOfEvent() {
        return TYPE_OF_EVENT_EXHIBITION;
    }

}
