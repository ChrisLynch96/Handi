package com.app.handi.handi.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.app.handi.handi.Activitys.ChooseHandiTypeActivity;
import com.app.handi.handi.Activitys.ViewJobDescriptionActivity;
import com.app.handi.handi.Adapters.DisplayUserJobsAdapter;
import com.app.handi.handi.DataTypes.Job;
import com.app.handi.handi.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    View view;
    ListView list;
    int jobState;
    android.support.design.widget.FloatingActionButton newJobButton;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    DisplayUserJobsAdapter adapter;
    private static ArrayList<Job> job = new ArrayList<>();
    private ArrayList<Job> jobs = new ArrayList<>();

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2,ArrayList<Job> jobs) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        job=jobs;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        list = (ListView) view.findViewById(R.id.fragment_home_list_view_user_jobs);
        for(int i=0;i<job.size();i++){
            if(job.get(i).getStatus().equals("Incomplete"))
                jobs.add(job.get(i));
        }
        newJobButton = (android.support.design.widget.FloatingActionButton) view.findViewById(R.id.fragment_home_floating_action_button_fab);
        newJobButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(getActivity(), ChooseHandiTypeActivity.class));
            }
        });
        adapter = new DisplayUserJobsAdapter(jobs,getActivity());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Log.d("size",Integer.toString(job.size()));
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Job job = (Job) view.getTag();
                Intent intent = new Intent(getActivity(), ViewJobDescriptionActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("LeJob",job);
                intent.putExtras(bundle);
                if(job.isAccepted()) {
                    jobState = 1;
                    intent.putExtra("ViewButton",jobState );
                }
                else{
                    jobState =0;
                    intent.putExtra("ViewButton",jobState);
                }
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onClick(View view) {

    }

}
