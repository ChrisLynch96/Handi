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

public class SignupActivityTest {

    SignUpChoiceActivity mSignUpChoiceActivity;

    @Before
    public void setUp(){
        PowerMockito.mockStatic(TextUtils.class);
        mSignUpChoiceActivity = new SignUpChoiceActivity();
    }

    @Test
    public void onCreate() throws Exception {

    }

    @Test
    public void onResume() throws Exception {

    }

}