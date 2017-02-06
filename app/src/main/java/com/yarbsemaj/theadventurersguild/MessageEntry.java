package com.yarbsemaj.theadventurersguild;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by james on 06/02/17.
 */

public class MessageEntry {
    public String body;
    public String time;
    public String username;
    MessageEntry(String body, String time,String username){
        this.body=body;
        this.username=username;
        Date date = new Date(Integer.parseInt(time)*1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM d yyyy h:mm a");
        this.time = sdf.format(date);
    }
}
