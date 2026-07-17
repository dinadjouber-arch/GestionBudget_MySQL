# Budget Manager

A Java Swing desktop application designed to help users manage their personal expenses and monitor their budget through an intuitive graphical interface connected to a MySQL database.

---

# Features

* Create and update a budget.
*  Add new expenses.
*  Edit existing expenses.
*  Delete expenses.
*  Organize expenses using categories.
*  Manage payment methods.
*  Display spending statistics.
*  Calculate the remaining budget automatically.
*  Visual warning when the budget becomes negative.
*  Store all data in a MySQL database.

---

# Technologies

* Java 17
* Java Swing
* JDBC
* MySQL
* IntelliJ IDEA

---

# Database

The application relies on a MySQL relational database composed of the following tables:

* `budget`
* `depense`
* `categorie`
* `moyen_paiement`

These tables are linked together to ensure consistent and efficient data management.

---


## Home Dashboard

*Overview of the application's main interface.*

screenshot 

---

## Budget Management

*Screen displaying the current budget and remaining amount.*

screenshot 
---

## Add an Expense

*Form used to add a new expense.*
screenshot 
---

## Expense List

*Table displaying all recorded expenses.*

screenshot 
---

## Category Management

*Interface for creating and managing expense categories.*

screenshot 

---

## Payment Methods

*Manage the available payment methods.*

screenshot 

---

## Statistics

*Charts and indicators summarizing expenses and budget usage.*

screenshot 

---

## Empty Database

*The application when no expenses have been recorded yet.*

screenshot 
---

## Negative Budget

*Example showing the warning displayed when expenses exceed the available budget.*

screenshot 

---

# Future Improvements

* Income management.
* Monthly filtering.
* Expense search.
* Data export (PDF/Excel).
* User authentication.
* Dark mode.

---

# Installation

1. Clone the repository.
2. Create the MySQL database.
3. Import the SQL script.
4. Configure the database connection.
5. Run the project.

---

# Author

Developed by **Pinguin** as a university project to practice Java programming, object-oriented design, Swing GUI development, JDBC, and MySQL database management.

