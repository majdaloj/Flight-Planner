package entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.List;

/**
 * An object representing the path which a traveller takes to a certain destination.
 *
 * Storing the departure and destination airports, the departure date of the first flight, and a list of one or
 * more flights to the final destination.
 */
public class Route implements Serializable {
    private Airport departureAirport;
    private Airport destinationAirport;
    private Calendar departureDate;
    private final List<Flight> flights;
    private int routeID = -1;

    public Route(Airport departureAirport, Airport destinationAirport, Calendar departureDate, List<Flight> flights) {
        this.departureAirport = departureAirport;
        this.destinationAirport = destinationAirport;
        this.departureDate = departureDate;
        this.flights = flights;
    }

    public Airport getDepartureAirport() {
        return this.departureAirport;
    }

    public Airport getDestinationAirport() {
        return this.destinationAirport;
    }

    public void setDepartureAirport(Airport newDepartureAirport) {
        this.departureAirport = newDepartureAirport;
    }

    public void setDestinationAirport(Airport newDestinationAirport) {
        this.destinationAirport = newDestinationAirport;
    }

    public Calendar getDepartureDate() {
        return this.departureDate;
    }

    public void setDepartureDate(Calendar departureDate) {
        this.departureDate = departureDate;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public double getPriceOfFlights(){
        double p = 0;
        for (Flight f : this.flights) {
            p = p + f.getPrice();
        }
        return p;
    }

    public double getTotalDuration() {
        double d = 0;
        for (Flight f : this.flights) {
            d = d + f.getDuration();
        }
        return d;
    }

    public int getRouteID() {
        return this.routeID;
    }

    public void setRouteID(int routeID) {
        this.routeID = routeID;
    }

    public String toString() {
        StringBuilder returnString = new StringBuilder();

        returnString.append("{");

        // Adding departure airport
        returnString.append("\"departureAirport\": ").append(departureAirport.toString()).append(", ");
        // Adding destination airport
        returnString.append("\"destinationAirport\": ").append(destinationAirport.toString()).append(", ");

        // Adding departure date
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        returnString.append("\"departureDate\": \"").append(sdf.format(getDepartureDate().getTime())).append("\", ");

        // Adding flights
        returnString.append("\"flights\": [");
        for (Flight flight : getFlights()) {

            returnString.append(flight.toString());

            returnString.append(", ");
        }
        returnString.setLength(returnString.length() - 2);
        returnString.append("], ");

        // Adding price
        returnString.append("\"price\": ").append(getPriceOfFlights()).append(", ");
        // Adding duration
        returnString.append("\"duration\": ").append(getTotalDuration()).append(", ");
        // Adding id

        returnString.append("\"id\": \"").append(getRouteID()).append("\"");

        returnString.append("}");

        return new String(returnString);

    }
}
