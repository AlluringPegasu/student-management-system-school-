# Student Management System (JDBC)

A console-based Java application built using **JDBC (Java Database Connectivity)** to manage student records.

--FEATURES
* **Add Student:** Insert new student records with specific Department IDs.
* **View Students:** Display a list of all students.
* **Update Email:** Modify an existing student's email using their unique ID.
* **Delete Student:** Permanently remove a student record from the system.

--PREREQUISITES
1. Database Configuration
Before running the application, make sure your database server is active and contains the necessary schema. Run the provided sql file to set up the relational tables:

2. Connection Configuration
Open the DBconnection.java file and update the database URL, username, and password fields to match your local database settings.

2. Add the Driver
add the JDBC jar file to your IDE's build path,

--PROJECT STRUCTURE
* StudentManagement.java - Contains the main menu loop and all the database SQL queries.
* DBconnection.java - Handles connecting the code to the database server.
* database.sql - The file containing the table structures and starter data.
