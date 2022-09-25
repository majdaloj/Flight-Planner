import entities.Plane;
import entities.Route;
import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.*;


public class PlaneTest {
    Plane p;

    @Before
    public void setup() throws Exception {
        // create an instance of Plane

        p = new Plane("Airbus A321", 220, 20,
                200, true, "A321");
    }

    @Test(timeout = 50)

    public void TestGetBrandName(){
        assertEquals("Airbus A321", p.getBrandName());
    }

    @Test(timeout = 50)
    public void TestGetSeatCount(){
        assertEquals(220, p.getSeatCount());
    }

    @Test(timeout = 50)
    public void TestGetFirstClassSeats(){
        assert(20 == p.getFirstClassSeats());
    }

    @Test(timeout = 50)
    public void TestGetEconomySeats(){
        assertEquals("Airbus A321", p.getBrandName());
    }

    @Test(timeout = 50)
    public void TestGetHasVacantSeats(){
        assertTrue(p.getHasVacantSeats());
    }


}
