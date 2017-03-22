package com.app.handi.handi.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.app.handi.handi.R;

public class HandiProfileView extends AppCompatActivity {
    TextView title, Name, Number, Email;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handi_profile_view);
        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("HandiName");
        String number = bundle.getString("HandiPhone");
        String email = bundle.getString("HandiEmail");
        title = (TextView) findViewById(R.id.handi_view_profile_header);
        Name = (TextView) findViewById(R.id.handi_view_profile_name);
        Number = (TextView) findViewById(R.id.handi_view_profile_number);
        Email = (TextView) findViewById(R.id.handi_view_profile_email);
        String set1 = name + "'s Profile";
        title.setText(set1);
        Name.setText(name);
        Number.setText(number);
        Email.setText(email);
        button = (Button) findViewById(R.id.handi_view_profile_button_offer_job);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HandiProfileView.this,JobDescriptionActivity.class));
            }
        });
    }
}
