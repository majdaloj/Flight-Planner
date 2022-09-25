package com.example.demo;

import controller.PlanFlight;
import controller.UserController;
import entities.Airport;
import entities.Route;
import entities.SearchResults;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import usecases.AirportReadWriter;
import usecases.LoginHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Class to set up application for future use.
 * Consists of multiple functions that tie together database (springboot application) and hmtl/js code
 */

@CrossOrigin(origins = "*")
@SpringBootApplication
@RestController
public class DemoApplication {
    private final LoginHandler us = new LoginHandler();
    private final UserController uc = new UserController(us);
    private SearchResults sr;
    private Route selectedRoute;

    public void runDemo(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @GetMapping("/login")
    public String login(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password) {
        try {
            return (String.valueOf(uc.login(username, password)));
        } catch (NullPointerException e) {
            return ("false");
        }
    }

    @GetMapping("/createAccount")
    public String createAccount(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password,
                                @RequestParam(value = "repeatPassword") String repeatPassword, @RequestParam(value = "email") String email,
                                @RequestParam(value = "phoneNumber") String phoneNumber) {
		if(repeatPassword.equals(password)) {
			return uc.createAccount(username, password, email, phoneNumber);
		} else {
			return "Passwords don't match.";
		}
    }

    @GetMapping("/searchFlight")
    public String searchFlight(@RequestParam(value = "departure") String departure, @RequestParam(value = "destination") String destination,
                               @RequestParam(value = "date") String date) {
        if (departure.equals("")) {
            return ("Missing departure");
        }
        if (destination.equals("")) {
            return ("Missing destination");
        }
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
        try {
            cal.setTime(sdf.parse(date));
        } catch (ParseException e) {
            return ("Invalid Date Format");
        }
        Airport departureAirport = AirportReadWriter.getAirportByName(departure);

        Airport destinationAirport = AirportReadWriter.getAirportByName(destination);
        if (departureAirport == null) {
            return ("Departure airport not found");
        }
        if (destinationAirport == null) {
            return ("Destination airport not found");
        }
        this.sr = PlanFlight.EnterSearchRequirements(cal, departureAirport, destinationAirport);

        return null;
    }

	@GetMapping("/getPotentialFlights")
	public String getPotentialFlights() {
		return this.sr.toString(uc.getCurrentUser());
	}
	@GetMapping("/getPotentialFlightsByDuration")
	public String getPotentialFlightsByDuration() {
		sr.sortByDuration();
		return this.sr.toString(uc.getCurrentUser());
	}
	@GetMapping("/getPotentialFlightsByPrice")
	public String getPotentialFlightsByPrice() {
		sr.sortByPrice();
		return this.sr.toString(uc.getCurrentUser());
	}
	@GetMapping("/selectFlight")
	public String selectFlight(@RequestParam(value = "id") String id) {
		 for (Route r : this.sr.getPotentialRoutes()){
			 if (r.getRouteID() == Integer.parseInt(id)){
				 this.selectedRoute = r;
			 }
		 }
		return("true");
	}
	@GetMapping("/getSelectedFlight")
	public String getSelectedFlight() {
		return(this.selectedRoute.toString());
	}
	@GetMapping("/bookFlight")
	public String bookFlight(@RequestParam(value = "firstName") String firstName, @RequestParam(value = "lastName") String lastName,
							 @RequestParam(value = "phoneNumber") String phoneNumber, @RequestParam(value = "email") String email,
							 @RequestParam(value = "dateOfBirth") String dateOfBirth) {
		// Unused parameters kept for future features

		uc.addRouteToHistory(this.selectedRoute);
		System.out.println("booked flight with id = "+this.selectedRoute.getRouteID());
		return "it worked!!!";
	}
	@GetMapping("/getUserData")
	public String getUserData() {
		return uc.getUserDataJson();
	}
	@GetMapping("/getUserSettings")
	public String getUserSettings() {
		return uc.getUserSettingsJson();
	}
	@GetMapping("/UpdateAndSaveUserInformation")
	public String UpdateAndSaveUserInformation(@RequestParam(value = "username") String username, @RequestParam(value = "password") String  password,
											@RequestParam(value = "email") String  email,
											@RequestParam(value = "phoneNumber") String  phoneNumber) {
		// Unused parameter kept for consistency and potential to change username

		uc.setPassword(password);
		uc.setEmail(email);
		uc.setPhoneNumber(phoneNumber);
		uc.saveSettings();
		return ("true");
	}

	@GetMapping("/UpdateAndSaveUserSettings")
	public String UpdateAndSaveUserSettings(@RequestParam(value="settingsDict") String settingsDict){

		String[] keyValuePairs = settingsDict.split(",");
		Map<String, String> settingsHash = new HashMap<>();

		for (String pair: keyValuePairs)
		{
			String[] setting = pair.split(":");
			settingsHash.put(setting[0].trim().replace("\"",""), setting[1].trim().replace("\"",""));
		}

		if(settingsHash.get("userType").equals("premium")
				&& uc.upgradeUserType().equals("User Type downgraded to Basic.")){
			uc.saveSettings();
		} else if (settingsHash.get("userType").equals("basic")
				&& uc.downgradeUserType().equals("User Type upgraded to Premium.")){
			uc.saveSettings();
		}

		String returnString = uc.updateSettings(settingsHash);

		uc.saveSettings();

		return returnString;
	}

	@GetMapping("/viewRouteHistory")
	public String viewRouteHistory() {
		return uc.getRouteHistory();
	}
	@GetMapping("/deleteRoute")
	public String viewRouteHistory(@RequestParam(value = "id") String id) {
		uc.removeRouteByID(id);
		return "removed route";
	}

}


            