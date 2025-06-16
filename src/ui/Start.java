package ui;

import exceptions.BookNotFoundException;
import exceptions.InvalidGenreException;
import exceptions.InvalidUpdateField;
import model.Book;
import model.IBook;
import service.Management;
import util.ExceptionPrinting;
import util.ISBNGenerator;
import util.UpdateBook;

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

    public void start() throws InterruptedException {
        boolean cont = true;
        try {
            while (cont) {
                menu.menu();

                int choice = menu.promptMenuMessage("Enter a number from the actions above: ");
                switch (choice) {
                    case 1 -> addBook();
                    case 2 -> removeBook();
                    case 3 -> updateBook();
                    case 4 -> viewAllBooks();
                    case 5 -> searchBook();
                    case 6 -> searchBooks();
                    case 7 -> {
                        if (exitApp()) {
                            System.out.println("GoodBye");
                            cont = false;
                        }
                    }
                    default -> throw new IllegalArgumentException("Invalid menu selection. Enter valid number");
                }
            }
        }
        catch (IllegalArgumentException e){
            exceptionPrinting.exceptions(e);
        }
    }

    private void addBook(){
       try {

           String title =  menu.promptMessage("Enter the title: ");


           String author = menu.promptMessage("Enter author name: ");


           String genre =  menu.promptMessage("Enter the genre: ");

           management.addBook(title,author,genre);
       }
       catch (IllegalArgumentException | NullPointerException | InvalidGenreException e){
            exceptionPrinting.exceptions(e);
       }

        System.out.println("Successfully added a book");


    }


    private void removeBook(){
        try {
            String title = menu.promptMessage("Enter the title of the book you want to remove: ");

            management.removeBook(title);
        }
        catch (BookNotFoundException | NullPointerException e){
            exceptionPrinting.exceptions(e);
        }

        System.out.println("You have successfully Removed a book");

    }

    private void viewAllBooks(){
        menu.printList(management.viewAllBooks());

    }

    private void searchBook(){
        try{
            String title = menu.promptMessage("Enter the title of the book you searching for: ");
            IBook book = management.searchBook(title);
            System.out.println(book);
        }
        catch(NullPointerException | BookNotFoundException e){
            exceptionPrinting.exceptions(e);
        }
    }

    private void searchBooks(){
        try{
            String genre = menu.promptMessage("Enter the genre of the books you want to search: ");
            List<IBook> books = management.searchBooksByGenre(genre);

            for(IBook bk : books){
                System.out.println("ISBN: " + bk.getISBN());
                System.out.println("Title: " + bk.getTitle());
                System.out.println("Author: " + bk.getAuthor());
                System.out.println("Genre: " + bk.getGenre().toString());
            }
        }
        catch (NullPointerException | InvalidGenreException e){
            exceptionPrinting.exceptions(e);
        }
    }

    private boolean exitApp() throws InterruptedException {
        System.out.println("Are you sure you want to exit (Y/N)");
        String input = scanner.nextLine();

        if (input.equalsIgnoreCase("y")){
            System.out.print("exiting");

            for(int i = 0; i<3; i++){
                Thread.sleep(500);
                System.out.print(".");
            }
            System.out.println();
            return true;
        }
        return  false;
    }
    private void updateBook(){
        try {
            String bookTitle = menu.promptMessage("Enter the name of the book you want to update: ");
            String fieldWantUpdated = menu.promptMessage("Enter the field you want to be updated: ");
            String updateFieldTo = menu.promptMessage("Enter what you want it updated to: ");

            management.updateDetails(bookTitle,fieldWantUpdated,updateFieldTo);
        }
        catch (NullPointerException | BookNotFoundException | InvalidGenreException | InvalidUpdateField e){
            exceptionPrinting.exceptions(e);
        }

        System.out.println("You have successfully updated the book");
    }


}
