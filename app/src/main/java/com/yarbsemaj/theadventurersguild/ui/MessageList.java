package com.yarbsemaj.theadventurersguild.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.yarbsemaj.theadventurersguild.R;
import com.yarbsemaj.theadventurersguild.arrayAdapter.MessageArrayAdapter;
import com.yarbsemaj.theadventurersguild.arrayList.MessageArrayList;
import com.yarbsemaj.theadventurersguild.web.WebPoster;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import static com.yarbsemaj.theadventurersguild.ui.Login.PREFS_NAME;

public class MessageList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_list);
        final String boardID = getIntent().getExtras().getString("boardID");
        updateMessages(boardID);

        Button post = (Button)findViewById(R.id.post);

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences getToken = getSharedPreferences(PREFS_NAME, 0);
                String token = getToken.getString("token","blar");
                EditText messageBody = (EditText) findViewById(R.id.messageText);
                String body = messageBody.getText().toString();
                HashMap<String , String> prams = new HashMap();
                WebPoster poster = new WebPoster("http://tag.yarbsemaj.com/api/app/messagePost.php",prams);
                prams.put("Token",token);
                prams.put("BoardID",boardID);
                prams.put("Message",body);
                try {
                    JSONObject jsonObject = new JSONObject(poster.execute().get());
                    Log.i("json", jsonObject.toString());
                    if (jsonObject.getBoolean("success")) {
                        updateMessages(boardID);
                        messageBody.setText("");
                    }else{
                        Toast.makeText(getApplicationContext(),"Message Post failed",Toast.LENGTH_LONG);
                    }
                } catch (InterruptedException | ExecutionException | JSONException e) {
                    e.printStackTrace();
                }



            }
        });


    }
    private void updateMessages(String boardID){
        ListView messageList = (ListView) findViewById(R.id.messageList);
        messageList.setAdapter(new MessageArrayAdapter(getApplicationContext(), new MessageArrayList(getApplicationContext(), boardID)));
    }
}
