package com.other.challenges;

import java.util.HashMap;
import java.util.Map;

/**
 * Challenge:
 *
 *   Write a Map that can receive a case insensitive key.
 *   Then extend this to a Map that can receive a customizable key.
 *
 */
//@SuppressWarnings("unchecked")
public class CustomizableMap<T> {

    private Map<T, Integer> map = new HashMap<>();
    private CustomParser<T> parser;

    CustomizableMap(CustomParser<T> parser) {
        this.parser = parser;
    }

    public Integer get(T k) {
        return map.get(parser.getParsedValue(k));
    }

    public void set(T k, Integer v) {
        map.put(parser.getParsedValue(k), v);
    }



    public static void main(String[] args) {

//        CustomizableMap m = new CustomizableMap(new StringParser());
//        m.set(111, 222);
//        System.out.println(m.get(111));  // => java.lang.Integer cannot be cast to java.lang.String   at Runtime


        CustomizableMap<String> m = new CustomizableMap<>(new StringParser());
        m.set("Aaa", 1);
        m.set("aaa", 2);  // will overwrite above
        System.out.println(m.get("aaa"));
        System.out.println(m.get("Aaa"));
        System.out.println(m.get("Baa"));
        System.out.println();


        CustomizableMap<Integer> m2 = new CustomizableMap<>(new IntegerParser());
        m2.set(10, 1);
        m2.set(11, 2); // will overwrite above
        System.out.println(m2.get(10));
        System.out.println(m2.get(11));
        System.out.println(m2.get(12));
    }
}



interface CustomParser<T> {
    T getParsedValue(T value);
}

class StringParser implements CustomParser<String> {
    @Override
    public String getParsedValue(String value) {
        return value.toLowerCase();
    }

    private String gettt(){
        return "tt";
    }
}

class CharacterParser implements CustomParser<Character> {

    @Override
    public Character getParsedValue(Character value) {
        return Character.toLowerCase(value);
    }
}

class IntegerParser implements CustomParser<Integer> {
    @Override
    public Integer getParsedValue(Integer value) {
        return (value % 2 == 0 ? value : value-1);
    }
}
