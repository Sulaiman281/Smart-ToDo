package edu.rmit;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class CommonGUI {

    public static Button getButton(String name, Font font, EventHandler<ActionEvent> event, double layoutX, double layoutY){
        Button btn = new Button(name);
        btn.setFont(font);
        btn.setOnAction(event);
        btn.setLayoutX(layoutX);
        btn.setLayoutY(layoutY);
        return btn;
    }

    public static TextField getTextField(String hint, Font font, StringProperty bind, double layoutX, double layoutY, double width, double height){
        TextField tf = new TextField();
        tf.setPromptText(hint);
        tf.setFont(font);
        tf.setLayoutX(layoutX);
        tf.setLayoutY(layoutY);
        tf.setPrefSize(width, height);
        bind.bind(tf.textProperty());
        return tf;
    }
    public static TextArea getTextArea(String hint, Font font, StringProperty bind, double layoutX, double layoutY, double width, double height){
        TextArea tf = new TextArea();
        tf.setPromptText(hint);
        tf.setFont(font);
        tf.setLayoutX(layoutX);
        tf.setLayoutY(layoutY);
        tf.setPrefSize(width, height);
        bind.bind(tf.textProperty());
        return tf;
    }

    public static PasswordField getPassField(String hint, Font font, StringProperty bind, double layoutX, double layoutY, double width, double height){
        PasswordField passField = new PasswordField();
        passField.setPromptText(hint);
        passField.setFont(font);
        bind.bind(passField.textProperty());
        passField.setLayoutX(layoutX);
        passField.setLayoutY(layoutY);
        passField.setPrefSize(width, height);
        return passField;
    }

    public static Label getLabel(String str, Font font, Color fill, double layoutX, double layoutY){
        Label label = new Label(str);
        label.setFont(font);
        label.setWrapText(true);
        label.setTextFill(fill);
        label.setLayoutX(layoutX);
        label.setLayoutY(layoutY);
        return label;
    }
    public static Label getLabel(StringProperty bind, Font font, ObjectProperty<Paint> fillProperty, double layoutX, double layoutY, Pos align){
        Label label = new Label();

        label.setFont(font);
        label.setWrapText(true);
        if(fillProperty != null)
            label.textFillProperty().bind(fillProperty);
        else label.setTextFill(Color.BLACK);
        label.setLayoutX(layoutX);
        label.setLayoutY(layoutY);
        label.textProperty().bind(bind);
        label.setAlignment(align);
        return label;
    }
}