package io.priyank.dev.unittesting.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SimpleServiceTest {

    @Test
    public void calculateSumTest_basic() {
        SimpleService simpleService = new SimpleService();
        Integer actualResult = simpleService.calculateSum(new Integer[] {1, 2, 3});
        Integer expectedResult = 6;
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void calculateSumTest_empty() {
        SimpleService simpleService = new SimpleService();
        Integer actualResult = simpleService.calculateSum(new Integer[] {});
        Integer expectedResult = 0;
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void calculateSumTest_oneElement() {
        SimpleService simpleService = new SimpleService();
        Integer actualResult = simpleService.calculateSum(new Integer[] { 5 });
        Integer expectedResult = 5;
        assertEquals(expectedResult, actualResult);

    }
}
