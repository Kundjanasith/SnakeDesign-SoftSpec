package com.ske.snakebaddesign.models.square;

import android.graphics.Color;

import com.ske.snakebaddesign.models.Square;

/**
 * Created by exceed on 3/15/16 AD.
 */
public class FinishSquare implements Square {

    private String status;
    private int foregroundColor;
    private int backgroundColor;

    public FinishSquare(){
        status = "Finish";
        foregroundColor = Color.rgb(255,255,255);
        backgroundColor = Color.rgb(180,170,160);
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
