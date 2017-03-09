package com.app.handi.handi.Activitys;

import android.test.ActivityInstrumentationTestCase2;
import android.text.TextUtils;
import android.widget.TextView;
import android.view.View;
import android.widget.Button;
import org.junit.*;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.*;

import com.app.handi.handi.Activitys.HandiManSignupActivity;
import com.app.handi.handi.R;
/**
 * Created by Richard Noonan on 05/03/2017.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(TextUtils.class)

    public class HandiManSignupActivityTest  {

    HandiManSignupActivity mHandimanSignupActivity;

    @Before
    public void setUp()throws Exception{
        PowerMockito.mockStatic(TextUtils.class);
        mHandimanSignupActivity=new HandiManSignupActivity();
    }

    @Test
    public void testEmpty() throws Exception{


    }

    @After
    public void tearDown() throws Exception{
    }
}