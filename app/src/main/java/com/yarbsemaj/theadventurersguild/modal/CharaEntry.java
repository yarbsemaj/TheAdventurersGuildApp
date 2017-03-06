package com.yarbsemaj.theadventurersguild.modal;

import java.io.Serializable;

/**
 * Created by yarbs on 10/02/2017.
 */

public class CharaEntry implements Serializable {

    public String characterID;
    public String name;
    public String currentHealth;
    public String maxHealth;
    public String type;

    public CharaEntry(String characterID, String name, String currentHealth, String maxHealth, String type) {
        this.characterID = characterID;
        this.name = name;
        this.currentHealth = currentHealth;
        this.maxHealth = maxHealth;
        this.type = type;
    }

    public String toString() {
        return name;
    }
}
