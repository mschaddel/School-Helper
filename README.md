School-Helper
=============
Requirements 

Overview:

We want to build an Android app that will help with school. We want to make a check-in system for classes and a homework tracker. The check-in system would use the user's location to verify that they are in class. The homework tracker would allow the student to add and track their homework, as well as possibly turn in their homework to the professor or receive homework from their professor via a bluetooth transmission. We also want to use alerts to notify the user of homework due dates and classes. We will prioritize making features for the students first and then if time permits we will add a separate account type for professors. Therefore, all professor features discussed in the requirements will be optional.

Requirement 1: User Interface (UI)

Requirement 1a: Login Screen (Optional)
Logging in will require an email and password.
Requirement 1b: Main Menu
List of tasks that can be accessed.
Requirement 1c: Attendance Screen
Has multiple sub screens to fulfill the Attendance System requirements.
Requirement 1d: Homework Screen
Has multiple sub screens to fulfill the Homework System requirements.
Requirement 1e: Settings Menu
Gives user the capability to change how the app works.

Requirement 2: Attendance System

Requirement 2a: Input Schedule
Students will first need to set their schedule and class locations in the app.

	When the student attempts to check into classes, the app will first verify the student’s location to ensure that the student is actually at class via the following methods:

Requirement 2b: via Location Services
Will use location services to verify location
Location services includes GPS and WiFi.
Requirement 2c: via Bluetooth Handshake (Optional)
Will connect to the professor’s bluetooth device to verify attendance
Requirement 2d: via NFC (Optional)
A NFC Tag will be used to open a special activity that verifies attendance
	
The student will have the capabilities to keep track of their own attendance.  Another feature we would like to add is providing the professor the capability of keeping track of their student’s attendance.

Requirement 3: Homework System

Requirement 3a: Homework Tracking
Students can enter homework details and keep track of due dates.
Optional: Homework due dates can be pushed to calendar.
Requirement 3b: Homework Storage
Students can keep a copy of their homework on the app.
Students will be able to view their stored homework files via the app.
Requirement 3c: Homework Turn In (Optional)
Student turns in their homework via Bluetooth to the professor.

Requirement 4: Notification/Alert System

	The method of notification can be changed within the settings page.

Requirement 4a: Homework Notifications
Will notify students of approaching homework due dates
Requirement 4b: Class Notification
Will alert students a set time prior to class
Requirement 4c: Test Notification (Optional)
Will notify students of approaching test dates

Requirement 5: Database System

	An embedded database to store attendance, homework, and essentially all information used by the app.

Requirement 6: Class Document Storage

	Allows students and professors to store their class documents, such as the class syllabus, on the app. Students will also be able to view these documents via the app. The location of storing can be determined via the settings page. Students can take a picture of their graded homework to keep track of their grades.

Requirement 7: Login System (Optional)

	Used to keep track of user information. Allows user to choose between creating a student or a professor account.
