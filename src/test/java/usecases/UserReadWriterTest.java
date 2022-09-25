package usecases;

import entities.User;
import gateway.InitializeDatabase;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UserReadWriterTest {

    @Before
    public void setUp() {
        InitializeDatabase.resetTestData();
    }

    @Test
    void readFromFile() {
        InitializeDatabase.resetTestData();
        UserList userData = UserReadWriter.readFromFile();
        User test = userData.getUser("user1");
        assertEquals(test.getUsername(), "user1");
        assertEquals(test.getPassword(), "111");
        assertEquals(test.getPhoneNumber(), "(416)-000-0001");
    }

    @Test
    void saveToFile() {
        UserList userData = new UserList();
        userData.addUser(new User("user4", "123", "sana@twice.ca", "(647)-555-1234"));
        UserReadWriter.saveToFile(userData);

        UserList testData = UserReadWriter.readFromFile();
        User test = testData.getUser("user4");
        assertEquals(test.getUsername(), "user4");
        assertEquals(test.getPassword(), "123");
        assertEquals(test.getEmail(), "sana@twice.ca");
        assertEquals(test.getPhoneNumber(), "(647)-555-1234");
    }

    @After
    public void terminate() {
        InitializeDatabase.resetTestData();
    }
}