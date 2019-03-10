package com.canales.josue.curso.tetris.gameboard.logic.shapes;

import java.util.Random;

public class ShapeFactory {

    public static Shape getInstance(){
        Random random = new Random();
        int option = random.nextInt(Shape.ShapeTypeID.values().length);
        return new Shape(Shape.ShapeTypeID.values()[option]);
    }

    public static int getCount(){
        return Shape.ShapeTypeID.values().length;
    }

}
