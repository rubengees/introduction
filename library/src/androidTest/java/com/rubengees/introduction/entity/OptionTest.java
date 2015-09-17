package com.rubengees.introduction.entity;

import android.os.Parcel;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;

import com.rubengees.introduction.IntroductionActivity;
import com.rubengees.introduction.R;
import com.rubengees.introduction.exception.ConfigurationException;

import org.junit.After;
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
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());

        this.option = new Option("Title", true);
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void testGetTitle() throws Exception {
        assertEquals("Title", option.getTitle());
    }

    @Test(expected = ConfigurationException.class)
    public void testGetTitleResourceBeforeInit() throws Exception {
        Option option = new Option(R.string.library_introduction_author, true);

        option.getTitle();
    }

    @Test
    public void testIsActivated() throws Exception {
        assertTrue(option.isActivated());
    }

    @Test
    public void testSetActivated() throws Exception {
        option.setActivated(false);
        assertFalse(option.isActivated());
    }

    @Test(expected = ConfigurationException.class)
    public void testGetPositionBeforeInit() throws Exception {
        option.getPosition();
    }

    @Test
    public void testParcelable() throws Exception {
        Parcel parcel = Parcel.obtain();

        option.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);

        Option createdFromParcel = Option.CREATOR.createFromParcel(parcel);
        assertEquals(option, createdFromParcel);
    }

    @Test
    public void testInit() throws Exception {
        option = new Option(R.string.library_introduction_author, true);

        option.init(getActivity(), 3);

        assertEquals("Ruben Gees", option.getTitle());
        assertEquals(3, option.getPosition());
    }
}