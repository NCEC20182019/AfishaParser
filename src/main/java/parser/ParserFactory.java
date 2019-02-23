package parser;

public class ParserFactory {
    public static Parser getParser(String typeOfEvent){
        switch (typeOfEvent) {
            case "cinema" : {
                return new  CinemaParser();
            }
            case "exhibition" : {
                return new ExhibitionParser();
            }
            case "theatre" : {
                return new TheatreParser();
            }
            case "concert" : {
                return new ConcertParser();
            }
        }
        return null;
    }
}
