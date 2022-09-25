package usecases;

import entities.User;

import java.util.ArrayList;
import java.util.Map;

/**
 * LoginHandler handles login and account creation.
 */

public class LoginHandler {
    private UserList users;
    public ViewProfile currentUser;
    UserReadWriter userReadWriter = new UserReadWriter();

    /**
     * Initialize UserManager with users pointing to the current serialized user list.
     */
    public LoginHandler() {
        this.users = userReadWriter.readFromFile();
    }

    public void saveSettings() {
        // Re-instantiating the current user in the user list to avoid pointer errors
        users.removeUser(currentUser.getCurrentUser());
        users.addUser(currentUser.getCurrentUser());
        serializeUsers();
        users = deserializeUsers();
    }

    /**
     * Writes the current users to users.ser.
     */
    public void serializeUsers() {
        userReadWriter.saveToFile(this.users);
    }

    /**
     * Reads the current user list and returns them in a UserList.
     *
     * @return a UserList of the current users
     */
    public UserList deserializeUsers() {
        users = userReadWriter.readFromFile();
        return users;
    }

    /**
     * Create a new UserManager with username, password, email, and phoneNumber and add them to users
     *
     * @param username    username of new user
     * @param password    password of new user
     * @param email       email address of user
     * @param phoneNumber phone number of new user
     */
    public ArrayList<String> createAccount(String username, String password, String email, String phoneNumber) {
        User newUser = new User(username, password, email, phoneNumber);

        // Verify the given info checks out
        ArrayList<String> errors = validateAccount(username, email, phoneNumber);

        if (errors.size() == 0) {
            // everything behaving well
            this.users.addUser(newUser);
            saveSettings();
        }
        return errors; // <-- empty list, if everything is correct
    }

    private ArrayList<String> validateAccount(String username, String email, String phoneNumber) {
        ArrayList<String> errors = new ArrayList<>(0);
        if (users.getUser(username) != null) {
            // username is already taken
            errors.add("Account Creation Error: Username already exists");
        }
        if (!phoneNumber.matches("^\\(\\d\\d\\d\\)-\\d\\d\\d-\\d\\d\\d\\d$")) {
            // phone number fails regex of form: (416)-123-4567
            errors.add("Account Creation Error: Phone Number is incorrect format");
        }
        if (!email.matches("^\\w+@\\w+\\.((com)|(ca))$")) {
            // email fails regex
            errors.add("Account Creation Error: E-mail is incorrect format");
        }
        if (users.phoneExists(phoneNumber)) {
            // phone number already exists
            errors.add("Account Creation Error: Phone Number already exists");
        }
        if (users.emailExists(email)) {
            // email already exists
            errors.add("Account Creation Error: Email already exists");
        }
        return errors;
    }

    /**
     * Runs a login attempt with username and password and sets currentUser to user specified by username.
     *
     * @param username username of this user
     * @param password password attempt for this login
     * @return whether password matches stored password for this user
     */
    public boolean loginAttempt(String username, String password) {
        User user = users.getUser(username);
        currentUser = new ViewProfile(user); // currentUser points to a ViewProfile for the user who is logged in
        return user.passwordMatches(password);
    }

    public String settingsToString() {
        return currentUser.settingsToString();
    }

    public String updateSettings(Map<String, String> settingsHash){
        return currentUser.updateSettings(settingsHash);
    }
}
