package com.app.handi.handi.Activitys;

import android.text.TextUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.*;

/**
 * Created by richi on 09/03/2017.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(TextUtils.class)

public class SignUpChoiceActivityTest {

    SignUpChoiceActivity mSignUpChoiceActivity;

    @Before
    public void setUp() throws Exception {
        PowerMockito.mockStatic(TextUtils.class);
        mSignUpChoiceActivity = new SignUpChoiceActivity();

    }

    @After
    public void tearDown() throws Exception {

    }

}