import entities.Airport;
import entities.Flight;
import entities.Plane;
import entities.Route;

import java.util.Calendar;
import java.util.Date;

import org.junit.*;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class FlightTest {
    Flight f;
    Plane p;
    Route r1;
    Route r2;
    Airport a1;
    Airport a2;


    @Before
    public void setup() throws Exception {

        // create an instance of Plane

        p = new Plane("Airbus A321", 220, 20,
                200, true, "A321");

        // create two instances of Airport (one source and one destination point of the flight)

        ArrayList<Route> routes = new ArrayList<>();
        routes.add(r1);
        routes.add(r2);

        a1 = new Airport("Toronto", "YYZ");
        a2 = new Airport("London", "YXU");

        // create an instance of Flight
        //Generate a date for Jan. 9, 2021, 10:11:12 AM
        Calendar cal = Calendar.getInstance();
        cal.set(2021, Calendar.JANUARY, 9, 10, 11, 12); //Year, month, day of month, hours, minutes and seconds
        f = new Flight(cal, p, 1, 4, a1, a2);
    }

    @Test(timeout = 50)

    public void TestGetPlane(){
        assertEquals(p, f.getPlane());
    }

    @Test(timeout = 50)
    public void TestGetDate(){
        //Generate a date for Jan. 9, 2021, 10:11:12 AM
        Calendar cal = Calendar.getInstance();
        cal.set(2021, Calendar.JANUARY, 9, 10, 11, 12); //Year, month, day of month, hours, minutes and seconds

        assertEquals(cal, f.getDate());
    }

    @Test(timeout = 50)
    public void TestGetDuration(){
        assert(4 == f.getDuration());
    }

    @Test(timeout = 50)
    public void TestGetSource(){
        assert(a1 == f.getSourceAirport());
    }

    @Test(timeout = 50)
    public void TestGetDestination(){
        assert(a2 == f.getDestinationAirport());
    }


}
