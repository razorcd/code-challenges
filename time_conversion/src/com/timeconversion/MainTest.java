package com.timeconversion;

import static org.junit.Assert.*;

public class MainTest {

    @org.junit.Test
    public void timeConversion() {

        assertEquals("11:11:11", Main.timeConversion("11:11:11AM"));
        assertEquals("23:11:11", Main.timeConversion("11:11:11PM"));
        assertEquals("00:11:11", Main.timeConversion("12:11:11AM"));
        assertEquals("12:11:11", Main.timeConversion("12:11:11PM"));

    }
}