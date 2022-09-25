package usecases;

import entities.Airport;
import entities.Flight;
import entities.Plane;
import gateway.InitializeDatabase;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class FlightReadWriterTest {

    @Before
    public void setUp() {
        InitializeDatabase.resetTestData();
    }

    @Test
    void getFlightList() {
        ArrayList<Flight> test = FlightReadWriter.getFlightList();
        assertTrue(test.get(0).getPlane().getBrandName().equals("Boeing 747"));
        assertTrue(test.get(2).getDuration() == 3);
    }

    @Test
    void postFlight() {
        GregorianCalendar date = new GregorianCalendar(2021, Calendar.DECEMBER, 8);
        Airport a1 = new Airport("Houston", "006");
        Airport a2 = new Airport("Sydney", "007");
        Plane p = new Plane("Shuttle", 1337, 15, 1337 - 15, true, "004");
        Flight toAdd = new Flight(date, p, 150, 12, a1, a2);

        FlightReadWriter.postFlight(toAdd);

        ArrayList<Flight> list = FlightReadWriter.getFlightList();
        Flight test = list.get(list.size()-1);
        assertEquals(test.getDuration(), 12);
        assertEquals(test.getPrice(), 150);
        assertEquals(test.getSourceAirport().getCity(), "Houston");
    }

    @After
    public void terminate() {
        InitializeDatabase.resetTestData();
    }
}