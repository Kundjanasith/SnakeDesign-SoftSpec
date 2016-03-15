package com.ske.snakebaddesign.models;

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

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
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

}
