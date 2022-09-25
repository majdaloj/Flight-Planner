package usecases;

import entities.User;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * A map of user id -> User objects.
 */
public class UserList implements Serializable {
    private final Map<String, User> userList = new HashMap<>();

    /**
     * Add a user to the user list.
     *
     * @param user the UserManager to be added
     */
    public void addUser(User user) {
        userList.put(user.getUsername(), user);
    }

    /**
     * Remove a user from the user list.
     *
     * @param user the UserManager to be removed
     */
    public void removeUser(User user) {
        userList.remove(user.getUsername());
    }

    /**
     * Get the UserManager by username
     *
     * @param username the username of the user to get
     */
    public User getUser(String username) {
        return userList.get(username);
    }

    /**
     * Returns true if the given email exists in the system.  False otherwise
     *
     * @param email the email to compare
     * @return if the email exists
     */
    public boolean emailExists(String email) {
        for (User account : userList.values()) {
            if (account.getEmail().equals(email)) {
                // the email exists
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if the given phone number exists in the system.  False otherwise
     *
     * @param phoneNumber the phone number to compare
     * @return if the phone number exists
     */
    public boolean phoneExists(String phoneNumber) {
        for (User account : userList.values()) {
            if (account.getPhoneNumber().equals(phoneNumber)) {
                // phone number exists
                return true;
            }
        }
        return false;
    }

}
