package edu.rmit.controller;

import edu.rmit.Singleton;
import edu.rmit.models.User;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class ProfileEditController {

    private StringProperty username;
    private ObjectProperty<Paint> fillUsername;
    private StringProperty firstName;
    private StringProperty lastName;
    private ObjectProperty<Image> imgView;

    private String image_path;

    public ProfileEditController(){
        username = new SimpleStringProperty();
        fillUsername = new SimpleObjectProperty<>();
        firstName = new SimpleStringProperty();
        lastName = new SimpleStringProperty();
        imgView = new SimpleObjectProperty<>();
        initialize();
    }

    void initialize(){
        User active_user = Singleton.getInstance().userObserver.getActive_user();
        username.set(active_user.getUsername());
        fillUsername.set(Color.BLACK);
        if(active_user.getProfile_path() != null){
            imgView.set(Singleton.getInstance().loadImage(active_user.getProfile_path()));
        }
    }

    public EventHandler<MouseEvent> update_profile(){
        return e->{
            imgView.set(loadImage());
        };
    }

    public Image loadImage() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Choice Profile Picture");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image File (*.png)", "*.png", "*.jpg");
        chooser.getExtensionFilters().add(extFilter);
        File file = chooser.showOpenDialog(null);
        if(file == null) return null;
        image_path = file.toURI().toString();
        return new Image(image_path);
    }

    public EventHandler<ActionEvent> confirm_edit(Node node){
        return e->{
            if(firstName.get().isEmpty() || lastName.get().isEmpty()) return;

            User user = Singleton.getInstance().userObserver.getActive_user();
            user.setProfile_path(image_path);
            user.setFirstName(firstName.get());
            user.setLastName(lastName.get());

            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();
        };
    }

    public EventHandler<ActionEvent> cancel(Node node){
        return e->{
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();
        };
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

    public Paint getFillUsername() {
        return fillUsername.get();
    }

    public ObjectProperty<Paint> fillUsernameProperty() {
        return fillUsername;
    }

    public void setFillUsername(Paint fillUsername) {
        this.fillUsername.set(fillUsername);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public Image getImgView() {
        return imgView.get();
    }

    public ObjectProperty<Image> imgViewProperty() {
        return imgView;
    }

    public void setImgView(Image imgView) {
        this.imgView.set(imgView);
    }
}
