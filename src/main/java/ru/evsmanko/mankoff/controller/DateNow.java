package ru.evsmanko.mankoff.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateNow {
    public static String getDate() {
        Date dateNow = new Date();
        SimpleDateFormat formatForDate = new SimpleDateFormat("E dd.MM.yyyy'. Время' hh:mm:ss'.'");
        return formatForDate.format(dateNow);
    }
}
