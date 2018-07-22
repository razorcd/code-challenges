package com.other.challenges;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Given an array, find the most frequently occurring item.
 * O(n)
 */
public class MostFrequentlyOccurringItemInArray {

    private Object[] objects;

    public MostFrequentlyOccurringItemInArray(Object[] objects) {
        this.objects = objects;
    }


    public Object getMostOccuringItem() {
        Map<Object, Integer> objectsCount = new HashMap<>();
        Map.Entry<Object, Integer> memoizedMostOccuringItem = new HashMap.SimpleEntry<>(null, 0);

        for (Object o : objects) {
            objectsCount.compute(o, (k, oldValue) ->
                Optional.ofNullable(oldValue).orElse(0)+1
            );
            if (objectsCount.get(o) > memoizedMostOccuringItem.getValue()) memoizedMostOccuringItem = new HashMap.SimpleEntry<>(o, objectsCount.get(o));
        }

        return memoizedMostOccuringItem.getKey();

//        return objectsCount.entrySet()
//                .stream()
////                .reduce((o, e) -> o.getValue() > e.getValue() ? o : e)
//                .max(Map.Entry.comparingByValue())
//                .orElse(new HashMap.SimpleEntry(null, 0))
//                .getKey();
    }
}
