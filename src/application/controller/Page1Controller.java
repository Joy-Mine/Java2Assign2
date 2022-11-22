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
import java.net.URL;
import java.util.ResourceBundle;

//import static application.MyClient.login;

public class Page1Controller implements Initializable{
    SimpleStringProperty usernameProperty=new SimpleStringProperty();
    SimpleStringProperty passwordProperty=new SimpleStringProperty();
    SimpleStringProperty command=new SimpleStringProperty();

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

        usernameProperty.bind(textField1.textProperty());
        passwordProperty.bind(textField2.textProperty());
        ok.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
//                System.out.println(userName+password);
                System.out.println(usernameProperty.get()+passwordProperty.get());
                command.set("Login "+usernameProperty.get()+" "+passwordProperty);
                getCommand();
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
            boolean success=true;
                for(int i=0;i<MyServer.playerList.size();++i)
                    if(userName==MyServer.playerList.get(i).username) {
                        success = false;
                        //注册成功
                        MyServer.playerList.add(new Player(userName,password,0,0));
                        System.out.println("注册成功");
                        //todo:继续
                    }

            if (!success){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.titleProperty().set("Error");
                alert.headerTextProperty().set("This username has been registered.");
                alert.showAndWait();
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

    }

    public SimpleStringProperty getCommand(){
        return command;
    }
    public boolean executeCommand(){
        return false;
    }
//
//    public String LoginPage(){
//    }
}