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

    /*
       TODO: Corregir el desface de la matriz, actualizar matriz de string, etc...
    */

    Tetris tetris;
    GridLayout tetrisGridLayout;
    Handler handler;
    int[][] prevCoordinates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tetris = new Tetris(19,19);
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

    private void updateTetrisView(){
        Log.i("Board",printMatrix(tetris.getStringBoard()));
        if(tetris.canPlay){
            if (prevCoordinates != null){
                for(int[] coord: prevCoordinates){
                    tetrisGridLayout.getChildAt(19*(coord[0]+1)+ (coord[1]+1))
                            .getBackground()
                            .setColorFilter(Color.rgb(0,0,0), PorterDuff.Mode.SRC);
                }
            }
            prevCoordinates = tetris.getCurrentShape().getCoordinates();
            for(int[] coord: prevCoordinates){
                tetrisGridLayout.getChildAt(19*(coord[0]+1)+ (coord[1]+1))
                        .getBackground()
                        .setColorFilter(tetris.getCurrentShape().getID().getColor(), PorterDuff.Mode.SRC);
            }if(tetris.height != Integer.MAX_VALUE){
                for (int i = tetris.height + 1; i < 20; i++) {
                    for (int j = 1; j < 20 ; j++) {
                        if(tetris.getStringBoard()[i - 1][j - 1].equals("")){
                            tetrisGridLayout.getChildAt(19*i+j)
                                    .getBackground()
                                    .setColorFilter(Color.rgb(0,0,0), PorterDuff.Mode.SRC);
                        }else{
                            tetrisGridLayout.getChildAt(19*i+j)
                                    .getBackground()
                                    .setColorFilter(Shape.
                                            ShapeTypeID.valueOf(tetris.getStringBoard()[i-1][j-1] + "TYPE")
                                            .getColor(), PorterDuff.Mode.SRC);
                        }
                    }
                }
            }
        }
    }

    private String printMatrix(String[][] matrix){
        StringBuilder newString= new StringBuilder();
        for (String[] aMatrix : matrix) {
            for (int j = 0; j < matrix[0].length; j++) {
                newString.append(aMatrix[j]).append("\t");
            }
            newString.append("\n");
        }
        return newString.toString();
    }

    public void rotate(View view){
        tetris.getCurrentShape().rotate();
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
