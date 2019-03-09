package com.canales.josue.curso.tetris.gameboard.logic.shapes;

public class Stype extends Shape{

    ShapeTypeID ID = Shape.ShapeTypeID.STYPE;

    public Stype(){
        super();
    }

    @Override
    public String toString() {
        return "S";
    }

}
