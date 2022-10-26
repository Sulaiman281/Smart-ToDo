package edu.rmit.controller;

import edu.rmit.Singleton;
import edu.rmit.View.EditProfile;
import edu.rmit.View.LoginView;
import edu.rmit.View.ProjectTab;
import edu.rmit.models.Quote;
import javafx.beans.property.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;

import java.util.Optional;

public class BoardController {

    private StringProperty quote;
    private ObjectProperty<Paint> quoteFill;
    private StringProperty userName;
    private ObjectProperty<Paint> userNameFill;
    private ObservableList<Tab> tabs;
    private SelectionModel<Tab> active_tab;
    private ObjectProperty<Paint> profile_picture;

    // menu items
    private BooleanProperty add_column;
    private BooleanProperty rename;
    private BooleanProperty set_default;
    private BooleanProperty unset_default;
    private BooleanProperty delete;

    private Image profile;
    private ProjectTab currentTab;

    public BoardController(){
        quote = new SimpleStringProperty();
        quoteFill = new SimpleObjectProperty<>();
        userName = new SimpleStringProperty();
        userNameFill = new SimpleObjectProperty<>();
        profile_picture = new SimpleObjectProperty<>();

        add_column = new SimpleBooleanProperty();
        rename = new SimpleBooleanProperty();
        set_default = new SimpleBooleanProperty();
        unset_default = new SimpleBooleanProperty();
        delete = new SimpleBooleanProperty();

        initialize();
    }

    public boolean isAdd_column() {
        return add_column.get();
    }

    public BooleanProperty add_columnProperty() {
        return add_column;
    }

    public void setAdd_column(boolean add_column) {
        this.add_column.set(add_column);
    }

    public boolean isRename() {
        return rename.get();
    }

    public BooleanProperty renameProperty() {
        return rename;
    }

    public void setRename(boolean rename) {
        this.rename.set(rename);
    }

    public boolean isSet_default() {
        return set_default.get();
    }

    public BooleanProperty set_defaultProperty() {
        return set_default;
    }

    public void setSet_default(boolean set_default) {
        this.set_default.set(set_default);
    }

    public boolean isUnset_default() {
        return unset_default.get();
    }

    public BooleanProperty unset_defaultProperty() {
        return unset_default;
    }

    public void setUnset_default(boolean unset_default) {
        this.unset_default.set(unset_default);
    }

    public boolean isDelete() {
        return delete.get();
    }

    public BooleanProperty deleteProperty() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete.set(delete);
    }

    void initialize(){
        userNameFill.set(Color.BLUE);
        userName.set(Singleton.getInstance().userObserver.getActive_user().toString());
        if(Singleton.getInstance().userObserver.getActive_user().getProfile_path() != null){
            profile = new Image(Singleton.getInstance().userObserver.getActive_user().getProfile_path());
            profile_picture.set(new ImagePattern(profile));
        }
        quote.set(Quote.getRandomQuote());
        quoteFill.set(Color.DARKVIOLET);
    }

    public EventHandler<ActionEvent> newProject(){
        return e->{
            // create new Project.
            Optional<String> answer = Singleton.getInstance().getUserInput();
            answer.ifPresent(s -> tabs.add(new ProjectTab(s)));
        };
    }

    public EventHandler<ActionEvent> add_column(){
        return e->{
            // add column Into Project.
            if(currentTab == null) return;
            currentTab.addColumn();
        };
    }

    public EventHandler<ActionEvent> rename(){
        return e->{
            // rename the column
            if(active_tab == null) return;
            Optional<String> answer = Singleton.getInstance().getUserInput();
            if(answer.isEmpty()) return;
            answer.ifPresent(s -> active_tab.getSelectedItem().setText(s));
        };
    }

    public EventHandler<ActionEvent> set_default(){
        return e->{
            // set default
        };
    }

    public EventHandler<ActionEvent> unset_default(){
        return e->{
            // unset default
        };
    }

    public EventHandler<ActionEvent> delete(){
        return e->{
            // delete column from project.
            if(active_tab == null) return;
            tabs.remove(active_tab.getSelectedItem());
        };
    }

    public EventHandler<ActionEvent> profile_edit(){
        return e->{
            Singleton.getInstance().openDialog("Edit Profile", new EditProfile().getScene());
            if(Singleton.getInstance().userObserver.getActive_user().getProfile_path() == null) return;
            profile = Singleton.getInstance().loadImage(Singleton.getInstance().userObserver.getActive_user().getProfile_path());
            profile_picture.set(new ImagePattern(profile));
        };
    }

    public EventHandler<ActionEvent> log_out(){
        return e->{
            // log out the user from smart board.
            Singleton.getInstance().switch_scene("Sign In Smart Board", new LoginView().getScene());
        };
    }



    public String getQuote() {
        return quote.get();
    }

    public StringProperty quoteProperty() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote.set(quote);
    }

    public Paint getQuoteFill() {
        return quoteFill.get();
    }

    public ObjectProperty<Paint> quoteFillProperty() {
        return quoteFill;
    }

    public void setQuoteFill(Paint quoteFill) {
        this.quoteFill.set(quoteFill);
    }

    public String getUserName() {
        return userName.get();
    }

    public StringProperty userNameProperty() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName.set(userName);
    }

    public Paint getUserNameFill() {
        return userNameFill.get();
    }

    public ObjectProperty<Paint> userNameFillProperty() {
        return userNameFill;
    }

    public void setUserNameFill(Paint userNameFill) {
        this.userNameFill.set(userNameFill);
    }

    public ObservableList<Tab> getTabs() {
        return tabs;
    }

    public void setTabs(ObservableList<Tab> tabs) {
        this.tabs = tabs;
    }

    public Paint getProfile_picture() {
        return profile_picture.get();
    }

    public ObjectProperty<Paint> profile_pictureProperty() {
        return profile_picture;
    }

    public void setProfile_picture(Paint profile_picture) {
        this.profile_picture.set(profile_picture);
    }

    public SelectionModel<Tab> getActive_tab() {
        return active_tab;
    }

    public void setActive_tab(SelectionModel<Tab> active_tab) {
        this.active_tab = active_tab;
        setSet_default(true);
        setUnset_default(true);
        active_tab.selectedItemProperty().addListener((observable, oldValue, newValue)->{
            toggleMenuItemDisableState(active_tab.isEmpty());
            if(newValue == null) return;
            currentTab = (ProjectTab) newValue;
        });
    }
    public void toggleMenuItemDisableState(boolean cond){
        setAdd_column(cond);
        setRename(cond);
        setDelete(cond);
    }

}
