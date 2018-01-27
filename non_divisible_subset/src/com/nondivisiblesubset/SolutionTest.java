package com.nondivisiblesubset;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class SolutionTest {

    @Test
    public void testNonDivisibleSubset() {
        assertEquals(3, Solution.nonDivisibleSubset(3, new int[]{1,7,2,4}));
        assertEquals(2, Solution.nonDivisibleSubset(4, new int[]{1,3,4}));
        assertEquals(4, Solution.nonDivisibleSubset(7, new int[]{1,2,7,10}));
        assertEquals(7, Solution.nonDivisibleSubset(11, new int[]{1,2,3,4,5,6,7,8,9,15,5,10}));
        assertEquals(1, Solution.nonDivisibleSubset(5, new int[]{15,5,10}));

        assertEquals(1, Solution.nonDivisibleSubset(1, new int[]{1,2,3,4,5}));
    }

    @Test
    public void testIsDivisibleSubsetOftwo() {
        assertFalse(Solution.isDivisibleSubsetOftwo(3, Arrays.asList(1,7,4, 0)));
        assertFalse(Solution.isDivisibleSubsetOftwo(3, Arrays.asList(5,9)));
        assertTrue(Solution.isDivisibleSubsetOftwo(3, Arrays.asList(1,7,4,2)));
        assertTrue(Solution.isDivisibleSubsetOftwo(3, Arrays.asList(6,3)));
    }
}