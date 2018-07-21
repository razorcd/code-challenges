package com.other.challenges.dayofweek;

import org.junit.Test;

import static org.junit.Assert.*;

public class DayofweekTest {

    @Test
    public void shouldReturnInitializedDay() {
        Dayofweek dayofweek = new Dayofweek("Monday");
        String currentDay = dayofweek.getDay();
        assertEquals("Should return initialized day.", "Monday", currentDay);
    }

    @Test
    public void shouldReturnCorrectDayWhenAdding() {
        String[][] table = new String[][]{
                {"Monday", "0", "Monday"},
                {"Monday", "1", "Tuesday"},
                {"Monday", "2", "Wednesday"},
                {"Monday", "3", "Thursday"},
                {"Monday", "4", "Friday"},
                {"Monday", "5", "Saturday"},
                {"Monday", "6", "Sunday"},
                {"Monday", "7", "Monday"},
                {"Monday", "8", "Tuesday"},

                {"Tuesday", "5", "Sunday"},
                {"Wednesday", "3", "Saturday"},
                {"Thursday", "1", "Friday"},
                {"Friday", "2", "Sunday"},
                {"Saturday", "2", "Monday"},
                {"Sunday", "6", "Saturday"},
        };

        for (String[] line : table) {
            Dayofweek dayofweek = new Dayofweek(line[0]);
            dayofweek.add(Integer.parseInt(line[1]));
            assertEquals("Should return " + line[2] + " when adding " + line[1] + " to " + line[0], line[2], dayofweek.getDay());
        }
    }

    @Test
    public void shouldReturnCorrectDayWhenSubstracting() {
        String[][] table = new String[][]{
                {"Sunday", "8", "Saturday"},
                {"Sunday", "7", "Sunday"},
                {"Sunday", "6", "Monday"},
                {"Sunday", "5", "Tuesday"},
                {"Sunday", "4", "Wednesday"},
                {"Sunday", "3", "Thursday"},
                {"Sunday", "2", "Friday"},
                {"Sunday", "1", "Saturday"},
                {"Sunday", "0", "Sunday"},

                {"Saturday", "3", "Wednesday"},
                {"Friday", "5", "Sunday"},
                {"Wednesday", "1", "Tuesday"},
                {"Tuesday", "3", "Saturday"},
                {"Monday", "3", "Friday"},
        };

        for (String[] line : table) {
            Dayofweek dayofweek = new Dayofweek(line[0]);
            dayofweek.substract(Integer.parseInt(line[1]));
            assertEquals("Should return " + line[2] + " when substracting " + line[1] + " to " + line[0], line[2], dayofweek.getDay());
        }
    }
}