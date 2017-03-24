package com.app.handi.handi.Activitys;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.rule.ActivityTestRule;

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
 * Created by richi on 17/03/2017.
 */
public class ChooseHandiTypeActivityTest {

    @Rule
    public ActivityTestRule<ChooseHandiTypeActivity> mActivtyTestRule = new ActivityTestRule<ChooseHandiTypeActivity>(ChooseHandiTypeActivity.class);
    private ChooseHandiTypeActivity mActivity = null;
    Instrumentation.ActivityMonitor monitorChooseHandi = getInstrumentation().addMonitor(JobSelectionActivity.class.getName(),null,false);

    @Before
    public void setUp() throws Exception {
        mActivity = mActivtyTestRule.getActivity();
    }

    //TODO launch to specific screens in the class, only launches generic screen
    @Test
    public void testCleanerLaunch (){
        //tests cleaner image is present and that it launches the correct activity
        assertNotNull(mActivity.findViewById(R.id.activity_job_selection_LinearLayout_cleaner));
        onView(withId(R.id.activity_job_selection_LinearLayout_cleaner)).perform(click());
        Activity cleanerLaunch;
        cleanerLaunch = getInstrumentation().waitForMonitorWithTimeout(monitorChooseHandi, 10000);
        assertNotNull(cleanerLaunch);
        cleanerLaunch.finish();
    }

    @Test
    public void testElectricianLaunch(){
        assertNotNull(mActivity.findViewById(R.id.activity_job_selection_LinearLayout_electrician));
        onView(withId(R.id.activity_job_selection_LinearLayout_electrician)).perform(click());
        Activity electrianLaunch;
        electrianLaunch = getInstrumentation().waitForMonitorWithTimeout(monitorChooseHandi, 10000);
        assertNotNull(electrianLaunch);
        electrianLaunch.finish();
    }

    @Test
    public void testPlumberLaunch (){
        assertNotNull(mActivity.findViewById(R.id.activity_job_selection_LinearLayout_plumber));
        onView(withId(R.id.activity_job_selection_LinearLayout_plumber)).perform(click());
        Activity plumberLaunch;
        plumberLaunch = getInstrumentation().waitForMonitorWithTimeout(monitorChooseHandi, 10000);
        assertNotNull(plumberLaunch);
        plumberLaunch.finish();
    }

    @Test
    public void testPainterLaunch(){
        assertNotNull(mActivity.findViewById(R.id.activity_job_selection_LinearLayout_painter));
        onView(withId(R.id.activity_job_selection_LinearLayout_painter)).perform(click());
        Activity painterLaunch;
        painterLaunch = getInstrumentation().waitForMonitorWithTimeout(monitorChooseHandi, 10000);
        assertNotNull(painterLaunch);
        painterLaunch.finish();
    }

    @Test
    public void testHandiLaunch(){
        assertNotNull(mActivity.findViewById(R.id.activity_job_selection_image_view_handiman));
        onView(withId(R.id.activity_job_selection_image_view_handiman)).perform(click());
        Activity handiLaunch;
        handiLaunch = getInstrumentation().waitForMonitorWithTimeout(monitorChooseHandi, 10000);
        assertNotNull(handiLaunch);
        handiLaunch.finish();
    }

    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }

}