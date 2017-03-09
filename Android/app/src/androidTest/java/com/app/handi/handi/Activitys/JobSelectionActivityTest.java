package com.app.handi.handi.Activitys;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import android.support.test.rule.ActivityTestRule;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;
import android.view.View;
import android.widget.Button;

import com.app.handi.handi.R;

import org.junit.*;
import static org.junit.Assert.*;

import static org.junit.Assert.*;

/**
 * Created by richi on 08/03/2017.
 */
public class JobSelectionActivityTest {

    @Rule
    public ActivityTestRule<JobSelectionActivity> mActivtyTestRule = new ActivityTestRule<JobSelectionActivity>(JobSelectionActivity.class);
    private JobSelectionActivity mActivity = null;

    @Before
    public void setUp() throws Exception {
        mActivity = mActivtyTestRule.getActivity();
    }

    @Test
    public void testLaunch(){
        View view = mActivity.findViewById(R.id.activity_job_selection_LinearLayout_job_icons_container);
        assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }

}