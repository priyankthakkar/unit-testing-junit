package io.priyank.dev.unittesting.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import java.util.Arrays;
import java.util.List;

import io.priyank.dev.unittesting.dao.SimpleDao;

class BasicDao implements SimpleDao {

    public List<Integer> getSampleData() {
        return Arrays.asList(new Integer[] {1, 2, 3});
    }
}

class EmptyDao implements SimpleDao {

    public List<Integer> getSampleData() {
        return Arrays.asList(new Integer[] {});
    }
}

class OneElementDao implements SimpleDao {

    public List<Integer> getSampleData() {
        return Arrays.asList(new Integer[] { 5 });
    }
}

public class SimpleServiceStubTest {

    @Test
    public void calculateSumWithData_basic() {
        SimpleService simpleService = new SimpleService();
        BasicDao basicDao = new BasicDao();
        simpleService.setSimpleDao(basicDao);
        Integer actualResult = simpleService.calculateSumWithData();
        Integer expectedResult = 6;
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void calculateSumWithData_empty() {
        SimpleService simpleService = new SimpleService();
        EmptyDao emptyDao = new EmptyDao();
        simpleService.setSimpleDao(emptyDao);
        Integer actualResult = simpleService.calculateSumWithData();
        Integer expectedResult = 0;
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void calculateSumWithData_oneElement() {
        SimpleService simpleService = new SimpleService();
        OneElementDao oneElementDao = new OneElementDao();
        simpleService.setSimpleDao(oneElementDao);
        Integer actualResult = simpleService.calculateSumWithData();
        Integer expectedResult = 5;
        assertEquals(expectedResult, actualResult);
    }
}
