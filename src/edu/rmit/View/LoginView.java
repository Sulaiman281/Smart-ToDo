package edu.rmit.View;

import edu.rmit.CommonGUI;
import edu.rmit.controller.LoginController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class LoginView extends Pane {

    private final LoginController controller;
    public LoginView(){
        new Scene(this);
        controller = new LoginController();
        this.setPrefSize(500, 400);
        this.setMaxSize(500, 400);
        initialize();
    }

    private void initialize() {

        this.getChildren().add(
                CommonGUI.getLabel("Username", Font.font("Arial", 14),
                        Color.BLACK, 108, 85)
        );

        this.getChildren().add(
                CommonGUI.getTextField("username", Font.font("Arial", 16),
                        controller.usernameProperty(), 108, 115, 271, 25)
        );

        this.getChildren().add(
                CommonGUI.getLabel("Password", Font.font("Arial", 14),
                        Color.BLACK, 108, 173)
        );

        this.getChildren().add(
                CommonGUI.getPassField("password", Font.font("Arial", 16),
                        controller.passwordProperty(), 108, 204, 271, 25)
        );

        this.getChildren().add(
                CommonGUI.getButton("Sign in", Font.font("Arial", 12),
                        controller.login(), 178, 268)
        );

        this.getChildren().add(
                CommonGUI.getButton("Close", Font.font("Arial", 12),
                        controller.close(), 254, 268)
        );

        this.getChildren().add(
                CommonGUI.getLabel(controller.msgProperty(), Font.font("Arial", 12), controller.msgFillProperty(), 200, 350, Pos.CENTER)
        );

        Hyperlink hyperlink = new Hyperlink();
        hyperlink.setText("create new account");
        hyperlink.setOnAction(controller.create_new_account());
        hyperlink.setLayoutX(188);
        hyperlink.setLayoutY(313);
        this.getChildren().add(hyperlink);
    }
}
