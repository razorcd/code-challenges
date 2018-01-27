package com.nondivisiblesubset;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {

    public static void main(String[] args) {
        System.out.println(nonDivisibleSubset(3, new int[]{1,7,2,4}));
    }

    public static int nonDivisibleSubset(int k, int[] arr) {
        LinkedList<Integer> list = new LinkedList<>(Arrays.stream(arr).boxed().collect(Collectors.toList()));
        return Solution.printSubArrays(k, list, new LinkedList<>(), 0, 1);
    }

    /**
     * Prints all possible subarrays with or without order duplication
     *     call as: Main.printSubArrays(new LinkedList<>(Arrays.asList(9, 8, 7, 6, 4)), new LinkedList<>(), 0);
     *
     * @param arrayList original array
     * @param sublist current subarray
     * @param element current position
     * @return [int] max subSet
     */
    public static int printSubArrays(int k, List<Integer> arrayList, List<Integer> sublist, int element, int maxSubset) {
        for (int i = element; i < arrayList.size(); i++) {
            sublist.add(arrayList.remove(i));
            if (sublist.size()>=2 && !isDivisibleSubsetOftwo(k, sublist)) {
                maxSubset = Math.max(maxSubset, sublist.size());
//                System.out.println("Posssible good result: " + Arrays.toString(sublist.toArray())); // move this top, here, bottom to change result order and inclusion of [].
            }
            maxSubset = printSubArrays(k, arrayList, sublist, i, maxSubset); // add element 0 for duplicates and element i for no duplicates
            arrayList.add(i, sublist.remove(sublist.size()-1));
        }
        return maxSubset;
    }

    /**
     * Inspects if the sum of any 2 numbers from subset is divisible by k.
     * @param k the number to divide by
     * @param subset the array of numbers
     * @return [boolean]
     */
    public static boolean isDivisibleSubsetOftwo(int k, List<Integer> subset) {
        if (subset.size()<2) throw new IllegalArgumentException("Subset must be at least 2 elements");

        for (int i = 0; i < subset.size()-1; i++) {
            for (int j = i+1; j < subset.size(); j++) {
                if (isDivisible( subset.get(i) + subset.get(j), k)) return true;
            }
        }

        return false;
    }

    private static boolean isDivisible(int value, int by) {
        return value % by == 0;
    }
}

