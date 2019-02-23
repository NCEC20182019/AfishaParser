package parser;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Event {


    private String title;
    private String description;
    private String source_uri;
    private String name_location;
    private String typeOfEvent;
    private LocalDateTime date_start;
    private LocalDateTime date_end;



    public LocalDateTime getDate_start() {
        return date_start;
    }

    public void setDate_start(LocalDateTime date_start) {
        this.date_start = date_start;
    }

    public LocalDateTime getDate_end() {
        return date_end;
    }

    public void setDate_end(LocalDateTime date_end) {
        this.date_end = date_end;
    }

    public String getTypeOfEvent() {
        return typeOfEvent;
    }

    public void setTypeOfEvent(String typeOfEvent) {
        this.typeOfEvent = typeOfEvent;
    }

    private static ArrayList<Event> eventList = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSource_uri() {
        return source_uri;
    }

    public void setSource_uri(String source_uri) {
        this.source_uri = source_uri;
    }

    // конструктор для теста и времен, когда я сделаю парсинг за один цикл

    public String getName_location() {
        return name_location;
    }

    public void setName_location(String name_location) {
        this.name_location = name_location;
    }

    public Event(){
        title="";
        description="";
        source_uri="";
        name_location ="";
        typeOfEvent="";


    }

    public Event(String title, String source_uri, String typeOfEvent){
        this.title = title;
        this.source_uri = source_uri;
        this.description = "";
        this.name_location = "";
        this.typeOfEvent = typeOfEvent;
        date_start = LocalDateTime.now();
        date_end = LocalDateTime.now();

    }
    public Event(String title, String description, String name_location, String source_uri) {
        this.title = title;
        this.description = description;
        this.source_uri = source_uri;
        this.name_location = name_location;
        date_start = LocalDateTime.now();
        date_end = LocalDateTime.now();

    }
    //основной конструктор
    public Event(String title, String source_uri, String description, String name_location, String type_of_event) {
        this.title = title;
        this.description = description;
        this.source_uri = source_uri;
        this.name_location = name_location;
        this.typeOfEvent = type_of_event;

    }

    public Event(String title, String description, String name_location, String source_uri, String type_of_event, String date_start, String date_end) {
        this.title = title;
        this.description = description;
        this.source_uri = source_uri;
        this.name_location = name_location;
        this.typeOfEvent = type_of_event;
        date_start = "";
        date_end = "";

    }

    public static ArrayList<Event> getEventList() {
        return eventList;
    }

    public static void setEventList(ArrayList<Event> eventList) {
        Event.eventList = eventList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (title != null ? !title.equals(event.title) : event.title != null) return false;
        if (description != null ? !description.equals(event.description) : event.description != null) return false;
        if (source_uri != null ? !source_uri.equals(event.source_uri) : event.source_uri != null) return false;
        if (name_location != null ? !name_location.equals(event.name_location) : event.name_location != null)
            return false;
        return  typeOfEvent != null ? typeOfEvent.equals(event.typeOfEvent) : event.typeOfEvent == null;
        //if (typeOfEvent != null ? !typeOfEvent.equals(event.typeOfEvent) : event.typeOfEvent != null) return false;
        /*if (date_start != null ? !date_start.equals(event.date_start) : event.date_start != null) return false;
        return date_end != null ? date_end.equals(event.date_end) : event.date_end == null;*/
    }

    public void show() {

        System.out.println("title=" + title);
        System.out.println("desc=" + description);
        System.out.println("source_uri=" + source_uri);
        System.out.println("name_location=" + name_location);
        System.out.println("type_of_event=" + typeOfEvent);
        System.out.println("date_start=" + date_start);
        System.out.println("date_end=" + date_end);
        System.out.println("--------------------");
    }


}
