To enter the application, a user must first login using the UserController, which interacts with EditUser. BasicUser stores the user’s credentials and uses InteractDatabase to verify that the user’s information is valid. If the user exists, then they have the option to book a new flight. UserController interacts with PlanFlight to begin inputting the user’s search specifications. These search specifications are then used by SearchFlights to find any flights that meet the requirements.  

Backend: Given the information received by SearchFlight, we search through a sorted list of all current flights taken from the API (currently hard-coded) and return those that meet the specifications. 

Once the user finds an appropriate flight, the selected flight is given to StoreFlight, which contains all the information related to the airport, plane, flight, and route. EditUser then takes this information and uses InteractDatabase to store it. 

Backend: Given the information received by StoreFlight, we store the flight information to the associated user’s account.