package com.timeconversion;

public class Main {

    public static void main(String[] args) {

        System.out.println(timeConversion("11:11:11AM"));
        System.out.println(timeConversion("11:11:11PM"));
        System.out.println(timeConversion("12:11:11AM"));
        System.out.println(timeConversion("12:11:11PM"));
    }

    static String timeConversion(String s) {
        boolean pm = s.contains("PM");
        String[] result = s.replaceAll("AM|PM", "").split(":");

        if (pm) {
            if (Integer.parseInt(result[0]) < 12) {
                result[0] = String.valueOf(Integer.parseInt(result[0]) + 12);
            }
        }
        else if (result[0].equals("12")) {result[0] = "00";}
        return String.join(":", result);
    }
}
