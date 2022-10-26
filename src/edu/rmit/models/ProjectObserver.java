package edu.rmit.models;

import java.util.ArrayList;

public class ProjectObserver {

    private ArrayList<Project> projects;
    private Project selected_project;

    public ProjectObserver(){
        projects = new ArrayList<>();
    }

    public Project addProject(String name){
        Project project = projectExists(name);
        if(project != null) return null;
        project = new Project(name);
        projects.add(project);
        return project;
    }

    public Project projectExists(String name){
        for (Project project : projects) {
            if(project.getName().equals(name)) return project;
        }
        return null;
    }

    public void create_new_task(String name, String description){
        if(selected_project == null) return;
        selected_project.getTasks().add(new Task(name, description));
    }

    public Project getSelected_project() {
        return selected_project;
    }

    public void setSelected_project(Project selected_project) {
        this.selected_project = selected_project;
    }
}
