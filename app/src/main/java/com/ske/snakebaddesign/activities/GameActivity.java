package com.ske.snakebaddesign.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ske.snakebaddesign.R;
import com.ske.snakebaddesign.guis.BoardView;
import com.ske.snakebaddesign.models.Game;
import com.ske.snakebaddesign.models.square.BlackHoleSquare;
import com.ske.snakebaddesign.models.square.FastTrackSquare;

public class GameActivity extends AppCompatActivity {

    private BoardView boardView;
    private Button buttonTakeTurn;
    private Button buttonRestart;
    private TextView textPlayerTurn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        initComponents();
    }

    @Override
    protected void onStart() {
        super.onStart();
        resetGame();
    }

    private void initComponents() {
        boardView = (BoardView) findViewById(R.id.board_view);
        buttonTakeTurn = (Button) findViewById(R.id.button_take_turn);
        buttonTakeTurn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takeTurn();
            }
        });
        buttonRestart = (Button) findViewById(R.id.button_restart);
        buttonRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetGame();
            }
        });
        textPlayerTurn = (TextView) findViewById(R.id.text_player_turn);
        textPlayerTurn.setBackgroundColor(Game.getInstance().getCurrentPlayer().getColor());
    }

    private void resetGame() {
        Game.getInstance().getBoard().refreshBoard();
        Game.getInstance().setTurn(0);
        boardView.setPlayerPosition(0);
        boardView.setPlayerPosition(0);
    }

    private void takeTurn() {
        final int value = Game.getInstance().getDice().getValue();
        String title = Game.getInstance().getCurrentPlayer().getRollToString();
        String msg = "You got " + value;
        OnClickListener listener = new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                moveCurrentPiece(value);
                dialog.dismiss();
            }
        };
        displayDialog(title, msg, listener);
    }

    private void moveCurrentPiece(int value) {
        Game.getInstance().getCurrentPlayer().setPosition(adjustPosition(Game.getInstance().getCurrentPlayer().getPosition(), value));
            boardView.setPlayerPosition(Game.getInstance().getCurrentPlayer().getPosition());
            if(Game.getInstance().getBoard().getSquareList().get(findPosition(Game.getInstance().getCurrentPlayer().getName())) instanceof BlackHoleSquare){
                Game.getInstance().getCurrentPlayer().setPosition(0);
                boardView.setPlayerPosition(Game.getInstance().getCurrentPlayer().getPosition());
            }
            if(Game.getInstance().getBoard().getSquareList().get(findPosition(Game.getInstance().getCurrentPlayer().getName())) instanceof FastTrackSquare){
                Game.getInstance().getCurrentPlayer().setPosition((Game.getInstance().getBoard().getSize() * Game.getInstance().getBoard().getSize()) - 1);
                boardView.setPlayerPosition(Game.getInstance().getCurrentPlayer().getPosition());
            }
            textPlayerTurn.setText(Game.getInstance().getCurrentPlayer().getTurnToString());
            textPlayerTurn.setBackgroundColor(Game.getInstance().getCurrentPlayer().getColor());
        checkWin();
        Game.getInstance().setTurn(Game.getInstance().getTurn() + 1);;
    }

    private int findPosition(String player){
        int temp = 0;
        int mod = 0;
        if(player.equals("1")){
            temp = Game.getInstance().getCurrentPlayer().getPosition()/Game.getInstance().getBoard().getSize();
            mod = Game.getInstance().getCurrentPlayer().getPosition()%Game.getInstance().getBoard().getSize();
        }
        if(player.equals("2")){
            temp = Game.getInstance().getCurrentPlayer().getPosition()/Game.getInstance().getBoard().getSize();
            mod = Game.getInstance().getCurrentPlayer().getPosition()%Game.getInstance().getBoard().getSize();
        }
        for(int i=0 ; i<mod ; i++){
            temp += Game.getInstance().getBoard().getSize();
        }
        return temp;
    }
    private int adjustPosition(int current, int distance) {
        current = current + distance;
        int maxSquare = Game.getInstance().getBoard().getSize() * Game.getInstance().getBoard().getSize() - 1;
        if(current > maxSquare) {
            current = maxSquare - (current - maxSquare);
        }
        return current;
    }

    private void checkWin() {
        String title = "Game Over";
        String msg = "";
        OnClickListener listener = new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                resetGame();
                dialog.dismiss();
            }
        };
        if (Game.getInstance().getCurrentPlayer().getPosition() == Game.getInstance().getBoard().getSize() * Game.getInstance().getBoard().getSize() - 1) {
            msg = Game.getInstance().getCurrentPlayer().getWinToString();
        } else if (Game.getInstance().getCurrentPlayer().getPosition() == Game.getInstance().getBoard().getSize() * Game.getInstance().getBoard().getSize() - 1) {
            msg = Game.getInstance().getCurrentPlayer().getWinToString();
        } else {
            return;
        }
        displayDialog(title, msg, listener);
    }

    private void displayDialog(String title, String message, DialogInterface.OnClickListener listener) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setCancelable(false);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", listener);
        alertDialog.show();
    }

}
