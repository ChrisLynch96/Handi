package com.app.handi.handi.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.app.handi.handi.Activitys.AcceptJobDescriptionActivity;
import com.app.handi.handi.Activitys.ViewJobDescriptionActivity;
import com.app.handi.handi.Adapters.DisplayHandiJobsAdapter;
import com.app.handi.handi.Adapters.DisplayHandiOffersAdapter;
import com.app.handi.handi.DataTypes.Job;
import com.app.handi.handi.Firebase.HelperUser;
import com.app.handi.handi.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HandiHomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HandiHomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HandiHomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ListView listView;
    ListView list2;
    View view;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private static ArrayList<Job> job = new ArrayList<>();
    private ArrayList<Job> jobs = new ArrayList<>();
    private ArrayList<Job> jobs2 = new ArrayList<>();
    DisplayHandiJobsAdapter adapter;
    DisplayHandiOffersAdapter adapter2;

    private OnFragmentInteractionListener mListener;

    public HandiHomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HandiHomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HandiHomeFragment newInstance(String param1, String param2, ArrayList<Job> jobs) {
        HandiHomeFragment fragment = new HandiHomeFragment();
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
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_handi_home, container, false);
        listView = (ListView) view.findViewById(R.id.fragment_handi_home_list_view_jobs);
        list2 = (ListView) view.findViewById(R.id.fragment_handi_home_list_view_jobs_offers);
        for(int i=0;i<job.size();i++){
            if(job.get(i).isAccepted())
                jobs.add(job.get(i));
            else
                jobs2.add(job.get(i));
        }
        adapter = new DisplayHandiJobsAdapter(jobs,getActivity());
        adapter2 = new DisplayHandiOffersAdapter(jobs2,getActivity());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        listView.setAdapter(adapter);
        list2.setAdapter(adapter2);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Job job = (Job) view.getTag();
                Intent intent = new Intent(getActivity(), ViewJobDescriptionActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("LeJob",job);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        list2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Job job = (Job) view.getTag();
                Intent intent = new Intent(getActivity(), AcceptJobDescriptionActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("LeJobOffer",job);
                intent.putExtras(bundle);
                intent.putExtra("Jobs",jobs);
                startActivity(intent);
            }
        });
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
