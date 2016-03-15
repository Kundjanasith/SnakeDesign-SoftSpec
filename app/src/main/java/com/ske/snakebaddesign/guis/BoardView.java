package com.ske.snakebaddesign.guis;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.ske.snakebaddesign.models.Game;
import com.ske.snakebaddesign.models.Square;

import java.util.List;

public class BoardView extends View {

    // Graphics variables
    private Paint paint;
    private float viewWidth;
    private float cellSize;
    private float padding;
    private float playerSize;
    private int colorBG = Color.parseColor("#C155FF");

    public BoardView(Context context) {
        super(context);
        init();
    }

    public BoardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BoardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        reloadViewDimensions();
        drawBoard(canvas);
        drawSquares(canvas);
        drawPlayerPieces(canvas);
    }


    public void setP1Position(int p1Position) {
        Game.getInstance().getPlayer1().setPosition(p1Position);
        postInvalidate();
    }

    public void setP2Position(int p2Position) {
        Game.getInstance().getPlayer2().setPosition(p2Position);
        postInvalidate();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(20);
        paint.setTextAlign(Paint.Align.CENTER);
    }

    private void reloadViewDimensions() {
        viewWidth = getWidth();
        cellSize = (viewWidth / Game.getInstance().getBoard().getSize());
        padding = 0.05f * cellSize;
        playerSize = cellSize/8;
    }

    private void drawBoard(Canvas canvas) {
        paint.setColor(colorBG);
        canvas.drawRect(0, 0, viewWidth, viewWidth, paint);
    }

    private void drawSquares(Canvas canvas) {
        List<Square> temp = Game.getInstance().getBoard().getSquareList();
        for(int i = 0; i < Game.getInstance().getBoard().getSize(); i++) {
            for(int j = 0; j < Game.getInstance().getBoard().getSize(); j++) {
                float startX = i * cellSize + padding/2;
                float startY = j * cellSize + padding/2;
                float endX = startX + cellSize - padding;
                float endY = startY + cellSize - padding;
                Square square = temp.get((i * Game.getInstance().getBoard().getSize()) + j);
                paint.setColor(square.getBackgroundColor());
                canvas.drawRect(startX, startY, endX, endY, paint);
                paint.setColor(square.getForegroundColor());
                paint.setTextSize((float)(100/Game.getInstance().getBoard().getSize())*3);
                String label = square.getStatus();
                canvas.drawText(label, startX + cellSize/2 - padding/2, startY + cellSize/2, paint);
            }
        }
    }

    private void drawPlayerPieces(Canvas canvas) {
        // Draw player 1 (0.33 is the 1/3 position of the cell height)
        paint.setColor(Game.getInstance().getPlayer1().getColor());
        float p1X = positionToCol(Game.getInstance().getPlayer1().getPosition()) * cellSize + cellSize/2;
        float p1Y = positionToRow(Game.getInstance().getPlayer1().getPosition()) * cellSize + (cellSize * 0.33f);
        canvas.drawCircle(p1X, p1Y, playerSize, paint);
        // Draw player 2 (0.66 is the 2/3 position of the cell height)
        paint.setColor(Game.getInstance().getPlayer2().getColor());
        float p2X = positionToCol(Game.getInstance().getPlayer2().getPosition()) * cellSize + cellSize/2;
        float p2Y = positionToRow(Game.getInstance().getPlayer2().getPosition()) * cellSize + (cellSize * 0.66f);
        canvas.drawCircle(p2X, p2Y, playerSize, paint);
    }

    private int positionToRow(int position) {
        return position / Game.getInstance().getBoard().getSize();
    }

    private int positionToCol(int position) {
        return position % Game.getInstance().getBoard().getSize();
    }


}
