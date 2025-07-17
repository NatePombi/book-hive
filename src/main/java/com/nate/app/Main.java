package com.nate.app;

import com.nate.model.BookFactory;
import com.nate.model.IBook;
import com.nate.model.IBookFactory;
import com.nate.service.Management;
import com.nate.ui.Menu;
import com.nate.ui.Start;
import com.nate.util.ExceptionPrinting;
import com.nate.util.FindBook;
import com.nate.util.UpdateBook;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        List<IBook> bookList = new ArrayList<>();
        UpdateBook updateBook = new UpdateBook();
        FindBook findBook = new FindBook(bookList);
        IBookFactory bookFactory = new BookFactory();
        Management management = new Management(bookList,findBook,updateBook,bookFactory);
        ExceptionPrinting exceptionPrinting = new ExceptionPrinting();
        Menu menu = new Menu(scanner,exceptionPrinting);
        Start start = new Start(management,menu,scanner,exceptionPrinting);
        start.runApp();
    }
}