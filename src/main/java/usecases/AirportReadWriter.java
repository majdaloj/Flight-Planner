package usecases;

import entities.Airport;
import gateway.InteractDatabase;

import java.util.ArrayList;

public class AirportReadWriter {
    public static ArrayList<Airport> getAirportList() {
        // Returns list of Airports
        return InteractDatabase.fetchList(Airport.class);
    }

    public static void postAirport(Airport toStore) {
        // Serializes <toStore>
        InteractDatabase.post(toStore, Airport.class);
    }

    public static Airport getAirportByIata(String iataCode) {
        ArrayList<Airport> airportList = getAirportList();
        for (Airport airport : airportList) {
            if (airport.getIataCode().equals(iataCode)) {
                return airport;
            }
        }
        return null;
    }

    public static Airport getAirportByName(String name) {
        if (name.equals("")){
            return new Airport();
        }
        ArrayList<Airport> airportList = getAirportList();
        for (Airport airport : airportList) {
            if (airport.getCity().toLowerCase().contains(name.toLowerCase())) {
                return airport;
            }
        }
        return null;
    }

}
