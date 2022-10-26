package edu.rmit.controller;

import edu.rmit.Singleton;
import edu.rmit.View.SmartBoard;
import edu.rmit.models.User;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import java.io.File;

public class RegisterController {

    private StringProperty username;
    private StringProperty first_name;
    private StringProperty last_name;
    private StringProperty password;
    private StringProperty msg;
    private ObjectProperty<Paint> msgFill;

    private String image_path;

    public RegisterController() {
        username = new SimpleStringProperty();
        first_name = new SimpleStringProperty();
        last_name = new SimpleStringProperty();
        password = new SimpleStringProperty();
        msg = new SimpleStringProperty();
        msgFill = new SimpleObjectProperty<>();
    }


    public EventHandler<ActionEvent> create_user() {
        return event -> {
            if (username.get().isEmpty() || first_name.get().isEmpty() || last_name.get().isEmpty() || password.get().isEmpty())
                return;
            User user = new User(username.get(), password.get(), first_name.get(), last_name.get());
            if (image_path != null) user.setProfile_path(image_path);
            if (Singleton.getInstance().userObserver.create_user(user)) {
                msg.set("Created User Successfully");
                msgFillProperty().set(Color.GREEN);
                Singleton.getInstance().switch_scene("Smart Board", new SmartBoard().getScene());
            }
            msg.set("User Already Exists");
            msgFillProperty().set(Color.RED);
        };
    }

    public EventHandler<ActionEvent> close() {
        return actionEvent -> System.exit(0);
    }

    public void setImageChoseEvent(ImageView img, Label label) {
        img.setOnMouseClicked(e -> {
            // load image on double click
            Image image = loadImage();
            if (image == null) return;
            img.setImage(image);
        });

        label.setOnMouseClicked(e -> {
            // load image on double click
            Image image = loadImage();
            if (image == null) return;
            img.setImage(image);
        });
    }

    public Image loadImage() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Choice Profile Picture");
        ExtensionFilter extFilter = new ExtensionFilter("Image File (*.png)", "*.png", "*.jpg");
        chooser.getExtensionFilters().add(extFilter);
        File file = chooser.showOpenDialog(null);
        if (file == null) return null;
        image_path = file.toURI().toString();
        return new Image(image_path);
    }


    public String getUsername() {
        return username.get();
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getFirst_name() {
        return first_name.get();
    }

    public StringProperty first_nameProperty() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name.set(first_name);
    }

    public String getLast_name() {
        return last_name.get();
    }

    public StringProperty last_nameProperty() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name.set(last_name);
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getMsg() {
        return msg.get();
    }

    public StringProperty msgProperty() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg.set(msg);
    }

    public Paint getMsgFill() {
        return msgFill.get();
    }

    public ObjectProperty<Paint> msgFillProperty() {
        return msgFill;
    }

    public void setMsgFill(Paint msgFill) {
        this.msgFill.set(msgFill);
    }
}
