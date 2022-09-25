package entities;

import java.io.Serializable;
import java.util.*;

/**
 * BasicUserSettings is responsible for implementing basic user actions which are defined in BaseUserSettings
 */

public class BasicUserSettings implements BaseUserSettings, Serializable {
    private final User user;
    private String colorScheme;
    private int autoLogoutTimer;


    public BasicUserSettings(User user) {
        this.user = user;
        this.colorScheme = "default";
        this.autoLogoutTimer = 60;
    }

    public String getColorScheme() {
        return this.colorScheme;
    }

    public boolean setColorScheme(String colorScheme) { return false; }

    public int getAutoLogoutTimer() {
        return this.autoLogoutTimer;
    }

    public boolean setAutoLogoutTimer(int autoLogoutTimer) { return false; }

    public Airport getHomeAirport() { return null; }

    public boolean setHomeAirport(Airport homeAirport) { return false; }

    public String downgradeUserType() {
        return "User Type is already Basic.";
    }

    public String getClassType() { return null; }

    public boolean setClassType(String classType) {
        return false;
    }

    public Date getRenewalDate() { return null; }

    public boolean setRenewalDate(Date renewalDate) {
        return false;
    }

    public Airport getFavouriteAirport() { return null; }

    public boolean setFavouriteAirport(Airport favouriteAirport) {
        return false;
    }

    /**
     * Upgrade BasicUser to PremiumUser by creating a new PremiumUser and passing it to userManager
     * @return String
     */
    public String upgradeUserType() {
        this.user.changeUserType(new PremiumUserSettings(this.user));
        return "User Type upgraded to Premium.";
    }

    /**
     * Update this user's settings given a HashMap of settings to be updated
     */
    public String updateSettings(Map<String, String> settingsHash) {
        try{
            this.autoLogoutTimer = Integer.parseInt(settingsHash.get("Auto_Logout_Timer"));
            this.colorScheme = settingsHash.get("Color_Scheme");
            return "true";
        } catch (NumberFormatException nfe) {
            return "Invalid auto logout timer format.";
        }
    }

    /**
     * Return this user's settings as a JSON parseable string
     */
    public String toJSONString() {
        return "{" +
                "\"userType\":" +
                "\"basic\"," +
                "\"Color_Scheme\":" +
                "\"" +
                this.colorScheme +
                "\"," +
                "\"Auto_Logout_Timer\":" +
                "\"" +
                this.autoLogoutTimer +
                "\"" +
                "}";
    }

    public User getUser() {
        return this.user;
    }

}