package com.app.handi.handi.Activitys;

import android.text.TextUtils;

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

public class LoginOrSignupActivityTest {

    LoginOrSignupActivity mLoginOrSignupActivity;

    @Test
    public void setUp(){
        PowerMockito.mockStatic(TextUtils.class);
        mLoginOrSignupActivity = new LoginOrSignupActivity();
    }

    @Test
    public void onCreate() throws Exception {

    }

    @Test
    public void onClick() throws Exception {

    }

}