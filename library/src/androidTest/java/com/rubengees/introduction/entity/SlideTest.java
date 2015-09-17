package com.rubengees.introduction.entity;

import android.os.Parcel;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;

import com.rubengees.introduction.IntroductionActivity;
import com.rubengees.introduction.R;

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
public class SlideTest extends ActivityInstrumentationTestCase2<IntroductionActivity> {

    private Slide slide;

    public SlideTest() {
        super(IntroductionActivity.class);
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());

        slide = new Slide().withTitle("Title").withDescription("Description");
    }

    @Test
    public void testParcelable() throws Exception {
        slide.withColor(342454).withImage(R.drawable.introduction_ic_arrow_next);
        Parcel parcel = Parcel.obtain();

        slide.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);

        Slide createdFromParcel = Slide.CREATOR.createFromParcel(parcel);
        assertEquals(slide, createdFromParcel);
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }
}