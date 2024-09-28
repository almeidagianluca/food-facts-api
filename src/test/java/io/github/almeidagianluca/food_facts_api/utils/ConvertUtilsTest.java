package io.github.almeidagianluca.food_facts_api.utils;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ConvertUtilsTest {

    @Test
    void bytesToMegabytes() {
        long bytes = 1048576;

        long result = ConvertUtils.bytesToMegabytes(bytes);

        assertEquals(1, result);
    }

    @Test
    void millisecondsToSeconds() {
        long milliseconds = 5000;

        long result = ConvertUtils.millisecondsToSeconds(milliseconds);

        assertEquals(5, result);
    }

    @Test
    void dateToString() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String expected = formatter.format(date);

        String result = ConvertUtils.dateToString(date);

        assertEquals(expected, result);
    }
}