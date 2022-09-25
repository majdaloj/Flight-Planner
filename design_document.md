# Kamamans: Flight Planner

Create a web-based interface that lets users join  a  platform named Flight Planner.  Users of Flight Planner  should be allowed to join at specific tiers that offer different  benefits. Each user has an ID (username), password, email, and phone number. Users should have access to their information and the ability to edit their own attributes.  Running the platform starts the web-based interface that allows the user to interact with a login page. While running, the interface starts by letting the user log in or sign-up verifying credentials. Credentials are case sensitive to increase security.

While logged in, the users can  search for and select a flight using data from an external API. Flights  contain a date, plane entity, price, duration, departure location, and destination location. Plane entities  contain a brand name and  capacity for  distinct types of passengers. Routes should combine multiple flights so that a user can get from their departure to their destination even using multiple flights. The platform will source its flight data from a current API called aviation stack and will use real-life, real-time flights. The platform  will save selected flights per users’ choices to a centralized database (currently a csv), to allow for user’s flight history to accumulate. The state of the user will be saved after logging out and closing the web-based interface.


##  Solid

One of the design principles we use is the Single Responsibility Principle. In our code we have the class UserManager which is responsible for common methods and attributes across all users. The SRP states that each class should have only one responsibility and therefore we had no way to store, add, remove, or access users without violating the Single Responsibility Principle. Therefore, we ended up creating UserList which is a short concise class that allows for mapping of Users for easy access. Although this design is quite simple it achieves our goal for having concise classes/objects that are responsible for a single task only.

Another design principle we originally used was the Liskov Substitution Principle. This will be discussed more later in our Design Pattern Summary. The LSP states that if a program module uses a Base class (which is what we ended up using to differentiate between Basic/Premium Users) then it could potentially be replaced with a derived class without affecting the function of the program module. However, we violated this to use the State Design Pattern as both premium user and basic user are implementations of Base User. The SOLID principle used here is just an extension of the Open Close Principle since we are making sure the newly derived classes (users) are extending the base class (the simplest Base User) without changing its behaviour.

## UML Diagram

![alt text](https://github.com/CSC207-UofT/course-project-kamamans/blob/main/FINAL%20UML%20207.png)

## Clean Architecture

Based on feedback we renamed the UserManager to User, which removes confusion on whether it is a use case class or entity. Restructuring has been done to move UserList into the use case folder and we have kept our two controllers, PlanFlight and UserController, very separate. Removed the “routeBackend” lines with more clearly named calls (airport server, plane server etc.). Continued to use HTML and JS files as presenters, automatically separating presenters from the controller classes.

Our project adheres to Clean Architecture as seen by our UML diagram. Our entities are objects that are used within our Flight Planner System to solidify what can be manipulated, recorded, and changed. Our use cases are the only classes that manipulate our entities. We will now go over a walkthrough to show our updated clean architecture:

To enter the application, a user must first log in with the User Controller, which goes through the LoginHandler and UserReadWriter to pull up existing users and/or make a new account. In the UserList we store user’s credentials and uses InteractDatabase to verify that the user’s information is valid. If the user exists, then they have the option to book a new flight. UserController interacts with PlanFlight  (the adjacent controller) to begin inputting the user’s search specifications. These search specifications are then used by InteractDatabase and AllPossibleFlights to find any flights that meet the  requirements. Through the backend we take the given information received by InteractDatabase, and we search through a sorted list of all current flights taken from the API, using the APILayer, and return those that meet the specifications.

Once the user finds an appropriate flight, the selected flight is given to SearchResults, which contains all the information related to the airport, plane, flight, and route. ViewProfile  then takes this information to store it. Through the backend we take the given the information received by InteractDatabase, we store the flight information to the associated user’s account.

## Design Patterns

There were two main design patterns at the core of our program, one of which we used and another which we decided not to.

The most prominent design pattern we have implemented is a structural design pattern called State. The state design pattern is meant to allow an object to changes behaviour in a way that switches classes. The object would be able to be characterized real-time and accomplish different things depending on its current state.

Currently our group has having an issue on how to control having a basicUser account and premiumUser account. We originally had two classes but ran into the issue that when a user upgrades and/or downgrades extra internal states must be created and/or are lost. Currently we have a Boolean inside our basicUser class that keeps track of whether or not an account is premium, and we manually check to allow for that specific user to access premium methods/settings. We are looking for a simpler method to encapsulate a user’s information (when premium) so when they downgrade and later upgrade their settings/methods/bookmarks/capabilities are all restored to the internal state it had previous.

![alt text](https://github.com/CSC207-UofT/course-project-kamamans/blob/main/design%20pattern%20207.png)

Currently we have our User’s encapsulated in a “wrapper” class which is the User Manager. This means that it’s hierarchy mirrors everything with the exception of the single extra parameter, in this case it’s whether it’s a basic user or not. This means we do not lose any information, simply lose access to the functions that would allow us to access/change and therefore it will not allow any behavioural changes other than the ones we specify.

We will look into more design patterns for significant code sections when we begin incorporating the GUI more heavily (chain of responsibility for controlling the way of passing requests between a chain of objects) and when we implement more complicated search methods (template method design pattern to defer very exact steps of algorithms for searching).

After deciding to implement the state design pattern, we in turn decided not to use memento.  We were considering memento as an alternative option to solving the basicUser and premiumUser account issue we previously described above. The memento design pattern is meant to provide the ability for the program to restore an object to a previous state that it has held before. This works using three different classes: the originator, the caretaker and the memento. The originator is the class that makes the memento and can access it, this allows it to save the current internal state for a later time. The caretaker is the class that asks the originator for the memento object, therefore playing the role of a ‘client’ that requests the memento (to save the internal states) and to pass a memento back (to restore the saved internal states). Lastly, the memento class itself creates an object where only the originator can access the states and not the caretaker where the internal states are saved. Combining these three classes together creates a software pattern that allows the client to save and restore internal states that doesn’t violate the encapsulation.

We had derived the following approach for implementing the memento design pattern in our program:

![alt text](https://github.com/CSC207-UofT/course-project-kamamans/blob/main/memento%20design%20pattern.png)

We would create a User Memento (which could be an interface that basicUser implements) to create a method that (a) gets all the states compiled [getStates] and (b) overwrite the pre-existing states [potential constructor method]. In this way we would have a temporary Userstate saved.

We agreed that this method is more plausible if we decided to store all our Users not locally (therefore when undoing/restoring a user) we can directly override the User’s most current state. Then we came to the realization that we’d have an issue in conversing the user database and trying to alter it at the same time. Further,  if we did decide  to store our Users locally then there would no way for us to keep track of the mementos and its states. In addition, it’s unclear what classes would oversee each role (and if it’s possible for it to play the role).

We had a lengthy meeting as a team trying to decide if memento was the optimal pattern for our program. We made a pros and cons list, taking into consideration if the positives would outweigh the negatives. They did not. The few positives we agreed would result is that it would be a very straight forward approach since it creates only two new classes to take over the role of caretaker and memento. It could also prevent overlap between the current classes we had and their roles. But these benefits paled in comparison to the issues we would face when using memento. First of all, using this design pattern would require extensive refactoring of the code to allow for  premiumUser (which would be an entity) to prevent ViewProfile from directly interacting with basicUser. Secondly our program currently doesn’t have a “degrade” or “ExpireClass”. The memento design pattern doesn’t explicitly say how the Caretaker tells the Originator to restore, so we’d have to work that out which would also take a lot of time for little payback.

In the end, we decided that using memento would not be a feasible approach, as the amount of restructuring and refactoring needed to logistically make it work would take too long and possibly not even solve our problem, proving to be an inefficient use of our time and resources. Thus, the state design pattern reigned victorious in being the pattern we chose to implement in our final design.

## Use of Github Features

Our group used the various features of GitHub to facilitate the development of our code such as pull requests. Through our pull requests we were able to review, comment and resolve merge conflicts. This was helpful since we had a few issues with merging, specifically some IDE-related files were pushed; this was solved by either choosing to overwrite or adding those files to the gitignore file. The other merge requests had a few slight issues due to code conflicts but those were solved quickly.

## Code Style and Documentation

We addressed major errors that would pop up when our code is opened in IntelliJ. In entities folder,  the majority of the errors that appear are unused functions (left for the sake of potential expansion of program later) and typos (of terms that refer to airports, planes etc.). In use cases, the majority of errors that appear are again unused functions, typos and recommendations of changing explicit type arguments to be replaced by <>. All classes have need javadoc describing class roles, and functions that are not self-explanatory also have accompanying javadoc.

## Testing

The testing classes that we created are for the main entities (Airport, Flight, Plane, Route, SearchResults, and User). These are all simply checking that the methods that are the basis of our program work and return the expected values. In addition, we have DemoApplicationTests and KamamansApplicationTests that now give us the ability to test the Frameworks and Drivers layer in clean architecture.

## Refactoring

Pull request no.1: https://github.com/CSC207-UofT/course-project-kamamans/pull/30 

In this pull request we refactored the way searched the routes. Originally, we had a depth first search that wasn’t appropriate for usage of the front-end since it returned unorganized jsons, broke rules of clean architecture and had multiple code smells. Some code smells that existed were couplers (between controllers and use cases) and bloaters (which we fixed by using helper methods, which were then delegated useless and removed later). 

Pull request no.2: https://github.com/CSC207-UofT/course-project-kamamans/pull/45 

In this pull request we refactored database interact into a gateway to address the issues brought up by a TA during our presentation. Originally, we had Interact Database as a controller due to its nature following on as an application business rules. We consequentially refactored user data, mitigated storage of information to a higher clean architecture layer, and refactored a few other classes and how they interact with other classes to follow clean architecture. 

A few other pull requests include: 
- https://github.com/CSC207-UofT/course-project-kamamans/pull/44 
- For fixing the way we searched the graph as well, including changing endpoints that connected frontend to back end 
- https://github.com/CSC207-UofT/course-project-kamamans/pull/38 
- Creating a graph entity to help with linking plane, airport, route etc. In a way that would produce organized jsons to be able to link with database 

## Code Organization

Our code is organized in a meaningful way since there are two root folders: main and test. In test general test files are kept in the general java folder while the demo and kamamans tests are kept in their respective folder.

In the main folder we separate our java code from our web code. In web the HTML, JS and CSS files are all clear separated in respective folders. Our java folders show we clearly follow clean architecture since entities, use cases, controllers and frameworks are separated.

We decided to organize our code this way to allow for our team separation when working on different sections of the project (frontend vs backend) and to allow for more simple refactoring (entity changes vs adding/deleting different usecases).

## Functionality

During our presentation we have done a live demo of program functionality. It included closing the program and reopening to the same saved state (creating a new account, closing program, opening program, logging into the same account), as well as searching for different flights and adding them and removing them from to our user’s profile. We displayed the ability to update new information into the user's profile that would remained saved after closing.

## Project Accessibility Report
### Equitable Use
This program allows all users (basic and user) to search for flights and save them to their profile. This provides a usable and accessible baseline that only “charges” for being able to customize to match their taste.
### Flexibility in Use
This program has limited amount of flexibility in use, but since most of our program requires typing (for searching) and non-timed mouse handling (clicking clear buttons) it accommodates right and left-hand access.
### Simple and Intuitive Use
Our program uses simple words and descriptions and does not use symbols to represent order buttons (sort by duration, price etc.). There are no unnecessary loading screens or complexity.
### Perceptive Information
Our program does not fully follow perceptive information since being on computer (browser) is required. However, we are in the process of implementing adequate contrast between pages colour, text colour and fields for fill-in colours.
### Tolerance for Error
Has fail safe features such as pop ups that notify users that the actions they were attempting failed (whether it be logging in, failing to save information to their profile etc.).
### Low Physical Effort
We could maximize our low physical effort by removing repetitive actions of filling in fields when saving information. For example, including a button that says “auto-fill” could save users time and effort from having to type all the information again.
### Size and Space for Approach and Use
Buttons and fields to fill in are all large enough and are in a clear line of sight for all users (standing/sitting). They are all reachable and do not require increased accuracy of mouse movements.

## Marketability
Our program would be marketed towards people who fly often. We have considered two options:

The first option is a business flyer approach. This means that businessmen/women who go on work flights are more likely to fly the same paths and therefore saving their favourite airports (for departure, destination and layovers) and routes (from which city to city) would save them more time when they want to book their flights

Our second option is a hobby flyer approach. People who want to plan out multiple flights through certain cities/airports this would be very helpful. Unlike business flyers they would be less likely to save routes for the sake of reusing them, but could be for memory’s sake and compiling a list of routes they’ve flown over the years.

## Alienated Demographics

Our program is less likely to be used by the demographics that is hard of sight. Our program heavily relies on visual listings, logins and searches and currently we have no way implemented of reading out the program to aid in reaching that demographic. We have implemented using meta tags that can be used with other programs to make it more accessible for that demographic. In addition, another demographic that could but is less likely to use our program are those who are under 18 since our program is meant for those who are able to book their own flights and pay for them (not implemented).

## PROGRESS REPORT

- **Majda:**
  - Part of the backend team. Implemented and tested the Plane Database.
  - Implemented and tested the find all routes algorithm.
  - Worked with Salwa to create presentations.
  - Reviewed other pull requests relevant to the backend. Helped with documentation and updated the CRC cards.
  - [https://github.com/CSC207-UofT/course-project-kamamans/pull/30](https://github.com/CSC207-UofT/course-project-kamamans/pull/30)
  - PR showing major contribution to creating depth first search (allowing for different search priorities of price, duration, location etc.) 
  - [https://github.com/CSC207-UofT/course-project-kamamans/pull/9](https://github.com/CSC207-UofT/course-project-kamamans/pull/9 )
  - Setting up Flight, Plane entities that remained crucial to clean architecture. Includes creating accopanying test classes 

- **Salwa:**
  - Created the basis of the frontend websites including starting the LoginPage, ProfileMakingPage, SearchFlight, BookingConfirmationPage, UpdateProfilePage.
  - Created the design documentation, UML diagram, design pattern diagram and presentation
  - [https://github.com/CSC207-UofT/course-project-kamamans/pull/33/](https://github.com/CSC207-UofT/course-project-kamamans/pull/33)
  - PR showing one of the contributions to setting up the backend (HTML, JS, CSS files and functions required for connecting)
  - [https://github.com/CSC207-UofT/course-project-kamamans/pull/1](https://github.com/CSC207-UofT/course-project-kamamans/pull/1)
  - First PR (along with #3, #4) set up the basis of the program setting up our team to follow clean architecture

- **Kevin:**
  - Finished adding the new frontend webpages (for viewing/editing profiles and booking flights) and worked with Nathan to connect requests to endpoints.
  - Helped implement user controller methods and add unique IDs to routes. Reviewed and merged pull  requests.
  - [https://github.com/CSC207-UofT/course-project-kamamans/pull/32](https://github.com/CSC207-UofT/course-project-kamamans/pull/32)
  - [https://github.com/CSC207-UofT/course-project-kamamans/pull/17](https://github.com/CSC207-UofT/course-project-kamamans/pull/17)
  - In these PRs, I pushed the full front end (disconnected from backend) and linked them to JavaScript files. I ensured that the web interface could export the relevant data (user inputs) and display the necessary outputs.

- **Nathan:**
  - Created More endpoints in the backend to add functionality to the front end and then worked with Kevin to send requests to those end points from the front end.
  - Also called usercontroller methods in the endpoints and expanded on some of the ways they were implemented, for example, making it so every route has a unique ID for each user for ease in selecting, adding and deleting them in our database backend.
  - [https://github.com/CSC207-UofT/course-project-kamamans/pull/37](https://github.com/CSC207-UofT/course-project-kamamans/pull/37)
  - Basically, connected the front and backend and put a lot of parts together.

-  **Albert:**
   - Created user signup functionality in the backend.  Refactored InteractDatabase to behave better with Airport, Flight, and Plane Data with a more robust database.
   - [https://github.com/CSC207-UofT/course-project-kamamans/pull/30](https://github.com/CSC207-UofT/course-project-kamamans/pull/30)
   - https://github.com/CSC207-UofT/course-project-kamamans/pull/31
   - Refactored algorithm into distinct and AllPossibleFlight and Graph classes which finds routes through a graph matrix.
   - Created unit tests for Graph class. Worked with frontend team to resolve implementation bugs.

- **Marian:**
  - Implemented user functionality (BaseUserSettings, BasicUserSettings, PremiumUserSettings) and aligned with CLEAN architecture by creating appropriate use case classes (UserList, UserReadWriter, LoginHandler, ViewProfile) for specific responsibilities.
  - Refactored so that UserController contains all specifications outlined by frontend team. Created methods to parse JSONs into appropriate entities (Route, Flight, Airport).
  - [https://github.com/CSC207-UofT/course-project-kamamans/pull/27](https://github.com/CSC207-UofT/course-project-kamamans/pull/27)
  - PR showing (almost) complete implementation of user methods so that UserController is the only class interacting with the presenter/UI
  - [https://github.com/CSC207-UofT/course-project-kamamans/pull/15](https://github.com/CSC207-UofT/course-project-kamamans/pull/15)
  - PR showing State design pattern refactoring for users

- **Andrew:**
  - Implemented the Route class as well as writing RouteTest. Worked on javadoc and comments for all classes and method, and reviewed code.
  - Part of the backend team helped implement the Route database. Reviewed backend pull requests.
  - [https://github.com/CSC207-UofT/course-project-kamamans/tree/backend](https://github.com/CSC207-UofT/course-project-kamamans/tree/backend)
  - The original code for the routebackend and the route class. Significant as it was the base for one of the larger components of the backend.

- **Andrei:**
  - Created endpoints to connect to the API to retrieve data. Parsed JSON files to obtain data, and serialized objects to be able to store them in the database for retrieval later. Creating tests for methods in the program (wip).
