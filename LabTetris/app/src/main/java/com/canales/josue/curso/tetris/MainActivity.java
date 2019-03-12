package com.canales.josue.curso.tetris;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.canales.josue.curso.tetris.gameboard.logic.board.Tetris;
import com.canales.josue.curso.tetris.gameboard.logic.shapes.Shape;


public class MainActivity extends AppCompatActivity {

    Tetris tetris;
    GridLayout tetrisGridLayout;
    Handler handler;
    private int rows, columns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rows = 20;
        columns = 20;

        tetris = new Tetris(18,18);
        generateTetris(20,20);

        tetris.start();
        updateTetrisView();

/*
        handler = new Handler();
        Runnable run = new Runnable() {
            @Override
            public void run() {
                tetris.moveCurrentShape(Tetris.Movement.DOWN);
                updateTetrisView();

                handler.postDelayed(this, 1000);
            }
        };

        handler.post(run);
*/

    }

    private void generateTetris( int rowCount, int columnCount){
        tetrisGridLayout = (GridLayout) findViewById(R.id.mytetrisboard);
        tetrisGridLayout.setColumnCount(columnCount);
        tetrisGridLayout.setRowCount(rowCount);
        for (int i = 0; i < rowCount; i++){
            for (int j = 0; j < columnCount; j++ ){
                View child = new ImageView(this);
                child.setLayoutParams(new ViewGroup.LayoutParams(54,54));
                child.setBackgroundResource(R.drawable.tags_rounded_corners);
                if (i == 0 || j == 0 || i + 1 == rowCount || j+1 == columnCount ){
                    child.getBackground().setColorFilter(Color.rgb(112,112,122), PorterDuff.Mode.SRC);
                }else {
                    child.getBackground().setColorFilter(Color.rgb(0,0,0), PorterDuff.Mode.SRC);
                }
                tetrisGridLayout.addView(child, i*rowCount + j);
            }
        }
    }

    private void emptyTetrisLayout(){
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns ; j++) {
                if (i == 0 || j == 0 || i + 1 == rows || j+1 == columns ){
                    tetrisGridLayout.getChildAt(i*rows +j).getBackground().setColorFilter(Color.rgb(112,112,122), PorterDuff.Mode.SRC);
                }else{
                    tetrisGridLayout.getChildAt(i*rows +j).getBackground().setColorFilter(Color.rgb(0,0,0), PorterDuff.Mode.SRC);
                }
            }
        }
    }

    private void displayCurrentShape(){
        for(int[] coord: tetris.getCurrentShape().getCoordinates()){
            tetrisGridLayout.getChildAt(20*(coord[0]+1)+ (coord[1]+1))
                    .getBackground()
                    .setColorFilter(tetris.getCurrentShape().getID().getColor(), PorterDuff.Mode.SRC);
        }
    }

    private void displayTetrisState(){
        int m = 0, n = 0;
        for (int i = 1; i < rows-1; i++, m++) {
            for (int j = 1; j < columns-1 ; j++, n++) {
                if(!tetris.getStringBoard()[m][n].equals("")){
                    tetrisGridLayout.getChildAt(rows*i+j)
                            .getBackground()
                            .setColorFilter(Shape.
                                    ShapeTypeID.valueOf(tetris.getStringBoard()[m][n] + "TYPE")
                                    .getColor(), PorterDuff.Mode.SRC);
                }
            }
            n = 0;
        }
    }

    private void updateTetrisView(){
        if(tetris.canPlay){
            emptyTetrisLayout();
            displayCurrentShape();
            displayTetrisState();
        }
    }

    public void rotate(View view){
        tetris.getCurrentShape().rotate(tetris.getStringBoard());
        updateTetrisView();
    }

    public void moveRight(View view){
        tetris.moveCurrentShape(Tetris.Movement.RIGHT);
        updateTetrisView();
    }

    public void moveLeft(View view){
        tetris.moveCurrentShape(Tetris.Movement.LEFT);
        updateTetrisView();
    }

    public void moveDown(View view){
        tetris.moveCurrentShape(Tetris.Movement.DOWN);
        updateTetrisView();
    }




}
