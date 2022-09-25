package usecases;

import entities.Airport;
import gateway.InitializeDatabase;
import gateway.InteractDatabase;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AirportReadWriterTest {
    @Before
    public void setUp() {
        InitializeDatabase.resetTestData();
    }

    @Test
    void postAirport() {
        Airport toAdd = new Airport("New York", "679");
        AirportReadWriter.postAirport(toAdd);
        ArrayList<Airport> list = InteractDatabase.fetchList(Airport.class);
        Airport test = list.get(list.size()-1);
        assertTrue(test.getIataCode().equals("679"));
        assertTrue(test.getCity().equals("New York"));
    }

    @Test
    void getAirportByIata() {
        Airport toAdd = new Airport("New York", "679");
        AirportReadWriter.postAirport(toAdd);
        Airport test = AirportReadWriter.getAirportByIata("679");
        assertTrue(test.getIataCode().equals("679"));
    }

    @Test
    void getAirportByName() {
        Airport toAdd = new Airport("New York", "679");
        AirportReadWriter.postAirport(toAdd);
        Airport test = AirportReadWriter.getAirportByName("New York");
        assertTrue(test.getCity().equals("New York"));
    }

    @After
    public void terminate() {
        InitializeDatabase.resetTestData();
    }
}