package com.ske.snakebaddesign.models.square;

import android.graphics.Color;

import com.ske.snakebaddesign.models.Square;

/**
 * Created by exceed on 3/15/16 AD.
 */
public class NormalSquare implements Square {

    private String status;
    private int foregroundColor;
    private int backgroundColor;

    public NormalSquare(int i){
        status = i+"";
        foregroundColor = Color.rgb(00, 00, 00);
        backgroundColor = Color.rgb(232,213,173);
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
