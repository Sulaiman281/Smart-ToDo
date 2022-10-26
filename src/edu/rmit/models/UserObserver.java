package edu.rmit.models;

import java.util.ArrayList;
import java.util.Locale;

public class UserObserver {

    private ArrayList<User> users;
    private User active_user;

    public UserObserver() {

    }

    public int authenticateUser(String username, String password) {
        User user = user_exists(username);
        if (user == null) return 0;
        if (!user.getPassword().equals(password)) return -1;
        active_user = user;
        return 1;
    }

    public boolean create_user(User user) {
        if (users == null) users = new ArrayList<>();
        if (user_exists(user.getUsername()) != null) return false;
        users.add(user);
        active_user = user;
        return true;
    }

    public User user_exists(String username) {
        if(users == null) return null;
        for (User user : users) {
            if (user.getUsername().toLowerCase(Locale.ROOT).equals(username.toLowerCase(Locale.ROOT))) return user;
        }
        return null;
    }

    public User getActive_user() {
        return active_user;
    }
}
