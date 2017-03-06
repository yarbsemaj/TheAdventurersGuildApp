package com.yarbsemaj.theadventurersguild.arrayAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.yarbsemaj.theadventurersguild.R;
import com.yarbsemaj.theadventurersguild.modal.StatEntry;

import java.util.ArrayList;

/**
 * Created by yarbs on 06/02/2017.
 */

public class StatArrayAdapter extends ArrayAdapter<StatEntry> {
    public StatArrayAdapter(Context context, ArrayList<StatEntry> statEntries) {
        super(context, 0, statEntries);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        StatEntry statEntry = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.stat_entry, parent, false);
        }
        TextView userName = (TextView) convertView.findViewById(R.id.statName);
        TextView score = (TextView) convertView.findViewById(R.id.statValue);

        userName.setText(statEntry.name);
        score.setText(statEntry.value);

        return convertView;
    }


}
