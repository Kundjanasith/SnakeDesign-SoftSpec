package com.ske.snakebaddesign.guis;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.ske.snakebaddesign.models.Board;
import com.ske.snakebaddesign.models.Square;

import java.util.List;

public class BoardView extends View {

    // Graphics variables
    private Paint paint;
    private float viewWidth;
    private float cellSize;
    private float padding;
    private float playerSize;
    private int colorP1 = Color.parseColor("#A1FFD8");
    private int colorP2 = Color.parseColor("#E16868");
    private int colorBG = Color.parseColor("#C155FF");

    // These variables will be used to keep track of what to render
//    private int boardSize;
    private int p1Position;
    private int p2Position;
    private Board board;

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
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        reloadViewDimensions();
        drawBoard(canvas);
        drawSquares(canvas);
        drawPlayerPieces(canvas);
    }


    public void setP1Position(int p1Position) {
        this.p1Position = p1Position;
        postInvalidate();
    }

    public void setP2Position(int p2Position) {
        this.p2Position = p2Position;
        postInvalidate();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(20);
        paint.setTextAlign(Paint.Align.CENTER);
    }

    private void reloadViewDimensions() {
        viewWidth = getWidth();
        cellSize = (viewWidth / board.getSize());
        padding = 0.05f * cellSize;
        playerSize = cellSize/8;
    }

    private void drawBoard(Canvas canvas) {
        paint.setColor(colorBG);
        canvas.drawRect(0, 0, viewWidth, viewWidth, paint);
    }

    private void drawSquares(Canvas canvas) {
        List<Square> temp = this.board.getSquareList();
        for(int i = 0; i < board.getSize(); i++) {
            for(int j = 0; j < board.getSize(); j++) {
                float startX = i * cellSize + padding/2;
                float startY = j * cellSize + padding/2;
                float endX = startX + cellSize - padding;
                float endY = startY + cellSize - padding;
                Square square = temp.get((i * board.getSize()) + j);
                paint.setColor(square.getBackgroundColor());
                canvas.drawRect(startX, startY, endX, endY, paint);
                paint.setColor(square.getForegroundColor());
                paint.setTextSize((float)(100/board.getSize())*3);
                String label = square.getStatus();
                canvas.drawText(label, startX + cellSize/2 - padding/2, startY + cellSize/2, paint);
            }
        }
    }

    private void drawPlayerPieces(Canvas canvas) {
        // Draw player 1 (0.33 is the 1/3 position of the cell height)
        paint.setColor(colorP1);
        float p1X = positionToCol(p1Position) * cellSize + cellSize/2;
        float p1Y = positionToRow(p1Position) * cellSize + (cellSize * 0.33f);
        canvas.drawCircle(p1X, p1Y, playerSize, paint);
        // Draw player 2 (0.66 is the 2/3 position of the cell height)
        paint.setColor(colorP2);
        float p2X = positionToCol(p2Position) * cellSize + cellSize/2;
        float p2Y = positionToRow(p2Position) * cellSize + (cellSize * 0.66f);
        canvas.drawCircle(p2X, p2Y, playerSize, paint);
    }

    private int positionToRow(int position) {
        return position / board.getSize();
    }

    private int positionToCol(int position) {
        return position % board.getSize();
    }

    public int getColorP1() {
        return colorP1;
    }

    public void setColorP1(int colorP1) {
        this.colorP1 = colorP1;
    }

    public int getColorP2() {
        return colorP2;
    }

    public void setColorP2(int colorP2) {
        this.colorP2 = colorP2;
    }

    public void setBoard(Board board){
        this.board = board;
    }
}
