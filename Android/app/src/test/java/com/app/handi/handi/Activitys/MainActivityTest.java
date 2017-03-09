package com.app.handi.handi.Activitys;

import android.text.TextUtils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.*;

/**
 * Created by richi on 05/03/2017.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(TextUtils.class)

public class MainActivityTest {

    MainActivity mMainActivity;

    @Before
    public void setUp(){
        PowerMockito.mockStatic(TextUtils.class);
        mMainActivity = new MainActivity();
    }

    @Test
    public void onCreate() throws Exception {

    }

    @Test
    public void signOut() throws Exception {

    }

    @Test
    public void onResume() throws Exception {

    }

    @Test
    public void onStart() throws Exception {

    }

    @Test
    public void onStop() throws Exception {

    }

}