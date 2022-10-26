package edu.rmit.View;

import edu.rmit.CommonGUI;
import edu.rmit.Singleton;
import edu.rmit.controller.ProfileEditController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class EditProfile extends HBox {

    private final ProfileEditController controller;

    public EditProfile() {
        new Scene(this);
        controller = new ProfileEditController();

        setPrefSize(500, 300);
        leftSide();
        rightSide();
    }

    void leftSide() {
        VBox box = new VBox();
        box.setAlignment(Pos.CENTER);
        box.setSpacing(20);
        ImageView imgView = new ImageView();
        imgView.setFitWidth(150);
        imgView.setFitHeight(150);
        imgView.imageProperty().bind(controller.imgViewProperty());
        box.getChildren().add(imgView);
        Label imgLabel = CommonGUI.getLabel("update profile picture", Font.font("Arial", 12), Color.GREY, 0, 0);
        box.getChildren().add(imgLabel);
        imgLabel.setOnMouseClicked(controller.update_profile());
        this.getChildren().add(box);
    }

    void rightSide() {
        VBox box = new VBox();
        box.setSpacing(10);
        box.getChildren().add(
                CommonGUI.getLabel("Username", Font.font("Arial", 14),
                        Color.BLACK, 0, 0)
        );
        box.getChildren().add(
                CommonGUI.getLabel(controller.usernameProperty(), Font.font("Arial", 12), controller.fillUsernameProperty(), 0, 0, Pos.CENTER_LEFT)
        );
        box.getChildren().add(
                CommonGUI.getLabel("First Name", Font.font("Arial", 14),
                        Color.BLACK, 0, 0)
        );
        box.getChildren().add(
                CommonGUI.getTextField("John", Font.font("Arial", 12), controller.firstNameProperty(), 0, 0, 250, 30)
        );
        ((TextField)box.getChildren().get(box.getChildren().size()-1)).setText(Singleton.getInstance().userObserver.getActive_user().getFirstName());
        box.getChildren().add(
                CommonGUI.getLabel("Last Name", Font.font("Arial", 14),
                        Color.BLACK, 0, 0)
        );
        box.getChildren().add(
                CommonGUI.getTextField("Snow", Font.font("Arial", 12), controller.lastNameProperty(), 0, 0, 250, 30)
        );
        ((TextField)box.getChildren().get(box.getChildren().size()-1)).setText(Singleton.getInstance().userObserver.getActive_user().getLastName());

        HBox btn_box = new HBox();
        btn_box.setAlignment(Pos.CENTER);
        btn_box.setSpacing(20);
        btn_box.getChildren().add(
                CommonGUI.getButton("Ok", Font.font("Arial", 13), controller.confirm_edit(this), 0, 0)
        );
        btn_box.getChildren().add(
                CommonGUI.getButton("Cancel", Font.font("Arial", 13), controller.cancel(this), 0, 0)
        );
        box.getChildren().add(btn_box);
        this.getChildren().add(box);
    }
}