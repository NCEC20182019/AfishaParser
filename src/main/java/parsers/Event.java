package parsers;

import parsers.updates_for_events.Dictionary;
import parsers.vk_parser.VkApiParser.VkEventsApiParser;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class Event {
    private String title;
    private String description;
    private String source_uri;
    private String name_location;
    private Dictionary.TypeOfEvent typeOfEvent;
    //private LocalDateTime date_start;
    //private LocalDateTime date_end;
    private Date date_start;
    private Date date_end;
    private String image_url;
    private double latitude;//широта
    private double longitude;//долгота
    private String street_address;




    public String getImage_url() {
        return image_url;
    }


    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getStreet_address() {
        return street_address;
    }

    public void setStreet_address(String street_address) {
        this.street_address = street_address;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
/*
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
*/
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


    public Dictionary.TypeOfEvent getTypeOfEvent() {
        return typeOfEvent;
    }

    public void setTypeOfEvent(Dictionary.TypeOfEvent typeOfEvent) {
        this.typeOfEvent = typeOfEvent;
    }

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
        image_url="=";
    }



    public Event(String title, String source_uri, String description, String name_location, Dictionary.TypeOfEvent type_of_event, String image_url,
                 LocalDateTime date_start, LocalDateTime date_end) {
        this.title = title;
        this.description = description;
        this.source_uri = source_uri;
        this.name_location = name_location;
        this.typeOfEvent = type_of_event;
        this.image_url = image_url;

        this.date_start = VkEventsApiParser.convertToDateViaInstant(date_start);
        this.date_end = VkEventsApiParser.convertToDateViaInstant(date_end);
    }
    public Event(String title, String source_uri, String description, String name_location, double latitude, double longitude, String street_address, Dictionary.TypeOfEvent type_of_event, String image_url,
                 LocalDateTime date_start, LocalDateTime date_end) {
        this.title = title;
        this.description = description;
        this.source_uri = source_uri;
        this.name_location = name_location;
        this.typeOfEvent = type_of_event;
        this.image_url = image_url;
        this.date_start = VkEventsApiParser.convertToDateViaInstant(date_start);
        this.date_end = VkEventsApiParser.convertToDateViaInstant(date_end);
        this.latitude = latitude;
        this.longitude = longitude;
        this.street_address = street_address;
        //костыль

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

        System.out.println("title= " + title);
        System.out.println("desc= " + description);
        System.out.println("source_uri= " + source_uri);
        System.out.println("name_location= " + name_location);
        System.out.println("street address= " + street_address);
        System.out.println("latitude= " + latitude);
        System.out.println("longitude= " + longitude);
        System.out.println("type_of_event= " + typeOfEvent);
        System.out.println("date_start= " + date_start);
        System.out.println("date_end= " + date_end);
        System.out.println("image_url= " + image_url);
        //for (String tag : tags)
            //System.out.print(tag);
        System.out.println();
        System.out.println("--------------------");
    }


}
