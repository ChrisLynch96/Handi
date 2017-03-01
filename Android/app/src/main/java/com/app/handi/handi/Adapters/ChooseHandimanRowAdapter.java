package com.app.handi.handi.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.handi.handi.Activitys.HandiProfileUViewActivity;
import com.app.handi.handi.DataTypes.HandimanData;
import com.app.handi.handi.R;

import java.util.ArrayList;

/**
 * Created by christopherlynch on 27/02/2017.
 */


public class ChooseHandimanRowAdapter extends RecyclerView.Adapter <ChooseHandimanRowAdapter.HandimenHolder>{

    private ArrayList<HandimanData> mHandiMen;

    public static class HandimenHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView profile;
        TextView name;

        public HandimenHolder(View v){
            super(v);
            profile = (ImageView) v.findViewById(R.id.handiman_portrait);
            name = (TextView) v.findViewById(R.id.text_handiman_name);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Context context = itemView.getContext();
            Intent showHandiProfile = new Intent (context, HandiProfileUViewActivity.class);
            context.startActivity(showHandiProfile);
        }

        public void bindHandi(HandimanData handimanData){
            name.setText(handimanData.name);
            profile = handimanData.profilePicture;
        }
    }

    public ChooseHandimanRowAdapter(ArrayList<HandimanData> handiMen){
        this.mHandiMen = handiMen;
    }

    @Override
    public HandimenHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_handimen, parent, false);
        return new HandimenHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(HandimenHolder holder, int position) {
        HandimanData handimanData = mHandiMen.get(position);

    }

    @Override
    public int getItemCount() {
        return mHandiMen.size();
    }
}
