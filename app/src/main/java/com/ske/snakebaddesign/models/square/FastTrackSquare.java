package com.ske.snakebaddesign.models.square;

import android.graphics.Color;

/**
 * Created by exceed on 3/15/16 AD.
 */
public class FastTrackSquare implements Square{


    private String status;
    private int foregroundColor;
    private int backgroundColor;

    public FastTrackSquare(){
        status = "Fast";
        foregroundColor = Color.rgb(255, 255, 255);
        backgroundColor = Color.rgb(255,126,166);
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
