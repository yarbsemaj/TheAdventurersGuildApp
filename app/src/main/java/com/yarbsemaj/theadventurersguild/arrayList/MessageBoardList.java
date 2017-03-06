package com.yarbsemaj.theadventurersguild.arrayList;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.yarbsemaj.theadventurersguild.modal.MessageBoardEntry;
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

public class MessageBoardList extends ArrayList<MessageBoardEntry> {
    public MessageBoardList(Context context){
        populate(context);
    }

    private void populate(Context context){
        SharedPreferences getToken = context.getSharedPreferences(PREFS_NAME, 0);
        String token = getToken.getString("token","blar");
        HashMap<String , String> prams = new HashMap();
        WebPoster poster = new WebPoster("http://tag.yarbsemaj.com/api/app/mbGet.php",prams);
        prams.put("Token",token);
        try {
            JSONObject jsonObject = new JSONObject(poster.execute().get());
            Log.i("json", jsonObject.toString());
            if (jsonObject.getBoolean("success")) {
                JSONObject boardList = jsonObject.getJSONObject("board");
                JSONArray boards=boardList.getJSONArray("board");
                for (int i = 0; i < boards.length(); i++) {
                    JSONObject board = boards.getJSONObject(i);
                    String boardName= board.getString("name");
                    String boardID= board.getString("boardID");
                    this.add(new MessageBoardEntry(boardName,boardID));
                }
            }else{
                getToken.edit().remove("token").apply();
            }
        } catch (InterruptedException | ExecutionException | JSONException e) {
            e.printStackTrace();
        }
    }
}
