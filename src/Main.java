import model.Book;
import model.BookFactory;
import model.IBook;
import model.IBookFactory;
import service.Management;
import ui.Menu;
import ui.Start;
import util.ExceptionPrinting;
import util.FindBook;
import util.UpdateBook;

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
        start.start();
    }
}