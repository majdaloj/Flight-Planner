import entities.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import static org.junit.Assert.*;
import gateway.InteractDatabase;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class SearchResultsTest {

    public Route r1;
    public Route r2;
    public Route r3;
    public ArrayList<Route> routes = new ArrayList<>();
    public SearchResults sq;

    @Before
    public void setUpRoutes(){
        // Airport Data
        Airport a1 = new Airport("Toronto", "001");
        Airport a2 = new Airport("Montreal", "002");
        Airport a3 = new Airport("Vancouver", "003");

        // Plane Data
        Plane p1 = new Plane("Boeing 747", 223, 7, 223-7, true, "B747");
        Plane p2 = new Plane("Apollo 11", 1738, 12, 1738-12, true, "A11");
        Plane p3 = new Plane("Falcon 1", 1337, 15, 1337-15, true, "F1");

        // Flight Data
        GregorianCalendar feb14 = new GregorianCalendar(2022, Calendar.FEBRUARY, 14);
        Flight f1 = new Flight(feb14, p1, 1, 2, a1, a2);
        Flight f2 = new Flight(feb14, p2, 3, 4, a2, a3);
        Flight f3 = new Flight(feb14, p3, 5, 6, a3, a1);

        // Route Data
        ArrayList<Flight> list1 = new ArrayList<>();
        list1.add(f1);
        list1.add(f2);
        Route r1 = new Route(a1, a3, feb14, list1); // Toronto --> Montreal --> Vancouver

        ArrayList<Flight> list2 = new ArrayList<>();
        list2.add(f2);
        list2.add(f3);
        Route r2 = new Route(a2, a1, feb14, list2); // Montreal --> Vancouver --> Toronto

        ArrayList<Flight> list3 = new ArrayList<>();
        list3.add(f3);
        list3.add(f1);
        Route r3 = new Route(a3, a2, feb14, list3); // Vancouver --> Toronto --> Montreal

        // Save routes for testing
        routes.add(r1);
        routes.add(r2);
        routes.add(r3);
    }

    @Before
    public void setUpSearchQueries(){
        sq = new SearchResults(routes);
    }

    @Test(timeout = 50)
    public void TestFlightsSortedByPrice() {
        sq.sortByPrice();

        ArrayList<Route> routesa = new ArrayList<>();
        routesa.add(r3);
        routesa.add(r1);
        routesa.add(r2);
        SearchResults sqa = new SearchResults(routesa);

        double a = sq.getPotentialRoutes().get(0).getPriceOfFlights();
        double b = sq.getPotentialRoutes().get(1).getPriceOfFlights();
        double c = sq.getPotentialRoutes().get(2).getPriceOfFlights();

        assertTrue((a <= b) && (b <= c));
    }

    @Test(timeout = 50)
    public void TestFlightsSortedByDuration() {
        sq.sortByDuration();

        ArrayList<Route> routesa = new ArrayList<>();
        routesa.add(r3);
        routesa.add(r1);
        routesa.add(r2);
        SearchResults sqa = new SearchResults(routesa);

        assertEquals(sq.getPotentialRoutes().get(0), sqa.getPotentialRoutes().get(0));
        assertEquals(sq.getPotentialRoutes().get(1), sqa.getPotentialRoutes().get(1));
        assertEquals(sq.getPotentialRoutes().get(2), sqa.getPotentialRoutes().get(2));
    }


}
