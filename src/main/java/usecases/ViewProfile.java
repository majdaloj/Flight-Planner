package usecases;

import entities.Airport;
import entities.Route;
import entities.SearchResults;
import entities.User;
import org.json.JSONException;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * ViewProfile handles editing user profile information and different methods based on user subscription status.
 */

public class ViewProfile {
    private final User currentUser;

    public ViewProfile(User user) {
        currentUser = user;
    }

    public String getUsername() {
        return currentUser.getUsername();
    }

    public String getPassword() {
        return currentUser.getPassword();
    }

    public void setPassword(String password) {
        currentUser.setPassword(password);
    }

    public String getEmail() {
        return currentUser.getEmail();
    }

    public void setEmail(String email) {
        currentUser.setEmail(email);
    }

    public String getPhoneNumber() {
        return currentUser.getPhoneNumber();
    }

    public void setPhoneNumber(String phoneNumber) {
        currentUser.setPhoneNumber(phoneNumber);
    }

    public int getAppRating() {
        return currentUser.getAppRating();
    }

    public void setAppRating(int appRating) {
        currentUser.setAppRating(appRating);
    }

    public void addRouteToHistory(Route routeJSON) throws JSONException, ParseException {
        currentUser.addRouteToHistory(routeJSON);


    }

    public User getCurrentUser() {
        return this.currentUser;
    }

    public void removeRoutebyID(String id) {
        currentUser.removeRoutebyID(id);
    }

    /**
     * Return the users route history in a json parseable string using the method in SearchResults.
     *
     * @return StringBuilder of the users route history in json parseable String format
     */
    public String getRouteHistory() {
        List<Route> routeHistory = currentUser.getRouteHistory();
        SearchResults routeHistoryResults = new SearchResults(routeHistory);
        return routeHistoryResults.toString(this.getCurrentUser());
    }

    public String upgradeUserType() {
        return currentUser.upgradeUserType();
    }

    public String downgradeUserType() {
        return currentUser.downgradeUserType();
    }

    public String getUserType() {
        return currentUser.getUserType();
    }

    // methods past here are premium user functions, so they check user type
    public String isValidRequest(boolean valid) {
        if (!valid) {
            return "Not available for Basic Users. Upgrade to Premium today!";
        } else {
            return "Successfully changed.";
        }
    }

    public String getClassType() {
        return currentUser.userSettings.getClassType();
    }

    public String setClassType(String classType) { return isValidRequest(currentUser.userSettings.setClassType(classType)); }

    public Date getRenewalDate() {
        return currentUser.userSettings.getRenewalDate();
    }

    public String setRenewalDate(Date date) { return isValidRequest(currentUser.userSettings.setRenewalDate(date)); }

    public String getColorScheme() { return currentUser.userSettings.getColorScheme(); }

    public String setColorScheme(String colorScheme) {
        return isValidRequest(currentUser.userSettings.setColorScheme(colorScheme));
    }

    public Airport getFavouriteAirport() {
        return currentUser.userSettings.getFavouriteAirport();
    }

    public boolean setFavouriteAirport(Airport airport) { return currentUser.userSettings.setFavouriteAirport(airport); }

    public int getAutoLogoutTimer() { return currentUser.userSettings.getAutoLogoutTimer(); }

    public String setAutoLogoutTimer(int autoLogoutTimer) {
        return isValidRequest(currentUser.userSettings.setAutoLogoutTimer(autoLogoutTimer));
    }

    public String getHomeAirport() {
        return currentUser.userSettings.getHomeAirport().toString();
    }

    public String setHomeAirport(Airport airport) { return isValidRequest(currentUser.userSettings.setHomeAirport(airport)); }

    public String settingsToString() {
        return currentUser.settingsToString();
    }

    public String updateSettings(Map<String, String> settingsHash) {
        return currentUser.updateSettings(settingsHash);
    }
}
