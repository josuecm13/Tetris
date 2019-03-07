package com.canales.josue.curso.tetris;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.canales.josue.curso.tetris.Classes.TetrisAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        generateTetris(20,20);

    }

    private void generateTetris( int rowCount, int columnCount){

        GridLayout tetrisGridLayout = (GridLayout) findViewById(R.id.mytetrisboard);

        //adapter del tetris TetrisAdapter adapter = new TetrisAdapter(this,new int[] {R.drawable.gray,})


        tetrisGridLayout.setColumnCount(columnCount);
        tetrisGridLayout.setRowCount(rowCount);
        for (int i = 0; i < rowCount; i++){
            for (int j = 0; j < columnCount; j++ ){
                ImageView child = new ImageView(this);
                child.setLayoutParams(new ViewGroup.LayoutParams(42,42));
                if (i == 0 || j == 0 || i + 1 == rowCount || j+1 == columnCount ){
                    child.setImageResource(R.drawable.gray);
                }else {
                    child.setImageResource(R.drawable.black);
                }
                child.setMaxWidth((int) (tetrisGridLayout.getWidth()/columnCount));
                child.setMaxHeight((int) (tetrisGridLayout.getHeight()/rowCount));
                tetrisGridLayout.addView(child, i*rowCount + j);
            }
        }
    }



}
