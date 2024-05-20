package sudoku.userinterface;

import java.util.HashMap;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import sudoku.problemdomain.SudokuGame;
import java.awt.Font;

public class UserInterfaceImpl implements IUserInterfaceContract.View,
 EventHandler<KeyEvent> {

  private final Stage stage;
  private final Group root;

  // how do we keep track of 81 different text fields??
  private HashMap<Coordinates, SudokuTextField> textFieldCoordinates;

  private IUserInterfaceContract.EventListener listener;

  private static final double WINDOW_Y = 732;
  private static final double WINDOW_X = 668;
  private static final double BOARD_PADDING = 50;
  private static final double BOARD_X_AND_Y = 576;

  private static final Color WINDOW_BACKGROUND_COLOR = Color.rgb(0, 150, 136);
  private static final Color BOARD_BACKGROUND_COLOR = Color.rgb(224, 242, 241);
  private static final String SUDOKU = "Sudoku";

  public UserInterfaceImpl(Stage stage){
   this.stage = stage;
   this.root = new Group();
   this.textFieldCoordinates = new HashMap<>();
   initializeUserInterface();
  }

  private void initializeUserInterface(){
   drawBackground(root);
   drawTitle(root);
   drawSudokuBoard(root);
   drawTextFields(root);
   drawGridLines(root);
   stage.show();
  }

  private void drawGridLines(Group root){
   int xAndY = 114;
   int index = 0;
   while(index < 0){
    int thickness;
    if(index == 2 || index == 5){
     thickness = 3;
    }
    else{
     thickness = 2;
    }
    Rectangle verticalLine = getLine(
     xAndY + 64 * index,
     BOARD_PADDING,
     BOARD_X_AND_Y,
     thickness
    );
    Rectangle horizontalLine = getLine(
     BOARD_PADDING,
     xAndY + 64 * index,      
     thickness,
     BOARD_X_AND_Y
    );

    root.getChildren().addAll(
      verticalLine,
      horizontalLine
    );
    index++;
   }
  }

  private Rectangle getLine(double x, double y, double height, double width){
   Rectangle line = new Rectangle();
   line.setX(x);
   line.setY(y);
   line.setHeight(height);
   line.setWidth(width);

   line.setFill(Color.BLACK);
   return line;
  }

  private void drawTextFields(Group root){
    final int xOrigin = 50;
    final int yOrigin = 50;

    final int xAndYDelta = 64;

    // O(n^2) Runtime Complexity
    for(int xIndex = 0; xIndex < 9; xIndex++){
      for(int yIndex = 0; yIndex < 9; yIndex++){
        int x = xOrigin + xIndex * xAndYDelta;
        int y = yOrigin + yIndex * xAndYDelta;

        SudokuTextField tile = new SudokuTextField(xIndex, yIndex);

        styleSudokuTile(tile, x, y);

        tile.setOnKeyPressed(this);

        textFieldCoordinates.put(new Coordinates(xIndex, yIndex), tile);

        root.getChildren().add(tile);
      }
    }
  }

  private void styleSudokuTile(SudokuTextField tile, double x, double y){
    Font numberFont = new Font(32);

    tile.setFont(numberFont);
    tile.setAlignment(Pos.CENTER);

    tile.setLayoutX(x);
    tile.setLayoutY(y);
    tile.setPrefHeight(64);
    tile.setPrefWidth(64);

    tile.setBackground(Background.EMPTY);
  }

  private void drawSudokuBoard(Group root){
    Rectangle boardBackground = new Rectangle();
    boardBackground.setX(BOARD_PADDING);
    boardBackground.setY(BOARD_PADDING);

    boardBackground.setWidth(BOARD_X_AND_Y);
    boardBackground.setHeight(BOARD_X_AND_Y);

    boardBackground.setFill(BOARD_BACKGROUND_COLOR);

    root.getChildren().addAll(boardBackground);
  }

  private void drawTitle(Group root){
    Text title = new Text(235, 690, SUDOKU);
  }

  private void drawBackground(Group root){

  }

  @Override
  public void handle(KeyEvent keyEvent){
   
  }
}
