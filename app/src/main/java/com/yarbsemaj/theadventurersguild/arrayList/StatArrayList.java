package com.yarbsemaj.theadventurersguild.arrayList;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.yarbsemaj.theadventurersguild.modal.StatEntry;
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

public class StatArrayList extends ArrayList<StatEntry> {
    public StatArrayList(Context context, String charID) {
        populate(context, charID);
    }

    private void populate(Context context, String chraID) {
        Log.d("Chara", "Pop");
        SharedPreferences getToken = context.getSharedPreferences(PREFS_NAME, 0);
        String token = getToken.getString("token", "blar");
        HashMap<String, String> prams = new HashMap();
        WebPoster poster = new WebPoster("http://tag.yarbsemaj.com/api/chara/getStat.php", prams);
        prams.put("Token", token);
        prams.put("CharID", chraID);
        try {
            JSONObject jsonObject = new JSONObject(poster.execute().get());
            Log.i("json", jsonObject.toString());
            if (jsonObject.getBoolean("success")) {
                JSONObject userList = jsonObject.getJSONObject("Starts");
                JSONArray users = userList.getJSONArray("stat");
                for (int i = 0; i < users.length(); i++) {
                    JSONObject user = users.getJSONObject(i);
                    String statID = user.getString("CharacteristicID");
                    String name = user.getString("Name");
                    String value = user.getString("Value");
                    this.add(new StatEntry(statID, name, value));
                }
            } else {
                getToken.edit().remove("token").apply();
            }
        } catch (InterruptedException | ExecutionException | JSONException e) {
            e.printStackTrace();
        }
    }
}
