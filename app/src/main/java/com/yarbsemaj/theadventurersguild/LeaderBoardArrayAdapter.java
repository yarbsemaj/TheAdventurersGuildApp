package com.yarbsemaj.theadventurersguild;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by yarbs on 06/02/2017.
 */

public class LeaderBoardArrayAdapter extends ArrayAdapter<LeaderBoardEntry>{
    public LeaderBoardArrayAdapter(Context context, ArrayList<LeaderBoardEntry> leaderBoardEntries) {
        super(context, 0, leaderBoardEntries);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LeaderBoardEntry leaderBoardEntry = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.leader_board_entry, parent, false);
        }
        TextView iPos = (TextView) convertView.findViewById(R.id.i);
        TextView userName = (TextView) convertView.findViewById(R.id.userName);
        TextView score = (TextView) convertView.findViewById(R.id.score);

        iPos.setText(leaderBoardEntry.rank+". ");
        userName.setText(leaderBoardEntry.name);
        score.setText(leaderBoardEntry.score);

        return convertView;
    }


}
