package test;

import com.nate.model.Book;
import com.nate.model.BookFactory;
import com.nate.model.IBook;
import com.nate.model.IBookFactory;
import  org.junit.jupiter.api.BeforeEach;
import  org.junit.jupiter.api.Test;
import com.nate.service.IManagement;
import com.nate.service.Management;
import com.nate.util.FindBook;
import com.nate.util.UpdateBook;

import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

public class ManagementTest {


    private IBookFactory bookFactory;
    private IManagement management;
    private List<IBook> books;
    @BeforeEach
    public void startUp(){
        books = new ArrayList<>();
        UpdateBook updateBook = new UpdateBook();
        FindBook findBook = new FindBook(books);
        bookFactory = new BookFactory();
        management = new Management(books, findBook, updateBook,bookFactory);
    }

    @Test
    public void shouldAddBookSuccessfully(){

        boolean result = management.addBook("Test1","tester", "non fiction");

        assertTrue(result);
        assertEquals(1,books.size());
        assertEquals("Test1",books.get(0).getTitle());
    }

    @Test
    public void shouldThrowExceptionWhenFails(){

        assertThrows(Exception.class, ()->{
            new Book("Test", "teste","non-Fiction");
        });

    }

    @Test
    public void shouldRemoveBookSuccessfully(){

        boolean result = management.addBook("test2", "Tester", "Fantasy");

        assertTrue(result);
        assertEquals(1, books.size());

        boolean result2 = management.removeBook(books.get(0).getTitle());
        assertTrue(result2);
        assertEquals(0,books.size());
    }


    @Test
    public void shouldThrowExceptionWhenRemoveBookFails(){
        boolean result = management.addBook("test2", "Tester", "Fantasy");
        assertTrue(result);
        assertThrows(Exception.class, ()->{
            management.removeBook("tes");
        });
    }

    @Test
    public void shouldSearchBookByTitleSuccessfully(){
        boolean result = management.addBook("test2", "Tester", "Fantasy");
        assertTrue(result);

        IBook bk = management.searchBook("test2");
        assertEquals("test2", bk.getTitle());
        assertEquals("Tester",bk.getAuthor());
        assertEquals("FANTASY", bk.getGenre().toString());

    }

    @Test
    public void shouldThrowExceptionWhenSearchBookByTitleFails(){
        boolean result = management.addBook("test2", "Tester", "Fantasy");
        assertTrue(result);

        assertThrows(Exception.class, () ->{
            management.searchBook("tezt");
        });
    }


    @Test
    public void shouldUpdateBookSuccessfully(){
        boolean result = management.addBook("test2", "Tester", "Fantasy");
        assertTrue(result);

        boolean result1 = management.updateDetails("test2","title","test3");
        boolean result2 = management.updateDetails("test3","author","Tester5");
        boolean result3 = management.updateDetails("test3","genre","NON FICTION");



        assertTrue(result1);
        assertEquals("test3", books.get(0).getTitle());

        assertTrue(result2);
        assertEquals("Tester5", books.get(0).getAuthor());

        assertTrue(result3);
        assertEquals("NON_FICTION", books.get(0).getGenre().toString());
    }


    @Test
    public void shouldThrowExceptionWhenUpdateBookFails(){
        boolean result = management.addBook("test2", "Tester", "Fantasy");
        assertTrue(result);

        assertThrows(Exception.class, ()->{
            management.updateDetails("test2","sky","Fiction");
        });

    }

    @Test
    public void shouldSearchBookByGenreSuccessfully(){
        boolean result = management.addBook("test2", "Tester", "Fantasy");
        assertTrue(result);

        List<IBook> bk = management.searchBooksByGenre("Fantasy");

        assertEquals(1,bk.size());
        assertEquals("test2",bk.get(0).getTitle());
        assertEquals("Tester",bk.get(0).getAuthor());
        assertEquals("FANTASY",bk.get(0).getGenre().toString());
    }

    @Test
    public void shouldThrowExceptionWhenSearchByGenreFails(){
        boolean result = management.addBook("test2", "Tester", "Fantasy");
        assertTrue(result);

        assertThrows(Exception.class, ()->{
            management.searchBooksByGenre("Fake");
        });
    }


}
