package com.canales.josue.curso.tetris.gameboard.logic.shapes;


import android.graphics.Color;

import java.util.Random;

public class Shape {

    private int[][][] orientations;
    private int[][] coordinates;
    private int currentOrientation;
    ShapeTypeID ID;


    public Shape(){
        currentOrientation = new Random().nextInt(ID.getLength());
        coordinates = new int[ID.getLength()][2];
        orientations = ID.getOrientations();
    }

    public int getColor(){
        return ID.getColor();
    }

    public ShapeTypeID getID() {
        return ID;
    }

    public String toString() {
        return null;
    }

    public void rotate(){
        currentOrientation = (currentOrientation + 1)%orientations.length;
        setCoordinates(coordinates[0][0],coordinates[0][1]);
    }

    public void setCoordinates(int i, int j){
        int k;
        for (k = 0; k < orientations[currentOrientation][0].length ; k++) {
            if(orientations[currentOrientation][0][k] == 1)
                break;
        }
        for (int m = 0; m < orientations[currentOrientation].length; m++) {
            for (int n = 0; n < orientations[currentOrientation][0].length; n++) {

            }
        }
    }


    public enum ShapeTypeID {
        LTYPE(  new int[][][]{{{1, 0, 0}, {1, 0, 0}, {1, 1, 0}},
                {{1, 1, 1}, {1, 0, 0}, {0, 0, 0}},
                {{1, 1, 0}, {0, 1, 0}, {0, 1, 0}},
                {{0, 0, 1}, {1, 1, 1}, {0, 0, 0}}},
                4,Color.argb(255,255, 153, 187) ,new int[][]{{0,3},{0,2}}),

        JTYPE(   new int[][][]{{{0, 1, 0}, {0, 1, 0}, {1, 1, 0}},
                {{1, 0, 0}, {1, 1, 1}, {0, 0, 0}},
                {{1, 1, 0}, {1, 0, 0}, {1, 0, 0}},
                {{1, 1, 1}, {0, 0, 1}, {0, 0, 0}}},
                4, Color.argb(255,231, 200, 67) ,new int[][]{{0, 3}, {1, 3}}),

        OTYPE(  new int[][][]{{{1, 1}, {1, 1}}},
                4,Color.argb(255,128, 140, 203),new int[][] {{0, 3}, {1, 2}}),

        ITYPE(  new int[][][]{{{1, 0, 0, 0}, {1, 0, 0, 0}, {1, 0, 0, 0}, {1, 0, 0, 0}},
                {{1, 1, 1, 1},{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}}},
                4, Color.argb(255,214, 95, 92), new int[0][0]),

        TTYPE(  new int[][][]{{{1, 1, 1}, {0, 1, 0}, {0, 0, 0}},
                {{0, 1, 0}, {1, 1, 0}, {0, 1, 0}},
                {{0, 1, 0}, {1, 1, 1}, {0, 0, 0}},
                {{1, 0, 0}, {1, 1, 0}, {1, 0, 0}}},
                4, Color.argb(255,77, 255, 136), new int[][]{}),

        STYPE(  new int[][][]{{ {0, 1, 1}, {1, 1, 0}, {0, 0, 0}},
                {{1, 0, 0},{1, 1, 0}, {0, 1, 0}}},
                4, Color.argb(255,51, 204, 204), new int[][]{{0,2}, {0,3}, {1,3}}),

        ZTYPE(  new int[][][]{{{1, 1, 0}, {0, 1, 1}, {0, 0, 0}},
                {{0, 1, 0}, {1, 1, 0}, {1, 0, 0}}},
                4, Color.argb(255,255, 69, 0), new int[][]{{0, 2}, {0, 3}, {1, 3}}),

        TWOTYPE(new int[][][]{{{1, 0}, {1, 0}},
                {{1, 1}, {0, 0}}},
                2, Color.argb(255,179, 102, 255),new int[0][0]);


        private final int[][][] orientations;
        private final int length;
        private final int color;
        private final int[][] valid_combination;

        ShapeTypeID(int[][][] orientations, int length, int color, int[][] valid_combination) {
            this.orientations = orientations;
            this.length = length;
            this.color = color;
            this.valid_combination = valid_combination;
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
    }
}
