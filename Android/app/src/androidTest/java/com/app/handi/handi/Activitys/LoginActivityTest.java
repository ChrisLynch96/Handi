package com.app.handi.handi.Activitys;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import com.app.handi.handi.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by richi on 08/03/2017.
 */
public class LoginActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivtyTestRule = new ActivityTestRule<LoginActivity>(LoginActivity.class);
    private LoginActivity mActivity = null;

    @Before
    public void setUp() throws Exception {
        mActivity = mActivtyTestRule.getActivity();
    }

    @Test
    public void testLaunch(){
        View view = mActivity.findViewById(R.id.progressBar);
        assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }

}