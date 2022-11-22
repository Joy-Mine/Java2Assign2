package application;

import application.Game;
import application.Player;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class MyService implements Runnable{
    ArrayList<Player> playerList=new ArrayList<>();
    ArrayList<Game> gameList=new ArrayList<>();
    private Scanner in;
    private PrintWriter out;
    Socket s;
    Game game;

    public MyService(Socket aSocket,Game aGame){
        s=aSocket;
        game=aGame;
    }

    @Override
    public void run() {
        try {
            try {
                in=new Scanner(s.getInputStream());
                out=new PrintWriter(s.getOutputStream());
                //todo: do the service
            } finally {
                s.close();
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

}
