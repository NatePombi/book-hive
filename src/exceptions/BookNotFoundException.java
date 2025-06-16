package exceptions;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String title) {
        super("Book with the name " + title + " was not found");
    }
}
