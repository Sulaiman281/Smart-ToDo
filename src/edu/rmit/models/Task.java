package edu.rmit.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;

public class Task {
    private StringProperty title;
    private StringProperty description;
    private StringProperty dueDate;
    private boolean complete;

    private ArrayList<CheckItem> checklist;

    public Task(String title, String description) {
        this.title = new SimpleStringProperty(title);
        this.description = new SimpleStringProperty(description);
        dueDate = new SimpleStringProperty();
        checklist = new ArrayList<>();
    }

    public void addItem(String title){
        checklist.add(new CheckItem(title));
    }
    public void addItem(CheckItem item){
        checklist.add(item);
    }

    public void mark_as_complete(String item){
        for (CheckItem checkItem : checklist) {
            if(checkItem.getName().equals(item)) {
                checkItem.setComplete(true);
                break;
            }
        }
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

    public String getDueDate() {
        return dueDate.get();
    }

    public StringProperty dueDateProperty() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate.set(dueDate);
    }

    public ArrayList<CheckItem> getChecklist() {
        return checklist;
    }

    public void setChecklist(ArrayList<CheckItem> checklist) {
        this.checklist = checklist;
    }

    public String checkListString(){
        int countComplete = 0;
        for (CheckItem checkItem : checklist) {
            if(checkItem.isComplete()) countComplete++;
        }
        return countComplete+"/"+checklist.size();
    }

    public void remove(String title){
        checklist.removeIf(checkItem -> checkItem.getName().equals(title));
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    @Override
    public String toString() {
        return title.get();
    }
}
