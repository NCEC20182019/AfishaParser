package parsers;

import parsers.updates_for_events.Dictionary;

import java.util.ArrayList;
import java.util.Date;

public class EventDTO {
    private String title;


    private Dictionary.TypeOfEvent type;
    private Date date_start;
    private Date date_end;
    private ArrayList<String> tags;
    private int event_id;

    public EventDTO(String title, Dictionary.TypeOfEvent type, Date date_start, Date date_end, int event_id) {
        this.title = title;
        this.type = type;
        this.date_start = date_start;
        this.date_end = date_end;
        this.event_id = event_id;
    }

    public EventDTO EventToDTO(Event e){
        EventDTO dto = new EventDTO();
        dto.title = e.getTitle();
        dto.type = e.getType();
        dto.date_start = e.getDate_start();
        dto.date_end = e.getDate_end();
        dto.event_id = 0;
        dto.createTags();
        return dto;
    }

    public EventDTO() {
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public ArrayList<String> getTags() {
        return tags;
    }


    public void createTags() {
        tags = new ArrayList<>();
        if (title.length() > 15) {
            String[] partsOfName = title.split(" ");
            int wordsCounter = 0;
            String name = "";
            for (String str : partsOfName) {
                if (wordsCounter <= 3) {
                    name += str;
                    wordsCounter++;
                } else break;
            }
            tags.add("%23Voronezh" + name);
            tags.add("%23Воронеж" + name);
            tags.add(name);
            if (!name.equals(Dictionary.toLatin(name))) {
                tags.add("%23Voronezh" + Dictionary.toLatin(name));
                tags.add("%23Воронеж" + Dictionary.toLatin(name));
            }
        }else {
            tags.add("%23Voronezh" + title.replaceAll(" ", "")
                    .replaceAll("«","")
                    .replaceAll("»",""));
            tags.add("%23Воронеж" + title.replaceAll(" ", "")
                    .replaceAll("«","")
                    .replaceAll("»",""));
        }

        /*tags.add("%23" + title.replaceAll(" ", "")
                .replaceAll("«","")
                .replaceAll("»",""));
        tags.add("%23Воронеж" + "%23" + title.replaceAll(" ", "")
                .replaceAll("«","")
                .replaceAll("»",""));//0 полное имя
        //tags.add("%23Воронеж");//1 и 2 теги всегда с городом
        //tags.add("%23Voronezh");
        tags.add("%23Voronezh" + "%23" + title.replaceAll(" ", "")
                .replaceAll("«","")
                .replaceAll("»",""));*/
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

    public Dictionary.TypeOfEvent getType() {
        return type;
    }

    public void setType(Dictionary.TypeOfEvent type) {
        this.type = type;
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
                ", type=" + type +
                ", date_start=" + date_start +
                ", date_end=" + date_end +
                ", tags=" + tags +
                ", event_id=" + event_id +
                '}';
    }
}
