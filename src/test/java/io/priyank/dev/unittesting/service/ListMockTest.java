package io.priyank.dev.unittesting.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.Test;

public class ListMockTest {

    // mocking the size method of java.util.List interface
    @Test
    public void mockListSizeTest() {
        List mockedList = mock(List.class);
        when(mockedList.size())
                .thenReturn(5);
        assertEquals(5, mockedList.size());
    }

    // mocking the size method of java.util.List interface
    // return the different values each time called
    // it will return 5 when it will be called first time
    // it will return 10 when it will called for the second time
    @Test
    public void mockListSizeWithMultipleReturns() {
        List mockedList = mock(List.class);
        when(mockedList.size())
                .thenReturn(5)
                .thenReturn(10);
        assertEquals(5, mockedList.size());
        assertEquals(10, mockedList.size());
    }

    // mock the get() method of the java.util.List interface
    // the get method method takes an int parameter
    // here method get() is hard-wried to value 0
    // if we pass any other int value than 0, it will return null
    @Test
    public void mockListGetWithParameter() {
        List mockedList = mock(List.class);
        when(mockedList.get(0))
                .thenReturn(20);
        assertEquals(20, mockedList.get(0));
        assertEquals(null, mockedList.get(1));
    }

    // here we mock the java.util.List interface and
    // we configure the get() method in such way that
    // for any integer passed, it will always return value 200
    // the utility method helping us here is anyInt()
    @Test
    public void mockListGetWithRandomParameter() {
        List mockedList = mock(List.class);
        when(mockedList.get(anyInt()))
                .thenReturn(200);
        assertEquals(200, mockedList.get(0));
        assertEquals(200, mockedList.get(500));
    }
}
