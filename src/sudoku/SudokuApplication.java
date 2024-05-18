package sudoku;

import java.io.IOException;

import javafx.application.application;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class SudokuApplication extends Application{
 private IUserInterfaceContract.View uiImpl;
 
 @Override
 public void start(Stage primaryStage) throws Exception {
  uiImpl = new UserInterfaceImpl(primaryStage);
  try{
   SudokuBuildLogic.build(uiImpl);
  }
  catch(IOException e){
   e.printStackTrace();
   throw e;
  }
 }

 public static void main(String[] args){
  launch(args);
 }
}
