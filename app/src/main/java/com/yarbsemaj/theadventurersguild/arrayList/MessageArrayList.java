package com.yarbsemaj.theadventurersguild.arrayList;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.yarbsemaj.theadventurersguild.modal.MessageEntry;
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

public class MessageArrayList extends ArrayList<MessageEntry> {
    public MessageArrayList(Context context,String boardID){
        populate(context,boardID);
    }

    private void populate(Context context,String boardID){
        SharedPreferences getToken = context.getSharedPreferences(PREFS_NAME, 0);
        String token = getToken.getString("token","blar");
        HashMap<String , String> prams = new HashMap();
        WebPoster poster = new WebPoster("http://tag.yarbsemaj.com/api/app/messageGet.php",prams);
        prams.put("Token",token);
        prams.put("BoardID",boardID);
        try {
            JSONObject jsonObject = new JSONObject(poster.execute().get());
            Log.i("json", jsonObject.toString());
            if (jsonObject.getBoolean("success")) {
                JSONObject messageList = jsonObject.getJSONObject("message");
                JSONArray messages=messageList.getJSONArray("message");
                for (int i = 0; i < messages.length(); i++) {
                    JSONObject message = messages.getJSONObject(i);
                    String username= message.getString("UserName");
                    String body= message.getString("Message");
                    String time = message.getString("time");
                    this.add(new MessageEntry(body,time,username));
                }
            }else{
                getToken.edit().remove("token").apply();
            }
        } catch (InterruptedException | ExecutionException | JSONException e) {
            e.printStackTrace();
        }
    }
}
