package com.ske.snakebaddesign.models;

import com.ske.snakebaddesign.models.square.Square;

/**
 * Created by exceed on 3/10/16 AD.
 */
public class Game {

    private Player player1;
    private Player player2;
    private Board board;
    private Dice dice;
    private int turn;
    private static Game instance = null;

    private Game(){
        board = Board.getInstance();
        dice = Dice.getInstance();
        player1 = new Player("1");
        player2 = new Player("2");
        turn = 0;
    }

    public static Game getInstance(){
        if(instance==null) return instance = new Game();
        return instance;
    }

    public Player getPlayer1() {
        return player1;
    }


    public Player getPlayer2() {
        return player2;
    }

    public Player getCurrentPlayer(){
        if(this.getTurn()%2==0) return this.player1;
        else return this.player2;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }


    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public Dice getDice() {
        return dice;
    }

    public void setDice(Dice dice) {
        this.dice = dice;
    }

    public boolean checkWin(){
        return this.getCurrentPlayer().getPosition() ==
                this.getInstance().getBoard().getSize() * Game.getInstance().getBoard().getSize() - 1 ;
    }

    public void resetGame(){
        this.getBoard().refreshBoard();
        this.setTurn(0);
        this.getPlayer1().setPosition(0);
        this.getPlayer2().setPosition(0);
    }

    public String  checkSquare(){
        Square s =  Game.getInstance().getBoard().getSquareList().get(findPosition());
        s.execute();
        if(s.getStatus().equals("Hole")) return "This is a black hole \n Please go to started point";
        if(s.getStatus().equals("Fast")) return "This is a fast track \n You will be the winner";
        return  null;
    }


    private int findPosition(){
        int temp = Game.getInstance().getCurrentPlayer().getPosition()/Game.getInstance().getBoard().getSize();
        int mod = Game.getInstance().getCurrentPlayer().getPosition()%Game.getInstance().getBoard().getSize();
        for(int i=0 ; i<mod ; i++){
            temp += Game.getInstance().getBoard().getSize();
        }
        return temp;
    }


}
