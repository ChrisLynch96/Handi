package com.app.handi.handi.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.app.handi.handi.Activitys.HandiEditProfileActivity;
import com.app.handi.handi.DataTypes.HandimanData;
import com.app.handi.handi.R;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 */
public class HandiViewProfileFragment extends Fragment implements View.OnClickListener {

    View view;
    Button editProfileButton;
    TextView Name,dateOfBirth,Email,Number;
    private static final String ARG_PARAM1 = "param1";
    private static HandimanData Handi;

    public HandiViewProfileFragment() {
        // Required empty public constructor
    }
    public static HandiViewProfileFragment newInstance(String param1, HandimanData handimanData){
        HandiViewProfileFragment fragment = new HandiViewProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1,param1);
        Handi = handimanData;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_handi_view_profile, container, false);
        Name = (TextView) view.findViewById(R.id.fragment_handi_view_profile_first_name);
        final String name = "Name: " + Handi.getName();
        dateOfBirth = (TextView) view.findViewById(R.id.fragment_handi_view_profile_text_view_DOB);
        final String dob = "Date of Birth: " + Handi.getdateOfbirth();
        Email = (TextView) view.findViewById(R.id.fragment_handi_view_profile_text_view_email);
        final String email = "Email: " + Handi.getEmail();
        Number = (TextView) view.findViewById(R.id.fragment_handi_view_profile_text_view_mobile);
        final String num = "Number: " + Handi.getNumber();
        Name.setText(name);
        dateOfBirth.setText(dob);
        Email.setText(email);
        Number.setText(num);
        editProfileButton = (Button) view.findViewById(R.id.fragment_handi_view_profile_button_edit_profile);
        editProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), HandiEditProfileActivity.class));
            }
        });
        return view;
    }

    @Override
    public void onClick(View view) {

    }
}
