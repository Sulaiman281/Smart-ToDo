package edu.rmit.controller;

import edu.rmit.models.CheckItem;
import edu.rmit.models.Task;
import javafx.beans.property.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.util.ArrayList;

public class TaskEditorController {

    private StringProperty title;
    private StringProperty description;

    private boolean dueDateOption;
    private BooleanProperty mark_as_complete;
    private StringProperty dueDate;

    private DoubleProperty checkListProgress;
    private Task currentTask;
    private ArrayList<CheckItem> checklist;

    public TaskEditorController() {
        title = new SimpleStringProperty();
        description = new SimpleStringProperty();
        mark_as_complete = new SimpleBooleanProperty();
        dueDate = new SimpleStringProperty();
        checkListProgress = new SimpleDoubleProperty();
        checklist = new ArrayList<>();
    }

    public EventHandler<ActionEvent> confirmTask(Node node){
        return e->{

            ((Stage) node.getScene().getWindow()).close();
        };
    }

    public EventHandler<ActionEvent> closeWindow(Node node){
        return e->{
            ((Stage) node.getScene().getWindow()).close();
        };
    }

    public void setCurrentTask(Task currentTask) {
        this.currentTask = currentTask;
    }

    public Task getCurrentTask() {
        return currentTask;
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public boolean isDueDateOption() {
        return dueDateOption;
    }

    public void setDueDateOption(boolean dueDateOption) {
        this.dueDateOption = dueDateOption;
    }

    public boolean isMark_as_complete() {
        return mark_as_complete.get();
    }

    public BooleanProperty mark_as_completeProperty() {
        return mark_as_complete;
    }

    public void setMark_as_complete(boolean mark_as_complete) {
        this.mark_as_complete.set(mark_as_complete);
    }

    public String getDueDate() {
        return dueDate.get();
    }

    public StringProperty dueDateProperty() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate.set(dueDate);
    }

    public double getCheckListProgress() {
        return checkListProgress.get();
    }

    public DoubleProperty checkListProgressProperty() {
        return checkListProgress;
    }

    public void setCheckListProgress(double checkListProgress) {
        this.checkListProgress.set(checkListProgress);
    }

    public void addCheckItem(CheckItem item){
        if(currentTask == null) checklist.add(item);
        else currentTask.getChecklist().add(item);
    }

    public void deleteCheckItem(CheckItem item) {
        if(currentTask == null) checklist.remove(item);
        else currentTask.getChecklist().remove(item);
    }
}
