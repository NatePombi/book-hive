package service;

import exceptions.BookNotFoundException;
import model.IBook;
import model.IBookFactory;
import util.FindBook;
import util.UpdateBook;

import java.util.List;

public class Management implements IManagement{
private List<IBook> books;
private final FindBook find;
private final UpdateBook updateBook;
private final IBookFactory bookFactory;
    public Management(List<IBook> books,FindBook findBook,UpdateBook updateBook,IBookFactory bookFactory){
        this.books = books;
        this.find = findBook;
        this.updateBook = updateBook;
        this.bookFactory = bookFactory;
    }



    @Override
    public boolean addBook(String title, String author, String genre){
        IBook book = bookFactory.createBook(title, author,genre);
        return books.add(book);
    }

    @Override
    public boolean removeBook(String title) {
        //checking if book exists and removing the book
       IBook book = find.findByTitle(title)
                .orElseThrow(()->new BookNotFoundException(title));
        return books.remove(book);
    }



    @Override
    public boolean updateDetails(String bookTitle,String detail, String detailTo) {
        //finds the book with that specific title
        IBook book = find.findByTitle(bookTitle)
                .orElseThrow(()-> new BookNotFoundException(bookTitle));

        //updates the field that needed to be updated
        return updateBook.updateBookDetails(book,detail,detailTo);
    }

    @Override
    public List<IBook> searchBooksByGenre(String genre) {
        //checks if the genre field is empty and throws an exception if its empty
        if(genre.isBlank()){
            throw new NullPointerException("Genre field is empty");
        }

        //finds a list of books that matches the genre given
        return find.findByGenre(genre);
    }

    @Override
    public List<IBook> viewAllBooks() {
        return books;
    }

    @Override
    public IBook searchBook(String title) {
        //finds the book by searching for the specific title
        return find.findByTitle(title)
                .orElseThrow(() -> new BookNotFoundException(title));
    }
}
