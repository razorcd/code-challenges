package com.redknightsshortestpath;

import java.util.AbstractMap;
import java.util.Objects;

public class Position {
    AbstractMap.SimpleEntry<String, Integer> name;
    int i;
    int j;

    public Position() {
    }

    /**
     * Define a new position on the table
     *
     * @param name a value object containing: Key: the name of the movement as string (e.g. "UL"), Value: the order priority.
     * @param i row coordinate
     * @param j column coordinate
     */
    public Position(AbstractMap.SimpleEntry<String, Integer> name, int i, int j) {
        this.name = name;
        this.i = i;
        this.j = j;
    }

    public AbstractMap.SimpleEntry<String, Integer> getName() {
        return name;
    }

    public void setName(AbstractMap.SimpleEntry<String, Integer> name) {
        this.name = name;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return i == position.i &&
                j == position.j &&
                Objects.equals(name, position.name);
    }

    public boolean equalsIJ(Position nextPosition) {
        return nextPosition.getI() == getI() && nextPosition.getJ() == getJ();
    }

    @Override
    public String toString() {
        return "{" + name + ',' + i + ',' + j + '}';
    }
}
