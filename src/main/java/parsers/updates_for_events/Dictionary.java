package parsers.updates_for_events;


import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import parsers.Event;

import java.util.ArrayList;

import static parsers.updates_for_events.Dictionary.TypeOfEvent.*;

//Яндекс.Словарь ключ: dict.1.1.20190327T224428Z.8b8c9b6e7567b1cc.437fef8997c6f26daeef71df34d49aa5ef29daf2
//https://tech.yandex.ru/dictionary/doc/dg/reference/lookup-docpage/

public class Dictionary {
    private Dictionary(){}
    public enum TypeOfEvent {CINEMA, THEATRE, CONCERT, MEETING, FESTIVAL, EXHIBITION, SPORT, RACING, VK_EVENT, UNKNOWN};

    /**
     * ФИЛЬМ заменено на КИНО
     * ПРЕДСТАВЛЕНИЕ на ТЕАТР
     */
    public enum TypeOfEventInCyrillic {КИНО, ТЕАТР, КОНЦЕРТ, ВСТРЕЧА, ФЕСТИВАЛЬ, ВЫСТАВКА, СПОРТ, ВК_СОБЫТИЕ}
    public static TypeOfEvent getTypeOfEventByName(String TypeOfEventName){
        for(TypeOfEvent toe : TypeOfEvent.values())
            if(TypeOfEventName.toUpperCase().equals(toe.name())) return toe;
        return UNKNOWN;
    }
    public static TypeOfEventInCyrillic typeOfEventToCyrillic(TypeOfEvent toe){
        TypeOfEvent[] tmp = TypeOfEvent.values();
        int i = 0;
        for(; i < 7;){//7 т.к. всего 7 полезных типов событий
            if (tmp[i].equals(toe)) break;
            i++;
        }
        return TypeOfEventInCyrillic.values()[i] ;
    }

    public static String toLatin(String cirillic){
        char[] abcCyr =   {' ','а','б','в','г','д','е','ё', 'ж','з','и','й','к','л','м','н','о','п','р','с','т','у','ф','х', 'ц','ч' , 'ш','щ'  ,'ъ','ы','ь','э', 'ю','я' ,'А','Б','В','Г','Д','Е','Ё', 'Ж','З','И','Й','К','Л','М','Н','О','П','Р','С','Т','У','Ф','Х', 'Ц', 'Ч','Ш' , 'Щ' ,'Ъ','Ы','Ь','Э','Ю' ,'Я' ,'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        String[] abcLat = {" ","a","b","v","g","d","e","e","zh","z","i","y","k","l","m","n","o","p","r","s","t","u","f","h","ts","ch","sh","sch", "","i", "","e","ju","ja","A","B","V","G","D","E","E","ZH","Z","I","Y","K","L","M","N","O","P","R","S","T","U","F","H","TS","CH","SH","SCH", "","I", "","E","JU","JA","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < cirillic.length(); i++) {
            for (int x = 0; x < abcCyr.length; x++ ) {
                if (cirillic.charAt(i) == abcCyr[x]) {
                    builder.append(abcLat[x]);
                }
            }
        }
        return builder.toString();
    }







}
