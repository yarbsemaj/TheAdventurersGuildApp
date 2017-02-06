package com.yarbsemaj.theadventurersguild;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class Login extends AppCompatActivity {
    public static final String PREFS_NAME = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences getToken = getSharedPreferences(PREFS_NAME, 0);

        if(getToken.contains("token")){
            String token = getToken.getString("token","blar");
            HashMap<String , String> prams = new HashMap();
            WebPoster poster = new WebPoster("http://tag.yarbsemaj.com/api/login/auth.php",prams);
            prams.put("Token",token);
            try {
                JSONObject jsonObject = new JSONObject(poster.execute().get());
                Log.i("json", jsonObject.toString());
                if (jsonObject.getBoolean("success")) {
                    gotoMenu();
                }else{
                    getToken.edit().remove("token").apply();
                }
            } catch (InterruptedException | ExecutionException | JSONException e) {
                e.printStackTrace();
            }
        }
        setContentView(R.layout.activity_login);

        final Button button = (Button) findViewById(R.id.loginButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TextView usernameBox=(TextView) findViewById(R.id.userName);
                TextView passwordBox=(TextView) findViewById(R.id.password);
                String username = String.valueOf(usernameBox.getText());
                String password = String.valueOf(passwordBox.getText());
                HashMap<String , String> prams = new HashMap();
                WebPoster poster = new WebPoster("http://tag.yarbsemaj.com/api/login/login.php",prams);
                prams.put("User_Name",username);
                prams.put("Password",password);
                try {
                    JSONObject jsonObject = new  JSONObject(poster.execute().get());
                    Log.i("json",jsonObject.toString());
                    if(jsonObject.getBoolean("success")){
                        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putString("token", jsonObject.getString("token"));
                        editor.apply();
                        gotoMenu();

                    }else{
                        Log.i("Login","Failed");
                        Toast.makeText(getApplicationContext(), "Login Failed",Toast.LENGTH_LONG).show();

                    }
                } catch (InterruptedException | ExecutionException | JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void gotoMenu(){
        Intent myIntent = new Intent(this, MenuActivity.class);
        startActivity(myIntent);
    }
}
