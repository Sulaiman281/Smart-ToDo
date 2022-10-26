package edu.rmit;

import edu.rmit.View.LoginView;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Singleton.getInstance().setStage(primaryStage);
        Singleton.getInstance().switch_scene("Log into Smart Board", new LoginView().getScene());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
