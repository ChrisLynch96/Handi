package com.app.handi.handi.Activitys;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.annotation.NonNull;
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
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

import com.app.handi.handi.Activitys.HandiManSignupActivity;
import com.app.handi.handi.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static org.junit.Assert.*;

/**
 * Created by richi on 08/03/2017.
 */
public class HandiManSignupActivityTest {

    @Rule
    public ActivityTestRule<HandiManSignupActivity> mActivtyTestRule = new ActivityTestRule<HandiManSignupActivity>(HandiManSignupActivity.class);
    private HandiManSignupActivity mActivity = null;
    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(HandiHomeActivity.class.getName(),null,false);

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
    public void launchOfHandiHomeActivityOnSignUp(){
        assertNotNull(mActivity.findViewById(R.id.sign_up_handi_button));
        // Test empty form - no email address error message
        onView(withId(R.id.sign_up_handi_button)).perform(click());
        onView(withText("Enter email address!"))
                .inRoot(withDecorView(not(mActivity.getWindow().getDecorView())))
                .check(matches(isDisplayed()));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Test no name error message
        onView(withId(R.id.handi_email))
                .perform(typeText("johnsmith@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.sign_up_handi_button)).perform(click());
        onView(withText("Enter name!"))
                .inRoot(withDecorView(not(mActivity.getWindow().getDecorView())))
                .check(matches(isDisplayed()));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Test no DOB error message
        onView(withId(R.id.handi_name))
                .perform(typeText("John Smith"), closeSoftKeyboard());
        onView(withId(R.id.sign_up_handi_button)).perform(click());
        onView(withText("Enter date of birth!"))
                .inRoot(withDecorView(not(mActivity.getWindow().getDecorView())))
                .check(matches(isDisplayed()));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Test no number error message
        onView(withId(R.id.handi_DOB))
                .perform(typeText("01/02/89"), closeSoftKeyboard());
        onView(withId(R.id.sign_up_handi_button)).perform(click());
        onView(withText("Enter number!"))
                .inRoot(withDecorView(not(mActivity.getWindow().getDecorView())))
                .check(matches(isDisplayed()));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Test no password error message
        onView(withId(R.id.handi_phone_number))
                .perform(typeText("0871234567"), closeSoftKeyboard());
        onView(withId(R.id.sign_up_handi_button)).perform(click());
        onView(withText("Enter password!"))
                .inRoot(withDecorView(not(mActivity.getWindow().getDecorView())))
                .check(matches(isDisplayed()));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Test no confirm password error message
        onView(withId(R.id.handi_password_enter1))
                .perform(typeText("pass"), closeSoftKeyboard());
        onView(withId(R.id.sign_up_handi_button)).perform(click());
        onView(withText("Confirm password"))
                .inRoot(withDecorView(not(mActivity.getWindow().getDecorView())))
                .check(matches(isDisplayed()));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Test no mismatching passwords error message
        onView(withId(R.id.handi_password_enter2))
                .perform(typeText("pass2"), closeSoftKeyboard());
        onView(withId(R.id.sign_up_handi_button)).perform(click());
        onView(withText("Passwords don't match!"))
                .inRoot(withDecorView(not(mActivity.getWindow().getDecorView())))
                .check(matches(isDisplayed()));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Test password error message
        onView(withId(R.id.handi_password_enter2))
                .perform(clearText(), typeText("pass"), closeSoftKeyboard());
        onView(withId(R.id.sign_up_handi_button)).perform(click());
        onView(withText("Password too short, enter minimum 6 characters!"))
                .inRoot(withDecorView(not(mActivity.getWindow().getDecorView())))
                .check(matches(isDisplayed()));

        onView(withId(R.id.handi_password_enter1))
                .perform(clearText(), typeText("password"), closeSoftKeyboard());
        onView(withId(R.id.handi_password_enter2))
                .perform(clearText(), typeText("password"), closeSoftKeyboard());

        Activity HandiHomeActivity;
        try {
            onView(withId(R.id.sign_up_handi_button)).perform(click());
            HandiHomeActivity = getInstrumentation().waitForMonitorWithTimeout(monitor,30000);
            assertNotNull(HandiHomeActivity);
        } finally {
            try {
                Thread.sleep(5000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            removeUserTearDown();
        }
        HandiHomeActivity.finish();
    }

//

    public void removeUserTearDown(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.child("HandiMen").child("Plumber").child(uid).removeValue();user.delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {

                        }
                    }
                });

    }

    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }

}