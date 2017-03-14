package com.app.handi.handi.Activitys;

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
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

/**
 * Created by richi on 08/03/2017.
 */
public class ResetPasswordActivityTest {
    @Rule
    public ActivityTestRule<ResetPasswordActivity> mActivtyTestRule = new ActivityTestRule<ResetPasswordActivity>(ResetPasswordActivity.class);
    private ResetPasswordActivity mActivity = null;

    @Before
    public void setUp() throws Exception {
        mActivity = mActivtyTestRule.getActivity();
    }

    @Test
    public void testLaunch(){
        View view = mActivity.findViewById(R.id.email);
        assertNotNull(view);
    }

    @Test
    public void testResetPassword(){
        assertNotNull(mActivity.findViewById(R.id.btn_reset_password));

        //On click with no email
        onView(withId(R.id.btn_reset_password)).perform(click());
        onView(withText("Enter your registered email id"))
                .inRoot(withDecorView(not(mActivity.getWindow().getDecorView())))
                .check(matches(isDisplayed()));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //On click with correct email
        onView(withId(R.id.email)).perform(typeText("bobthebuilder@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.btn_reset_password)).perform(click());
        onView(withText("We have sent you instructions to reset your password!"))
                .inRoot(withDecorView(not(mActivity.getWindow().getDecorView())))
                .check(matches(isDisplayed()));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //On Click with incorrect email
        onView(withId(R.id.email)).perform(typeText("richieliveie"), closeSoftKeyboard());
        onView(withId(R.id.btn_reset_password)).perform(click());
        onView(withText("Failed to send reset email!"))
                .inRoot(withDecorView(not(mActivity.getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }
    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }
}