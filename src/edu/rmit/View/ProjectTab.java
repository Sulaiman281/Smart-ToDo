package edu.rmit.View;

import edu.rmit.CommonGUI;
import edu.rmit.Singleton;
import edu.rmit.controller.ProjectController;
import edu.rmit.models.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ProjectTab extends Tab {

    private HBox box;
    private ProjectController controller;

    public ProjectTab(String tabName) {
        this.setText(tabName);
        box = new HBox();
        box.setSpacing(40);
        this.setContent(box);
        this.setClosable(false);
        controller = new ProjectController(Singleton.getInstance().projectObserver.addProject(tabName));
    }

    public void addColumn() {
        VBox box = new VBox();
        box.setSpacing(20);
        box.getChildren().add(columnHeading());
        box.setPrefSize(280, 600);
        this.box.getChildren().add(box);
    }

    Button columnHeading() {
        Button btn = CommonGUI.getButton("To do", Font.font("Arial", 14), controller.add_task(), 0, 0);
        btn.setPrefSize(280, 30);
        btn.setAlignment(Pos.TOP_LEFT);
        btn.setContentDisplay(ContentDisplay.RIGHT);
        btn.setGraphicTextGap(180);

        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.setPromptText("Add Task");
        btn.setGraphic(comboBox);
        VBox.setMargin(btn, new Insets(10));
        return btn;
    }

    public void refresh(){

    }


    public Pane getTask(Task task){
        Pane pane = new Pane();
        pane.setPrefSize(280, 150);
        Label title = CommonGUI.getLabel(task.titleProperty(), Font.font("Arial", 16), null, 14 ,37, Pos.TOP_LEFT);
        title.setPrefSize(250, 40);
        pane.getChildren().add(title);
        if(!task.dueDateProperty().get().isEmpty()){
            Label date = CommonGUI.getLabel(task.dueDateProperty(), Font.font("Arial", 14), null, 14, 87, Pos.TOP_LEFT);
            date.setPrefSize(100, 25);
            pane.getChildren().add(date);
        }
        Label checkItems = CommonGUI.getLabel(task.checkListString(), Font.font("Arial", 14), Color.BLACK, 206, 87);
        checkItems.setPrefSize(42, 25);
        pane.getChildren().add(checkItems);

        pane.getChildren().add(
                getHyperLink("Edit", Font.font("Arial", 12), null, 202, 6)
        );
        pane.getChildren().add(
                getHyperLink("Delete", Font.font("Arial", 12), null, 231, 6)
        );
        return pane;
    }

    Hyperlink getHyperLink(String str, Font font, EventHandler<ActionEvent> event, double layoutX, double layoutY){
        Hyperlink link = new Hyperlink(str);
        link.setFont(font);
        link.setOnAction(event);
        link.setLayoutX(layoutX);
        link.setLayoutY(layoutY);
        return link;
    }
}
