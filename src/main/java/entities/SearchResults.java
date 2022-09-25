package entities;

import java.util.Comparator;
import java.util.List;

/**
 * An object that returns search results and information on results.
 *
 * Includes returning potential routes sorted by price, duration, location etc.
 */

public class SearchResults {

    private List<Route> potentialRoutes;

    public SearchResults(List<Route> potentialRoutes) {
        this.potentialRoutes = potentialRoutes;
    }

    public List<Route> getPotentialRoutes() {
        return potentialRoutes;
    }

    public void setPotentialRoutes(List<Route> potentialRoutes) {
        this.potentialRoutes = potentialRoutes;
    }

    public String getPrice(Route route) {
        return route.toString();
    }

    public void sortByPrice() {
        potentialRoutes.sort(Comparator.comparingDouble(Route::getPriceOfFlights));
    }

    public void sortByDuration() {
        potentialRoutes.sort(Comparator.comparingDouble(Route::getTotalDuration));
    }

    /**
     * Returns a StringBuilder object that is parseable and contains all the information for every route
     * in its list of routes.
     * @return String
     */
    public String toString(User u) {
        if(this.potentialRoutes.isEmpty()){
            return "";
        }
        StringBuilder returnString = new StringBuilder("[");
        int idCounter = 0;
        for (Route route : potentialRoutes) {
            // Adding id
            while (route.getRouteID() == -1) {
                if (!u.existingRouteId.contains(idCounter)) {
                    route.setRouteID(idCounter);
                }
                idCounter++;
            }

            returnString.append(route);
            returnString.append(",");
        }
        returnString.setLength(returnString.length() - 1);
        returnString.append("]");

        return new String(returnString);
    }
}