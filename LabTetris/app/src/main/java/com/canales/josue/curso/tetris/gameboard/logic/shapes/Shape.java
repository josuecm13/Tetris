package com.canales.josue.curso.tetris.gameboard.logic.shapes;


import android.graphics.Color;
import android.util.Log;

import com.canales.josue.curso.tetris.ShapeOverlapException;

import java.util.Random;

public class Shape {

    private int[][][] orientations;
    private int[][] coordinates;
    private int currentOrientation;
    private ShapeTypeID ID;


    public Shape(){
        /*
        currentOrientation = new Random().nextInt(ID.getLength());
        coordinates = new int[ID.getLength()][2];
        orientations = ID.getOrientations();
        */
    }

    Shape(ShapeTypeID ID){
        this.ID = ID;
        Log.i("SHAPE:",ID.toString());
        orientations = ID.getOrientations();
        currentOrientation = new Random().nextInt(orientations.length);
        coordinates = new int[ID.getLength()][2];
    }

    public int getColor(){
        return ID.getColor();
    }

    public ShapeTypeID getID() {
        return ID;
    }

    public void rotate(String[][] board){
        try{
            if (canRotate(board, getMinimum(coordinates,0), getMinimum(coordinates,1))){
                currentOrientation = (currentOrientation + 1)%orientations.length;
                setCoordinates(getMinimum(coordinates,0),getMinimum(coordinates,1));
            }
        }catch (IndexOutOfBoundsException ignored){

        }

    }

    //
    private boolean canRotate(String[][] board, int i, int j) throws IndexOutOfBoundsException{
        int possibleOrientation = (currentOrientation + 1)%orientations.length;
        int head = getOrientationHead(possibleOrientation);
        //recorre desde el minimo en i y en j de las coordenadas
        for (int m = 0; m < orientations[possibleOrientation].length; m++) {
            for (int n = 0; n < orientations[possibleOrientation][0].length; n++) {
                if (orientations[possibleOrientation][m][n] == 1 && !board[i + n][j + m - head].equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean canBe(String[][] board, int i, int j) throws ShapeOverlapException{
        int head = getOrientationHead(currentOrientation);
        try{
            for (int m = 0; m < orientations[currentOrientation].length; m++) {
                for (int n = 0; n < orientations[currentOrientation][0].length; n++) {
                    if (orientations[currentOrientation][m][n] == 1 && !board[i + m][j + n - head].equals("")) {
                        throw new ShapeOverlapException();
                    }
                }
            }
        }catch (IndexOutOfBoundsException e){
            return false;
        }
        return true;
    }

    public void setCoordinates(int i, int j){
        int k = getOrientationHead(currentOrientation);
        int conti = 0;
        for (int m = 0; m < orientations[currentOrientation].length; m++) {
            for (int n = 0; n < orientations[currentOrientation][0].length; n++) {
                if (orientations[currentOrientation][m][n] == 1) {
                    coordinates[conti][0] = i + m;
                    coordinates[conti++][1] = j + n - k;
                }
            }
        }
    }

    private int getOrientationHead(int orientation){
        int k;
        for (k = 0; k < orientations[orientation][0].length ; k++) {
            if(orientations[orientation][0][k] == 1)
                break;
        }
        return k;
    }

    public int getMinimum(int[][] array, int index){
        int min = Integer.MAX_VALUE;
        for (int[] anArray : array) {
            if (anArray[index] < min)
                min = anArray[index];
        }
        return min;
    }

    public int getMaximum(int[][] array, int index){
        int min = Integer.MIN_VALUE;
        for (int[] anArray : array) {
            if (anArray[index] > min)
                min = anArray[index];
        }
        return min;
    }



    public int[] getHead(){
        return coordinates[0];
    }

    public int[][] getCoordinates() {
        return coordinates;
    }


    public enum ShapeTypeID {
        LTYPE(  new int[][][]{{{1, 0, 0}, {1, 0, 0}, {1, 1, 0}},
                {{1, 1, 1}, {1, 0, 0}, {0, 0, 0}},
                {{1, 1, 0}, {0, 1, 0}, {0, 1, 0}},
                {{0, 0, 1}, {1, 1, 1}, {0, 0, 0}}},
                4,Color.argb(255,255, 153, 187) ,new int[][]{{0,3},{0,2}}, "L"),

        JTYPE(   new int[][][]{{{0, 1, 0}, {0, 1, 0}, {1, 1, 0}},
                {{1, 0, 0}, {1, 1, 1}, {0, 0, 0}},
                {{1, 1, 0}, {1, 0, 0}, {1, 0, 0}},
                {{1, 1, 1}, {0, 0, 1}, {0, 0, 0}}},
                4, Color.argb(255,231, 200, 67) ,new int[][]{{0, 3}, {1, 3}},"J"),

        OTYPE(  new int[][][]{{{1, 1}, {1, 1}}},
                4,Color.argb(255,128, 140, 203),new int[][] {{0, 3}, {1, 2}},"O"),

        ITYPE(  new int[][][]{{{1, 0, 0, 0}, {1, 0, 0, 0}, {1, 0, 0, 0}, {1, 0, 0, 0}},
                {{1, 1, 1, 1},{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}}},
                4, Color.argb(255,214, 95, 92), new int[0][0], "I"),

        TTYPE(  new int[][][]{{{1, 1, 1}, {0, 1, 0}, {0, 0, 0}},
                {{0, 1, 0}, {1, 1, 0}, {0, 1, 0}},
                {{0, 1, 0}, {1, 1, 1}, {0, 0, 0}},
                {{1, 0, 0}, {1, 1, 0}, {1, 0, 0}}},
                4, Color.argb(255,77, 255, 136), new int[][]{}, "T"),

        STYPE(  new int[][][]{{ {0, 1, 1}, {1, 1, 0}, {0, 0, 0}},
                {{1, 0, 0},{1, 1, 0}, {0, 1, 0}}},
                4, Color.argb(255,51, 204, 204), new int[][]{{0,2}, {0,3}, {1,3}}, "S"),

        ZTYPE(  new int[][][]{{{1, 1, 0}, {0, 1, 1}, {0, 0, 0}},
                {{0, 1, 0}, {1, 1, 0}, {1, 0, 0}}},
                4, Color.argb(255,255, 69, 0), new int[][]{{0, 2}, {0, 3}, {1, 3}}, "Z");


        private final int[][][] orientations;
        private final int length;
        private final int color;
        private final int[][] valid_combination;
        private final String symbol;

        ShapeTypeID(int[][][] orientations, int length, int color, int[][] valid_combination,String symbol) {
            this.orientations = orientations;
            this.length = length;
            this.color = color;
            this.valid_combination = valid_combination;
            this.symbol = symbol;
        }

        public int getLength() {
            return length;
        }

        public int[][][] getOrientations() {
            return orientations;
        }

        public int getColor() {
            return color;
        }

        @Override
        public String toString() {
            return symbol;
        }
    }
}
