package com.rubengees.introduction.entity;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;

import com.rubengees.introduction.IntroductionActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

/**
 * Todo: Describe Class
 *
 * @author Ruben Gees
 */
@RunWith(AndroidJUnit4.class)
public class SlideTest extends ActivityInstrumentationTestCase2<IntroductionActivity> {

    public SlideTest() {
        super(IntroductionActivity.class);
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());


    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }
}