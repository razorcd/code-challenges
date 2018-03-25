package com.redknightsshortestpath;

import java.util.*;
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
//        Position start = new Position(null, i_start, i_end);
//        Position end = new Position(null, i_end, j_end);

        return getShortestPathRecursive(n, i_start, j_start, i_end, j_end, new ArrayList<Position>(), new ArrayList<Position>())
                .stream()
                .map(Position::getName)
                .sorted((o1,o2) -> o1.getValue()-o2.getValue())  // will sort the result to follow order of priority: UL, UR, R, LR, LL, L.
                .map(name -> name.getKey())
                .toArray(String[]::new);
    }

    private static List<Position> getShortestPathRecursive(int n, int i_start, int j_start, int i_end, int j_end, List<Position> shortestPath, List<Position> currentPath) {
        //filter to get only the movement that is getting the closest
        List<Position> positions = getNextPossibleMoves(n, i_start, j_start);

        Position closestPosition = null;
        Double closestDistance = null;
        for (Position p : positions) {
            double distanceForP = distanceBetween(p, new Position(new AbstractMap.SimpleEntry<>("L", 0), i_end, j_end));
            if (Objects.isNull(closestDistance) || distanceForP < closestDistance) {
                closestPosition = p;
                closestDistance = distanceForP;
            }
        }
        positions = Arrays.asList(closestPosition);

        for (Position nextPosition : positions) {
            if (!isMovingCloser(new Position(new AbstractMap.SimpleEntry<>("",0), i_start, j_start), new Position(new AbstractMap.SimpleEntry<>("",0), i_end, j_end), nextPosition)) continue;  // not needed anymore

            // ensure nextPosition is not in the currentPath already
            if (currentPath.stream().anyMatch(position -> position.equalsIJ(nextPosition))) continue;

            currentPath.add(nextPosition);

            //is end position match?
            if(nextPosition.getI()==i_end && nextPosition.getJ()==j_end) {
                System.out.println("Found a path:        " + currentPath);
                if (shortestPath.isEmpty() || shortestPath.size() > currentPath.size()) {
                    shortestPath = currentPath.stream().collect(Collectors.toList());
                }
                System.out.println("Shortest path still: " + shortestPath);

                currentPath.remove(currentPath.size()-1);
                break; //skip following this path
            } else {
                if (shortestPath.isEmpty() || (!shortestPath.isEmpty() && shortestPath.size()>currentPath.size())) { // follow only paths that are smaller than the already found shortestPath
                    shortestPath = getShortestPathRecursive(n, nextPosition.getI(), nextPosition.getJ(), i_end, j_end, shortestPath, currentPath);
                }
                currentPath.remove(currentPath.size()-1);
            }
        }
        return shortestPath;
    }

    private static List<Position> getNextPossibleMoves(int n, int i, int j) {

        return Arrays.stream(new Position[]{
                new Position(new AbstractMap.SimpleEntry<String, Integer>("UL", 0), i - 2, j - 1),
                new Position(new AbstractMap.SimpleEntry<String, Integer>("UR", 1), i - 2, j + 1),
                new Position(new AbstractMap.SimpleEntry<String, Integer>("R", 2), i, j + 2),
                new Position(new AbstractMap.SimpleEntry<String, Integer>("LR", 3), i + 2, j + 1),
                new Position(new AbstractMap.SimpleEntry<String, Integer>("LL", 4), i + 2, j - 1),
                new Position(new AbstractMap.SimpleEntry<String, Integer>("L", 5), i, j - 2)
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
