package com.other.challenges.dayofweek;

import java.util.Arrays;
import java.util.List;

public class Dayofweek {

    private List<String> days = Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday");

    private Integer currentDayPos;

    public Dayofweek(String day) {
        this.currentDayPos = days.indexOf(day);
    }


    public void add(int i) {
        currentDayPos = (currentDayPos + i) % days.size();
    }

    public String getDay() {
        return days.get(currentDayPos);
    }

    public void substract(int i) {
        int pos = (currentDayPos - i) % days.size();
        if (pos < 0) {
            currentDayPos = getPositionFromLastElement(pos);
        } else {
            currentDayPos = pos;
        }
    }

    private int getPositionFromLastElement(int positionFromLast) {
        return days.size() - Math.abs(positionFromLast);
    }
}
