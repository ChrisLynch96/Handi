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
public class SignUpChoiceActivityTest {
    @Rule
    public ActivityTestRule<SignUpChoiceActivity> mActivtyTestRule = new ActivityTestRule<SignUpChoiceActivity>(SignUpChoiceActivity.class);
    private SignUpChoiceActivity mActivity = null;
    Instrumentation.ActivityMonitor userMonitor = getInstrumentation().addMonitor(UserSignupActivity.class.getName(),null,false);
    Instrumentation.ActivityMonitor handiMonitor = getInstrumentation().addMonitor(HandiManSignupActivity.class.getName(),null,false);

    @Before
    public void setUp() throws Exception {
        mActivity = mActivtyTestRule.getActivity();
    }
    //Ensures that activity is launched correctly
    @Test
    public void testLaunch(){
        View view = mActivity.findViewById(R.id.activity_sign_up_choice_button_user);
        assertNotNull(view);
    }

    //Tests that on user button click, UserSignupActivity is launched. Times out after 5 seconds.
    @Test
    public void testLaunchOfUserSignupActivity(){
        assertNotNull(mActivity.findViewById(R.id.activity_sign_up_choice_button_user));
        onView(withId(R.id.activity_sign_up_choice_button_user)).perform(click());
        Activity UserSignupActivity = getInstrumentation().waitForMonitorWithTimeout(userMonitor,5000);
        assertNotNull(UserSignupActivity);
        UserSignupActivity.finish();
    }

    //Tests that on handiman button click, HandiManSignupActivity is launched. Times out after 5 seconds.
    @Test
    public void HandiManSignupActivity(){
        assertNotNull(mActivity.findViewById(R.id.activity_sign_up_choice_button_handiman));
        onView(withId(R.id.activity_sign_up_choice_button_handiman)).perform(click());
        Activity HandiManSignupActivity = getInstrumentation().waitForMonitorWithTimeout(handiMonitor,5000);
        assertNotNull(HandiManSignupActivity);
        HandiManSignupActivity.finish();
    }

    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }
}