package com.app.handi.handi.Activitys;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import com.app.handi.handi.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

/**
 * Created by richi on 08/03/2017.
 */
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivtyTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);
        private MainActivity mActivity = null;
        Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(UserSignupActivity.class.getName(),null,false);

    @Before
    public void setUp() throws Exception {
        mActivity = mActivtyTestRule.getActivity();

    }

    @Test
    public void testLaunch(){
        View view = mActivity.findViewById(R.id.old_email);
        assertNotNull(view);
    }

    @Test
    public void UserSignupActivityLaunchOnRemoveUserTest (){
        assertNotNull(mActivity.findViewById(R.id.remove_user_button));
        onView(withId(R.id.remove_user_button)).perform(click());
        Activity UserSignupActivity = getInstrumentation().waitForMonitorWithTimeout(monitor,5000);
        assertNotNull(UserSignupActivity);
        UserSignupActivity.finish();
    }

    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }

}