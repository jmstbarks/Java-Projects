/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CheckerBoard;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author James
 */
public class Main extends Application {
    
     @Override
    public void start(Stage primaryStage) {
       
      CheckerBoard checkerBoard = new CheckerBoard(8, 8, 600, 600, Color.SKYBLUE,
                Color.DARKBLUE);
      
      //CheckerBoard checkerBoard = new CheckerBoard(8, 8, 600, 600);
      

        GridPane board = checkerBoard.build();
        board.setGridLinesVisible(true);
        
        primaryStage.setScene(new Scene(board, checkerBoard.getBoardWidth(), checkerBoard.getBoardHeight()));
        primaryStage.setTitle("Checkers"); 
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
