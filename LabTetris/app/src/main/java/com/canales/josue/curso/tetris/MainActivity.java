package com.canales.josue.curso.tetris;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.canales.josue.curso.tetris.Classes.TetrisAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    private void generateTetris(int rowCount, int columnCount){
        GridLayout tetrisGridLayout = findViewById(R.id.TetrisGridLayout);


        //adapter del tetris TetrisAdapter adapter = new TetrisAdapter(this,new int[] {R.drawable.gray,})


        tetrisGridLayout.setColumnCount(columnCount);
        tetrisGridLayout.setRowCount(rowCount);
        for (int i = 0; i < rowCount; i++){
            for (int j = 0; j < columnCount; columnCount++ ){
                ImageView child = (ImageView) tetrisGridLayout.getChildAt(i*rowCount + columnCount);
                child.setImageResource(R.drawable.gray);
                child.setMaxWidth((int) (tetrisGridLayout.getWidth()/columnCount));
                child.setMaxHeight((int) (tetrisGridLayout.getHeight()/rowCount));
            }
        }
    }

}
