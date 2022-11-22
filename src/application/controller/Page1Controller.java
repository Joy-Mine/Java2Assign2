package application.controller;

import application.MyClient;
import application.MyServer;
import application.Player;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

//import static application.MyClient.login;

public class Page1Controller implements Initializable{
//    SimpleStringProperty usernameProperty=new SimpleStringProperty();
//    SimpleStringProperty passwordProperty=new SimpleStringProperty();
//    SimpleStringProperty command=new SimpleStringProperty();

    Socket s;
    InputStream inputStream;
    OutputStream outputStream;
    Scanner in;
    PrintWriter out;
    int sceneNum=1;

    @FXML
    private Button SignUp;

    @FXML
    private Button LogIn;

    @FXML
    void LogIn(ActionEvent event) {
        Stage loginStage=new Stage();
        loginStage.setResizable(false);
        loginStage.setHeight(300);
        loginStage.setWidth(500);
        loginStage.initModality(Modality.APPLICATION_MODAL);
        loginStage.initStyle(StageStyle.UTILITY);
        loginStage.setTitle("Log In");

        Label label1=new Label("Username");
        label1.setLayoutX(80);
        label1.setLayoutY(50);
        Label label2=new Label("Password");
        label2.setLayoutX(80);
        label2.setLayoutY(100);
        TextField textField1=new TextField();
        textField1.setLayoutX(200);
        textField1.setLayoutY(50);
        TextField textField2=new TextField();
        textField2.setLayoutX(200);
        textField2.setLayoutY(100);
        Button ok=new Button("Log In");
        ok.setLayoutX(150);
        ok.setLayoutY(200);

//        usernameProperty.bind(textField1.textProperty());
//        passwordProperty.bind(textField2.textProperty());
        ok.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String userName=textField1.getText();
                String password=textField2.getText();
                System.out.println(userName+password);
                out.println("Login "+userName+" "+password);
                out.flush();
                String ans=in.next();
                System.out.println(ans);
                if("fail".equals(ans)){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.titleProperty().set("Error");
                    alert.headerTextProperty().set("The username or password is incorrect.");
                    alert.showAndWait();
                }else {
                    System.out.println("登录成功!");
                    //todo: 换场景！！
                    sceneNum=2;
                }
                loginStage.close();
            }
        });

        AnchorPane root=new AnchorPane();
        root.getChildren().addAll(ok,label1,label2,textField1,textField2);
        Scene scene=new Scene(root);
        loginStage.setScene(scene);
        loginStage.show();
    }

    @FXML
    void SignUp(ActionEvent event) {
        Stage signupStage=new Stage();
        signupStage.setResizable(false);
        signupStage.setHeight(300);
        signupStage.setWidth(500);
        signupStage.initStyle(StageStyle.UTILITY);
        signupStage.initModality(Modality.APPLICATION_MODAL);
        signupStage.setTitle("Sign Up");
        signupStage.show();

        Label label1=new Label("Username");
        label1.setLayoutX(80);
        label1.setLayoutY(50);
        Label label2=new Label("Password");
        label2.setLayoutX(80);
        label2.setLayoutY(100);
        TextField textField1=new TextField();
        textField1.setLayoutX(200);
        textField1.setLayoutY(50);
        TextField textField2=new TextField();
        textField2.setLayoutX(200);
        textField2.setLayoutY(100);
        Button ok=new Button("Sign Up");
        ok.setLayoutX(150);
        ok.setLayoutY(200);

        ok.setOnAction(event1 -> {
            String userName=textField1.getText();
            String password=textField2.getText();
            System.out.println(userName+password);
            out.println("Signup "+userName+" "+password);
            out.flush();
            String ans=in.next();
            System.out.println(ans);
            if("fail".equals(ans)){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.titleProperty().set("Error");
                alert.headerTextProperty().set("This username already exists.");
                alert.showAndWait();
            }else {
                System.out.println("注册成功!");
                //todo: 换场景！！
                sceneNum=2;
            }
            signupStage.close();
        });

        AnchorPane root=new AnchorPane();
        root.getChildren().addAll(ok,label1,label2,textField1,textField2);
        Scene scene=new Scene(root);
        signupStage.setScene(scene);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{
            s=new Socket("localhost",8886);
            inputStream=s.getInputStream();
            outputStream=s.getOutputStream();
            in=new Scanner(inputStream);
            out=new PrintWriter(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int changeScene(){
        return sceneNum;
    }
}