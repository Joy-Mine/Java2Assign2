package application;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class LoginService implements Runnable{
    private Socket s;
    private Scanner in;
    private PrintWriter out;

    public LoginService(Socket aSocket){
        this.s=aSocket;

    }

    @Override
    public void run() {

        InputStream inputStream= null;
        try {
            inputStream = s.getInputStream();
            OutputStream outputStream=s.getOutputStream();
            in=new Scanner(inputStream);
            out=new PrintWriter(outputStream);
            doSomeService();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //针对未匹配的玩家
    public void doSomeService(){
        while (true){
            if(!in.hasNext())
                break;
            String command=in.next();
            if(executeCommand(command)){ //登录成功
//                int id1=MyServer.onlineList.get(0),id2=MyServer.onlineList.get(1);
                MyService aService=new MyService(MyServer.socketList.get(0),MyServer.socketList.get(1));
                Thread t2=new Thread(aService);
                t2.start();
            }
        }
    }
    public boolean executeCommand(String command){
        boolean succeed=true;
        System.out.println("Received command: "+command);
        if("Login".equals(command)){
            String username=in.next();
            String password=in.next();
            System.out.println(command+username+password);
            succeed=false;
            for(int i=0;i<MyServer.playerList.size();++i)
                if(username.equals(MyServer.playerList.get(i).username) && password.equals(MyServer.playerList.get(i).password)){
                    out.println("succeed "+MyServer.playerList.get(i).totalGame+" "+MyServer.playerList.get(i).wonGame);
                    MyServer.onlineList.add(i);
                    MyServer.socketList.add(s);
                    succeed=true;
                    break;
                }
            if(!succeed)
                out.println("fail");
        }else if ("Signup".equals(command)){
            String username=in.next();
            String password=in.next();
            System.out.println(command+username+password);
            succeed=true;
            for(int i=0;i<MyServer.playerList.size();++i)
                if(username.equals(MyServer.playerList.get(i).username)){
                    out.println("fail");
                    succeed=false;
                    break;
                }
            if(succeed){
                out.println("succeed");
                MyServer.playerList.add(new Player(username,password,0,0));
                MyServer.onlineList.add(MyServer.playerList.size());
                MyServer.socketList.add(s);
            }
        }
        out.flush();
        if(succeed)
            return true;
        else return false;
    }
}
