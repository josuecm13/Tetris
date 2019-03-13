package com.canales.josue.curso.tetris.gameboard.logic.board;

public class TetrisController {

    private Tetris tetris;


    public TetrisController(int r, int c){
        tetris = new Tetris(r,c);
    }

    public void updateState(){
        if(!tetris.canPlay){
            return;
        }
        if(tetris.canDelete){
            tetris.deleteCompletedLastRow();
            return;
        }
        if(tetris.bottomTouched){
            tetris.checkCompletedRows();
            return;
        }
        if(tetris.getCurrentShape() == null){
            tetris.placeShape();
        }
    }

    public void move(Tetris.Movement direction){
        tetris.moveCurrentShape(direction);
    }

    public Tetris getTetris() {
        return tetris;
    }

    public void start(){
        tetris.placeShape();
    }

    public void rotateShape(){
        tetris.getCurrentShape().rotate(tetris.getStringBoard());
    }

}
