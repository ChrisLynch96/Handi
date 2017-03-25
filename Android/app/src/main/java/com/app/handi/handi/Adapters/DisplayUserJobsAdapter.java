package com.app.handi.handi.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.app.handi.handi.DataTypes.Job;
import com.app.handi.handi.R;

import java.util.ArrayList;

/**
 * Created by Killian on 23/03/2017.
 */

public class DisplayUserJobsAdapter extends BaseAdapter{
    Context c;
    ArrayList<Job> jobs;

    public DisplayUserJobsAdapter(ArrayList<Job> jobs, Context c){
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
        TextView accepted = (TextView) convertView.findViewById(R.id.list_item_text_view_job_accepted);

        final Job job = (Job) this.getItem(position);
        final String a = "Accepted";
        final String Na = "Not Accpeted";

        Log.d("t",job.getTitle());
        title.setText(job.getTitle());
        status.setText(job.getStatus());
        if(job.isAccepted())
            accepted.setText(a);
        else
            accepted.setText(Na);
        return convertView;
    }
}
