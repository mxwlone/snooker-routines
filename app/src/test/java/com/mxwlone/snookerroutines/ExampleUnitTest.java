package com.mxwlone.snookerroutines;

import org.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void JSONTest() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("1", 10);
        jsonObject.put("1", 20);
        jsonObject.put("3", 20);

        String results = jsonObject.toString();
        System.out.println(results);

        JSONObject jsonObject1 = new JSONObject(results);
        jsonObject1.put("3", 30);
        System.out.println(jsonObject1.toString());
    }
}