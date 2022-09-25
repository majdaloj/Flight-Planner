**a brief summary of your specification, CRC model, scenario walk-through, and skeleton program:**

Our project Flight Planner will be a web-based interface to allow users to log in, search, book and plan out real time flights using aviation stack API. Our program relies on 6 basic entities, 4 use cases, 2 controllers, 1 basic command line interface and various miscellaneous classes and unit tests. Their relationships can be seen looking at the crc dependency graph (first slide of crc cards.pdf) and are colour coded depending on their type (red: entities, blue: use cases, green: controllers, black: misc.). 

![alt text](https://github.com/CSC207-UofT/course-project-kamamans/blob/main/skeleton_walkthrough_example.png)

Above is an example of the user keshi logging into our program to select a flight. As our scenario walkthrough says, the user is asked for credentials, credentials are verified. In the case of our skeleton code, we automatically give the user the list of flights available. They are allowed to select for the flights based on (departure city, destination city, date) using the route #. This successfully goes through terminal interface, controllers, use cases and uses almost all of our entities. 

**open questions your group is struggling with:**

* We originally had a premium user that extended from basicUser, but switched to boolean to signify their level; how to make sure all “premium functions” are only accessible to premium level users? Requires boolean to passed into every function (redundant), or is there a better method? 
* How/where exactly will SearchResults store/pass of the search result to be stored into user history? Will it be centralized around each individual user or will there be a mass database for all users? 

**what has worked well so far with your design:**

* Defining functions and classes at their appropriate “usage” makes navigating through and putting all the code together much easier.  
* The classes have a certain “hierarchy” that constructs from the most basic classes upwards because a “Route” is very complex. 

**a brief summary of what each group member has been working on and plans to work on next:**

Overall, everyone contributed to creating preliminary CRC cards for program. 

1. Salwa Abdalla: Created CRC dependency graph. Formatted general repository (all blank files). Completed Airport entity. In charge of basicUser, loginInformation and premiumSettings and their respective unit tests. Created specifications.md, progress_report.md, and helped curate tutorial presentation. Next steps are to implement all basicUser and premiumSettings methods fully, to complete ViewProfile as well as keep dependency graph updated. 
2. Nathan Zavys-Cox: Implemented the PlanFlight class. Planning on implementing more communication with the backend for saving flights chosen to data to the database. 
3. Kevin Lai: Implemented TerminalInterface, PremiumSettings, SearchResults. Fixed EditUser --> ViewProfile and Route Class. Merged all branches and fixed compatibility issues. Updated classes with missing Route implementations. Next steps are to replace the TerminalInterface with an application frontend that interacts with the other classes and implements untouched capabilities.  
4. Majda Lojpur: Implemented the Flight and Plane classes. Also implemented FlightTest and PlaneTest. Reviewed other classes and updated the final version of the CRC cards and specifications documentation. Updated specifications.md, progress_report.md and designed tutorial presentation. Next steps are to implement all basicUser and premiumSetting methods and connect fully to ViewProfile.  
5. Marian Wang: Implemented UserController class and beginnings of TerminalInterface. Planning to work on integrating backend with user accounts and data storage, as well as designing the frontend. 
6. Albert Zhao: Implemented InteractDatabase, so far InteractDatabase has only dummy informations for skeleton run. Also reviewed other Pull Requests to see if they align with program specifications.  Next steps are setup a backend to fetch information from with InteractDatabase.
7. Andrew: Worked on the Route and Edituser classes which got condensed into a single basicUser class. Planning to work on commenting for user and route classes. 
8. Andrei Vilcan: In charge of reviewing pull requests and making sure no major issues go through, and that they are consistent with crc cards. Planning on continuing to make sure there are no issues with future pull requests and merges. 
