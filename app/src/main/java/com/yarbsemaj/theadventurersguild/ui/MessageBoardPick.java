package com.yarbsemaj.theadventurersguild.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.yarbsemaj.theadventurersguild.R;
import com.yarbsemaj.theadventurersguild.arrayAdapter.MessageBoardArrayAdapter;
import com.yarbsemaj.theadventurersguild.arrayList.MessageBoardList;
import com.yarbsemaj.theadventurersguild.modal.MessageBoardEntry;

import java.util.ArrayList;

public class MessageBoardPick extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_board_pick);

        ArrayList<MessageBoardEntry> messageBoardEntries = new MessageBoardList(getApplicationContext());

        final ListView messageBoardList = (ListView)findViewById(R.id.messageBoardList);
        final ArrayAdapter<MessageBoardEntry> messageBoardAdapter=new MessageBoardArrayAdapter(getApplicationContext(),messageBoardEntries);

        messageBoardList.setAdapter(messageBoardAdapter);

        messageBoardList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle mBundle = new Bundle();
                Intent mIntent = new Intent(getApplicationContext(), MessageList.class);
                mBundle.putString("boardID", messageBoardAdapter.getItem(i).ID);
                mIntent.putExtras(mBundle);
                startActivity(mIntent);
            }
        });
    }
}
