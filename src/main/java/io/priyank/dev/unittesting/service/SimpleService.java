package io.priyank.dev.unittesting.service;

import java.util.Arrays;
import java.util.List;

import io.priyank.dev.unittesting.dao.SimpleDao;

public class SimpleService {

    private SimpleDao simpleDao;

    public void setSimpleDao(SimpleDao simpleDao) {
        this.simpleDao = simpleDao;
    }

    public Integer calculateSum(Integer[] inputArray) {
        Integer result = Arrays
                            .stream(inputArray)
                            .reduce(0, (total, number) -> total = total + number);
        return result;
    }

    public Integer calculateSumWithData() {
        List<Integer> data = this.simpleDao.getSampleData();
        Integer result = data
                            .stream()
                            .reduce(0, (total, number) -> total = total + number);
        return result;
    }
}
