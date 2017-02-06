package com.yarbsemaj.theadventurersguild;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

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
                Log.d("Item",messageBoardAdapter.getItem(i).ID);

            }
        });
    }
}
