package io.priyank.dev.unittesting.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.spy;

import java.util.List;
import java.util.ArrayList;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

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

    // This test method will help us to verify
    // that a certain method was called on Mocked class/interface
    // here we will call get() method on java.util.List interface
    // and we will verify it with various scenarios
    @Test
    public void mockListVerifyMethod() {
        List mockedList = mock(List.class);
        when(mockedList.get(anyInt()))
                .thenReturn(20);

        assertEquals(20, mockedList.get(0));
        assertEquals(20, mockedList.get(20));

        // 1. verifies method get() was called with parameter as 0
        verify(mockedList).get(0);

        // 2. verifies method get() was called with parameter as 0
        // at least once
        verify(mockedList, atLeastOnce()).get(0);

        // 3. verifies method get() was called with any int parameters
        // exactly twice
        verify(mockedList, times(2)).get(anyInt());

        // 4. verifies method get() was never called with parameter as 30
        verify(mockedList, never()).get(30);

        // 5. verifies method get() was on list for maximum of two times
        verify(mockedList, atMost(2)).get(anyInt());
    }

    // Well, if a mocked method is being called in our test
    // and if we decided to verify what set of parameters are being passed
    // we need ArgumentCaptor, the role of ArgumentCaptor is
    // to capture the parameter being passed when certain method is called
    // in the following scenario, we can see when the add() method is being called
    // on mockedList, we are verifying that int value 40 is being passed as a parameter
    @Test
    public void argumentCapturing() {
        List<Integer> mockedList = mock(List.class);
        mockedList.add(40);
        ArgumentCaptor<Integer> captor = ArgumentCaptor.forClass(Integer.class);
        // captor.capture() here captures the parameter being passed when add()
        // method is called
        verify(mockedList).add(captor.capture());
        assertEquals((Integer) 40, captor.getValue());
    }

    // Here, we can see the add() method on mockedList is called multiple times
    // with different parameters, now to ensure that with what parameters it was
    // called each time?
    @Test
    public void multipleArgumentCapturing() {
        List<Integer> mockedList = mock(List.class);
        // 1. calling the add() method thrice with parameter
        mockedList.add(10);
        mockedList.add(20);
        mockedList.add(30);

        // 2. creating a captor
        ArgumentCaptor<Integer> captor = ArgumentCaptor.forClass(Integer.class);

        // 3. verifying parameter with the help of captor, also we are using times()
        // to verify that method() add was called exactly thrice
        verify(mockedList, times(3)).add(captor.capture());

        // 4. getAllValues() method on captor ensure, parameters are captured for each call
        // to the add() method on mockedList
        List<Integer> arguments = captor.getAllValues();

        // 5. verify the parameter size, it must be 3
        assertEquals(3, arguments.size());

        // 6. verify the first argument is 10
        assertEquals((Integer) 10, arguments.get(0));
    }

    // Mocking and Spying are two different phenomenons
    // When we mock a class/interface it loses its actual behaviour
    // if we spy on a class/interface it will retain its behaviour
    // until we override, below test case demonstrate this capability

    @Test
    public void spyingArrayList() {
        // 1. mocked ArrayList
        ArrayList<String> mockedList = mock(ArrayList.class);
        // 2. though there is nothing inside list, we can still invoke get() method
        // which returns null
        System.out.println(mockedList.get(0));
        // 3. size() method returns 0 as expected because there are not elements in the ArrayList
        System.out.println(mockedList.size());
        // 4. add a element to the list
        mockedList.add("first");
        // 5. strangely get() method on the 0th element still returns null (lost behaviour)
        System.out.println(mockedList.get(0));
        // 6. size() method also returns size as 0, until and unless override it to return
        // specific (lost behaviour)
        System.out.println(mockedList.size());

        // spying
        // spiedList, an instance of ArrayList with with help of spy()
        ArrayList<String> spiedList = spy(ArrayList.class);
        // 1. as spy retains the original behaviour, we cannot invoke get() method directly
        // it will throw index out bound exception
        // so let us add an element first
        spiedList.add("first");
        // 2. get() method with parameter 0 here returns string value 'first'
        System.out.println(spiedList.get(0));
        // 3. size() method here returns the size of the list
        System.out.println(spiedList.size());
        // 4. we can also verify similar to mocks
        verify(spiedList).add("first");
        // 5. we can also override the actual behaviour, we can see though there is only one element
        // part of the spiedList, we can still override the behaviour of size() method and force
        // it to return 5 when called
        when(spiedList.size())
                .thenReturn(5);
        System.out.println(spiedList.size());
    }
}
