package com.nate.ui;

import com.nate.model.IBook;
import com.nate.util.ExceptionPrinting;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private final Scanner scanner;
    private final ExceptionPrinting exceptionPrinting;
    public Menu(Scanner scanner,ExceptionPrinting exceptionPrinting){
        this.scanner = scanner;
        this.exceptionPrinting = exceptionPrinting;
    }

    public void menu(){
        System.out.println("""
                      Welcome to Book Hive
                     ----------------------
                        1) Add Book
                        2) Remove Book
                        3) Update Book details
                        4)  View All Books
                        5) Search for book
                        6) Search for books
                        7) Exit
                """);

    }

    public String promptMessage(String message){

        System.out.print(message);
        return scanner.nextLine();
    }

    public int promptMenuMessage(String message){
       try {
           System.out.println(message);
           int choice = scanner.nextInt();
           scanner.nextLine();
           return choice;
       }
       catch(InputMismatchException e){
           exceptionPrinting.exceptions(e);
       }

       return 0;
    }

    public void printList(List<IBook> books){

        for(IBook bk : books){
            System.out.println("-".repeat(30));
            System.out.println("Title: " + bk.getTitle());
            System.out.println("Author: " + bk.getAuthor());
            System.out.println("Genre: " + bk.getGenre());
            System.out.println("-".repeat(30));
        }
    }

}
