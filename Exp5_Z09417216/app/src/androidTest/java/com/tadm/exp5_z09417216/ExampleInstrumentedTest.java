/*
 * https://liuhongwei3.github.io Inc.
 * Name: ExampleInstrumentedTest.java
 * Date: 20-5-27 下午6:26
 * Author: Tadm
 * Copyright (c) 2020 All Rights Reserved.
 */

package com.tadm.exp5_z09417216;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("com.tadm.exp5_z09417216", appContext.getPackageName());
    }
}
