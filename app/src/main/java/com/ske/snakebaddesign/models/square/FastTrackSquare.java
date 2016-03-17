package com.ske.snakebaddesign.models.square;

import android.graphics.Color;

import com.ske.snakebaddesign.models.Game;

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
    public void execute() {
        Game.getInstance().getCurrentPlayer().setPosition((Game.getInstance().getBoard().getSize() * Game.getInstance().getBoard().getSize()) - 1);
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
