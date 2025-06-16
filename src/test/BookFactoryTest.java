package test;

import model.BookFactory;
import model.IBook;
import model.IBookFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class BookFactoryTest {
   private IBookFactory bookFactory;

    @BeforeEach
    public void startUp(){
        bookFactory = new BookFactory();
    }

    @Test
    public void shouldCreateABook(){
        IBook book = bookFactory.createBook("test1", "tester","non fiction");

        assertNotNull(book);
        assertEquals("test1",book.getTitle());
        assertEquals("tester", book.getAuthor());
        assertEquals("NONFICTION", book.getGenre().toString());
    }


    @Test
    public void shouldThrowExceptionWhenCreateBookWithANullField(){

        assertThrows(Exception.class,()->{
            bookFactory.createBook(null,"tester","fiction");
        });
    }

    @Test
    public void shouldThrowExceptionWhenCreateBookWithWrongGenre(){

        assertThrows(Exception.class,()->{
            bookFactory.createBook("Test","Tester","fake");
        });
    }

}
