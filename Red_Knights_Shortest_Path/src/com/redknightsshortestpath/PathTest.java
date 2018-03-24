package com.redknightsshortestpath;

import org.junit.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

public class PathTest {

    @Test
    public void testGetShortestPath() {
        assertArrayEquals(new String[]{"UL","UL","UR"}, Path.getShortestPath(7,6,6,0,5));
        assertArrayEquals(new String[]{"UL","UL","UL","L"}, Path.getShortestPath(7,6,6,0,1));
        assertArrayEquals(new String[]{}, Path.getShortestPath(6,5, 1, 0, 5));
        assertArrayEquals(new String[]{"LR", "LL"}, Path.getShortestPath(7, 0, 3, 4, 3));
    }

    @Test
    public void testGetShortestPathPerformance() {

        LocalDateTime start = LocalDateTime.now();
        assertArrayEquals(new String[]{"LR", "LL"}, Path.getShortestPath(10, 0, 3, 4, 3));
        LocalDateTime stop = LocalDateTime.now();

        Duration duration = Duration.between(start, stop);
        System.out.println(duration);
        assertTrue(duration.toMillis() < 5000L);
    }
}