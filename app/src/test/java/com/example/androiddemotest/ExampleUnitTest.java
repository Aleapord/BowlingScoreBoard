package com.example.androiddemotest;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws IOException {

        assertEquals(4, 2 + 2);
        List<User> users=SqlRequest.getGroup(4);
        System.out.println(users);
    }
}