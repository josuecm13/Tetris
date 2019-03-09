package com.canales.josue.curso.tetris.gameboard.logic.shapes;

public class Otype extends Shape{

    ShapeTypeID ID = Shape.ShapeTypeID.OTYPE;

    public Otype(){
        super();
    }


    @Override
    public String toString() {
        return "J";
    }
}
