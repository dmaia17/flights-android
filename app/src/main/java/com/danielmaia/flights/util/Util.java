package com.danielmaia.flights.util;

import java.util.Calendar;
import java.util.Date;

public class Util {

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

    public static String getSqlFilter(){

        return "";
    }
}
