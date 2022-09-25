package entities;

import java.util.Date;
import java.util.Map;

/**
 * BaseUserSettings defines all methods across BasicUserSettings and PremiumUserSettings with different implementations
 * depending on user type.
 */

public interface BaseUserSettings {
    boolean setClassType(String classType);
    String getClassType();
    Date getRenewalDate();
    boolean setRenewalDate(Date renewalDate);
    String getColorScheme();
    boolean setColorScheme(String colorScheme);
    Airport getFavouriteAirport();
    boolean setFavouriteAirport(Airport favAirport);
    int getAutoLogoutTimer();
    boolean setAutoLogoutTimer(int autoLogoutTimer);
    Airport getHomeAirport();
    boolean setHomeAirport(Airport homeAirport);
    String upgradeUserType();
    String downgradeUserType();
    String updateSettings(Map<String, String> settingsHash);
    String toJSONString();
    User getUser();
}
