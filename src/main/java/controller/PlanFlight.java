package controller;

import entities.*;
import usecases.AllPossibleFlights;

import java.util.*;

/**
 * A class responsible for booking a user a flight
 */

public class PlanFlight {

    public static SearchResults EnterSearchRequirements(Calendar departureDate, Airport departure, Airport destination) {

        AllPossibleFlights output = new AllPossibleFlights(departureDate);
        return output.getRoutes(departure, destination);

    }

}