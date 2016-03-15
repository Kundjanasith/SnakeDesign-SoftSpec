package com.ske.snakebaddesign.models;

import com.ske.snakebaddesign.models.square.FinishSquare;
import com.ske.snakebaddesign.models.square.NormalSquare;
import com.ske.snakebaddesign.models.square.StartSquare;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by exceed on 3/10/16 AD.
 */
public class Board {
    private int size;
    private static Board instance = null;
    private static int defaultSize = 0;
    private List<Square> squareList;

    private Board(int size){
        this.size = size;
        squareList = new ArrayList<Square>();
    }
    private void generateBoard(int size){
       for(int row=0 ; row<size ; row++){
           for(int col=0 ; col<size ; col++){
               if(row==0&&col==0){
                   squareList.add(new StartSquare());
               }
               else if(row==size-1&&col==size-1){
                   squareList.add(new FinishSquare());
               }
               else {
                   squareList.add(new NormalSquare(col * size + row + 1));
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

    public List<Square> getSquareList() {
        return squareList;
    }

    public void setSquareList(List<Square> squareList) {
        this.squareList = squareList;
    }
}
