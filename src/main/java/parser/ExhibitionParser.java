package parser;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.ListIterator;

public class ExhibitionParser extends Parser {
    private final static String EXHIBITION_CLASS_NAMES = "list__item-name";
    private final static String EXHIBITION_CLASS_INFOS = "list__item-info";
    private final static String EXHIBITION_CLASS_TIMES = "list__item-desc-time";

    /*
    @Override
    public void getLocationAndDescription(ListIterator<Event> iter, Elements infos) {
        infos.forEach(info -> {
            Event tmp;
            if (iter.hasNext()) {
                tmp = iter.next();
                tmp.setName_location(info.child(1).child(0).text());//нет описания
            }
        });
    }

    @Override
    public void getTime(ListIterator<Event> iter, Elements times) {

    }

    @Override
    public void getInfo(ArrayList<Event> events, Elements names, Elements infos, Elements times) {
        names.forEach(name ->  {
            String urlTmp = AFISHA_URL  + name.attr("href");
            String eventName = name.text();
            events.add(new Event(eventName, urlTmp, TYPE_OF_EVENT_EXHIBITION));
            ListIterator<Event> iter = events.listIterator();
            getLocationAndDescription(iter, infos);
            iter = events.listIterator();
            getTime(iter, times);

        });
    }
*/
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
    public void setTime(Event event, Elements classTime, int index) {

        try {
            if(! classTime.get(index).text().substring(0, 2).equals("с ")) event.setDate_end(new TimeParser().toDate(classTime.get(index).text()));
                else event.setDate_start(new TimeParser().toDate(classTime.get(index).text().substring(2)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
