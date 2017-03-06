package com.yarbsemaj.theadventurersguild.arrayList;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.yarbsemaj.theadventurersguild.modal.LeaderBoardEntry;
import com.yarbsemaj.theadventurersguild.web.WebPoster;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import static com.yarbsemaj.theadventurersguild.ui.Login.PREFS_NAME;

/**
 * Created by yarbs on 06/02/2017.
 */

public class LeaderBoardList extends ArrayList<LeaderBoardEntry> {
    public LeaderBoardList(Context context){
        populate(context);
    }

    private void populate(Context context){
        SharedPreferences getToken = context.getSharedPreferences(PREFS_NAME, 0);
        String token = getToken.getString("token","blar");
        HashMap<String , String> prams = new HashMap();
        WebPoster poster = new WebPoster("http://tag.yarbsemaj.com/api/app/lb.php",prams);
        prams.put("Token",token);
        try {
            JSONObject jsonObject = new JSONObject(poster.execute().get());
            Log.i("json", jsonObject.toString());
            if (jsonObject.getBoolean("success")) {
                JSONObject userList = jsonObject.getJSONObject("user");
                JSONArray users=userList.getJSONArray("user");
                for (int i = 0; i < users.length(); i++) {
                    JSONObject user = users.getJSONObject(i);
                    String username= user.getString("UserName");
                    String score= user.getString("Score");
                    String rank = user.getString("Rank");
                    this.add(new LeaderBoardEntry(username,score,rank));
                }
            }else{
                getToken.edit().remove("token").apply();
            }
        } catch (InterruptedException | ExecutionException | JSONException e) {
            e.printStackTrace();
        }
    }
}
