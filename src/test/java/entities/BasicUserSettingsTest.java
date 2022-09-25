package entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class BasicUserSettingsTest {
    BaseUserSettings BUS;
    @BeforeEach
    void setUp() {
        User u = new User("Bob", "123", "bob@gmail.com", "(416)-132-3242");
        this.BUS = new BasicUserSettings(u);
    }

    @Test
    void updateSettings() {
        HashMap<String, String> settings = new HashMap<>();
        settings.put("Auto_Logout_Timer", "10");
        settings.put("Color_Scheme", "Black");
        BUS.updateSettings(settings);
        assertEquals(BUS.getAutoLogoutTimer(), 10);
        assertEquals(BUS.getColorScheme(), "Black");
    }

    @Test
    void toJSONString() {
        assertEquals(this.BUS.toJSONString(), "{\"userType\":\"basic\",\"Color_Scheme\":\"default\",\"Auto_Logout_Timer\":\"60\"}");
    }

}