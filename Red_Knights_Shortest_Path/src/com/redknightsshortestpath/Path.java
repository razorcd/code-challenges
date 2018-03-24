package com.redknightsshortestpath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Path {

    static void printShortestPath(int n, int i_start, int j_start, int i_end, int j_end) {
        //  Print the distance along with the sequence of moves.
        String[] result = getShortestPath(n, i_start, j_start, i_end, j_end);

        if (result.length>0) {
            System.out.println(result.length);
            System.out.println(String.join(" ", result));
        } else {
            System.out.println("Impossible");
        }
    }

    static String[] getShortestPath(int n, int i_start, int j_start, int i_end, int j_end) {
        return getShortestPathRecursive(n, i_start, j_start, i_end, j_end, new ArrayList<Position>(), new ArrayList<Position>())
                .stream()
                .map(Position::getName)
                .toArray(String[]::new);
    }

    private static List<Position> getShortestPathRecursive(int n, int i_start, int j_start, int i_end, int j_end, List<Position> shortestPath, List<Position> currentPath) {
        for (Position nextPosition : getNextPossibleMoves(n, i_start, j_start)) {

            if (!isMovingCloser(new Position("", i_start, j_start), new Position("", i_end, j_end), nextPosition)) continue;

            // ensure nextPosition is not in the currentPath already
            if (!currentPath.stream().anyMatch(position -> position.equalsIJ(nextPosition))) {
                currentPath.add(nextPosition);

                //is end position match?
                if(nextPosition.getI()==i_end && nextPosition.getJ()==j_end) {
                    if (shortestPath.isEmpty() || shortestPath.size() > currentPath.size()) {
                        shortestPath = currentPath.stream().collect(Collectors.toList());
//                        System.out.println("Found a path: " + currentPath);
//                        System.out.println("Shortest path still: " + shortestPath);
                    }
                } else {
                    shortestPath = getShortestPathRecursive(n, nextPosition.getI(), nextPosition.getJ(), i_end, j_end, shortestPath, currentPath);
                }

                currentPath.remove(currentPath.size()-1);
            }
        }
        return shortestPath;
    }

    private static List<Position> getNextPossibleMoves(int n, int i, int j) {
        return Arrays.stream(new Position[]{
                new Position("UL", i - 2, j - 1),
                new Position("UR", i - 2, j + 1),
                new Position("R", i, j + 2),
                new Position("LR", i + 2, j + 1),
                new Position("LL", i + 2, j - 1),
                new Position("L", i, j - 2)
            })
            .filter(p -> p.getI()>=0 && p.getJ()>=0 && p.getI()<n && p.getJ()<n)  // filter only positions that are on the table
            .collect(Collectors.toList());
    }

    private static boolean isMovingCloser(Position start, Position end, Position nextPosition) {
        return distanceBetween(start, end)>distanceBetween(nextPosition, end);
    }

    private static double distanceBetween(Position start, Position end) {
        return Math.sqrt(Math.pow(start.getI()-end.getI(),2)+Math.pow(start.getJ()-end.getJ(), 2));
    }
}
