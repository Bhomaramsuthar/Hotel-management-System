Hotel Management System
Description
This is a desktop-based Hotel Management System developed in Java with a MySQL database. The application is designed to help hotel staff manage various daily operations, including room and customer records, employee details, and billing.

Features-
Role-Based Login: Secure login for Admin and regular users.

Room Management: Add, update, and search for rooms.

Customer Management: Add new customers and update existing records.

Staff & Employee Management: View and search for all employee details.

Driver Management: Track and manage driver information.

Billing System:

Add extra fees for services like mini-bar or laundry.

Calculate and display pending amounts at checkout.

Checkout Process: Check out guests and automatically update room and customer statuses.

Prerequisites-
To run this project, you need to have the following installed:

Java Development Kit (JDK) version 8 or higher.

MySQL Database for storing all hotel data.

MySQL Connector/J to connect Java to MySQL.

rs2xml.jar library for displaying database data in a JTable.

Setup Instructions-
1. Database Setup
   Open your MySQL command-line tool or a client like MySQL Workbench.

Copy and paste the entire content of the database.sql file from this repository into your client.

Execute the script. This will create the hotelmanagementsystem database and all the necessary tables with some initial data.

2. Project Configuration
   Clone this repository to your local machine.

Open the project in your preferred IDE (e.g., IntelliJ, Eclipse, NetBeans).

Ensure the mysql-connector-java-8.0.28.jar and rs2xml.jar files are in your project's build path.

In your connects.java file, update the database connection URL with your MySQL username and password.

3. Run the Application
   Run the main class to start the application. The login window will appear.

Use the following credentials to test the role-based login:

Admin: username: Tom, password: Tom

User: username: Jerry, password: Jerry

Contributions-
Feel free to open an issue or submit a pull request if you find any bugs or have suggestions for improvements.