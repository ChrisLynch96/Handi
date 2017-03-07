package com.app.handi.handi.Activitys;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;
import android.view.View;
import android.widget.Button;
import org.junit.*;
import static org.junit.Assert.*;

import com.app.handi.handi.Activitys.HandiManSignupActivity;
import com.app.handi.handi.R;
/**
 * Created by Richard Noonan on 05/03/2017.
 */

    public class HandiManSignupActivityTest extends ActivityInstrumentationTestCase2<HandiManSignupActivity> {


    private Button signup;
    private View mainLayout;

    public HandiManSignupActivityTest() {
        super("com.app.handi.handi", HandiManSignupActivity.class);

    }

    @Before
    public void setUp()throws Exception{
        super.setUp();
        HandiManSignupActivity activity = getActivity();
        signup = (Button) activity.findViewById(R.id.sign_up_handi_button);
        mainLayout = (View) activity.findViewById(R.id.match_parent);
    }


    @Test
    public void SignupButtonDiplayTest(){
        int fullWidth =
    }

    @Test
    public void testedittext(){
    }

    @After
    public void tearDown() throws Exception{
    }
}