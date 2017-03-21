package com.app.handi.handi.Activitys;

import android.app.Instrumentation;
import android.support.test.rule.ActivityTestRule;

import com.app.handi.handi.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

/**
 * Created by richi on 18/03/2017.
 */
public class HandiHomeActivityTest {

    @Rule
    public ActivityTestRule<HandiHomeActivity> mActivtyTestRule = new ActivityTestRule<HandiHomeActivity>(HandiHomeActivity.class);
    private HandiHomeActivity mActivity = null;
    //Instrumentation.ActivityMonitor monitorChooseHandi = getInstrumentation().addMonitor(JobSelectionActivity.class.getName(),null,false);


    @Before
    public void setUp() throws Exception {
        mActivity = mActivtyTestRule.getActivity();
    }

    @Test
    public void testDrawerDisplayed(){
        assertNotNull(mActivity.findViewById(R.id.drawer_layout));
    }

    @Test
    public void backPressed(){
        //test if drawer correctly closes/opens
    }

    @Test
    public void toastMessages (){
        //test if toast messages are displayed correctly
    }

    @After
    public void tearDown() throws Exception {

    }

}