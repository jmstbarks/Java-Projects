/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CheckerBoard;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author James
 */
public class CheckerBoard{

    private final int numRows;
    private final int numCols;
    private final int boardWidth;
    private final int boardHeight;
    private final Color lightColor;
    private final Color darkColor;
    private CheckerBoard checkerBoard;

 
    
    public CheckerBoard(int numRows, int numCols, int boardWidth, int boardHeight) {
        this(numRows, numCols, boardWidth, boardHeight, Color.RED, Color.BLACK);

    }

    public CheckerBoard(int numRows, int numCols, int boardWidth, int boardHeight, Color lightColor, Color darkColor) {
        this.numRows = numRows;
        this.numCols = numCols;
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.lightColor = lightColor;
        this.darkColor = darkColor;
    }

    
   

   

    public GridPane build() {
        GridPane grid = new GridPane();

        
            for (int i = 0; i < numRows; i++) {
                grid.getRowConstraints().add(new RowConstraints(this.getSquareWidth()));

                for (int j = 0; j < numCols; j++) {

                    if((i+j) % 2 == 0 ){
                        grid.getColumnConstraints().add(new ColumnConstraints(getSquareHeight()));
                        grid.add(new Rectangle(getSquareHeight(), getSquareWidth(), getLightColor()), i, j);
                    }

                    else{
                         grid.getColumnConstraints().add(new ColumnConstraints(getSquareHeight()));
                         grid.add(new Rectangle(getSquareHeight(), getSquareWidth(), getDarkColor()), i, j);
                    }
           
                }
        }
        return grid;
    }

    public GridPane getBoard() {
        
        GridPane board = this.build();

        if(board == null){
            return null;
        }
      return board;
    }

    public int getNumRows() {
        return numRows;
    }

    public int getNumCols() {
        return numCols;
    }

    public int getBoardWidth() {
        return boardWidth;
    }

    public int getBoardHeight() {
        return boardHeight;
    }

    public Color getLightColor() {
        return lightColor;
    }

    public Color getDarkColor() {
        return darkColor;
    }
    
    public int getSquareWidth(){
        return (this.getBoardWidth()/this.getNumRows());
    }
    
    public int getSquareHeight(){
         return (this.getBoardHeight()/this.getNumCols());
    }

   

}
