package entities;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class PremiumUserSettingsTest {
    BaseUserSettings PUS;

    @Test
    void updateSettings() {
        User u = new User("Bob", "123", "bob@gmail.com", "(416)-132-3242");
        BaseUserSettings PUS = new PremiumUserSettings(u);
        HashMap<String, String> settings = new HashMap<>();
        settings.put("Auto_Logout_Timer", "10");
        settings.put("Color_Scheme", "Black");
        settings.put("Class_Type", "basic");
        settings.put("Favourite_Airport", "Toronto");
        settings.put("Home_Airport", "Toronto");
        settings.put("Renewal_Date", "12/12/2002");
        PUS.updateSettings(settings);
        assertEquals(PUS.getAutoLogoutTimer(), 10);
        assertEquals(PUS.getColorScheme(), "Black");
    }

    @Test
    void toJSONString() {
        User u2 = new User("Bob", "123", "bob@gmail.com", "(416)-132-3242");
        BaseUserSettings PUS = new PremiumUserSettings(u2);
        assertEquals(PUS.toJSONString(), "{\"userType\":\"premium\",\"Color_Scheme\":\"default\",\"Home_Airport\":\"\",\"Favourite_Airport\":\"\",\"Auto_Logout_Timer\":\"60\",\"Class_Type\":\"None\",\"Renewal_Date\":\"12/08/2022\"}");
    }
}