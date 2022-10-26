package edu.rmit.View;

import edu.rmit.CommonGUI;
import edu.rmit.controller.BoardController;
import javafx.beans.property.BooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class SmartBoard extends VBox {

    private BoardController controller;

    public SmartBoard(){
        new Scene(this);
        this.setPrefSize(800, 600);

        controller = new BoardController();

        add_menuBar();
        add_heading();
        addBody();
    }

    void add_menuBar(){
        MenuBar bar = new MenuBar();
        Menu workSpace = new Menu("Workspace");
        workSpace.getItems().add(getMenuItem("New Project", controller.newProject()));

        Menu project = new Menu("Project");
        project.getItems().add(getMenuItem("Add column", controller.add_columnProperty(), controller.add_column()));
        project.getItems().add(getMenuItem("Rename", controller.renameProperty(), controller.rename()));
        project.getItems().add(getMenuItem("Set as default", controller.set_defaultProperty(), controller.set_default()));
        project.getItems().add(getMenuItem("Unset default", controller.unset_defaultProperty(), controller.unset_default()));
        project.getItems().add(getMenuItem("Delete", controller.deleteProperty(), controller.delete()));
        controller.toggleMenuItemDisableState(true);
        bar.getMenus().add(workSpace);
        bar.getMenus().add(project);
        this.getChildren().add(bar);
    }

    MenuItem getMenuItem(String name, BooleanProperty bool, EventHandler<ActionEvent> event){
        MenuItem item = new MenuItem(name);
        item.setOnAction(event);
        item.disableProperty().bind(bool);
        return item;
    }
    MenuItem getMenuItem(String name, EventHandler<ActionEvent> event){
        MenuItem item = new MenuItem(name);
        item.setOnAction(event);
        return item;
    }

    void add_heading(){
        HBox box = new HBox();
        box.setAlignment(Pos.CENTER);
        box.setSpacing(20);
        box.setPrefHeight(100);
        Label quote = CommonGUI.getLabel(controller.quoteProperty(), Font.font("Arial", FontPosture.ITALIC,14),
                controller.quoteFillProperty(), 0,0, Pos.CENTER);
        quote.setPrefSize(450,100);
        box.getChildren().add(quote);

        Label username = CommonGUI.getLabel(controller.userNameProperty(), Font.font("Arial", FontWeight.BOLD,14),
                controller.userNameFillProperty(), 0,0, Pos.CENTER);
        username.setPrefSize(150,100);
        box.getChildren().add(username);

        Circle profile = new Circle();
        profile.setRadius(20);
        profile.fillProperty().bind(controller.profile_pictureProperty());
        box.getChildren().add(profile);

        box.getChildren().add(
                CommonGUI.getButton("Profile", Font.font("Arial", 14), controller.profile_edit(), 0, 0)
        );
        box.getChildren().add(
                CommonGUI.getButton("Log out", Font.font("Arial", 14), controller.log_out(), 0, 0)
        );

        this.getChildren().add(box);
    }

    void addBody(){
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPrefSize(800, 800);
        TabPane pane = new TabPane();
        pane.setPrefSize(800, 800);
        controller.setActive_tab(pane.getSelectionModel());
        scrollPane.setContent(pane);
        this.getChildren().add(scrollPane);
        controller.setTabs(pane.getTabs());
    }


}
