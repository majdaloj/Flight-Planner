import entities.User;
import org.junit.jupiter.api.Test;
import usecases.UserList;

import static org.junit.jupiter.api.Assertions.*;

class UserListTest {
    User u1 = new User("u1", "u1", "u1@gmail.com", "(123)-456-7890");
    User u2 = new User("u2", "u2", "u2@gmail.com", "(223)-456-7890");
    UserList ul = new UserList();

    @Test
    void TestAddUser() {
        ul.addUser(u1);
        assertEquals(ul.getUser("u1"), u1);
    }

    @Test
    void TestRemoveUser() {
        ul.addUser(u1);
        ul.addUser(u2);
        ul.removeUser(u1);
        assertNull(ul.getUser("u1"));
    }

    @Test
    void TestGetUser() {
        ul.addUser(u1);
        ul.addUser(u2);
        assertEquals(ul.getUser("u1"), u1);
        assertEquals(ul.getUser("u2"), u2);
    }

    @Test
    void TestEmailExists() {
        ul.addUser(u1);
        assertTrue(ul.emailExists("u1@gmail.com"));
        assertFalse(ul.emailExists("u2@gmail.com"));
    }

    @Test
    void TestPhoneExists() {
        ul.addUser(u2);
        assertTrue(ul.phoneExists("(223)-456-7890"));
        assertFalse(ul.phoneExists("(123)-456-7890"));
    }
}