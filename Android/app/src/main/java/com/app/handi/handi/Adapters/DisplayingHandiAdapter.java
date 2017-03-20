package com.app.handi.handi.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.handi.handi.Activitys.JobDescriptionActivity;
import com.app.handi.handi.DataTypes.HandimanData;
import com.app.handi.handi.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by christopherlynch on 07/03/2017.
 */

public class DisplayingHandiAdapter extends BaseAdapter {
    Context c;
    ArrayList<HandimanData> HandiMen;

    public DisplayingHandiAdapter(ArrayList<HandimanData> handimen, Context c) {
        HandiMen = handimen;
        this.c = c;
    }

    @Override
    public int getCount() {
        return HandiMen.size();
    }

    @Override
    public Object getItem(int position) {
        return HandiMen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(c).inflate(R.layout.list_item_handi, parent, false);
        }

        TextView nameTxt = (TextView) convertView.findViewById(R.id.list_item_text_view_handi_name);
        TextView emailTxt = (TextView) convertView.findViewById(R.id.list_item_text_view_handi_email);
        TextView numTxt = (TextView) convertView.findViewById(R.id.list_item_text_view_handi_phone);

        final HandimanData h = (HandimanData) this.getItem(position);

        nameTxt.setText(h.getName());
        emailTxt.setText(h.getEmail());
        numTxt.setText(h.getNumber());

//        convertView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(c, h.getName(), Toast.LENGTH_SHORT).show();
//            }
//        });

        return convertView;
    }
}
