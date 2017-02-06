package com.yarbsemaj.theadventurersguild;

import java.security.PublicKey;

/**
 * Created by yarbs on 06/02/2017.
 */

public class LeaderBoardEntry {

    public String rank;
    public String name;
    public String score;

    public LeaderBoardEntry(String name, String score, String rank){
        this.name = name;
        this.score= score;
        this.rank = rank;
    }
}
