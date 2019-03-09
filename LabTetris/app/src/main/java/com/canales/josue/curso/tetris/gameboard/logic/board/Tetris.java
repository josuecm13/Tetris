package com.canales.josue.curso.tetris.gameboard.logic.board;

import com.canales.josue.curso.tetris.gameboard.logic.shapes.Shape;

public class Tetris {

    /*
    TODO: shapeTouched()
    TODO: verifyMovement()
    TODO: placeShape()
    TODO: disposeCompletedRows()
    TODO: movements [left, down, right]
    */

    Shape[][] shapeBoard;
    String[][] stringBoard;
    Shape genericShape;
    Shape currentShape;
    private final int COLUMNS;
    private final int ROWS;
    private final int j;
    private int height;

    public Tetris(int rows, int columns){
        this.COLUMNS = columns;
        this.ROWS = rows;
        this.j = (COLUMNS/2) - 2 > 0 ? (COLUMNS/2) - 2 : 0;
        this.height = 0;
        this.genericShape = new Shape();
        initBoards();
    }

    private void initBoards(){
        shapeBoard = new Shape[ROWS][COLUMNS];
        stringBoard = new String[ROWS][COLUMNS];
    }

    public String[][] getStringBoard() {
        return stringBoard;
    }

    public Shape getCurrentShape() {
        return currentShape;
    }
}
