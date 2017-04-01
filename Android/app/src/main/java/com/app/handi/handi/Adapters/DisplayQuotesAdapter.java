package com.app.handi.handi.Adapters;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.app.handi.handi.DataTypes.Quote;
import com.app.handi.handi.R;

import java.util.ArrayList;

public class DisplayQuotesAdapter extends BaseAdapter {
    Context c;
    ArrayList<Quote> quotes = new ArrayList<>();

  public DisplayQuotesAdapter(ArrayList<Quote> quotes,Context c){
      this.quotes = quotes;
      this.c = c;
  }

    @Override
    public int getCount() {
        return quotes.size();
    }

    @Override
    public Object getItem(int position) {
        return quotes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(c).inflate(R.layout.list_item_quote, parent, false);
        }

        TextView amount = (TextView) convertView.findViewById(R.id.list_item_text_view_quote_amount);
        TextView name = (TextView) convertView.findViewById(R.id.list_item_text_view_quote_name);

        final Quote quote = (Quote) this.getItem(position);
        amount.setText(quote.getAmount());
        name.setText(quote.getHandiName());
        convertView.setTag(quote);
        return convertView;
    }
}
