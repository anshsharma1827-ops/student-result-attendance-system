# Student Result & Attendance Management System

A desktop application built using Java Swing and MySQL to manage student records, attendance, and academic results.

## Features
- Admin login system
- Add new students
- View all students in a table
- Search student by roll number
- Delete student records
- Mark daily attendance (Present/Absent)
- Calculate attendance percentage automatically
- View student results with auto-calculated grades

## Tech Stack
- **Language:** Java
- **UI:** Java Swing
- **Database:** MySQL
- **Connectivity:** JDBC (Java Database Connectivity)

## How It Works
1. Admin logs in with username and password
2. Dashboard opens with 5 main options
3. Each option opens its own window connected live to the MySQL database
4. All data (students, attendance, results) is stored and retrieved in real-time

## Database Structure
- **students** table - stores roll number, name, course, email
- **attendance** table - stores roll number, date, status
- **results** table - stores roll number, subject, marks

## What I Learned
- Connecting Java applications to MySQL using JDBC
- Building desktop UI using Java Swing
- Writing and debugging SQL queries (JOIN, GROUP BY, aggregate functions)
- Handling real-world errors and exceptions
- Structuring code into reusable methods

## Author
Ansh Sharma - BVoc Software Development, Ramanujan College, University of Delhi
