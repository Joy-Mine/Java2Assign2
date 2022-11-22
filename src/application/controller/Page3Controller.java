package application.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

import static application.Game.*;
//棋盘，显示对手
public class Page3Controller implements Initializable {
//    private static final int PLAY_1 = 1;
//    private static final int PLAY_2 = 2;
//    private static final int EMPTY = 0;
//    private static final int BOUND = 90;
//    private static final int OFFSET = 15;
//
//    private static boolean TURN = false;
//
//    private static final int[][] chessBoard = new int[3][3];
//    private static final boolean[][] flag = new boolean[3][3];

    static boolean gameOver=false;



    @FXML
    private Pane base_square;

    @FXML
    private Rectangle game_panel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        System.out.println(location);
//        System.out.println(resources);
        game_panel.setOnMouseClicked(event -> {
            int x = (int) (event.getX() / BOUND);
            int y = (int) (event.getY() / BOUND);
//            System.out.println(x+" "+y);
            if (refreshBoard(x, y)) {
                if(gameOver){
                    //todo: gameover
                }
                TURN = !TURN;
            }
        });
    }

    private void switchScene(){

    }

    private boolean refreshBoard (int x, int y) {
        if (chessBoard[x][y] == EMPTY) {
            int tmp=chessBoard[x][y] = TURN ? PLAY_1 : PLAY_2;

            if(x==1 && y==1){
                if(chessBoard[x+1][y-1]==tmp && chessBoard[x-1][y+1]==tmp)
                    gameOver=true;
                else if(chessBoard[x+1][y+1]==tmp && chessBoard[x-1][y-1]==tmp)
                    gameOver=true;
                else if(chessBoard[x][y+1]==tmp && chessBoard[x][y-1]==tmp)
                    gameOver=true;
                else if(chessBoard[x+1][y]==tmp && chessBoard[x-1][y]==tmp)
                    gameOver=true;
            }
            else {
                //todo: gameover
            }

            drawChess();
            return true;
        }
        return false;
    }

    private void drawChess () {
        for (int i = 0; i < chessBoard.length; i++) {
            for (int j = 0; j < chessBoard[0].length; j++) {
                if (flag[i][j]) {
                    // This square has been drawing, ignore.
                    continue;
                }
                switch (chessBoard[i][j]) {
                    case PLAY_1:
                        drawCircle(i, j);
                        break;
                    case PLAY_2:
                        drawLine(i, j);
                        break;
                    case EMPTY:
                        // do nothing
                        break;
                    default:
                        System.err.println("Invalid value!");
                }
            }
        }
    }

    private void drawCircle (int i, int j) {
        Circle circle = new Circle();
        base_square.getChildren().add(circle);
        circle.setCenterX(i * BOUND + BOUND / 2.0 + OFFSET);
        circle.setCenterY(j * BOUND + BOUND / 2.0 + OFFSET);
        circle.setRadius(BOUND / 2.0 - OFFSET / 2.0);
        circle.setStroke(Color.RED);
        circle.setFill(Color.TRANSPARENT);
        flag[i][j] = true;
    }

    private void drawLine (int i, int j) {
        Line line_a = new Line();
        Line line_b = new Line();
        base_square.getChildren().add(line_a);
        base_square.getChildren().add(line_b);
        line_a.setStartX(i * BOUND + OFFSET * 1.5);
        line_a.setStartY(j * BOUND + OFFSET * 1.5);
        line_a.setEndX((i + 1) * BOUND + OFFSET * 0.5);
        line_a.setEndY((j + 1) * BOUND + OFFSET * 0.5);
        line_a.setStroke(Color.BLUE);

        line_b.setStartX((i + 1) * BOUND + OFFSET * 0.5);
        line_b.setStartY(j * BOUND + OFFSET * 1.5);
        line_b.setEndX(i * BOUND + OFFSET * 1.5);
        line_b.setEndY((j + 1) * BOUND + OFFSET * 0.5);
        line_b.setStroke(Color.BLUE);
        flag[i][j] = true;
    }
}
