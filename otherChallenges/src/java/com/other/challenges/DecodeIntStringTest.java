package com.other.challenges;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;

public class DecodeIntStringTest {

    @Test
    public void decodeEmptyStringShouldReturnEmptyArray() {
        DecodeIntString decodeIntString1 = new DecodeIntString("");
        assertEquals("Decoding empty string should return [].", Collections.EMPTY_LIST, decodeIntString1.decode());
    }

    @Test
    public void decodeStringOfOneNonZeroInt() {
        DecodeIntString decodeIntString1 = new DecodeIntString("3");
        assertEquals("Decoding '3' should return ['c'].", Arrays.asList("c"), decodeIntString1.decode());

        DecodeIntString decodeIntString2 = new DecodeIntString("8");
        assertEquals("Decoding '8' should return ['h'].", Arrays.asList("h"), decodeIntString2.decode());
    }

    @Test
    public void decodeStringOfOneZeroInt() {
        DecodeIntString decodeIntString = new DecodeIntString("0");
        assertEquals("Decoding '0' should return empty results.", Collections.EMPTY_LIST, decodeIntString.decode());
    }

    @Test
    public void decodeStringOfTwoInts() {
        DecodeIntString decodeIntString = new DecodeIntString("12");
        assertEquals("Decoding '12' should return ['ab', 'l'].", Arrays.asList("ab", "l"), decodeIntString.decode());
    }

    @Test
    public void decodeStringOfTwoIntsAndFirstChar0() {
        DecodeIntString decodeIntString = new DecodeIntString("02");
        assertEquals("Decoding '02' should return [].", Collections.EMPTY_LIST, decodeIntString.decode());
    }

    @Test
    public void decodeStringOfInts() {
        DecodeIntString decodeIntString = new DecodeIntString("12345");
        assertEquals("Decoding '12' should return ['abcde', 'awde', 'lcde'].", Arrays.asList("abcde", "awde", "lcde"), decodeIntString.decode());

        DecodeIntString decodeIntString2 = new DecodeIntString("121211");
        assertEquals("Decoding '12' should return ['ababaa', 'ababk', 'abaua', 'ablaa', 'ablk', 'aubaa', 'aubk', 'auua', 'labaa', 'labk', 'laua', 'llaa', 'llk'].",
                Arrays.asList("ababaa", "ababk", "abaua", "ablaa", "ablk", "aubaa", "aubk", "auua", "labaa", "labk", "laua", "llaa", "llk"), decodeIntString2.decode());
    }

    @Test
    public void decodeStringsOfIntsWithO() {
        DecodeIntString decodeIntString1 = new DecodeIntString("12045");
        assertEquals("Decoding '12' should return ['atde'].", Arrays.asList("atde"), decodeIntString1.decode());
    }
}