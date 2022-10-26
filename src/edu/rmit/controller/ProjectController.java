package edu.rmit.controller;

import edu.rmit.Singleton;
import edu.rmit.View.TaskEditorView;
import edu.rmit.models.Project;
import edu.rmit.models.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ProjectController {

    private Project project;

    public ProjectController(Project project){
        this.project = project;
    }

    public EventHandler<ActionEvent> add_task(){
        return e->{
            Singleton.getInstance().projectObserver.setSelected_project(project);
            TaskEditorView taskEditorView = new TaskEditorView();
            taskEditorView.initialize();
            Singleton.getInstance().openDialog("Add New Task", taskEditorView.getScene());
            System.out.println("DO THE REST BOI");
        };
    }

    public EventHandler<ActionEvent> edit_task(Task task){
        return e->{
            Singleton.getInstance().projectObserver.setSelected_project(project);
            TaskEditorView taskEditorView = new TaskEditorView();
            taskEditorView.getController().setCurrentTask(task);
            taskEditorView.initialize();
            Singleton.getInstance().openDialog("Edit Task", taskEditorView.getScene());
        };
    }
}
