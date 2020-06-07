/*
 * https://liuhongwei3.github.io Inc.
 * Name: ExampleUnitTest.java
 * Date: 20-6-7 上午8:38
 * Author: Tadm
 * Copyright (c) 2020 All Rights Reserved.
 */

package com.tadm.dailyapplication;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        System.out.println(new SimpleDateFormat("HH:mm:ss").format(new Date()));
        assertEquals(4, 2 + 2);
    }
}