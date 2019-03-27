package parsers;

import parsers.afisha_parser.CinemaParser;
import parsers.afisha_parser.ConcertParser;
import parsers.afisha_parser.ExhibitionParser;
import parsers.afisha_parser.TheatreParser;
import parsers.updates_for_events.Dictionary;
import parsers.vk_parser.VkEventsParser;

public class ParserFactory {
    public static Parser getParser(Dictionary.TypeOfEvent typeOfEvent){
        switch (typeOfEvent) {
            case CINEMA: {
                return new CinemaParser();
            }
            case EXHIBITION: {
                return new ExhibitionParser();
            }
            case THEATRE: {
                return new TheatreParser();
            }
            case CONCERT: {
                return new ConcertParser();
            }
            case VK_EVENT: {
                return new VkEventsParser();
            }
        }
        return null;
    }
}
