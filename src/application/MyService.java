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
    private Scanner in1,in2;
    private PrintWriter out1,out2;
    Socket s1,s2;
    Game game;

    public MyService(Socket a1,Socket a2){
        s1=a1;
        s2=a2;
    }

    @Override
    public void run() {
        try {
            in1=new Scanner(s1.getInputStream());
            out1=new PrintWriter(s1.getOutputStream());
            in2=new Scanner(s2.getInputStream());
            out2=new PrintWriter(s2.getOutputStream());
                //todo: do the service
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

}
