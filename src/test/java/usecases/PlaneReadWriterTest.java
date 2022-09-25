package usecases;

import entities.Plane;
import gateway.InitializeDatabase;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PlaneReadWriterTest {

    @Before
    public void setUp() {
        InitializeDatabase.resetTestData();
    }

    @Test
    void getPlaneList() {
        ArrayList<Plane> list = PlaneReadWriter.getPlaneList();
        Plane test = list.get(2);
        assertEquals(test.getBrandName(), "Falcon 1");
        assertTrue(test.getHasVacantSeats());
        assertEquals(test.getIataCode(), "003");
        assertEquals(test.getSeatCount(), 1337);
    }

    @After
    public void terminate() {
        InitializeDatabase.resetTestData();
    }
}