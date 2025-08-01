#  Book Hive — Java Console Application

Book Hive is a console-based Java application that helps users manage a digital library of books.
You can add books, update details, search by title or genre, and view your entire collection — all from a simple menu system.
---

##  Features

-  Add new books with automatic ISBN-13 generation
-  Remove books by title
-  Update book fields (`title`, `author`, `genre`)
-  Search for a single book by title
-  Search multiple books by genre
-  Custom exception handling for invalid inputs
-  Cleanly separated UI, logic, and data layers using interfaces
-  Fully testable and extensible design

---

##  Tech Stack

- Java 17+
- Console-based UI
- OOP Design (Encapsulation, Interfaces, Abstraction)
- Custom Exceptions
- JUnit 5 (for unit testing)

---

##  Project Structure


BookHive/
│
├── exceptions/
├── model/
├── service/
├── test/
├── ui/
├── util/
└── com.nate.app.Main.java

---

Here’s what running the app looks like:

Welcome to Book Hive!

1) Add Book
2) Remove Book
3) Update Book
4) View All Books
5) Search by Title
6) Search by Genre
7) Exit

---

##  Future Improvements

- Store books in a file or database (JDBC)
- JSON persistence using Gson
- GUI using JavaFX
- Author sorting and genre grouping features

---

## Build and Test

This is a maven project. To build and run test:

`bash
mvn clean

Make sure you have java 17 and maven installed

To run this application:
    mvn exec:java
        or
    mvn exec:java -Dexec.mainClass="com.nate.app.Main"

--


## Author

 Nate Pombi
[GitHub](https://github.com/NatePombi)
Aspiring Java Developer | Passionate about clean code and real-world applications.

---



