package com.yarbsemaj.theadventurersguild;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import static com.yarbsemaj.theadventurersguild.Login.PREFS_NAME;

public class LeaderBoard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);
        ArrayList<LeaderBoardEntry> leaderBoard = new LeaderBoardList(getApplicationContext());

        ListView leaderBoardListView = (ListView) findViewById(R.id.leaderBoardList);
        leaderBoardListView.setAdapter(new LeaderBoardArrayAdapter(getApplicationContext(), leaderBoard));


        SharedPreferences getToken = getSharedPreferences(PREFS_NAME, 0);
        String token = getToken.getString("token","blar");
        HashMap<String , String> prams = new HashMap();
        WebPoster poster = new WebPoster("http://tag.yarbsemaj.com/api/app/lb.php",prams);
        prams.put("Token",token);
        try {
            JSONObject jsonObject = new JSONObject(poster.execute().get());
            Log.i("json", jsonObject.toString());
            if (jsonObject.getBoolean("success")) {
                JSONObject user = jsonObject.getJSONObject("userPos");
                String userName= user.getString("UserName");
                String score= user.getString("Score");
                String rank = user.getString("Rank");

                TextView iPosTV = (TextView)findViewById(R.id.rank);
                TextView userNameTV = (TextView)findViewById(R.id.userName);
                TextView scoreTV = (TextView) findViewById(R.id.score);

                iPosTV.setText(rank+". ");
                userNameTV.setText(userName);
                scoreTV.setText(score);
            }else{
                getToken.edit().remove("token").apply();
            }
        } catch (InterruptedException | ExecutionException | JSONException e) {
            e.printStackTrace();
        }
    }
}
