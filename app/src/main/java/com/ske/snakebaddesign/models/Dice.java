package com.ske.snakebaddesign.models;

import java.util.Random;

/**
 * Created by exceed on 3/10/16 AD.
 */
public class Dice {
    private int value;
    private static Dice instance = null;
    private Dice(){
        this.value = 0;
    }
    public static Dice getInstance(){
        if(instance==null) return instance = new Dice();
        else return instance;
    }
    public int getValue() {
        return this.value = 1 + new Random().nextInt(6);
    }
}
