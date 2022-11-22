package application;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class MyServer {
    public static ArrayList<Player> playerList=new ArrayList<>();
    static Socket s;
    static InputStream inputStream;
    static OutputStream outputStream;
    static Scanner in;
    static PrintWriter out;

    public static void main(String[] args) throws IOException {
        begin();
        new BeforeExit();
        ServerSocket serverSocket=new ServerSocket(8886);
        System.out.println("已存档用户数："+playerList.size());

        System.out.println("Waiting for the players...");
        while (true){
            s=serverSocket.accept();
            System.out.println("Connected to a new player");

            inputStream=s.getInputStream();
            outputStream=s.getOutputStream();
            in=new Scanner(inputStream);
            out=new PrintWriter(outputStream);

            doSomeService();

        }

    }
    public static void doSomeService(){
        while (true){
            if(!in.hasNext())
                break;
            String command=in.next();
            executeCommand(command);
        }
    }
    public static void executeCommand(String command){
        System.out.println("Received command: "+command);
        if(command=="Login"){
            String username=in.next();
            String password=in.next();
            System.out.println(command+username+password);
            boolean succeed=false;
            for(int i=0;i<playerList.size();++i)
                if(username==playerList.get(i).username && password==playerList.get(i).password){
                    out.println("succeed");
                    succeed=true;
                    break;
                }
            if(!succeed)
                out.println("fail");
        }else if (command=="Signup"){

        }
    }
    static void begin() throws IOException {
        BufferedReader reader=new BufferedReader(new FileReader("resources//Players.txt"));
        String line;
        while ((line=reader.readLine())!=null){
            String[] a=line.split(" ");
            playerList.add(new Player(a[0],a[1],Integer.parseInt(a[2]),Integer.parseInt(a[3])));
        }
    }
    static void end() throws IOException {
        BufferedWriter writer=new BufferedWriter(new FileWriter("resources/Players.txt"));
        String line;
        for(int i=0;i<playerList.size();++i){
            line=playerList.get(i).username+" "
                    +playerList.get(i).password+" "
                    +playerList.get(i).totalGame+" "
                    +playerList.get(i).wonGame;
            writer.write(line);
        }
        writer.close();
    }
}
class BeforeExit{
    BeforeExit(){
        Thread t=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("正在善后...");
                    MyServer.end();
                    System.out.println("善后结束");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        Runtime.getRuntime().addShutdownHook(t);
    }
}