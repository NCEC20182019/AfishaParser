package parsers;

import parsers.updates_for_events.Dictionary;

import java.util.ArrayList;
import java.util.Date;

public class EventDTO {
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
    private ArrayList<String> tags;
    private double latitude;//широта
    private double longitude;//долгота
    private String street_address;
    private int event_id;

    public ArrayList<String> getTags() {
        return tags;
    }


    public void setTags() {
        tags = new ArrayList<>();
        tags.add("%23" + title.replaceAll(" ", "")
                .replaceAll("«","")
                .replaceAll("»",""));//0 полное имя
        tags.add("%23Воронеж");//1 и 2 теги всегда с городом
        tags.add("%23Voronezh");
        tags.add("%23" + typeOfEvent.name()); //3 и 4 тег с типом ивента
        tags.add("%23" + Dictionary.typeOfEventToCyrillic(typeOfEvent).name());
        if (name_location.equals(Dictionary.toLatin(name_location)))
            tags.add("%23" + name_location.replaceAll(" ", "")
                    .replaceAll("«","")
                    .replaceAll("»",""));
        else {
            tags.add("%23" + name_location.replaceAll(" ", "")
                    .replaceAll("«","")
                    .replaceAll("»",""));
            tags.add("%23" + Dictionary.toLatin(name_location .replaceAll(" ", "")
                    .replaceAll("«","")
                    .replaceAll("»","")));
        }

        String[] partsOfName = title.split( " ");
        if (partsOfName.length <= 4) {
            for (String s : partsOfName) {//тегом может быть часть названия ивента
                if (s.equals(Dictionary.toLatin(s))) tags.add("%23" + s);
                else {
                    tags.add("%23" + s);
                    tags.add("%23" + Dictionary.toLatin(s));
                }
            }
        }
    }

    @Override
    public String toString() {
        return "parsers.EventDTO{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", source_uri='" + source_uri + '\'' +
                ", name_location='" + name_location + '\'' +
                ", typeOfEvent=" + typeOfEvent +
                ", date_start=" + date_start +
                ", date_end=" + date_end +
                ", image_url='" + image_url + '\'' +
                ", tags=" + tags +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", street_address='" + street_address + '\'' +
                ", event_id=" + event_id +
                '}';
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

    public String getName_location() {
        return name_location;
    }

    public void setName_location(String name_location) {
        this.name_location = name_location;
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

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
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

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public EventDTO() {

    }
}
