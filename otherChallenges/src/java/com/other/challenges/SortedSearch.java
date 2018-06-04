package com.other.challenges;

public class SortedSearch {
    public static int countNumbers(int[] sortedArray, int lessThan) {
//        return (int) Arrays.stream(sortedArray).filter(v -> v < lessThan).count();  // => bad performance

        // if number is not within array boundary values
        if (lessThan < sortedArray[0]) return 0;
        if (lessThan > sortedArray[sortedArray.length-1]) return sortedArray.length;

        // recurcive by divide and conquer
        return searchArray(0, sortedArray.length-1, sortedArray, lessThan);
    }


    public static int searchArray(int minI, int maxI, int[] array, int num) {
        int middle = (minI + maxI) / 2;

        if (array[middle] == num) return middle;  // if exact match
        if (minI+1 == maxI) return maxI;    // If  It is return maxI because we search for less then. Otherwise return minI.
        if (array[middle] < num) return searchArray(middle, maxI, array, num);
        else return searchArray(minI, middle, array, num);
    }


    public static void main(String[] args) {
        System.out.println(SortedSearch.countNumbers(new int[] { 1, 3, 5, 7 }, 4));
        System.out.println(SortedSearch.countNumbers(new int[] { 1, 3}, 1));
        System.out.println(SortedSearch.countNumbers(new int[] { 1, 3}, 2));
        System.out.println(SortedSearch.countNumbers(new int[] { 1, 3}, 3));
        System.out.println(SortedSearch.countNumbers(new int[] { 1, 3}, 4));
        System.out.println(SortedSearch.countNumbers(new int[] { 1 }, 1));
        System.out.println(SortedSearch.countNumbers(new int[] { 1 }, 2));
        System.out.println(SortedSearch.countNumbers(new int[] { 1 }, 0));
    }
}