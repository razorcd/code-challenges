package com.fibonacci;

import org.junit.Test;

import static org.junit.Assert.*;

public class MainTest {

    @Test
    public void fib() {
        assertEquals(0, Main.fib(0));
        assertEquals(1, Main.fib(1));
        assertEquals(2, Main.fib(2));
        assertEquals(3, Main.fib(3));
        assertEquals(5, Main.fib(4));
        assertEquals(8, Main.fib(5));
        assertEquals(3524578, Main.fib(32));
        assertEquals(5702887, Main.fib(33));
    }
}