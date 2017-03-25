package com.app.handi.handi.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.stripe.android.TokenCallback;
import com.stripe.android.exception.AuthenticationException;
import com.stripe.android.model.Card;
import com.stripe.android.Stripe;
import com.stripe.android.model.Token;

import com.app.handi.handi.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SettingsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class  SettingsFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private EditText cardNumber, cardExpMonth, cardExpYear, cardCVC;
    private Button okButton;
    private int cardExpMonthInt;
    private int cardExpYearInt;
    Context mContext;
    View view;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
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
        view = inflater.inflate(R.layout.fragment_settings, container, false);
        okButton = (Button) view.findViewById(R.id.ok_button);
        cardNumber = (EditText) view.findViewById(R.id.card_number);
        cardExpMonth = (EditText) view.findViewById(R.id.card_exp_month);
        cardExpYear = (EditText) view.findViewById(R.id.card_exp_year);
        cardCVC = (EditText) view.findViewById(R.id.card_cvc);
        mContext = getActivity();
        okButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                final String number = cardNumber.getText().toString().trim();
                final String month = cardExpMonth.getText().toString().trim();
                final String year = cardExpYear.getText().toString().trim();
                final String cvc = cardCVC.getText().toString().trim();
                // Month and year needed to be converted to ints to be passed to card object
                cardExpMonthInt = Integer.parseInt(month);
                cardExpYearInt = Integer.parseInt(year);

                if(TextUtils.isEmpty(number)){
                    Toast.makeText(getActivity().getApplicationContext(), "Please enter your card number", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(month) || TextUtils.isEmpty(year)){
                    Toast.makeText(getActivity().getApplicationContext(), "Please enter your expiry date", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(cvc)){
                    Toast.makeText(getActivity().getApplicationContext(), "Please enter your CVC number", Toast.LENGTH_SHORT).show();
                    return;
                }

                //get card and check if all entries are valid entries
                Card cardTest = new Card(number, cardExpMonthInt, cardExpYearInt, cvc);
                if(!cardTest.validateCard()){
                    Toast.makeText(getActivity().getApplicationContext(), "dis ain't right", Toast.LENGTH_SHORT).show();
                    return;
                }

                //if card passes validation, pass card info as stripe token
                try{
                    Card card = new Card(number, cardExpMonthInt, cardExpYearInt, cvc);
                    Stripe stripe = new Stripe(mContext, "ca_AJS48q4Dsogw47bBPoG61DWz7RspXFSB");
                    stripe.createToken(
                            card,
                            new TokenCallback() {
                                public void onSuccess(Token token) {
                                    Toast.makeText(getActivity().getApplicationContext(), "Sent to server!",
                                            Toast.LENGTH_SHORT).show();
                                    // Send token to your server
                                }
                                public void onError(Exception error) {
                                    Toast.makeText(getActivity().getApplicationContext(), "Your card details are incorrect",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                    );
                }catch (AuthenticationException e){
                    Toast.makeText(getActivity().getApplicationContext(), "Your card is invalid",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
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

    @Override
    public void onClick(View v) {

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
