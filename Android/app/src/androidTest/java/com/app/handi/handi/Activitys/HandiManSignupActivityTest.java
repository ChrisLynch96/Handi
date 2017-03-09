package com.app.handi.handi.Activitys;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;
import android.view.View;
import android.widget.Button;
import org.junit.*;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

import com.app.handi.handi.Activitys.HandiManSignupActivity;
import com.app.handi.handi.R;

import static org.junit.Assert.*;

/**
 * Created by richi on 08/03/2017.
 */
public class HandiManSignupActivityTest {

    @Rule
    public ActivityTestRule<HandiManSignupActivity> mActivtyTestRule = new ActivityTestRule<HandiManSignupActivity>(HandiManSignupActivity.class);
        private HandiManSignupActivity mActivity = null;
        Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(MainActivity.class.getName(),null,false);

    @Before
    public void setUp() throws Exception {
        mActivity = mActivtyTestRule.getActivity();
    }

    @Test
    public void testLaunch(){
        View view = mActivity.findViewById(R.id.enter_credentials);
        assertNotNull(view);
    }

    @Test
    public void launchOfMainActivityOnSignUp(){
        assertNotNull(mActivity.findViewById(R.id.sign_up_handi_button));
        onView(withId(R.id.sign_up_handi_button)).perform(click());
        Activity MainActivity = getInstrumentation().waitForMonitorWithTimeout(monitor,5000);
        assertNotNull(MainActivity);
        MainActivity.finish();
    }

    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }

}