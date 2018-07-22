package com.other.challenges;

import org.junit.Test;

import static org.junit.Assert.*;

public class LetterRecurenceTest {

    @Test
    public void whenNoLetterRecuredShouldReturnNull() {
        LetterRecurence letterRecurence = new LetterRecurence("abcdefgh");

        assertNull("Should return null for string 'abcdefgh'.", letterRecurence.getFirstRecurence());
    }

    @Test
    public void whenOneLetterRecuredShouldReturnThatLatter() {
        LetterRecurence letterRecurence1 = new LetterRecurence("abcdafg");
        assertEquals("Should return 'a' for string 'abcdafg'.", Character.valueOf('a'), letterRecurence1.getFirstRecurence());

        LetterRecurence letterRecurence2 = new LetterRecurence("abcdbfg");
        assertEquals("Should return 'a' for string 'abcdbfg'.", Character.valueOf('b'), letterRecurence2.getFirstRecurence());

        LetterRecurence letterRecurence3 = new LetterRecurence("abcdff");
        assertEquals("Should return 'a' for string 'abcdff'.", Character.valueOf('f'), letterRecurence3.getFirstRecurence());
    }

    @Test

    public void whenMultipleLettersRecurredShouldReturnFirstRecurredLetter() {
        LetterRecurence letterRecurence1 = new LetterRecurence("abcbdefcghig");
        assertEquals("Should return 'a' for string 'abcbdefcghig'.", Character.valueOf('b'), letterRecurence1.getFirstRecurence());

        LetterRecurence letterRecurence2 = new LetterRecurence("abcdbebgbghicj");
        assertEquals("Should return 'a' for string 'abcdbebgbghicj'.", Character.valueOf('b'), letterRecurence1.getFirstRecurence());
    }
}