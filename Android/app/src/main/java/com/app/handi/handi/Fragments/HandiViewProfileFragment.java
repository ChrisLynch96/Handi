package com.app.handi.handi.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.app.handi.handi.Activitys.HandiEditProfileActivity;
import com.app.handi.handi.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HandiViewProfileFragment extends Fragment implements View.OnClickListener {

    View view;
    Button editProfileButton;

    public HandiViewProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_handi_view_profile, container, false);
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
