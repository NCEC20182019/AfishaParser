package rest_service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;



public class MockTest {
    private final static String CINEMA_CLASS_NAMES = "list__item-name";
    private final static String CINEMA_CLASS_INFOS = "list__item-info";
    private final static String EXHIBITION_CLASS_NAMES = "list__item-name";
    private final static String EXHIBITION_CLASS_INFOS = "list__item-info";
    private final static String THEATRE_CLASS_NAMES = "list__item-name";
    private final static String THEATRE_CLASS_INFOS = "list__item-desc-list";
    private final static String CONCERT_CLASS_NAMES = "list__item-name";
    private final static String CONCERT_CLASS_INFOS = "list__item-desc-list";
    private final static String AFISHA_CINEMA_HTML = "afishaCinema.html";
    private final static String AFISHA_EXHIBITION_HTML = "afishaExhibition.html";
    private final static String AFISHA_THEATRE_HTML = "afishaTheatre.html";
    private final static String AFISHA_CONCERT_HTML = "afishaConcert.html";
    private final static String EXHIBITION_CLASS_TIME = "list__item-desc-time";
    private final static String CONCERT_CLASS_TIME = "list__item-desc-time";
    private final static String THEATRE_CLASS_TIME = "list__item-desc-time";


/*
    private static ArrayList<Event> prepareCinemaList(){
        ArrayList<Event> evs = new ArrayList<>();
       Event ev = new Event("Т-34", "Еще одна патриотическая драма про танк, теперь с Александром Петровым",
                "", "https://www.afisha.ru/movie/230914/", "cinema");
        evs.add(ev);
        ev = new Event("Полицейский с Рублевки. Новогодний беспредел", "Герои популярного сериала теперь и в кино",
                "", "https://www.afisha.ru/movie/245609/", "cinema");
        evs.add(ev);
        ev = new Event("Гринч", "Авторы «Тайной жизни домашних животных» крадут Рождество",
                "", "https://www.afisha.ru/movie/221899/", "cinema");
        evs.add(ev);
        ev = new Event("Три богатыря и наследница престола", "И в «Богатырях» началась «Игра престолов»",
                "", "https://www.afisha.ru/movie/244439/", "cinema");
        evs.add(ev);
        ev = new Event("Елки последние", "Все, что имеет начало, имеет и конец",
                "", "https://www.afisha.ru/movie/245608/", "cinema");
        evs.add(ev);
        ev = new Event("Аквамен", "Джейсон Момоа становится королем Атлантиды и готовится к вступлению в Лигу справедливости",
                "", "https://www.afisha.ru/movie/222704/", "cinema");
        evs.add(ev);
        return evs;
    }
    private static ArrayList<Event>prepareExhibitionList(){
        ArrayList<Event> evs = new ArrayList<>();
        Event ev = new Event("Татьяна Назаренко и Игорь Новиков. Пространство времени", "",
                "Художественный музей им. Крамского ,","https://www.afisha.ru/exhibition/221175/",
                "exhibition");
        evs.add(ev);
        ev = new Event("Народные мотивы в творчестве воронежских авторов", "",
                "Музей-квартира Мордасовой ,","https://www.afisha.ru/exhibition/220765/",
                "exhibition");
        evs.add(ev);
        ev = new Event("Дамская сумочка", "",
                "Художественный музей им. Крамского ,","https://www.afisha.ru/exhibition/220066/",
                "exhibition");
        evs.add(ev);
        ev = new Event("Полтора века первому памятнику Алексею Кольцову", "",
                "Дом-музей Никитина ,","https://www.afisha.ru/exhibition/221277/",
                "exhibition");
        evs.add(ev);

        return evs;
    }
    private static ArrayList<Event>prepareConcertList() {
        ArrayList<Event> evs = new ArrayList<>();
        Event ev = new Event("Воронежский академический симфонический оркестр. Дирижер Игорь Вербицкий", "",
                "Воронежская филармония ,", "https://www.afisha.ru/concert/1860518/", "concert");
        evs.add(ev);
        ev = new Event("Сергей Жилин и «Фонограф-джаз-бэнда»", "",
                "Концертный зал ,", "https://www.afisha.ru/concert/1860522/", "concert");
        evs.add(ev);
        ev = new Event("Светлана Семенкова (фортепиано)", "",
                "Воронежская филармония ,", "https://www.afisha.ru/concert/1860513/", "concert");
        evs.add(ev);
        ev = new Event("Солисты симфонического оркестра филармонии", "",
                "Воронежская филармония ,", "https://www.afisha.ru/concert/1845195/", "concert");
        evs.add(ev);
        return evs;
    }
    private static ArrayList<Event> prepareTheatreList(){
        ArrayList<Event> evs = new ArrayList<>();
        Event ev = new Event("Одноклассники", "", "Воронежский камерный театр",
                "https://www.afisha.ru/performance/203795/","theatre");
        evs.add(ev);
        ev = new Event("Трамвай «Желание»", "", "Воронежский камерный театр",
                "https://www.afisha.ru/performance/103559/", "theatre");
        evs.add(ev);
        ev = new Event("Метель", "", "Театр драмы им. Кольцова",
                "https://www.afisha.ru/performance/115320/", "theatre");
        evs.add(ev);
        ev = new Event("Гроза", "", "Воронежский камерный театр",
                "https://www.afisha.ru/performance/191096/", "theatre");
        evs.add(ev);
        return evs;
    }
*/
/*
    @Test
    public void TestCinemaParser1() throws IOException {
        File in = new File("afishaCinema.html");
        Document docTest = Jsoup.parse(in, null);
        Connection con = mock(Connection.class);
        Mockito.when(con.get()).thenReturn(docTest);
        CinemaParser p = new CinemaParser();
        assertEquals(MockTest.prepareCinemaList(), p.parse("list__item-name", "list__item-info", AFISHA_CINEMA_URL));
    }

*/
/*
    @Test
    public void TestCinemaParser() throws IOException{
        Document afishaTest = Jsoup.parse(new File(AFISHA_CINEMA_HTML), null);
        assertEquals(MockTest.prepareCinemaList(), new CinemaParser().parse(CINEMA_CLASS_NAMES, CINEMA_CLASS_INFOS,"123", afishaTest));
    }

    @Test
    public void TestExhibitionParser() throws IOException{
        Document afishaTest = Jsoup.parse(new File(AFISHA_EXHIBITION_HTML), null);
        assertEquals(MockTest.prepareExhibitionList(), new ExhibitionParser().parse(EXHIBITION_CLASS_NAMES, EXHIBITION_CLASS_INFOS, EXHIBITION_CLASS_TIME , afishaTest));
    }
    @Test
    public void TestConcertParser() throws IOException{
        Document afishaTest = Jsoup.parse(new File(AFISHA_CONCERT_HTML), null);
        assertEquals(MockTest.prepareConcertList(), new ConcertParser().parse(CONCERT_CLASS_NAMES, CONCERT_CLASS_INFOS, CONCERT_CLASS_TIME, afishaTest));
    }
    @Test
    public void TestTheatreParser() throws IOException{
        Document afishaTest = Jsoup.parse(new File(AFISHA_THEATRE_HTML), null);
        assertEquals(MockTest.prepareTheatreList(), new TheatreParser().parse(THEATRE_CLASS_NAMES, THEATRE_CLASS_INFOS, THEATRE_CLASS_TIME, afishaTest));
    }

*/

}
