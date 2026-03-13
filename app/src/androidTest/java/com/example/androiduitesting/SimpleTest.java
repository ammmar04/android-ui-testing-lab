package com.example.androiduitesting;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class SimpleTest {
    @Test
    public void simpleTest() {
        assertEquals(2, 1 + 1);
    }
}
