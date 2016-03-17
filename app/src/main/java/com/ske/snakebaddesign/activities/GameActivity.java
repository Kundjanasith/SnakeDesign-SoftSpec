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

public class GameActivity extends AppCompatActivity{

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
        Game.getInstance().resetGame();
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
                Game.getInstance().resetGame();
                boardView.setPlayerPosition(Game.getInstance().getCurrentPlayer().getPosition());
            }
        });
        textPlayerTurn = (TextView) findViewById(R.id.text_player_turn);
        textPlayerTurn.setBackgroundColor(Game.getInstance().getCurrentPlayer().getColor());
    }

    private void takeTurn() {
        final int value = Game.getInstance().getDice().getValue();
        final String title = Game.getInstance().getCurrentPlayer().getRollToString();
        String msg = "You got " + value;
        OnClickListener listener = new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                moveCurrentPiece(title,value);
                dialog.dismiss();
            }
        };
        displayDialog(title, msg, listener);
    }

    private void moveCurrentPiece(String title,int value) {
        Game.getInstance().getCurrentPlayer().move(value);
        String msg = Game.getInstance().checkSquare();
        boardView.setPlayerPosition(Game.getInstance().getCurrentPlayer().getPosition());
        textPlayerTurn.setText(Game.getInstance().getCurrentPlayer().getTurnToString());
        textPlayerTurn.setBackgroundColor(Game.getInstance().getCurrentPlayer().getColor());
        if(Game.getInstance().checkWin())showWin();
        Game.getInstance().setTurn(Game.getInstance().getTurn() + 1);
        if(msg!=null)displayDialog(title, msg, null);
    }

    private void showWin() {
        String title = "Game Over";
        String msg = Game.getInstance().getCurrentPlayer().getWinToString();
        OnClickListener listener = new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Game.getInstance().resetGame();
                boardView.setPlayerPosition(Game.getInstance().getCurrentPlayer().getPosition());
                dialog.dismiss();
            }
        };
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
