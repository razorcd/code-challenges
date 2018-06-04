package com.other.challenges;


import java.util.LinkedList;

public class TrainComposition {
    LinkedList<Integer> queue = new LinkedList<>();

    public void attachWagonFromLeft(int wagonId) {
        queue.push(wagonId);
    }

    public void attachWagonFromRight(int wagonId) {
        queue.add(wagonId);
    }

    public int detachWagonFromLeft() {
        return queue.pop();
    }

    public int detachWagonFromRight() {
        return queue.remove(queue.size()-1);
    }

    public LinkedList<Integer> getQueue() {
        return queue;
    }

    public static void main(String[] args) {
        TrainComposition tree = new TrainComposition();
        tree.attachWagonFromLeft(7);
        tree.attachWagonFromLeft(13);
        System.out.println(tree.getQueue());
        System.out.println(tree.detachWagonFromRight()); // 7
        System.out.println(tree.detachWagonFromLeft()); // 13


        tree.attachWagonFromLeft(7);
        tree.attachWagonFromLeft(13);
        tree.attachWagonFromRight(5);
        tree.attachWagonFromLeft(15);
        System.out.println(tree.getQueue());

        System.out.println(tree.detachWagonFromRight()); // 5
        System.out.println(tree.detachWagonFromLeft()); // 15
        System.out.println(tree.detachWagonFromRight()); // 7
        System.out.println(tree.detachWagonFromLeft()); // 13
    }
}