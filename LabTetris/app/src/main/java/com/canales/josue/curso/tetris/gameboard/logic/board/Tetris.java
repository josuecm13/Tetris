package com.canales.josue.curso.tetris.gameboard.logic.board;

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
    private boolean bottomTouched;

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

    public void start(){
        placeShape();
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
        if(currentShape.canBe(stringBoard,0,center)){
            currentShape.setCoordinates(0, center);
            int [][] coordinates = currentShape.getCoordinates();
            for (int[] coordinate: coordinates) {
                shapeBoard[coordinate[0]][coordinate[1]] = currentShape;
                stringBoard[coordinate[0]][coordinate[1]] = currentShape.getID().toString();
            }
        }
        else{
            canPlay = false;
            bottomTouched = true;
        }
    }

    public void moveCurrentShape(Movement direction){
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

    private void move(int addedI, int addedJ){
        int possible_i = currentShape.getMinimum(currentShape.getCoordinates(),0) + addedI;
        int possible_j = currentShape.getMinimum(currentShape.getCoordinates(),1) + addedJ;
        if (currentShape.canBe(stringBoard,possible_i,possible_j)){
            currentShape.setCoordinates(possible_i,possible_j);
        }else{
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
    }

    public void checkCompletedRows(){
        boolean completedRow = true;
        completedRows = new ArrayList<>();
        for (int i = this.height; i < stringBoard.length; i++) {
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
