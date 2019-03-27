package parsers.afisha_parser;

import java.text.ParseException;
import java.time.LocalDateTime;


public class TimeParser {
    public int getMonth(String nameOfMonth){
        final String[] MONTHS = {"пустое значение, чтоб январь был под индексом 1",
                "января", "февраля", "марта", "апреля", "мая" ,"июня",
                "июля", "августа", "сентября", "октября", "ноября", "декабря"};
        int month = 1;
        while(!MONTHS[month].equals(nameOfMonth))
            month++;
        return month;
    }
    public int getMonthShort(String shortNameOfMonth){
        final String[] MONTHS = {"пустое значение, чтоб январь был под индексом 1","янв.", "фев.","мар.","апр."};
        int month = 1;
        while(!MONTHS[month].equals(shortNameOfMonth))
            month++;
        return month;
    }

    public int[] getPlaceOfSpaces(char[] array){
        int p1 = 0;
        int p2 = 0;
        int p3 = 0;
        for(int i = 0; i < array.length; i++){
            if (p1 != 0 && p2 != 0 && array[i] == ' ') p3 = i;
            if (p1 != 0 && p2 ==0 && array[i] == ' ') p2 = i;
            if (p1 == 0 && array[i] == ' ') p1 = i;
        }
        return new int[]{p1, p2, p3};
    }

    //1 июня 2019 в 14:00 (МСК) 1559386800
    public LocalDateTime toDate(String dateInString){
        LocalDateTime date;
        int hour, min, day, month, year;

        String[] tmpStr = dateInString.split(" ");
        String[] time = tmpStr[4].split(":");
        day = Integer.valueOf(tmpStr[0]);
        month = getMonth(tmpStr[1]);
        year = Integer.valueOf(tmpStr[2]);
        hour = Integer.valueOf(time[0]);
        min = Integer.valueOf(time[1]);
        date = LocalDateTime.of(year, month, day, hour, min, 0, 0);
        return date;
    }

    /*public LocalDateTime toDate(String afishaRubbish) throws ParseException {
        //https://docs.oracle.com/javase/8/docs/api/java/time/LocalDateTime.html
        LocalDateTime date;


        int day, month, hour, minute;
        if (afishaRubbish.substring(0, 6).equals("завтра")) {//для записей типа "завтра в 19.00"
            date = LocalDateTime.now().plusDays(1);
            hour = Integer.valueOf(afishaRubbish.substring(9, 11));
            minute = Integer.valueOf(afishaRubbish.substring(12,14));
            date = date.withHour(hour);
            date = date.withMinute(minute);
            date = date.withSecond(0);
            date = date.withNano(0);
            return date;
        }
        else if (afishaRubbish.substring(0, 2).equals("до")) {
            int[] spaces = getPlaceOfSpaces(afishaRubbish.toCharArray());
            day = Integer.valueOf(afishaRubbish.substring(spaces[0] + 1, spaces[1]));
            month = getMonth(afishaRubbish.substring(spaces[1] + 1));
            date = LocalDateTime.now().withMonth(month);
            date = date.withDayOfMonth(day);
            date = date.withHour(0);
            date = date.withMinute(0);
            date = date.withSecond(0);
            date = date.withNano(0);
            return date;

        }
        else if (afishaRubbish.length() > 7 && afishaRubbish.substring(0, 7).equals("сегодня")){
            date = LocalDateTime.now();
            hour = Integer.valueOf(afishaRubbish.substring(10, 12));
            minute = Integer.valueOf(afishaRubbish.substring(13, 15));
            date = date.withHour(hour);
            date = date.withMinute(minute);
            date = date.withSecond(0);
            date = date.withNano(0);
            return date;
        }
        else if (afishaRubbish.length() > 13) {
            day = 0; month = 0; hour = 0; minute = 0;
            // "для записей типa 21 января в 20.00"
            int[] spaces = getPlaceOfSpaces(afishaRubbish.toCharArray());
            day = Integer.valueOf(afishaRubbish.substring(0, spaces[0]));
            //System.out.println("day= " + day);
            month = getMonth(afishaRubbish.substring(spaces[0] + 1, spaces[1]));
            //System.out.println("month= " + month);
            hour = Integer.valueOf(afishaRubbish.substring(spaces[2] + 1, spaces[2] + 3));
            //System.out.println("hour =" + hour);
            minute = Integer.valueOf(afishaRubbish.substring(spaces[2] + 4));
            //System.out.println("minute =" + minute);
            date = LocalDateTime.now().withMonth(month);
            date = date.withDayOfMonth(day);
            date = date.withHour(hour);
            date = date.withMinute(minute);
            date = date.withSecond(0);
            date = date.withNano(0);
            return date;
        }
        else if (afishaRubbish.length() == 12 || afishaRubbish.length() == 13) {
            day = 0; month = 0; hour = 0; minute = 0;
            int[] spaces = getPlaceOfSpaces(afishaRubbish.toCharArray());//вернется массив из 2х чисел
            day = Integer.valueOf(afishaRubbish.substring(0, spaces[0]));
            month = getMonthShort(afishaRubbish.substring(spaces[0] + 1, spaces[1]));
            hour = Integer.valueOf(afishaRubbish.substring(spaces[1] + 1, spaces[1] + 3));
            minute = Integer.valueOf(afishaRubbish.substring(spaces[1] + 4));
            date = LocalDateTime.now().withMonth(month);
            date = date.withDayOfMonth(day);
            date = date.withHour(hour);
            date = date.withMinute(minute);
            date = date.withSecond(0);
            date = date.withNano(0);
            return date;
        }
        else {
            day = 0; month = 0;
            // "для записей типа 1 июля"
            if (afishaRubbish.charAt(1) == ' ') {month = getMonth(afishaRubbish.substring(2)); day = Integer.valueOf(afishaRubbish.substring(0, 1));}
            if (afishaRubbish.charAt(2) == ' ') {month = getMonth(afishaRubbish.substring(3)); day = Integer.valueOf(afishaRubbish.substring(0, 2));}
            date = LocalDateTime.now().withMonth(month);
            date = date.withDayOfMonth(day);
            date = date.withHour(0);
            date = date.withMinute(0);
            date = date.withSecond(0);
            date = date.withNano(0);
            return date;
        }

    }*/

    public static void main(String[] args) throws ParseException {
        TimeParser p1 = new TimeParser();

        System.out.println("сегодня в 17.00 | " + p1.toDate("сегодня в 17.00"));
        System.out.println("завтра в 19.00 | " + p1.toDate("завтра в 19.00"));
        System.out.println("1 июня в 19.00 | " + p1.toDate("1 июня в 19.00"));
        System.out.println("24 февраля в 19.00 | " + p1.toDate("24 февраля в 19.00"));
        System.out.println("10 января | " + p1.toDate("10 января"));
        System.out.println("1 июля | " + p1.toDate("1 июля"));
        System.out.println("21 фев. 19:00 | " + p1.toDate("21 фев. 19:00"));
        System.out.println("1 фев. 17:00 | " + p1.toDate("1 фев. 17:00"));
    }
}
