package com.canales.josue.curso.tetris.gameboard.logic.board;

import com.canales.josue.curso.tetris.ShapeOverlapException;
import com.canales.josue.curso.tetris.gameboard.logic.shapes.Shape;
import com.canales.josue.curso.tetris.gameboard.logic.shapes.ShapeFactory;

import java.util.ArrayList;
import java.util.List;

public class Tetris {

    private Shape[][] shapeBoard;
    private String[][] stringBoard;
    private Shape genericShape;
    private Shape currentShape;
    private final int COLUMNS;
    private final int ROWS;
    private final int center;
    public int height;
    public boolean canPlay;
    public boolean canDelete;
    private List<Integer> completedRows;
    public boolean bottomTouched;

    public Tetris(int rows, int columns){
        this.COLUMNS = columns;
        this.ROWS = rows;
        this.center = (COLUMNS/2) - 2 > 0 ? (COLUMNS/2) - 2 : 0;
        this.height = Integer.MAX_VALUE;
        this.genericShape = new Shape();
        this.bottomTouched = false;
        this.canPlay = true;
        completedRows = new ArrayList<>();
        initBoards();
    }

    private void initBoards(){
        shapeBoard = new Shape[ROWS][COLUMNS];
        stringBoard = new String[ROWS][COLUMNS];
        for(String[] stringRow:stringBoard){
            for (int i = 0; i < stringRow.length; i++) {
                stringRow[i] = "";
            }
        }
    }

    public String[][] getStringBoard() {
        return stringBoard;
    }

    public Shape getCurrentShape() {
        return currentShape;
    }

    public void placeShape(){
        currentShape = ShapeFactory.getInstance();
        try {
            if(currentShape.canBe(stringBoard,0,center)){
                currentShape.setCoordinates(0, center);
                int [][] coordinates = currentShape.getCoordinates();
                for (int[] coordinate: coordinates) {
                    shapeBoard[coordinate[0]][coordinate[1]] = currentShape;
                    //stringBoard[coordinate[0]][coordinate[1]] = currentShape.getID().toString();
                }
            }
        } catch (ShapeOverlapException e) {
            canPlay = false;
            bottomTouched = true;
        }
    }

    public void moveCurrentShape(Movement direction){
        if(currentShape == null){
            placeShape();
        }
        else{
            switch (direction){
                case RIGHT:
                    move(0,1);
                    break;
                case LEFT:
                    move(0,-1);
                    break;
                case DOWN:
                    move(1,0);
                    break;
                default:
                    break;
            }
        }
    }

    private void move(int addedI, int addedJ){
        int possible_i = currentShape.getHead()[0] + addedI;
        int possible_j = currentShape.getHead()[1] + addedJ;
        int bottomPart = currentShape.getMaximum(currentShape.getCoordinates(),0) + 1;
        try {
            if (currentShape.canBe(stringBoard,possible_i,possible_j)){
                currentShape.setCoordinates(possible_i,possible_j);
            }else if(bottomPart == ROWS) {
                shapeTouched();
            }
        } catch (ShapeOverlapException e) {
            shapeTouched();
        }
    }

    private void shapeTouched(){
        bottomTouched = true;
        int[][] newShapeCoordinates = currentShape.getCoordinates();
        int shapeHeight = currentShape.getMinimum(newShapeCoordinates,0);
        this.height = shapeHeight < this.height? shapeHeight: height;
        for (int[] array: newShapeCoordinates ) {
            stringBoard[array[0]][array[1]] = currentShape.getID().toString();
            shapeBoard[array[0]][array[1]] = genericShape;
        }
        currentShape = null;
    }

    public void checkCompletedRows(){
        boolean completedRow = true;
        bottomTouched = false;
        completedRows = new ArrayList<>();
        for (int i = this.height; i < stringBoard.length; i++) {
            completedRow = true;
            for (int j = 0; j < stringBoard[0].length; j++) {
                if(stringBoard[i][j].equals("")){
                    completedRow = false;
                    break;
                }
            }
            if(completedRow)
                completedRows.add(i);
        }
    }

    public void deleteCompletedLastRow(){
        if (completedRows.isEmpty()){
            canDelete = false;
        }else {
            int limit = completedRows.get(completedRows.size()-1);
            for (int i = limit; i > ( height - 1 > 0 ? height - 1 : 0 ) ; i--) {
                stringBoard[i] = stringBoard[i-1];
                shapeBoard[i] = shapeBoard[i-1];
            }
            height++;
            completedRows.remove(0);
        }
    }

    public enum Movement{ RIGHT, LEFT, DOWN}


}
