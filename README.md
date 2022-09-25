# flight-planner
A web-based interface that uses real life flight data to view, search and book flights

## Table of contents
* [General info](#general-info)
* [Languages and Sources](#languages-and-sources)
* [Credit](#credit)

## General info
A mixture of java, html, javascript, and css project that lets users join a platform named Flight Planner. Users of Flight Planner should be allowed to join at specific tiers that offer different benefits. Each user has an ID (username), password, email, and phone number. Users should have access to their information and the ability to edit their own attributes. Running the platform starts the web-based interface that allows the user to interact with a login page. While running, the interface starts by letting the user log in or sign-up verifying credentials. Credentials are case sensitive to increase security.

While logged in, the users can search for and select a flight using data from an external API. Flights contain a date, plane entity, price, duration, departure location, and destination location. Plane entities contain a brand name and capacity for distinct types of passengers. Routes should combine multiple flights so that a user can get from their departure to their destination even using multiple flights. The platform will source its flight data from a current API called aviation stack and will use real-life, real-time flights. The platform will save selected flights per users’ choices to a centralized database (currently a csv), to allow for user’s flight history to accumulate. The state of the user will be saved after logging out and closing the web-based interface.
 
Launching interface requires activating back-end API and launching demo class.

## Languages and Sources
Project is created with:
* java
* javascript
* html
* css

## Credit
Project completed with 6 major contributors. Personally responsible for entity creation, complete documentation and front end.
Submitted for csc207.
