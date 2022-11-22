package application;


public class Game{
    public static final int PLAY_1 = 1;
    public static final int PLAY_2 = 2;
    public static final int EMPTY = 0;
    public static final int BOUND = 90;
    public static final int OFFSET = 15;

    public static boolean TURN = false;

    public static final int[][] chessBoard = new int[3][3];
    public static final boolean[][] flag = new boolean[3][3];


    public String player1,player2;
}
