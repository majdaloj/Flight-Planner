package controller;

import entities.Route;
import entities.User;
import org.json.JSONException;
import usecases.LoginHandler;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Map;

/**
 * UserController is responsible for implementing features such as logging in a user, and creating new accounts.
 */

public class UserController {
    private final LoginHandler loginHandler;

    public UserController(LoginHandler loginHandler) {
        this.loginHandler = loginHandler;
    }

    /**
     * Create a new user account.
     * username, email, and phone number must be unique
     * email and phone number must follow respective formatting
     *
     * @param username    unique name of the user
     * @param password    password of the user
     * @param email       unique email of the user
     * @param phoneNumber unique phone number of the user
     * @return true if account creation is successful.  false otherwise.
     */
    public String createAccount(String username, String password, String email, String phoneNumber) {
        try {
            ArrayList<String> errors = loginHandler.createAccount(username, password, email, phoneNumber);

            if (errors.size() == 0) {
                // account creation is successful
                return "Account Created Successfully";
            }

            StringBuilder output = new StringBuilder();
            for (String item: errors) {
                output.append(item).append("\n");
            }
            return output.toString();
        } catch (NullPointerException e) {
            return "NullPointerException";
        }
    }

    public boolean login(String username, String password) {
        try {
            return loginHandler.loginAttempt(username, password);
        } catch (NullPointerException e) {
            return false;
        }
    }

    public User getCurrentUser() {
        return loginHandler.currentUser.getCurrentUser();
    }

    /**
     * Serializes and deserializes userList. Call this at the end of changing ViewProfile.
     */
    public void saveSettings() {
        loginHandler.saveSettings();
    }

    // methods below are getters and setters for various user information
    public String getUsername() {
        return loginHandler.currentUser.getUsername();
    }

    public String getPassword() {
        return loginHandler.currentUser.getPassword();
    }

    public void setPassword(String pw) {
        loginHandler.currentUser.setPassword(pw);
    }

    public String getEmail() {
        return loginHandler.currentUser.getEmail();
    }

    public void setEmail(String e) {
        loginHandler.currentUser.setEmail(e);
    }

    public String getPhoneNumber() {
        return loginHandler.currentUser.getPhoneNumber();
    }

    public void setPhoneNumber(String pn) {
        loginHandler.currentUser.setPhoneNumber(pn);
    }

    public int getAppRating() {
        return loginHandler.currentUser.getAppRating();
    }

    public void setAppRating(int appRating) {
        loginHandler.currentUser.setAppRating(appRating);
    }

    public String getRouteHistory() { return loginHandler.currentUser.getRouteHistory(); }

    public String upgradeUserType() {
        return loginHandler.currentUser.upgradeUserType();
    }

    public String downgradeUserType() {
        return loginHandler.currentUser.downgradeUserType();
    }

    public String getClassType() { return loginHandler.currentUser.getClassType(); }

    public String getUserType() {
        return loginHandler.currentUser.getUserType();
    }

    public String getUserDataJson () {
        return "{" +
                "\"userName\":" +
                "\"" +
                getUsername() +
                "\"," +
                "\"password\":" +
                "\"" +
                getPassword() +
                "\"," +
                "\"email\":" +
                "\"" +
                getEmail() +
                "\"," +
                "\"phoneNumber\":" +
                "\"" +
                getPhoneNumber() +
                "\"" +
                "}";
    }

    public String getUserSettingsJson () {
        return loginHandler.settingsToString();
    }

    public String updateSettings(Map<String, String> settingsHash){
        return loginHandler.updateSettings(settingsHash);
    }

    public void removeRouteByID(String id) {
        loginHandler.currentUser.removeRoutebyID(id);
        saveSettings();
    }

    public void addRouteToHistory(Route selectedRoute) {
        try {
            loginHandler.currentUser.addRouteToHistory(selectedRoute);
            saveSettings();
        } catch (JSONException | ParseException ignored) {
        }
    }

}