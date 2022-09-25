package entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Represents a scheduled flight. Creates an object that contains the scheduled date, a plane object, the cost of
 * the flight, its duration, and the airports it is departing from and flying to.
 */

public class Flight implements Serializable {
    private final Calendar date;
    private final Plane plane;
    private final double price;
    private final double duration;
    private final Airport sourceAirport;
    private final Airport destinationAirport;

    public Flight(Calendar date, Plane plane, double price, long duration, Airport source, Airport destination) {
        this.date = date;
        this.plane = plane;
        this.price = price;
        this.duration = duration;
        this.sourceAirport = source;
        this.destinationAirport = destination;
    }

    public Calendar getDate() {
        return date;
    }

    public Plane getPlane() {
        return this.plane;
    }

    public double getPrice() {
        return this.price;
    }

    public double getDuration() {
        return this.duration;
    }

    public Airport getSourceAirport() {
        return this.sourceAirport;
    }

    public Airport getDestinationAirport() {
        return this.destinationAirport;
    }


    public String toString() {

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

        StringBuilder returnString = new StringBuilder();

        returnString.append("{");

        // Adding departure date
        returnString.append("\"departureDate\": \"").append(sdf.format(getDate().getTime())).append("\", ");

        //Adding plane details
        returnString.append("\"plane\": ").append(getPlane()).append(", ");

        // Adding price
        returnString.append("\"price\": ").append(getPrice()).append(", ");
        // Adding duration
        returnString.append("\"duration\": ").append(getDuration()).append(", ");

        // Adding source airport
        returnString.append("\"sourceAirport\": ");
        returnString.append(getSourceAirport().toString()).append(", ");

        // Adding destination airport
        returnString.append("\"destinationAirport\": ");
        returnString.append(getDestinationAirport().toString());

        returnString.append("} ");

        return new String(returnString);
    }
}
