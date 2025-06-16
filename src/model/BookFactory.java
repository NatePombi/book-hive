package model;

public class BookFactory implements IBookFactory{


    @Override
    public IBook createBook(String title, String author, String genre) {
        if(title == null || title.isBlank()){
            throw new IllegalArgumentException("Title field is blank");
        }

        if(author == null || author.isBlank()){
            throw new IllegalArgumentException("Author field is blank");
        }

        Genre.getGenre(genre);


        return new Book(title,author,genre);
    }
}
