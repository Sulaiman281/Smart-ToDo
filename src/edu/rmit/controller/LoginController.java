package edu.rmit.controller;

import edu.rmit.Singleton;
import edu.rmit.View.Register;
import edu.rmit.View.SmartBoard;
import javafx.beans.property.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class LoginController {

    private StringProperty username;
    private StringProperty password;

    private StringProperty msg;
    private ObjectProperty<Paint> msgFill;

    public LoginController(){
        username = new SimpleStringProperty();
        password = new SimpleStringProperty();
        msg = new SimpleStringProperty();
        msgFill = new SimpleObjectProperty<>();
    }

    public EventHandler<ActionEvent> login() {
        return e -> {
            if(username.get().isEmpty() || password.get().isEmpty()){
                return;
            }
            int result = Singleton.getInstance().userObserver.authenticateUser(username.get(), password.get());
            msg.set(result == -1 ? "Wrong Password" : result == 0 ? "User doesn't exists" : "Login Success");
            msgFill.set(result < 1 ? Color.RED : Color.GREEN);

            if(result == 1)
                Singleton.getInstance().switch_scene("Smart Board", new SmartBoard().getScene());
        };
    }

    public EventHandler<ActionEvent> close() {
        return e -> {
            System.exit(0);
        };
    }

    public EventHandler<ActionEvent> create_new_account() {
        return e -> {
            // load the scene
            Singleton.getInstance().switch_scene("Create a new User", new Register().getScene());
        };
    }

    public String getUsername() {
        return username.get();
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
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
