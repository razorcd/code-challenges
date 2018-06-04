package com.other.challenges;


public class TwoSum {
    public static int[] findTwoSum(int[] list, int sum) {
        int lookingFor;

        for (int i = 0; i < list.length-1; i++) {
            lookingFor = sum-list[i];
            for (int j = i+1; j < list.length; j++) {
                if (list[j] == lookingFor) return new int[]{i,j};
            }
        }
        return null;
    }

    public static void main(String[] args) {
        int[] indices = findTwoSum(new int[] { 3, 1, 5, 7, 5, 9 }, 14);
        if(indices != null) {
            System.out.println(indices[0] + " " + indices[1]);
        }
    }
}