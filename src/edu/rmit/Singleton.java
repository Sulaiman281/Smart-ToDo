package edu.rmit;

import edu.rmit.models.ProjectObserver;
import edu.rmit.models.UserObserver;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Optional;

public class Singleton {

    public UserObserver userObserver;
    public ProjectObserver projectObserver;

    private static Singleton _instance;

    private Stage stage;

    private Singleton(){
        userObserver = new UserObserver();
        projectObserver = new ProjectObserver();
    }

    public static Singleton getInstance(){
        if(_instance == null) _instance = new Singleton();
        return _instance;
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }

    public void switch_scene(String title, Scene nextScene){
        if(stage == null) return;

        try{
            Thread.sleep(500*2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stage.setTitle(title);
        stage.setScene(nextScene);
        stage.centerOnScreen();
    }

    public void openDialog(String title, Scene scene){
        Stage dialog = new Stage();
        dialog.setTitle(title);
        dialog.setScene(scene);
        dialog.showAndWait();
    }

    public Image loadImage(String path) {
        return new Image(path);
    }

    public Optional<String> getUserInput() {
        TextInputDialog inputDialog = new TextInputDialog();
        inputDialog.setTitle("New Project Name");
        inputDialog.setWidth(400);
        inputDialog.setHeight(200);
        inputDialog.setHeaderText("");
        inputDialog.setContentText("");
        inputDialog.getEditor().setPromptText("name");

        return inputDialog.showAndWait();
    }
}
