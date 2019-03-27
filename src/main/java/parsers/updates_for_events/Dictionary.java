package parsers.updates_for_events;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static parsers.updates_for_events.Dictionary.TypeOfEvent.CINEMA;
import static parsers.updates_for_events.Dictionary.TypeOfEvent.ERROR;

//Яндекс.Словарь ключ: dict.1.1.20190327T224428Z.8b8c9b6e7567b1cc.437fef8997c6f26daeef71df34d49aa5ef29daf2
//https://tech.yandex.ru/dictionary/doc/dg/reference/lookup-docpage/

public class Dictionary {
    public enum TypeOfEvent {CINEMA, THEATRE, CONCERT, MEETING, FESTIVAL, EXHIBITION, SPORT, RACING, VK_EVENT, ERROR};

    private final static String YANDEX_DICTIONARY_KEY = "dict.1.1.20190327T224428Z.8b8c9b6e7567b1cc.437fef8997c6f26daeef71df34d49aa5ef29daf2";
    private final static String YANDEX_DICTIONARY_URL = "https://dictionary.yandex.net/api/v1/dicservice.json/lookup?";

    public static TypeOfEvent getTypeOfEventByName(String TypeOfEventName){
        for(TypeOfEvent toe : TypeOfEvent.values())
            if(TypeOfEventName.toUpperCase().equals(toe.name())) return toe;
        return ERROR;
    }

    public String getSynonyms(String word){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(createUrlForGetToYandexDictionart("ru-ru", word), String.class);
        //System.out.println();

        return response.toString();
    }

    public String createUrlForGetToYandexDictionart(String lang, String text){
        return YANDEX_DICTIONARY_URL + "key=" + YANDEX_DICTIONARY_KEY + "&lang=" + lang + "&text=" + text;
    }


    public static void main(String[] args) {
        System.out.println(new Dictionary().getSynonyms("лицо"));
    }
}
