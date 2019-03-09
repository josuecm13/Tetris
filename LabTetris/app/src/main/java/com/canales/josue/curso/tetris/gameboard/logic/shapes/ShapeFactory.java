package com.canales.josue.curso.tetris.gameboard.logic.shapes;

import java.util.Random;

public class ShapeFactory {

    public static Shape getInstance(){
        Random random = new Random();
        int option = random.nextInt();
        switch (option){
            case 0:
                return new Stype();
            case 1:
                return new Ztype();
            case 2:
                return new Ltype();
            case 3:
                return new Otype();
            case 4:
                return new Jtype();
            case 5:
                return new Itype();
            default:
                return new Ttype();
        }
    }

    public static int getCount(){
        return 7;
    }

}
