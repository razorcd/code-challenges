package com.other.challenges;

import java.util.HashSet;
import java.util.Set;

/**
 * Return first letter that is repeated or null.
 */
public class LetterRecurence {

    private String string;

    LetterRecurence(String string) {
        this.string = string;
    }

    public Character getFirstRecurence() {
        Set<Character> characters = new HashSet<>();

        for (Character c : string.toCharArray()) {
            if (characters.contains(c)) return c;
            characters.add(c);
        }
        return null;
    }
}
