package com.other.challenges;


import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Train {
    private List<Integer> wagons;

    public Train(int wagonCount, Function<Integer, Integer> fillWagon) {
        this.wagons = new ArrayList<>();

        for (int i = 0; i < wagonCount; i++) {
            this.wagons.add(fillWagon.apply(i));
        }
    }

    public int peekWagon(int wagonIndex) {
        return this.wagons.get(wagonIndex);
    }

    public static void main(String[] args) {
        Train train = new Train(10, wagonIndex -> wagonIndex);

        for (int i = 0; i < 10; i++) {
            System.out.println("Wagon: " + i + ", cargo: " + train.peekWagon(i));
        }
    }
}