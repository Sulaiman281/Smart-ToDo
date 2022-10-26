package edu.rmit.View;

import edu.rmit.CommonGUI;
import edu.rmit.controller.RegisterController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Register extends Pane {

    private final RegisterController controller;

    public Register(){
        new Scene(this);
        this.setPrefSize(550, 650);
        controller = new RegisterController();

        initialize();
    }

    void initialize(){
        ImageView imgView = new ImageView();
        imgView.setLayoutX(227);
        imgView.setLayoutY(32);
        imgView.setFitWidth(100);
        imgView.setFitHeight(100);
        this.getChildren().add(imgView);

        Label imgLabel = CommonGUI.getLabel("click to select profile", Font.font("Arial", 12), Color.GREY, 203, 139);

        controller.setImageChoseEvent(imgView, imgLabel);

        this.getChildren().add(imgLabel);

        this.getChildren().add(
                CommonGUI.getLabel("Username", Font.font("Arial", 14), Color.BLACK, 134, 198)
        );
        this.getChildren().add(
                CommonGUI.getTextField("username", Font.font("Arial", 14), controller.usernameProperty(),
                        134, 218, 280,30)
        );
        this.getChildren().add(
                CommonGUI.getLabel("First name", Font.font("Arial", 14), Color.BLACK, 134, 271)
        );
        this.getChildren().add(
                CommonGUI.getTextField("John", Font.font("Arial", 14), controller.first_nameProperty(),
                        134, 291, 280,30)
        );
        this.getChildren().add(
                CommonGUI.getLabel("Last name", Font.font("Arial", 14), Color.BLACK, 134, 344)
        );
        this.getChildren().add(
                CommonGUI.getTextField("snow", Font.font("Arial", 14), controller.last_nameProperty(),
                        134, 365, 280,30)
        );
        this.getChildren().add(
                CommonGUI.getLabel("Password", Font.font("Arial", 14), Color.BLACK, 134, 421)
        );
        this.getChildren().add(
                CommonGUI.getPassField("123", Font.font("Arial", 14), controller.passwordProperty(),
                        134, 440, 280,30)
        );
        this.getChildren().add(
                CommonGUI.getButton("Create User", Font.font("Arial", 14), controller.create_user(), 201,489)
        );
        this.getChildren().add(
                CommonGUI.getButton("Close", Font.font("Arial", 14), controller.close(), 302, 489)
        );

        this.getChildren().add(
                CommonGUI.getLabel(controller.msgProperty(), Font.font("Arial", 14), controller.msgFillProperty(), 198,565, Pos.CENTER)
        );
    }

}
