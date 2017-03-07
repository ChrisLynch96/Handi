package com.app.handi.handi.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.handi.handi.R;

/**
 * Created by christopherlynch on 07/03/2017.
 */

public class DisplayingHandiAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final Integer[] handiProfilePics;
    private final String[] handiNames;
    private final String[] handiEmails;
    private final String[] handiPhoneNumbers;


    public DisplayingHandiAdapter(Activity context, Integer[] handiProfilePics, String[] handiNames, String[] handiEmails, String[] handiPhoneNumbers){
        super(context, R.layout.list_item_handi, handiNames);
        this.context = context;
        this.handiProfilePics = handiProfilePics;
        this.handiNames = handiNames;
        this.handiEmails = handiEmails;
        this.handiPhoneNumbers = handiPhoneNumbers;
    }

    public View getView(int position, View convertView, ViewGroup parent){

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_item_handi, null, true);
        ImageView handiPicture = (ImageView) rowView.findViewById(R.id.list_item_handi_image_view_handi_profile);
        TextView handiName = (TextView) rowView.findViewById(R.id.list_item_text_view_handi_name);
        TextView handiEmail = (TextView) rowView.findViewById(R.id.list_item_text_view_handi_email);
        TextView handiPhone = (TextView) rowView.findViewById(R.id.list_item_text_view_handi_phone);

        handiPicture.setImageResource(handiProfilePics[position]);
        handiName.setText(handiNames[position]);
        handiEmail.setText(handiEmails[position]);
        handiPhone.setText(handiPhoneNumbers[position]);

        return rowView;
    }

}
