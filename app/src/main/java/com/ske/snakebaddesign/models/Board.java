package com.ske.snakebaddesign.models;

import com.ske.snakebaddesign.models.square.BlackHoleSquare;
import com.ske.snakebaddesign.models.square.FastTrackSquare;
import com.ske.snakebaddesign.models.square.FinishSquare;
import com.ske.snakebaddesign.models.square.NormalSquare;
import com.ske.snakebaddesign.models.square.StartSquare;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by exceed on 3/10/16 AD.
 */
public class Board {
    private int size;
    private static Board instance = null;
    private static int defaultSize = 5;
    private List<Square> squareList;

    private Board(int size){
        this.size = size;
        squareList = new ArrayList<Square>();
    }
    private void generateBoard(int size){
        int fast = new Random().nextInt(size);
       for(int row=0 ; row<size ; row++){
           for(int col=0 ; col<size ; col++){
               if(row==0&&col==0){
                   squareList.add(new StartSquare());
               }
               else if(row==size-1&&col==size-1){
                   squareList.add(new FinishSquare());
               }
               else if(row==fast&&col==(fast+(new Random().nextInt(size)))%size){
                   squareList.add(new FastTrackSquare());
               }
               else if(row==new Random().nextInt(size)){
                   squareList.add(new BlackHoleSquare());
               }
               else {
                   squareList.add(new NormalSquare(col * size + row ));
               }
           }
       }
    }
    public static Board getInstance(){
        if(instance==null) return instance = new Board(defaultSize);
        return instance;
    }
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        generateBoard(size);
        this.size = size;
    }

    public void refreshBoard(){
        this.squareList.clear();
        this.generateBoard(this.size);
    }


    public List<Square> getSquareList() {
        return squareList;
    }

}
