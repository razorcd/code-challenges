package com.sherlockandthevalidstring;

import java.util.LinkedHashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

    }

    // Frequency map logic:
    //   case 1: all values equal?
    //   case 2: is value 1 unique? then remove it => all rest equal?
    //   case 3: get max value => is max value unique? then remove it => all rest equal? => is max-1 == all rest value?


    public static String isValid(String s){
        LinkedHashMap<Character, Integer> frequency = getFrequency(s);

        if (allCharsHaveSameFrequency(frequency))  return "YES";

        return "NO";
    }

    private static boolean allCharsHaveSameFrequency(LinkedHashMap<Character, Integer> frequency) {
        Integer[] distinctValues = frequency.values().parallelStream().distinct().toArray(Integer[]::new);

        //  check case 1: all values equal?
        if (allValuesEqual(frequency)) return true;

        int oneCount = 0;
        Character  oneKey;
        Character maxKey;
        int maxValue = 0;

        for (Map.Entry<Character, Integer> e : frequency.entrySet()) {
            if (e.getValue() == 1) {
                oneCount++;
                oneKey = e.getKey();
            }
            if (e.getValue() > maxValue) {
                maxValue = e.getValue();
                maxKey = e.getKey();
            }
        }

        // check case 2: is value 1 unique? then remove it => all rest equal?
        if (oneCount==1 && frequency.values().parallelStream().filter(v -> v != 1).distinct().count() == 1) return true;

        // check case 3: get max value => is max value unique? then remove it => all rest equal? => is max-1 == all rest value?
        int finalMaxValue = maxValue;
        long maxCount = frequency.values().parallelStream().filter(v -> v == finalMaxValue).count();
        if (maxCount==1 &&
            frequency.values().parallelStream().filter(v -> v != finalMaxValue).distinct().count() == 1 &&
            frequency.values().parallelStream().filter(v -> v != finalMaxValue).distinct().findFirst().get() == finalMaxValue-1) return true;

        return false;
    }

    private static LinkedHashMap<Character, Integer> getFrequency(String s) {
        LinkedHashMap<Character, Integer> frequency = new LinkedHashMap<>();
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            frequency.put(c, frequency.getOrDefault(c,0)+1);
        }
        return frequency;
    }

    private static boolean allValuesEqual(LinkedHashMap<Character, Integer> frequency) {
        return frequency.values().parallelStream().distinct().count()==1;
    }

}
