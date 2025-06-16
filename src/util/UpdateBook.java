package util;

import exceptions.InvalidUpdateField;
import model.IBook;

public class UpdateBook {
    public UpdateBook(){}

    public boolean updateBookDetails(IBook book, String fieldToChange, String fieldChangingTo){


        return switch (fieldToChange.toLowerCase()){
            case "author" -> {book.setAuthor(fieldChangingTo);
                yield true; }
            case "genre" -> {book.setGenre(fieldChangingTo);
                yield true;  }
            case "title" -> {book.setTitle(fieldChangingTo);
                yield true;}
            default -> throw new InvalidUpdateField();
        };
    }
}
