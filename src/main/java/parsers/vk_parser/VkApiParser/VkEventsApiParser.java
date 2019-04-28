package parsers.vk_parser.VkApiParser;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
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
import rest_service.PostToEventService;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import static parsers.updates_for_events.Dictionary.TypeOfEvent.VK_EVENT;

/**
 * Информация о зарегистрированном приложении в ВК:
 * Название: lemmeknow.com
 * ID приложения:	6915826
 * Защищённый ключ: 0J2wwocAJfuGrkcKUqxO
 * Сервисный ключ доступа: 71520c0b71520c0b71520c0b4f713b8af97715271520c0b2dc884c68485171e8451050f
 * Запрос на получение токена: https://oauth.vk.com/authorize?client_id=6963375&display=page&redirect_uri=https://oauth.vk.com/blank.html&scope=offline&response_type=token&v=5.92
 */
//Ключ доступа пользователя: https://vk.com/dev/access_token
//https://oauth.vk.com/access_token?client_id=6963375&display=page&redirect_uri=https://oauth.vk.com/blank.html&scope=offline&response_type=code&v=5.92
public class VkEventsApiParser{

    //private final static String ACCESS_TOKEN = "97ea6a7ae75c619cbe62a6e5d6e941b35fe162ca64606039340052bb9241931456c622b6e147ce143e570";
    private final static String CODE = "13f2d2e1c38e5e19b1";
    private final static Integer APP_ID = 6963375;
    private final static String CLIENT_SECRET = "421bc9af421bc9af421bc9affe427189004421b421bc9af1edf6b505371940902d5881d";
    private final static String REDIRECT_URL = "https://oauth.vk.com/blank.html";
    private final static String ACCESS_TOKEN_URL = "https://oauth.vk.com/authorize?client_id=6963375&display=page&redirect_uri=https://oauth.vk.com/blank.html&scope=offline&response_type=token&v=5.92";

    public UserActor getUserActor(VkApiClient vk) throws ClientException, ApiException {
        //UserAuthResponse authResponse = vk.oauth().userAuthorizationCodeFlow(APP_ID, CLIENT_SECRET, REDIRECT_URL, CODE).execute();
        //return new UserActor(authResponse.getUserId(), authResponse.getAccessToken());
        return new UserActor(APP_ID, getAccessToken());
    }
/*
КОД ДЛЯ ПОЛУЧЕНИЯ БЕССРОЧНОГО ТОКЕНА
КОД ДЕЙСТВУЕТ ЧАС
ДЛЯ ПОЛУЧЕНИЯ КОДА ПЕРЕЙТИ ПО ДАННОМУ УРЛ
В УРЛ РЕДИРЕКТА БУДЕТ КОД
https://oauth.vk.com/authorize?client_id=6963375&display=page&redirect_uri=https://oauth.vk.com/blank.html&scope=offline&response_type=code&v=5.95

https://oauth.vk.com/access_token?cliend_id=6963375&client_secret=AgUGuVVAR6BiXHzlVcOP&redirect_uri=https://oauth.vk.com/blank.html&code=b33c282f9dd748e543
 */

    public String getAccessToken(){
        java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF);
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(false);
        final String login = "56958042671";
        final String pass ="projectnc2019";
        try {
            HtmlPage page = webClient.getPage(ACCESS_TOKEN_URL);
            int i = 0;
            HtmlForm form = page.getForms().get(0);
            form.getInputByName("email").setValueAttribute(login);
            form.getInputByName("pass").setValueAttribute(pass);
            HtmlPage homepage = form.getInputByName("submit_input").click();
            System.out.println(homepage.getBaseURL());
            String[] urlArgs = homepage.getBaseURL().toString().split("=");
            return urlArgs[1].split("&")[0];

        }catch (IOException e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public static java.util.Date convertToDateViaInstant(LocalDateTime dateToConvert) {
        try {
            return  java.util.Date
                    .from(dateToConvert.atZone(ZoneId.systemDefault())
                            .toInstant());
        }catch (Exception e){
            return null;
        }

    }
    public static void main(String[] args) throws ClientException, ApiException {
        VkEventsApiParser vkEventsApiParser = new VkEventsApiParser();

        //for(Event e : vkEventsApiParser.getEvents())
         //   e.show();
        //2020-05-22T10:32
        System.out.println(vkEventsApiParser.convertToDateViaInstant(LocalDateTime.now()));
        //PostToEventService.post(new Event("TestEvent","https://www.baeldung.com/java-date-to-localdate-and-localdatetime", "test desc","test loc", 39.45464, 64.12313, "Pushkin Street, House of Kolotushkin", VK_EVENT, "https://www.baeldung.com/wp-content/themes/baeldung/icon/logo.svg",LocalDateTime.now(), LocalDateTime.now()));
        //PostToEventService.post(new Event("TestEvent","https://www.baeldung.com/java-date-to-localdate-and-localdatetime", "test desc","test loc", 39.45464, 64.12313, "Pushkin Street, House of Kolotushkin", VK_EVENT, "https://www.baeldung.com/wp-content/themes/baeldung/icon/logo.svg",LocalDateTime.now(),LocalDateTime.now()));

//(String title, String source_uri, String description, String name_location, double latitude, double longitude, String street_address, Dictionary.TypeOfEvent type_of_event, String image_url,LocalDateTime date_start, LocalDateTime date_end)
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
        String title, description, source_url, image_url, location_name, street_address;
        LocalDateTime date_start, date_end;
        double latitude, longitude;


        TransportClient transportClient = HttpTransportClient.getInstance();
        VkApiClient vk = new VkApiClient(transportClient);
        UserActor actor = getUserActor(vk);

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
            try {
                location_name = g.getPlace().getTitle();
            }catch (Exception e){
                location_name = "";
            }
            try {
                street_address = g.getPlace().getAddress();
            }catch (Exception e){
                street_address = "";
            }
            try {
                latitude = g.getPlace().getLatitude();
                longitude = g.getPlace().getLongitude();
            }catch (Exception e){
                latitude = 0;
                longitude = 0;
            }

            description = g.getDescription();
            date_start = LocalDateTime.ofInstant(Date.from(Instant.ofEpochSecond(g.getStartDate())).toInstant(), ZoneId.systemDefault());
            if(g.getFinishDate() != null) date_end = LocalDateTime.ofInstant(Date.from(Instant.ofEpochSecond(g.getFinishDate())).toInstant(), ZoneId.systemDefault());
                else date_end = null;

            events.add(new Event(title, source_url, description, location_name, latitude, longitude, street_address, VK_EVENT, image_url, date_start, date_end));
            //events.get(events.size() - 1).setTags();
        }

        return events;
    }
}
