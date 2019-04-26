package parsers.vk_parser.VkApiParser;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.groups.Group;
import com.vk.api.sdk.objects.groups.GroupFull;
import com.vk.api.sdk.objects.groups.responses.SearchResponse;
import com.vk.api.sdk.queries.groups.GroupField;
import parsers.Event;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static parsers.updates_for_events.Dictionary.TypeOfEvent.VK_EVENT;

/**
 * Информация о зарегистрированном приложении в ВК:
 * Название: lemmeknow.com
 * ID приложения:	6915826
 * Защищённый ключ: 0J2wwocAJfuGrkcKUqxO
 * Сервисный ключ доступа: 71520c0b71520c0b71520c0b4f713b8af97715271520c0b2dc884c68485171e8451050f
 * Запрос на получение токена: https://oauth.vk.com/authorize?client_id=6915826&display=page&redirect_uri=https://oauth.vk.com/blank.html&scope=groups&response_type=token&v=5.92
 */
//Ключ доступа пользователя: https://vk.com/dev/access_token
//https://oauth.vk.com/authorize?client_id=6915826&display=page&redirect_uri=https://oauth.vk.com/blank.html&scope=friends&response_type=code&v=5.92
public class VkEventsApiParser{

    private final static String ACCESS_TOKEN = "97ea6a7ae75c619cbe62a6e5d6e941b35fe162ca64606039340052bb9241931456c622b6e147ce143e570";
    private final static String CODE = "d2db3be1fbf0693edc";
    private final static Integer APP_ID = 6915826;
    private final static String CLIENT_SECRET = "0J2wwocAJfuGrkcKUqxO";
    private final static String REDIRECT_URL = "https://oauth.vk.com/blank.html";

    public UserActor getAccessToken(VkApiClient vk) throws ClientException, ApiException {
        //UserAuthResponse authResponse = vk.oauth().userAuthorizationCodeFlow(APP_ID, CLIENT_SECRET, REDIRECT_URL, CODE).execute();
        //return new UserActor(authResponse.getUserId(), authResponse.getAccessToken());
        return new UserActor(APP_ID, ACCESS_TOKEN);
    }
//работает сутки
//d9680d3520f121358f7a804d98a2723a8b9ac92c2a3b9bc969444dc930d92c308fe6594b1042a53bd7698
//https://vk.com/dev/Java_SDK
    public static void main(String[] args) throws ClientException, ApiException {
        VkEventsApiParser vkEventsApiParser = new VkEventsApiParser();

        for(Event e : vkEventsApiParser.getEvents())
            e.show();
    }

    private ArrayList<String> getGroupIds(List<Group> groups){
        ArrayList<String> list = new ArrayList<>();
        for (Group g : groups)
            list.add(g.getId().toString());
        return list;
    }
    private List<GroupField> getWantedFields(){
        ArrayList<GroupField> list = new ArrayList<>();
        list.add(GroupField.SCREEN_NAME);
        list.add(GroupField.PHOTO_200);
        list.add(GroupField.DESCRIPTION);
        list.add(GroupField.START_DATE);
        list.add(GroupField.FINISH_DATE);
        list.add(GroupField.PLACE);
        return list;
    }

    public ArrayList<Event> getEvents() throws ClientException, ApiException {
        ArrayList<Event> events = new ArrayList<>();
        String title, description, source_url, image_url, location;
        LocalDateTime date_start, date_end;


        TransportClient transportClient = HttpTransportClient.getInstance();
        VkApiClient vk = new VkApiClient(transportClient);
        UserActor actor = getAccessToken(vk);

        SearchResponse response = vk.groups().search(actor, " ")
                .cityId(42)
                .count(500)
                .type("event")
                .future(true)
                .execute();
        List<Group> groups =  response.getItems();
        List<GroupFull> groupFulls = vk.groups().getById(actor).groupIds(getGroupIds(groups)).fields(getWantedFields()).execute();
        for (GroupFull g : groupFulls){
            title = g.getName();
            source_url = "https://vk.com/event" + g.getId().toString();
            image_url = g.getPhoto200();
            location = "";
            description = g.getDescription();
            System.out.println(g.getStartDate());
            date_start = LocalDateTime.ofInstant(Date.from(Instant.ofEpochSecond(g.getStartDate())).toInstant(), ZoneId.systemDefault());
            if(g.getFinishDate() != null) date_end = LocalDateTime.ofInstant(Date.from(Instant.ofEpochSecond(g.getFinishDate())).toInstant(), ZoneId.systemDefault());
                else date_end = null;

            events.add(new Event(title, source_url, description, location, VK_EVENT, image_url, date_start, date_end));
            events.get(events.size() - 1).setTags();
        }

        return events;
    }
}
