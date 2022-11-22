package application;

import application.controller.Page1Controller;
import application.controller.Page2Controller;
import application.controller.Page3Controller;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Scanner;


public class MyClient extends Application {
            Stage secondStage=new Stage();
//    static Socket s;
//    static InputStream inputStream;
//    static OutputStream outputStream;
//    static Scanner in;
//    static PrintWriter out;

//    public static Stage secondStage=new Stage();
//    public static Scene scene1,scene2,scene3;
//    static {
//        try {
//            scene1=switchScene("page1.fxml");
//            scene2=switchScene("page2.fxml");
//            scene3=switchScene("page3.fxml");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public Scene switchScene(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
//        fxmlLoader.setLocation(MyClient.class.getClassLoader().getResource(fxml));
        fxmlLoader.setLocation(getClass().getClassLoader().getResource(fxml));
        Pane root = fxmlLoader.load();
        return new Scene(root);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            int currentScene=0;
//            FXMLLoader fxmlLoader = new FXMLLoader();
//            fxmlLoader.setLocation(getClass().getClassLoader().getResource("Page1.fxml"));
//            Pane root = fxmlLoader.load();
//            primaryStage.setTitle("Tic Tac Toe");
//            primaryStage.setScene(new Scene(root));
//            primaryStage.setResizable(false);
//            primaryStage.show();

//            Stage secondStage=new Stage();
//.......................................
//            FXMLLoader fxmlLoader1=new FXMLLoader();
//            fxmlLoader1.setLocation(getClass().getClassLoader().getResource("page1.fxml"));
//            Pane root1=fxmlLoader1.load();
//            Scene scene1=new Scene(root1);
//            Page1Controller controller1=fxmlLoader1.getController();
//
//            FXMLLoader fxmlLoader2=new FXMLLoader();
//            fxmlLoader2.setLocation(getClass().getClassLoader().getResource("page2.fxml"));
//            Pane root2=fxmlLoader2.load();
//            Scene scene2=new Scene(root2);
//            Page2Controller controller2=fxmlLoader2.getController();
//
//            FXMLLoader fxmlLoader3=new FXMLLoader();
//            fxmlLoader3.setLocation(getClass().getClassLoader().getResource("page3.fxml"));
//            Pane root3=fxmlLoader3.load();
//            Scene scene3=new Scene(root3);
//            Page3Controller controller3=fxmlLoader3.getController();
//
//            secondStage.setTitle("Tic Tac Toe");
//            secondStage.setScene(scene1);
//            currentScene=1;
//            secondStage.setResizable(false);
//            secondStage.show();
//
//
//            Socket s=new Socket("localhost",8886);
//            InputStream inputStream=s.getInputStream();
//            OutputStream outputStream=s.getOutputStream();
//            Scanner in=new Scanner(inputStream);
//            PrintWriter out=new PrintWriter(outputStream);
//.......................................
//            SimpleStringProperty commandProperty=new SimpleStringProperty();
//            while (true){
////                while (controller1.)
//                Thread.sleep(1000);
//                if(currentScene==1){
//                    commandProperty.bind(controller1.getCommand());
//                    out.println(commandProperty.get());
//                }
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
//        try {
            Socket s=new Socket("localhost",8886);
            InputStream inputStream=s.getInputStream();
            OutputStream outputStream=s.getOutputStream();
            Scanner in=new Scanner(inputStream);
            PrintWriter out=new PrintWriter(outputStream);

            launch(args);
//            List<Node> nodeList1=scene1.getRoot().getChildrenUnmodifiable();

        SimpleStringProperty commandProperty=new SimpleStringProperty();


            s.close();
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }
//    public static boolean login(String userName,String password){
//        boolean success=false;
//        out.println("Login "+userName+" "+password);
//        String ans=in.next();
//        System.out.println("ans: "+ans);
//        if(ans=="succeed"){
//            System.out.println("登录成功");
//            secondStage.setScene(scene2);
//        }else {
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.titleProperty().set("Error");
//            alert.headerTextProperty().set("The user name or password is incorrect");
//            alert.showAndWait();
//            System.out.println("登录失败");
//        }
//
//        if(success)
//            return true;
//
//        return false;
//    }
}
