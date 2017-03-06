package com.yarbsemaj.theadventurersguild.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.yarbsemaj.theadventurersguild.R;
import com.yarbsemaj.theadventurersguild.web.WebPoster;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import static com.yarbsemaj.theadventurersguild.ui.Login.PREFS_NAME;

public class MenuActivity extends AppCompatActivity {
    private TextView header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu);
        header = (TextView) findViewById(R.id.welcomeBanner);
        Button leaderBoard = (Button) findViewById(R.id.leaderBoardButton);
        Button messageBoard = (Button) findViewById(R.id.gotoMessageBoard);
        Button charaList = (Button) findViewById(R.id.gotoCharaList);
        setUsername();


        leaderBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), LeaderBoard.class);

                Log.i("Chra", "dsp");
                startActivity(myIntent);
            }
        });

        messageBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), MessageBoardPick.class);
                startActivity(myIntent);
            }
        });

        charaList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), CharaList.class);
                startActivity(myIntent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                SharedPreferences getToken = getSharedPreferences(PREFS_NAME, 0);
                getToken.edit().remove("token").apply();
                Toast.makeText(getApplicationContext(), "Logged Out", Toast.LENGTH_LONG).show();
                Intent myIntent = new Intent(this, Login.class);
                startActivity(myIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setUsername(){
        SharedPreferences getToken = getSharedPreferences(PREFS_NAME, 0);
        String token = getToken.getString("token","blar");
        HashMap<String , String> prams = new HashMap();
        WebPoster poster = new WebPoster("http://tag.yarbsemaj.com/api/user/getInfo.php",prams);
        prams.put("Token",token);
        try {
            JSONObject jsonObject = new JSONObject(poster.execute().get());
            Log.i("json", jsonObject.toString());
            if (jsonObject.getBoolean("success")) {
                JSONObject user = jsonObject.getJSONObject("Users");
                String userName = user.getString("UserName");
                header.setText("Welcome back "+userName);

            }else{
                getToken.edit().remove("token").apply();
            }
        } catch (InterruptedException | ExecutionException | JSONException e) {
            e.printStackTrace();
        }
    }
}
