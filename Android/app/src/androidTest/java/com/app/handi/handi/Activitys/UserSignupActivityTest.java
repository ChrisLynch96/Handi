package com.app.handi.handi.Activitys;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.annotation.NonNull;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import com.app.handi.handi.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
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
public class UserSignupActivityTest {
    @Rule
    public ActivityTestRule<UserSignupActivity> mActivtyTestRule = new ActivityTestRule<UserSignupActivity>(UserSignupActivity.class);
    private UserSignupActivity mActivity = null;
    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(UserHomeActivity.class.getName(),null,false);

    @Before
    public void setUp() throws Exception {
        mActivity = mActivtyTestRule.getActivity();
    }

    @Test
    public void testLaunch(){
        View view = mActivity.findViewById(R.id.progressBar);
        assertNotNull(view);
    }

    @Test
    public void launchOfUserHomeActivityOnSignUp(){
        assertNotNull(mActivity.findViewById(R.id.sign_up_button));
        // Test empty form - no email address error message
        onView(withId(R.id.sign_up_button)).perform(click());
        onView(withText("Enter email address!"))
                .inRoot(withDecorView(not(mActivity.getWindow().getDecorView())))
                .check(matches(isDisplayed()));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Test no password error message
        onView(withId(R.id.email))
                .perform(typeText("johnsmith@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.sign_up_button)).perform(click());
        onView(withText("Enter password!"))
                .inRoot(withDecorView(not(mActivity.getWindow().getDecorView())))
                .check(matches(isDisplayed()));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Test no first name message
        onView(withId(R.id.password))
                .perform(typeText("pass"), closeSoftKeyboard());
        onView(withId(R.id.sign_up_button)).perform(click());
        onView(withText("Enter first name!"))
                .inRoot(withDecorView(not(mActivity.getWindow().getDecorView())))
                .check(matches(isDisplayed()));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Test no first name message
        onView(withId(R.id.firstName))
                .perform(typeText("John"), closeSoftKeyboard());
        onView(withId(R.id.sign_up_button)).perform(click());
        onView(withText("Enter last name!"))
                .inRoot(withDecorView(not(mActivity.getWindow().getDecorView())))
                .check(matches(isDisplayed()));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Test password too short message
        onView(withId(R.id.lastName))
                .perform(typeText("Smith"), closeSoftKeyboard());
        onView(withId(R.id.sign_up_button)).perform(click());
        onView(withText("Password too short, enter minimum 6 characters!"))
                .inRoot(withDecorView(not(mActivity.getWindow().getDecorView())))
                .check(matches(isDisplayed()));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Test no number error message
        onView(withId(R.id.password))
                .perform(clearText(), typeText("password"), closeSoftKeyboard());

        Activity UserHomeActivity;
        try {
            onView(withId(R.id.sign_up_button)).perform(click());
            UserHomeActivity = getInstrumentation().waitForMonitorWithTimeout(monitor,10000);
            assertNotNull(UserHomeActivity);
        } finally {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            removeUserTearDown();
        }
        UserHomeActivity.finish();
    }

    public void removeUserTearDown(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        user.delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            System.out.println("User deleted");
                        }
                    }
                });
    }

    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }

}