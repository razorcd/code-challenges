package com.other.challenges;


import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Function;
import java.util.stream.IntStream;

public class TrainWithStreams {
    private CopyOnWriteArrayList<Integer> wagons;

    public TrainWithStreams(int wagonCount, Function<Integer, Integer> fillWagon) {
        this.wagons = new CopyOnWriteArrayList<>();

        IntStream.range(0, wagonCount).forEach(i ->
            this.wagons.add(fillWagon.apply(i)));
    }

    public int peekWagon(int wagonIndex) {
        return this.wagons.get(wagonIndex);
    }

    public static void main(String[] args) {
        TrainWithStreams train = new TrainWithStreams(10, wagonIndex -> wagonIndex);

        for (int i = 0; i < 10; i++) {
            System.out.println("Wagon: " + i + ", cargo: " + train.peekWagon(i));
        }
    }
}