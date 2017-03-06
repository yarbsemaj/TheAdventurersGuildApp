package com.yarbsemaj.theadventurersguild.arrayList;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.yarbsemaj.theadventurersguild.R;
import com.yarbsemaj.theadventurersguild.modal.CharaEntry;
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

public class CharaArrayList extends ArrayList<CharaEntry> {
    public CharaArrayList(Context context) {
        populate(context);
    }

    public static int getCharaImage(String type) {
        switch (type) {
            case "0":
                return R.drawable.character1;
            case "1":
                return R.drawable.charcter2;
            case "2":
                return R.drawable.charcter3;
            case "3":
                return R.drawable.character4;
        }
        return R.drawable.character4;
    }

    private void populate(Context context) {
        Log.d("Chara", "Pop");
        SharedPreferences getToken = context.getSharedPreferences(PREFS_NAME, 0);
        String token = getToken.getString("token", "blar");
        HashMap<String, String> prams = new HashMap();
        WebPoster poster = new WebPoster("http://tag.yarbsemaj.com/api/chara/list.php", prams);
        prams.put("Token", token);
        try {
            JSONObject jsonObject = new JSONObject(poster.execute().get());
            Log.i("json", jsonObject.toString());
            if (jsonObject.getBoolean("success")) {
                JSONObject userList = jsonObject.getJSONObject("char");
                JSONArray users = userList.getJSONArray("char");
                for (int i = 0; i < users.length(); i++) {
                    JSONObject user = users.getJSONObject(i);
                    String characterID = user.getString("CharacterID");
                    String name = user.getString("Name");
                    String currentHelth = user.getString("CurrentHealth");
                    String maxHelth = user.getString("MaxHealth");
                    String type = user.getString("Type");
                    Log.d("Chara", name);
                    this.add(new CharaEntry(characterID, name, currentHelth, maxHelth, type));
                }
            } else {
                getToken.edit().remove("token").apply();
            }
        } catch (InterruptedException | ExecutionException | JSONException e) {
            e.printStackTrace();
        }
    }
}
