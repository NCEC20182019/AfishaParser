package parsers;

import parsers.updates_for_events.Dictionary;

import java.util.ArrayList;
import java.util.Date;

public class EventDTO {
    private String title;

    private Dictionary.TypeOfEvent typeOfEvent;
    private Date date_start;
    private Date date_end;
    private ArrayList<String> tags;
    private int event_id;

    public EventDTO(String title, Dictionary.TypeOfEvent typeOfEvent, Date date_start, Date date_end, int event_id) {
        this.title = title;
        this.typeOfEvent = typeOfEvent;
        this.date_start = date_start;
        this.date_end = date_end;
        this.event_id = event_id;
    }

    public EventDTO() {
    }

    public ArrayList<String> getTags() {
        return tags;
    }


    public void setTags() {
        tags = new ArrayList<>();
        tags.add("%23" + title.replaceAll(" ", "")
                .replaceAll("«","")
                .replaceAll("»",""));
        tags.add("%23Воронеж" + "%23" + title.replaceAll(" ", "")
                .replaceAll("«","")
                .replaceAll("»",""));//0 полное имя
        //tags.add("%23Воронеж");//1 и 2 теги всегда с городом
        //tags.add("%23Voronezh");
        tags.add("%23Voronezh" + "%23" + title.replaceAll(" ", "")
                .replaceAll("«","")
                .replaceAll("»",""));
        if (typeOfEvent != null){
            tags.add("%23" + typeOfEvent.name()); //3 и 4 тег с типом ивента
            tags.add("%23" + Dictionary.typeOfEventToCyrillic(typeOfEvent).name());
        }
        String[] partsOfName = title.split( " ");
        /*if (partsOfName.length <= 3) {
            for (String s : partsOfName) {//тегом может быть часть названия ивента
                if (s.equals(Dictionary.toLatin(s))) tags.add("%23" + s);
                else {
                    tags.add("%23" + s);
                    tags.add("%23" + Dictionary.toLatin(s));
                }
            }
        }*/
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Dictionary.TypeOfEvent getTypeOfEvent() {
        return typeOfEvent;
    }

    public void setTypeOfEvent(Dictionary.TypeOfEvent typeOfEvent) {
        this.typeOfEvent = typeOfEvent;
    }

    public Date getDate_start() {
        return date_start;
    }

    public void setDate_start(Date date_start) {
        this.date_start = date_start;
    }

    public Date getDate_end() {
        return date_end;
    }

    public void setDate_end(Date date_end) {
        this.date_end = date_end;
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    @Override
    public String toString() {
        return "EventDTO{" +
                "title='" + title + '\'' +
                ", typeOfEvent=" + typeOfEvent +
                ", date_start=" + date_start +
                ", date_end=" + date_end +
                ", tags=" + tags +
                ", event_id=" + event_id +
                '}';
    }
}
