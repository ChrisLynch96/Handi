package com.app.handi.handi.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.app.handi.handi.DataTypes.Job;
import com.app.handi.handi.R;

import java.util.ArrayList;

/**
 * Created by Killian on 25/03/2017.
 */

public class DisplayHandiOffersAdapter extends BaseAdapter {
    Context c;
    ArrayList<Job> jobs;

    public DisplayHandiOffersAdapter(ArrayList<Job> jobs, Context c){
        this.c = c;
        this.jobs = jobs;
    }

    @Override
    public int getCount() {
        return jobs.size();
    }

    @Override
    public Object getItem(int position) {
        return jobs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(c).inflate(R.layout.list_item_job, parent, false);
        }

        TextView title = (TextView) convertView.findViewById(R.id.list_item_text_view_job_title);
        TextView status = (TextView) convertView.findViewById(R.id.list_item_text_view_job_status);
        TextView quote = (TextView) convertView.findViewById(R.id.list_item_text_view_job_quote);


        final Job job = (Job) this.getItem(position);
        final String recieved = "Offer Recieved";

        if (!job.isAccepted()) {
            title.setText(job.getTitle());
            status.setText(job.getStatus());
            quote.setText(recieved);
        }
        convertView.setTag(job);
        return convertView;
    }
}
