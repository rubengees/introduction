package com.rubengees.introduction.entity;

import android.os.Parcel;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;

import com.rubengees.introduction.IntroductionActivity;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Todo: Describe Class
 *
 * @author Ruben Gees
 */
@RunWith(AndroidJUnit4.class)
public class OptionTest extends ActivityInstrumentationTestCase2<IntroductionActivity> {

    private Option option;

    public OptionTest() {
        super(IntroductionActivity.class);
    }

    @Before
    public void setUp() throws Exception {
        this.option = new Option("Title", true);
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void testGetTitle() throws Exception {

    }

    @Test
    public void testIsActivated() throws Exception {

    }

    @Test
    public void testSetActivated() throws Exception {

    }

    @Test
    public void testGetPosition() throws Exception {

    }

    @Test
    public void testInit() throws Exception {

    }

    @Test
    public void testParcelable() throws Exception {
        Parcel parcel = Parcel.obtain();

        option.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);

        Option createdFromParcel = Option.CREATOR.createFromParcel(parcel);
        Assert.assertEquals(option, createdFromParcel);
    }
}