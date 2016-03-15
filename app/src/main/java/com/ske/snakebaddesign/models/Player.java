package com.ske.snakebaddesign.models;

import android.graphics.Color;

/**
 * Created by exceed on 3/10/16 AD.
 */
public class Player {
    private String name;
    private int position;
    private int color;
    public Player(String name){
        this.name = name;
        this.position = 0;
        if(name.equals("1")) this.color = Color.parseColor("#A1FFD8");
        if(name.equals("2")) this.color = Color.parseColor("#E16868");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getTurnToString(){
        return "Player "+getName()+"'s Turn";
    }

    public String getWinToString(){ return "Player "+getName()+" won!";}

    public String getRollToString(){ return "Player "+getName()+" rolled a die";}

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
