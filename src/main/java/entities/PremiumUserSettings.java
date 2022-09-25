package entities;

import usecases.AirportReadWriter;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * PremiumUserSettings is responsible for implementing premium user features and actions which are defined in BaseUserSettings
 */

public class PremiumUserSettings implements BaseUserSettings, Serializable {
    private String classType;
    private Date renewalDate;
    private final User user;
    private String colorScheme;
    private Airport favouriteAirport;
    private int autoLogoutTimer;
    private Airport homeAirport;

    public PremiumUserSettings(User user) {
        this.user = user;
        this.classType = "None";
        Calendar c = Calendar.getInstance();
        c.add(Calendar.YEAR, 1);
        this.renewalDate = c.getTime();
        this.colorScheme = "default";
        this.autoLogoutTimer = 60;
        this.favouriteAirport = new Airport();
        this.homeAirport = new Airport();
    }

    public boolean setClassType(String classType) {
        this.classType = classType;
        return true;
    }

    public String getClassType() { return classType; }

    public Date getRenewalDate() { return renewalDate; }

    public boolean setRenewalDate(Date renewalDate) {
        this.renewalDate = renewalDate;
        return true;
    }

    public String getColorScheme() { return colorScheme; }

    public boolean setColorScheme(String colorScheme) {
        this.colorScheme = colorScheme;
        return true;
    }

    public Airport getFavouriteAirport() { return favouriteAirport; }

    public boolean setFavouriteAirport(Airport favouriteAirport) {
        this.favouriteAirport = favouriteAirport;
        return true;
    }

    public int getAutoLogoutTimer() { return autoLogoutTimer; }

    public boolean setAutoLogoutTimer(int autoLogoutTimer) {
        this.autoLogoutTimer = autoLogoutTimer;
        return true;
    }

    public Airport getHomeAirport() { return homeAirport; }

    public boolean setHomeAirport(Airport homeAirport) {
        this.homeAirport = homeAirport;
        return true;
    }

    public String upgradeUserType() {
        return "User Type is already Premium.";
    }

    /**
     * Downgrade PremiumUser to BasicUser by creating a new BasicUser and passing it to userManager
     * @return None
     */
    public String downgradeUserType() {
        this.user.changeUserType(new BasicUserSettings(this.user));
        return "User Type downgraded to Basic.";
    }

    /**
     * Update this user's settings given a HashMap of settings to be updated
     */
    public String updateSettings(Map<String, String> settingsHash) {
        DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        System.out.println(settingsHash);
        try{
            this.autoLogoutTimer = Integer.parseInt(settingsHash.get("Auto_Logout_Timer"));
            this.colorScheme = settingsHash.get("Color_Scheme");
            String class_type = settingsHash.get("Class_Type");
            if(class_type != null){
                this.classType = class_type;
            }

            // Returning early if we have basic settings passed in
            if(settingsHash.get("Renewal_Date") == null){
                return "true";
            }
            this.renewalDate = sdf.parse(settingsHash.get("Renewal_Date"));
            Airport favAirport = AirportReadWriter.getAirportByName(settingsHash.get("Favourite_Airport"));
            Airport homAirport = AirportReadWriter.getAirportByName(settingsHash.get("Home_Airport"));
            if(favAirport != null){
                this.favouriteAirport = favAirport;
            } else {
                return "Favourite airport is invalid.";
            }
            if(homAirport != null){
                this.homeAirport = homAirport;
            } else {
                return "Home airport is invalid.";
            }
            return "true";
        } catch (NumberFormatException nfe) {
            return "Invalid auto logout timer format.";
        } catch (ParseException e) {
            return "Invalid date format.";
        } catch (NullPointerException npe) {
            npe.printStackTrace();
            return "Null Pointer Exception";
        }
    }

    /**
     * Return this user's settings as a JSON parseable string
     */
    public String toJSONString() {

        DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

        return "{" +
                "\"userType\":" +
                "\"premium\"," +
                "\"Color_Scheme\":" +
                "\"" +
                this.colorScheme +
                "\"," +
                "\"Home_Airport\":" +
                "\"" +
                this.homeAirport.getCity() +
                "\"," +
                "\"Favourite_Airport\":" +
                "\"" +
                this.favouriteAirport.getCity() +
                "\"," +
                "\"Auto_Logout_Timer\":" +
                "\"" +
                this.autoLogoutTimer +
                "\"," +
                "\"Class_Type\":" +
                "\"" +
                this.classType +
                "\"," +
                "\"Renewal_Date\":" +
                "\"" +
                sdf.format(this.renewalDate) +
                "\"" +
                "}";
    }

    public User getUser(){
        return this.user;
    }
}
