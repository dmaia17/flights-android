package com.danielmaia.flights.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Util {

    public static String getHourAndMinAsString(Date date) {
        String asString = new SimpleDateFormat("HH:mm", new Locale("pt", "BR")).format(date.getTime());

        return asString;
    }

    public static String getHourHMinAsString(long duration) {

        long hours = duration / 60;
        long minutes = duration % 60;

        return String.format("%dh%d", hours, minutes);
    }


    public static int getPeriodOfTheDay(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int hour = cal.get(Calendar.HOUR_OF_DAY);

        if (hour < 6)
            return Constantes.DAWN;
        else if (hour < 12)
            return Constantes.MORNING;
        else if (hour < 18)
            return Constantes.AFTERNOON;
        else
            return Constantes.NIGHT;
    }
}
