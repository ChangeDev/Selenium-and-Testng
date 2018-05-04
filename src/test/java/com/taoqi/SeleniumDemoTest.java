package com.taoqi;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by ChangFeng on 2017/11/6.
 */
@Test
public class SeleniumDemoTest {

    //Test
    public void test2() {
        throw new RuntimeException();
    }

    //@Test(dependsOnMethods = {"test2"})
    public void test1() {
        System.out.println("test1");
    }

    //Test(dataProvider = "provideNumbers")
    public void assertValue(int number, int expected) {
        assertEquals(number, expected);
    }

    //@DataProvider(name = "provideNumbers")
    public Object[][] dataValues() {
        return new Object[][]{{1, 2}, {2, 2}};
    }

    int count = 0;

    @Test(invocationCount = 10, threadPoolSize = 5)
    public void multiThreadTest() throws Exception {
        System.out.printf("Thread Id : %s%n", Thread.currentThread().getId());
    }
}