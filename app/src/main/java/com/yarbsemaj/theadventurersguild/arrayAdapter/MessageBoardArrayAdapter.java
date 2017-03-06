package com.yarbsemaj.theadventurersguild.arrayAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.yarbsemaj.theadventurersguild.R;
import com.yarbsemaj.theadventurersguild.modal.MessageBoardEntry;

import java.util.ArrayList;

/**
 * Created by yarbs on 06/02/2017.
 */

public class MessageBoardArrayAdapter extends ArrayAdapter<MessageBoardEntry>{
    public MessageBoardArrayAdapter(Context context, ArrayList<MessageBoardEntry> leaderBoardEntries) {
        super(context, 0, leaderBoardEntries);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MessageBoardEntry messageBoardEntry = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.message_board_entry, parent, false);
        }
        TextView messageBoardButton = (TextView) convertView.findViewById(R.id.messageBoardButton);

        messageBoardButton.setText(messageBoardEntry.name);

        return convertView;
    }


}
