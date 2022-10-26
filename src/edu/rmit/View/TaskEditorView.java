package edu.rmit.View;

import edu.rmit.CommonGUI;
import edu.rmit.controller.TaskEditorController;
import edu.rmit.models.CheckItem;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.time.LocalDate;

public class TaskEditorView extends ScrollPane {

    private TaskEditorController controller;
    private VBox root;

    public TaskEditorView(){
        controller = new TaskEditorController();
        root = new VBox();
        root.setPrefSize(550, 600);
        this.setContent(root);
        this.setPrefSize(550, 650);
        this.setHbarPolicy(ScrollBarPolicy.NEVER);
        new Scene(this);
    }

    public void initialize() {
        root.getChildren().add(CommonGUI.getLabel("Task", Font.font("Arial", 16), Color.BLACK,0, 0));
        TextField title_tf = CommonGUI.getTextField("title", Font.font("Arial", 14), controller.titleProperty(),0 ,0, 0, 0);;
        if(controller.getCurrentTask() != null) title_tf.setText(controller.getTitle());
        root.getChildren().add(title_tf);
        addDueDateOption();
        root.getChildren().add(CommonGUI.getLabel("Description", Font.font("Arial", 16), Color.BLACK, 0 ,0));
        TextArea desc_ta = CommonGUI.getTextArea("description", Font.font("Arial", 14), controller.descriptionProperty() , 0 , 0 ,0 ,100);
        if(controller.getCurrentTask() != null) desc_ta.setText(controller.getDescription());
        root.getChildren().add(desc_ta);
        add_btn_options();
        add_checkList();

        for (Node child : root.getChildren()) {
            VBox.setMargin(child, new Insets(10, 20, 0, 20));
        }
        VBox.setMargin(root.getChildren().get(0), new Insets(40, 20, 0, 20));
    }

    public void add_btn_options(){
        HBox box = getDefaultHorizontalBox();
        box.setAlignment(Pos.CENTER);
        box.getChildren().add(
                CommonGUI.getButton("Ok", Font.font("Arial", 14), controller.confirmTask(this), 0 ,0)
        );
        box.getChildren().add(
                CommonGUI.getButton("Cancel", Font.font("Arial", 14), controller.closeWindow(this), 0 ,0)
        );

        HBox.setMargin(box.getChildren().get(0), new Insets(5));
        HBox.setMargin(box.getChildren().get(1), new Insets(5));

        root.getChildren().add(box);
    }

    public TaskEditorController getController() {
        return controller;
    }

    public void addDueDateOption(){
        controller.setDueDateOption(true);
        Hyperlink link = hyperlink("Add due date", Font.font("Arial", 14), e->{
            addDateFields();
        });
        VBox.setMargin(link, new Insets(10, 20, 0, 20));
        root.getChildren().add(2, link);
    }

    public void add_checkList(){
        Hyperlink link = hyperlink("Add checklist", Font.font("Arial", 14), e->{
            checkLists();
        });
        VBox.setMargin(link, new Insets(10,40,40,5));
        root.getChildren().add(root.getChildren().size()-1,link);
    }

    public void checkLists(){
        int index = root.getChildren().size()-2;
        root.getChildren().remove(index);
        HBox box = getDefaultHorizontalBox();
        box.getChildren().add(
                CommonGUI.getLabel("Checklist", Font.font("Arial", 14), Color.BLACK, 0 , 0)
        );

        ProgressBar checkListProgress = new ProgressBar();
        checkListProgress.progressProperty().bind(controller.checkListProgressProperty());
        checkListProgress.setPrefSize(300, 20);
        VBox.setMargin(checkListProgress, new Insets(5, 40, 5, 40));
        Hyperlink link = hyperlink("Add an item", Font.font("Arial", 14), e->{
            root.getChildren().add(root.getChildren().size()-2, editItem(null));
        });
        VBox.setMargin(link, new Insets(5, 40, 5, 40));
        box.getChildren().add(
                hyperlink("Delete", Font.font("Arial", 14), e->{
                    root.getChildren().remove(box);
                    root.getChildren().remove(checkListProgress);
                    root.getChildren().remove(link);
                    while(root.getChildren().size() >= 7){
                        root.getChildren().remove(root.getChildren().size()-3);
                    }
                    add_checkList();
                })
        );
        VBox.setMargin(box, new Insets(10, 40, 10,40));
        root.getChildren().add(root.getChildren().size()-1, box);
        root.getChildren().add(root.getChildren().size()-1, checkListProgress);
        root.getChildren().add(root.getChildren().size()-1, link);
    }

    public HBox getItem(CheckItem item){
        HBox box = getDefaultHorizontalBox();

        CheckBox checkBox = new CheckBox(item.getName());
        checkBox.setFont(Font.font("Arial", 14));
        item.completeProperty().bind(checkBox.selectedProperty());
        box.getChildren().add(checkBox);
        box.getChildren().add(hyperlink("Edit", Font.font("Arial", 14), e->{
            int index = root.getChildren().indexOf(box);
            root.getChildren().add(index, editItem(item));
            root.getChildren().remove(box);
            item.completeProperty().unbind();
        }));
        box.getChildren().add(hyperlink("Delete", Font.font("Arial", 14), e->{
            controller.deleteCheckItem(item);
            root.getChildren().remove(box);
        }));
        VBox.setMargin(box, new Insets(10, 40, 10, 40));
        return  box;
    }

    public HBox editItem(CheckItem item){
        HBox box = getDefaultHorizontalBox();
        boolean isNew = false;
        if(item == null) {
            item = new CheckItem();
            isNew = true;
        }
        String name = item.getName();

        TextField textField = CommonGUI.getTextField("", Font.font("Arial", 12),
                item.nameProperty(), 0, 0, 250 ,25);
        textField.setText(name);
        box.getChildren().add(textField);
        CheckItem finalItem = item;
        box.getChildren().add(
                CommonGUI.getButton("Save", Font.font("Arial", 12), e->{
                    finalItem.nameProperty().unbind();
                    controller.addCheckItem(finalItem);
                    int index = root.getChildren().indexOf(box);
                    root.getChildren().add(index, getItem(finalItem));
                    root.getChildren().remove(box);
                }, 0 ,0)
        );
        boolean finalIsNew = isNew;
        box.getChildren().add(
                CommonGUI.getButton("Cancel", Font.font("Arial", 12), e->{
                    if (!finalIsNew) {
                        finalItem.nameProperty().unbind();
                        finalItem.setName(name);
                        int index = root.getChildren().indexOf(box);
                        root.getChildren().add(index, getItem(finalItem));
                    }
                    root.getChildren().remove(box);
                }, 0 ,0)
        );
        VBox.setMargin(box, new Insets(10, 40, 10, 40));
        return box;
    }

    public void addDateFields(){
        root.getChildren().remove(2);

        HBox box = getDefaultHorizontalBox();

        HBox dateBox = getDefaultHorizontalBox();

        box.getChildren().add(CommonGUI.getLabel("Due date", Font.font("Arial", 14),Color.BLACK,0 ,0));

        box.getChildren().add(hyperlink("Delete", Font.font("Arial", 14), e->{
            root.getChildren().remove(box);
            root.getChildren().remove(dateBox);
            controller.setDueDateOption(false);
            addDueDateOption();
        }));

        DatePicker datePicker = new DatePicker();
        datePicker.setValue(LocalDate.now());
        dateBox.getChildren().add(datePicker);
        dateBox.getChildren().add(CommonGUI.getLabel("ON TRACK", Font.font("Arial", 12), Color.BLACK, 0 ,0));

        CheckBox mark_as_complete = new CheckBox("Mark as completed");
        controller.mark_as_completeProperty().bind(mark_as_complete.selectedProperty());
        dateBox.getChildren().add(mark_as_complete);
        root.getChildren().add(2, box);
        root.getChildren().add(3, dateBox);

        for (Node child : box.getChildren()) {
            HBox.setMargin(child, new Insets(10, 20, 0, 20));
        }
        for (Node child : dateBox.getChildren()) {
            HBox.setMargin(child, new Insets(10, 20, 0, 20));
        }
    }

    public Hyperlink hyperlink(String str, Font font, EventHandler<ActionEvent> event){
        Hyperlink hyperlink = new Hyperlink(str);
        hyperlink.setFont(font);
        hyperlink.setOnAction(event);
        return hyperlink;
    }

    public HBox getDefaultHorizontalBox(){
        HBox box = new HBox();
        box.setSpacing(15);
        return box;
    }

}
