import entities.User;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


import usecases.LoginHandler;
import controller.UserController;
import usecases.ViewProfile;

public class UserTest {
    LoginHandler um = new LoginHandler();
    UserController uc = new UserController(um);
    User u = new User("user01", "1234", "user01@gmail.com", "1234");
    ViewProfile vp = new ViewProfile(u);

    @Before
    public void setUp() {
        uc.createAccount("user01", "1234", "user01@gmail.com", "1234");
    }

    @Test(timeout = 50)
    public void TestBasicUserDowngrade() {
        assertEquals("User Type is already Basic.", uc.downgradeUserType());
    }

    @Test(timeout = 50)
    public void TestBasicUserUpgrade() {
        assertEquals("User Type upgraded to Premium.", uc.upgradeUserType());
    }

    @Test(timeout = 50)
    public void TestPremiumUserDowngrade() {
        assertEquals("User Type upgraded to Premium.", uc.upgradeUserType());
        assertEquals("User Type downgraded to Basic.", uc.downgradeUserType());
    }

    @Test(timeout = 50)
    public void TestViewProfileUpgrade() {
        assertEquals("User Type upgraded to Premium.", vp.upgradeUserType());
    }

    @Test(timeout = 50)
    public void TestViewProfileDowngrade() {
        vp.upgradeUserType();
        assertEquals("User Type downgraded to Basic.", vp.downgradeUserType());
    }
}
