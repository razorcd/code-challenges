package com.other.challenges;

import org.junit.Test;

import static org.junit.Assert.*;

public class MostFrequentlyOccurringItemInArrayTest {

    @Test
    public void getMostOccuringItemFromAnEmptyArray() {
        Object[] objects = new Object[]{};

        MostFrequentlyOccurringItemInArray m = new MostFrequentlyOccurringItemInArray(objects);

        assertEquals("Most occurring item in [] should be null.", null, m.getMostOccuringItem());
    }

    @Test
    public void getMostOccuringItemFromAnArrayOfOne() {
        Object[] objects = new Object[]{"a"};

        MostFrequentlyOccurringItemInArray m = new MostFrequentlyOccurringItemInArray(objects);

        assertEquals("Most occurring item in ['a'] should be 'a'.", "a", m.getMostOccuringItem());
    }

    @Test
    public void getMostOccureingItemFromAnArrayOfMultiple() {
        Object[] objects = new Object[]{"a", "b", "c", "b"};
        MostFrequentlyOccurringItemInArray m = new MostFrequentlyOccurringItemInArray(objects);

        assertEquals("Most occurring item in ['a', 'b', 'c', 'b'] should be 'b'.", "b", m.getMostOccuringItem());
    }

    @Test
    public void getMostOccureingItemFromAnArrayOfMultipleWithSmaeCount() {
        Object[] objects = new Object[]{"a", "b", "c", "b", "d", "c"};
        MostFrequentlyOccurringItemInArray m = new MostFrequentlyOccurringItemInArray(objects);

        assertEquals("Most occurring item in ['a', 'b', 'c', 'b', 'd', 'c'] should be 'b'.", "b", m.getMostOccuringItem());
    }

    @Test
    public void getMostOccureingItemFromAnArrayOfMultipleWithDifferentTypes() {
        Object[] objects = new Object[]{"a", 4, true, 2, 4, "c", "a", 4};
        MostFrequentlyOccurringItemInArray m = new MostFrequentlyOccurringItemInArray(objects);

        assertEquals("Most occurring item in [\"a\", 4, true, 2, 4, \"c\", \"a\", 4] should be 4.", 4, m.getMostOccuringItem());
    }
}