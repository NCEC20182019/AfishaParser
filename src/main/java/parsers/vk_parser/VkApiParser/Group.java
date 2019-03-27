package parsers.vk_parser.VkApiParser;

public class Group {
    //Список объектов › Сообщество: https://vk.com/dev/objects/group
    private int id;
    private String name;
    private String type;
    private String photo_200;//	URL главной фотографии в максимальном размере.
    private String description;
    private Place place;

    public Group(int id, String name, String type, String photo_200, String description, Place place) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.photo_200 = photo_200;
        this.description = description;
        this.place = place;
    }
    public Group(){

    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhoto_200() {
        return photo_200;
    }

    public void setPhoto_200(String photo_200) {
        this.photo_200 = photo_200;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }
}
