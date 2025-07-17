package com.nate.ui;

import com.nate.exceptions.BookNotFoundException;
import com.nate.exceptions.InvalidGenreException;
import com.nate.exceptions.InvalidUpdateField;
import com.nate.model.Genre;
import com.nate.model.IBook;
import com.nate.service.Management;
import com.nate.util.ExceptionPrinting;

import java.util.List;
import java.util.Scanner;

public class Start {
    private final Scanner scanner;
    private final Management management;
    private final Menu menu;
    private final ExceptionPrinting exceptionPrinting;
    public Start(Management management,Menu menu, Scanner scanner, ExceptionPrinting exceptionPrinting){
        this.management = management;
        this.scanner = scanner;
        this.menu = menu;
        this.exceptionPrinting = exceptionPrinting;
    }

    //Runs the main application loop, displaying the menu and handling user input
    public void runApp() throws InterruptedException {
        boolean cont = true;
        try {
            while (cont) {
                //displays main menu
                menu.menu();
                //prompts user to choose an action
                int choice = menu.promptMenuMessage("Enter a number from the actions above: ");
                switch (choice) {
                    case 1 -> addBook();           //adding new book to the system
                    case 2 -> removeBook();        //removes an existing book by title
                    case 3 -> updateBook();        //updating an existing book fields e.g(author,title,genre)
                    case 4 -> viewAllBooks();      //views all existing books
                    case 5 -> searchBook();        //search book by title
                    case 6 -> searchBooks();       //search book a collective of books by genre
                    case 7 -> {
                        //exits the app if user approves
                        if (exitApp()) {
                            System.out.println("GoodBye");
                            cont = false; //stops the loop
                        }
                    } // Handles invalid input
                    default -> throw new IllegalArgumentException("Invalid menu selection. Enter valid number"); //throws an exception when an invalid number is entered
                }
            }
        }

        catch (IllegalArgumentException e){
            // printing the error message using the exceptionPrinting utility
            exceptionPrinting.exceptions(e);
        }
    }

    // Adds a new book to the system by collecting input and delegating it to the service layer
    private void addBook(){
       try {
           // prompts user for book details
           String title =  menu.promptMessage("Enter the title: ");
           String author = menu.promptMessage("Enter author name: ");
           String genre =  menu.promptMessage("Enter the genre: ");

           //
           management.addBook(title,author,genre);

           //Confirm successfully addition
           System.out.println("Successfully added a book");

       }
       catch (IllegalArgumentException | NullPointerException | InvalidGenreException e){
           // handle input and genre exceptions gracefully
            exceptionPrinting.exceptions(e);
       }




    }

    // Handles user input and removes a book by delegating it to the service layer
    private void removeBook(){
        try {
            //prompts user for title to remove
            String title = menu.promptMessage("Enter the title of the book you want to remove: ");

            //delegate removal to management system
            management.removeBook(title);

            //Notify user of successful removal
            System.out.println("You have successfully Removed a book");

        }
        catch (BookNotFoundException | NullPointerException e){
            //Print error if book does not exist or input is invalid
            exceptionPrinting.exceptions(e);
        }


    }

    //Retrieves and displays all books by delegating to service layer
    private void viewAllBooks(){
        menu.printList(management.viewAllBooks());
    }

    //Prompts user for book title and displays the results by delegating it to service layer
    private void searchBook(){
        try{
            //prompts user input for book title
            String title = menu.promptMessage("Enter the title of the book you searching for: ");

            //delegates search to management system and collects the book to the book object
            IBook book = management.searchBook(title);

            //prints out the book object
            System.out.println(book);
        }
        catch(NullPointerException | BookNotFoundException e){
            //Prints error if book does not exist or invalid input
            exceptionPrinting.exceptions(e);
        }
    }

    //Prompts user for book title and displays the results by delegating it to service layer
    private void searchBooks(){
        try{
            //Prompts user input for title
            String genre = menu.promptMessage("Enter the genre of the books you want to search: ");

            //delegates retrieval of books to the management system and collects them into a list
            List<IBook> books = management.searchBooksByGenre(genre);

            //Prints out each book by each field
            for(IBook bk : books){
                System.out.println("ISBN: " + bk.getISBN());  //prints the ISBN
                System.out.println("Title: " + bk.getTitle()); //prints title
                System.out.println("Author: " + bk.getAuthor()); //prints author
                System.out.println("Genre: " + bk.getGenre().toString()); //prints genre
            }
        }
        catch (NullPointerException | InvalidGenreException e){
            //prints error message if genre or null point exception triggers
            exceptionPrinting.exceptions(e);
        }
    }

    //handles the exiting of the app
    private boolean exitApp() throws InterruptedException {

        //prompts user input for confirmation if they want to exit
        String input = menu.promptMessage("Are you sure you want to exit (Y/N)");

        //checks the input if user confirms exiting
        if (input.equalsIgnoreCase("y")){
            System.out.print("exiting");

            //displays exiting animation
            for(int i = 0; i<3; i++){
                Thread.sleep(500);
                System.out.print(".");
            }
            System.out.println();
            return true;
        }
        return  false;
    }

    //Handles updating the books by delegating it to service layer
    private void updateBook(){
        try {
            //prompts user input for book title
            String bookTitle = menu.promptMessage("Enter the name of the book you want to update: ");

            //prompts user input for field to update
            int fieldWantUpdated = menu.promptMenuMessage("Enter the number for the field you want to be update. [1.Title, 2.Author, 3.Genre] : ");

            //checks the field that the user entered
           String field = switch (fieldWantUpdated){
                case 1 -> "Title";
                case 2 -> "Author";
                case 3 -> "Genre";
               default -> throw new InvalidUpdateField();
            };

           //checks if user entered the genre field
           if(field.equalsIgnoreCase("genre")){
               Genre.getGenre(field); //checks if it's a valid genre type if not it throws an InvalidGenreException
           }

           //Prompts user input for field you want to change
            String updateFieldTo = menu.promptMessage("Enter what you want it updated to: ");

           //delegates update to management system
            management.updateDetails(bookTitle,field,updateFieldTo);

            //Notifies user of successful update
            System.out.println("You have successfully updated the book");

        }
        catch (NullPointerException | BookNotFoundException | InvalidGenreException | InvalidUpdateField e){
            //prints the error message if book is not found , invalid genre and invalid input
            exceptionPrinting.exceptions(e);
        }


    }


}
