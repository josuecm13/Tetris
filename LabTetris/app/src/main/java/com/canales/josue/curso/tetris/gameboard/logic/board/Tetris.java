package com.canales.josue.curso.tetris.gameboard.logic.board;

import com.canales.josue.curso.tetris.gameboard.logic.shapes.Shape;

public class Tetris {

    Shape[][] shapeBoard;
    String[][] stringBoard;
    private final int COLUMNS;
    private final int ROWS;

    public Tetris(int rows, int columns){
        this.COLUMNS = columns;
        this.ROWS = rows;
        initBoards();
    }

    private void initBoards(){
        shapeBoard = new Shape[ROWS][COLUMNS];
        stringBoard = new String[ROWS][COLUMNS];
    }




}
