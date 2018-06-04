package com.other.challenges;

import java.util.stream.Stream;

public class Fibonacci {

    public static void main(String[] args) {
        System.out.println(fibonacci(5));
    }

    public static long fibonacci(int n) {
            return Stream.iterate(new Long[]{1L,1L}, v -> new Long[]{v[1], v[0]+v[1]})
                    .limit(n)
                    .skip(n==0 ? 0 : n-1)
                    .findFirst()
                    .orElse(new Long[]{0L,0L})[1];
        }
}
