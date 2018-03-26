package com.theinquiringmanager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        List<Integer[]> orders = new ArrayList<>();

        try (Scanner in = new Scanner(System.in)) {
            in.useDelimiter("\n");
            int eventsCount = in.nextInt();

            for (int i = 0; i < eventsCount; i++) {
                String[] line = in.next().split(" ");

                if (line[0].equals("1")) {  // read action
                    orders.add(new Integer[]{Integer.parseInt(line[1]), Integer.parseInt(line[2])}); // read currentTime and currentPrice
                } else {
                    //find max price for last 60 sec
                    int maxPrice = -1;
                    int pos = orders.size()-1;
                    int timeLimit = Integer.parseInt(line[1])-60; // read current time and calculate the lower time limit
                    while(pos >= 0 && (timeLimit < 0 || orders.get(pos)[1]> timeLimit)) {
                        if (orders.get(pos)[0] > maxPrice) maxPrice = orders.get(pos)[0];
                        pos--;
                    }

                    System.out.println(maxPrice);
                }
            }
        }
    }
}
