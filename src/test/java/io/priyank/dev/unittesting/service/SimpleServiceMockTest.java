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

@RunWith(MockitoJUnitRunner.class)
public class SimpleServiceMockTest {

    @InjectMocks
    private SimpleService simpleService;

    @Mock
    private SimpleDao mockedDao;

    @Before
    public void before() {
        this.simpleService.setSimpleDao(this.mockedDao);
    }

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
