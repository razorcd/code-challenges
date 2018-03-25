package com.redknightsshortestpath;

import java.util.*;
import java.util.stream.Collectors;

public class PathRecursive {

    /**
     * Prints the steps needed to move from start to finish, to stdout
     *
     * @param n the table size
     * @param i_start row coordinate for start position
     * @param j_start column coordinate for start position
     * @param i_end i row coordinate for end position
     * @param j_end j column coordinate for end position
     */
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

    /**
     * Returns a string of steps needed to move from start to end
     *
     * @param n table size
     * @param i_start row coordinate for start position
     * @param j_start column coordinate for start position
     * @param i_end i row coordinate for end position
     * @param j_end j column coordinate for end position
     * @return an array of movements as strings
     */
    static String[] getShortestPath(int n, int i_start, int j_start, int i_end, int j_end) {
        Position start = new Position(null, i_start, j_start);
        Position end = new Position(null, i_end, j_end);

        return getShortestPathRecursive(n, start, end, new ArrayList<Position>(), new ArrayList<Position>())
                .stream()
                .map(Position::getName)
                .sorted((o1,o2) -> o1.getValue()-o2.getValue())  // will sort the result to follow order of priority: UL, UR, R, LR, LL, L.
                .map(name -> name.getKey())
                .toArray(String[]::new);
    }

    /**
     * Get List of positions between 2 fixed positions of the table
     *
     * @param n table size
     * @param start start position
     * @param end end position
     * @param shortestPath argument used to store the state of shortestPath between recursive states. Should be initialised to new ArrayList<Position>()
     * @param currentPath argument used to store the state of currentPath between recursive states. Should be initialised to new ArrayList<Position>()
     * @return the shortest list of positions
     */
    private static List<Position> getShortestPathRecursive(int n, Position start, Position end, List<Position> shortestPath, List<Position> currentPath) {
        //filter to get only the movement that is getting the closest
        List<Position> possiblePositions = getNextPossibleMoves(n, start.getI(), start.getJ());

        //get closest next position
        Position nextPosition = getClosestPositionTowardsEnd(possiblePositions, end);

        // ensure nextPosition is not in the currentPath already
        if (currentPath.stream().anyMatch(position -> position.equalsIJ(nextPosition))) return shortestPath;

        currentPath.add(nextPosition);

        //is end position match?
        if(nextPosition.getI()==end.getI() && nextPosition.getJ()==end.getJ()) {
            System.out.println("Found a path:        " + currentPath);
            if (shortestPath.isEmpty() || shortestPath.size() > currentPath.size()) {
                shortestPath = currentPath.stream().collect(Collectors.toList());
            }
            System.out.println("Shortest path still: " + shortestPath);
        } else {
            if (shortestPath.isEmpty() || (!shortestPath.isEmpty() && shortestPath.size()>currentPath.size())) { // follow only paths that are smaller than the already found shortestPath
                shortestPath = getShortestPathRecursive(n, nextPosition, end, shortestPath, currentPath);
            }
        }

        currentPath.remove(currentPath.size()-1);
        return shortestPath;
    }

    /**
     * Defines all possible movements from specified coordinates
     *
     * @param n table size
     * @param i row coordinate
     * @param j column coordinate
     * @return list of all possible positions
     */
    private static List<Position> getNextPossibleMoves(int n, int i, int j) {
        return Arrays.stream(new Position[]{
                new Position(new AbstractMap.SimpleEntry<>("UL", 0), i - 2, j - 1),
                new Position(new AbstractMap.SimpleEntry<>("UR", 1), i - 2, j + 1),
                new Position(new AbstractMap.SimpleEntry<>("R", 2), i, j + 2),
                new Position(new AbstractMap.SimpleEntry<>("LR", 3), i + 2, j + 1),
                new Position(new AbstractMap.SimpleEntry<>("LL", 4), i + 2, j - 1),
                new Position(new AbstractMap.SimpleEntry<>("L", 5), i, j - 2)
            })
            .filter(p -> p.getI()>=0 && p.getJ()>=0 && p.getI()<n && p.getJ()<n)  // filter only positions that are on the table
            .collect(Collectors.toList());
    }

    /**
     * Get the position that is closest to the end
     *
     * @param positions the positions to compare
     * @param end the destination position
     * @return the closest position
     */
    private static Position getClosestPositionTowardsEnd(List<Position> positions, Position end) {
        Position closestPosition = null;
        Double closestDistance = null;
        for (Position p : positions) {
            double distanceForP = distanceBetween(p, end);
            if (Objects.isNull(closestDistance) || distanceForP < closestDistance) {
                closestPosition = p;
                closestDistance = distanceForP;
            }
        }
        return closestPosition;
    }

    private static double distanceBetween(Position start, Position end) {
        return Math.sqrt(Math.pow(start.getI()-end.getI(),2)+Math.pow(start.getJ()-end.getJ(), 2));
    }
}
