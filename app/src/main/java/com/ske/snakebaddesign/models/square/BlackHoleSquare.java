package com.ske.snakebaddesign.models.square;

import android.graphics.Color;

import com.ske.snakebaddesign.models.Square;

///**
// * Created by exceed on 3/15/16 AD.
// */
public class BlackHoleSquare implements Square{

    private String status;
    private int foregroundColor;
    private int backgroundColor;

    public BlackHoleSquare(){
        status = "Hole";
        foregroundColor = Color.rgb(255, 255, 255);
        backgroundColor = Color.rgb(136,115,145);
    }

    @Override
    public int getForegroundColor() {
        return this.foregroundColor;
    }

    @Override
    public int getBackgroundColor() {
        return this.backgroundColor;
    }

    @Override
    public String getStatus() {
        return this.status;
    }
}
