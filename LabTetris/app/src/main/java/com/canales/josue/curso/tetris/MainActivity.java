package com.canales.josue.curso.tetris;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.canales.josue.curso.tetris.gameboard.logic.board.Tetris;
import com.canales.josue.curso.tetris.gameboard.logic.board.TetrisController;
import com.canales.josue.curso.tetris.gameboard.logic.shapes.Shape;


/*
    TODO: FUNCION QUE BUSCA EL TOPE DEL TETRIS
*/


public class MainActivity extends AppCompatActivity {

    TetrisController tetrisController;
    GridLayout tetrisGridLayout;
    Handler handler;
    Button downbtn;
    Button leftbtn;
    Button rightbtn;
    private int rows, columns;
    float speedMultiplier;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rows = 20;
        columns = 20;

        speedMultiplier = 1;
        tetrisController = new TetrisController(rows-2,columns-2);
        generateTetris(rows,columns);
        tetrisController.start();
        updateTetrisView();

        downbtn = findViewById(R.id.downBtn);
        leftbtn = findViewById(R.id.leftBtn);
        rightbtn = findViewById(R.id.rightBtn);


        leftbtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        tetrisController.move(Tetris.Movement.LEFT);
                        updateTetrisView();
                        return true;
                    case MotionEvent.ACTION_UP:
                        return true;
                }
                return false;
            }
        });

        rightbtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        tetrisController.move(Tetris.Movement.RIGHT);
                        updateTetrisView();
                        return true;
                    case MotionEvent.ACTION_UP:
                        return true;
                }
                return false;
            }
        });



        downbtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        speedMultiplier = 0.1f;
                        return true;
                    case MotionEvent.ACTION_UP:
                        speedMultiplier = 1;
                        return true;
                }
                return false;
            }
        });

        handler = new Handler();
        Runnable run = new Runnable() {
            @Override
            public void run() {
                tetrisController.move(Tetris.Movement.DOWN);
                updateTetrisView();

                handler.postDelayed(this,(int)(500*speedMultiplier));
            }
        };

        handler.post(run);

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
        if(tetrisController.getTetris().getCurrentShape() == null){
            tetrisController.updateState();
        }else{
            for(int[] coord: tetrisController.getTetris().getCurrentShape().getCoordinates()){
                tetrisGridLayout.getChildAt(this.rows*(coord[0]+1)+ (coord[1]+1))
                        .getBackground()
                        .setColorFilter(tetrisController.getTetris().getCurrentShape().getID().getColor(), PorterDuff.Mode.SRC);
            }
        }

    }

    private void displayTetrisState(){
        int m = 0, n = 0;
        for (int i = 1; i < rows-1; i++, m++) {
            for (int j = 1; j < columns-1 ; j++, n++) {
                if(!tetrisController.getTetris().getStringBoard()[m][n].equals("")){
                    tetrisGridLayout.getChildAt(rows*i+j)
                            .getBackground()
                            .setColorFilter(Shape.
                                    ShapeTypeID.valueOf(tetrisController.getTetris().getStringBoard()[m][n] + "TYPE")
                                    .getColor(), PorterDuff.Mode.SRC);
                }
            }
            n = 0;
        }
    }

    private void updateTetrisView(){
        tetrisController.updateState();
        emptyTetrisLayout();
        displayCurrentShape();
        displayTetrisState();
    }

    public void rotate(View view){
        tetrisController.rotateShape();
        updateTetrisView();
    }

    public void moveRight(View view){
        tetrisController.move(Tetris.Movement.RIGHT);
        updateTetrisView();
    }

    public void moveLeft(View view){
        tetrisController.move(Tetris.Movement.LEFT);
        updateTetrisView();
    }

    public void moveDown(View view){
        tetrisController.move(Tetris.Movement.DOWN);
        updateTetrisView();
    }


}
