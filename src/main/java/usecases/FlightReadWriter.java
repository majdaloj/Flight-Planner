package usecases;

import entities.Flight;
import gateway.InteractDatabase;

import java.util.ArrayList;
import java.util.Calendar;

public class FlightReadWriter {
    public static ArrayList<Flight> getFlightList() {
        // Returns list of Airports
        return InteractDatabase.fetchList(Flight.class);
    }

    public static void postFlight(Flight toStore) {
        // Serializes <toStore>
        InteractDatabase.post(toStore, Flight.class);
    }

}
