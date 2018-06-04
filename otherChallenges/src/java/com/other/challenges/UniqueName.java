package com.other.challenges;


import java.util.AbstractMap;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Stream;

public class UniqueName {


    /**
     * Challenge:  return first unique element in the array
     *
     * @param names  array of names. Example: { "xx", "Abbi", "Adalia", "xx", "UNIQUE", "Abbi", "Adalia" }
     * @return first unique element. Example: "UNIQUE"
     */
    public static String firstUniqueName(String[] names) {


//        // ITERATE  O(n^2)  - works
//        boolean unique;
//
//        for (int i = 0; i < names.length; i++) {
//            unique = true;
//            for (int j = 0; j < names.length; j++) {
////                System.out.println("Checking " + names[i] + " and " + names[j]);
//                if (i!=j && names[i].equals(names[j])) {
//                    unique=false;
//                    break;
//                }
//            }
//            if (unique) return names[i];
//
//        }
//        return null;
//



//        // ITERATE with Array mutation O(n^2)  - works
//        boolean unique;
//
//        for (int i = 0; i < names.length; i++) {
//            if (Objects.isNull(names[i])) continue;
//            unique = true;
//            for (int j = 0; j < names.length; j++) {
//                if (i==j || Objects.isNull(names[j])) continue;
////                System.out.println("Checking " + names[i] + " and " + names[j]);
//                if (names[i].equals(names[j])) {
//                    names[i] = null;
//                    names[j] = null;
//                    unique=false;
//                    break;
//                }
//            }
//            if (unique) return names[i];
//
//        }
//        return null;



////        USING PARALLEL STREAMS   - works
//        ConcurrentHashMap<String, Integer> hash = new ConcurrentHashMap<>();
//
//        Arrays.stream(names).parallel().forEach(s -> {
//            if (hash.get(s) == null) hash.put(s, 1);
//            else hash.put(s, hash.get(s) + 1);
//        });
//
//        return hash.entrySet().parallelStream().filter((e)-> e.getValue()==1).findFirst().orElse(new AbstractMap.SimpleEntry<>(null, null)).getKey();



//////        USING PARALLEL STREAMS   - works
            return Arrays.stream(names).distinct().filter(elem ->
                    Arrays.stream(names).parallel().filter(v -> elem.equals(v)).count() == 1)
                .findFirst().orElse(null);



////        USING PARALLEL STREAMS with ThreadPool  - works
//        try {
//            ForkJoinPool customThreadPool = new ForkJoinPool(8);
//            String uniqueS = customThreadPool.submit(() -> {
//                return Arrays.stream(names).distinct().filter(elem ->
//                        Arrays.stream(names).parallel().filter(v -> elem.equals(v)).count() == 1
//                ).findFirst().orElse(null);
//            }).get();
//            return uniqueS;
//        } catch (InterruptedException | ExecutionException e) {throw new RuntimeException(e.getMessage());}
    }

    public static void main(String[] args) {
        System.out.println(firstUniqueName(new String[] { "Abbi", "Adalia", "Abbi", "Adalia", "x", "x" }));
        System.out.println(firstUniqueName(new String[] { "xx", "Abbi", "Adalia", "xx", "UNIQUE", "Abbi", "Adalia" }));
        System.out.println(firstUniqueName(new String[] { "xx", "unique1", "Adalia", "xx", "unique2", "Adalia" }));
    }
}
