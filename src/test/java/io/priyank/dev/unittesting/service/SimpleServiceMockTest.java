package io.priyank.dev.unittesting.service;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.Arrays;

import io.priyank.dev.unittesting.dao.SimpleDao;

// Run with annotation is mandatory while using mocking features of Mockito
@RunWith(MockitoJUnitRunner.class)
public class SimpleServiceMockTest {

    // @InjectMocks ensures that instance of mocked SimpleDao will be injected
    // to the instance of SimpleService
    @InjectMocks
    private SimpleService simpleService;

    // @Mock annotation helps use to create a mocked instance of SimpleDao
    @Mock
    private SimpleDao mockedDao;

    @Test
    public void calculateSumWithData_basic() {
        when(mockedDao.getSampleData())
                .thenReturn(Arrays.asList(new Integer[] {1, 2, 3}));
        assertEquals((Integer) 6, simpleService.calculateSumWithData());
    }

    @Test
    public void calculateSumWithData_empty() {
        when(this.mockedDao.getSampleData())
                .thenReturn(Arrays.asList(new Integer[] {}));
        assertEquals((Integer) 0, this.simpleService.calculateSumWithData());
    }

    @Test
    public void calculateSumWithData_oneElement() {
        when(this.mockedDao.getSampleData())
                .thenReturn(Arrays.asList(new Integer[] { 5 }));
        assertEquals((Integer) 5, this.simpleService.calculateSumWithData());
    }
}
