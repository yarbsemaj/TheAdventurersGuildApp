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

public class MessageArrayAdapter extends ArrayAdapter<MessageEntry>{
    public MessageArrayAdapter(Context context, ArrayList<MessageEntry> messageEntries) {
        super(context, 0, messageEntries);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MessageEntry messageEntry = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.message_entry, parent, false);
        }
        TextView messageBody = (TextView) convertView.findViewById(R.id.messageBody);
        TextView messageTime = (TextView) convertView.findViewById(R.id.messageDate);
        TextView messageUsername = (TextView) convertView.findViewById(R.id.messageUsername);

        messageBody.setText(messageEntry.body);
        messageTime.setText(messageEntry.time);
        messageUsername.setText(messageEntry.username);

        return convertView;
    }


}
