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
public class LoginOrSignupActivityTest {

    @Rule
    public ActivityTestRule<LoginOrSignupActivity> mActivtyTestRule = new ActivityTestRule<LoginOrSignupActivity>(LoginOrSignupActivity.class);
    private LoginOrSignupActivity mActivity = null;
    Instrumentation.ActivityMonitor LogInMonitor = getInstrumentation().addMonitor(LoginActivity.class.getName(),null,false);
    Instrumentation.ActivityMonitor SignUpMonitor = getInstrumentation().addMonitor(SignUpChoiceActivity.class.getName(),null,false);
    @Before
    public void setUp() throws Exception {

        mActivity = mActivtyTestRule.getActivity();
    }

    @Test
    public void testLaunch(){
        View view = mActivity.findViewById(R.id.buttons_container);
        assertNotNull(view);
    }

    @Test
    public void testLaunchOfLoginActivity(){
        assertNotNull(mActivity.findViewById(R.id.login_button));
        onView(withId(R.id.login_button)).perform(click());
        Activity LoginActivity = getInstrumentation().waitForMonitorWithTimeout(LogInMonitor,5000);
        assertNotNull(LoginActivity);
        LoginActivity.finish();
    }

    @Test
    public void testLaunchOfSignUpActivity(){
        assertNotNull(mActivity.findViewById(R.id.signup_button));
        onView(withId(R.id.signup_button)).perform(click());
        Activity SignUpActivity = getInstrumentation().waitForMonitorWithTimeout(SignUpMonitor,5000);
        assertNotNull(SignUpActivity);
        SignUpActivity.finish();
    }

    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }

}