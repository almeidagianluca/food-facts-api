package io.github.almeidagianluca.food_facts_api.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ConvertUtils {
    public static long bytesToMegabytes(long bytes) {
        return bytes / (1024L * 1024L);
    }

    public static long millisecondsToSeconds(long milliseconds) {
        return milliseconds / 1000;
    }

    public static String dateToString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return formatter.format(date);
    }
}
