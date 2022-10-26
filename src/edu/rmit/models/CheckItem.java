package edu.rmit.models;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CheckItem {
    private StringProperty name;
    private BooleanProperty complete;

    public CheckItem(){
        name = new SimpleStringProperty();
        complete = new SimpleBooleanProperty();
    }

    public CheckItem(String name) {
        this.name = new SimpleStringProperty(name);
        this.complete = new SimpleBooleanProperty(false);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public boolean isComplete() {
        return complete.get();
    }

    public BooleanProperty completeProperty() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete.set(complete);
    }
}
