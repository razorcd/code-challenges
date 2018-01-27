package com.sherlockandthevalidstring;

import org.junit.Test;

import static org.junit.Assert.*;

public class MainTest {

    @Test
    public void testIsValid() {

        //same frequency
        assertEquals("YES", Main.isValid("d"));            // 1
        assertEquals("YES", Main.isValid("dddddd"));       // 6
        assertEquals("YES", Main.isValid("aabbcc"));       // 2 2 2
        assertEquals("YES", Main.isValid("aaaabbbbcccc")); // 4 4 4
        assertEquals("YES", Main.isValid("abc"));          // 1 1 1

        //same frequency except 1 char
        assertEquals("YES", Main.isValid("aaaaab"));        // 5 1
        assertEquals("YES", Main.isValid("abcdd"));         // 1 1 1 2
        assertEquals("YES", Main.isValid("aabbc"));         // 2 2 1
        assertEquals("YES", Main.isValid("aabbccc"));       // 2 2 3
        assertEquals("YES", Main.isValid("aaabbbcccdddd")); // 3 3 3 4
        assertEquals("YES", Main.isValid("aabbccz"));       // 2 2 2 1
        assertEquals("YES", Main.isValid("zaaabbbccc"));    // 1 3 3 3
        assertEquals("YES", Main.isValid("zzzzaaabbbccc")); // 4 3 3 3

        // same frequency except multiple chars
        assertEquals("NO", Main.isValid("abbbc"));         // 1 3 1
        assertEquals("NO", Main.isValid("aabbbbbcc"));     // 2 5 2
        assertEquals("NO", Main.isValid("aabbcd"));        // 2 2 1 1
        assertEquals("NO", Main.isValid("aaabbbccdd"));    // 3 3 2 2
        assertEquals("NO", Main.isValid("aaabbc"));        // 3 2 1
        assertEquals("NO", Main.isValid("zaababcbccz"));   // 2 3 3 3

    }
}

