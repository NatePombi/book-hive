package model;

import util.ISBNGenerator;

public class Book implements IBook{
    private final String ISBN;
    private  String title;
    private  String author;
    private  Genre genre;


    public Book(String title, String author, String genre) {
        this.ISBN = ISBNGenerator.generator();
        this.title = title;
        this.author = author;
        this.genre = Genre.getGenre(genre);
    }

    @Override
    public String setTitle(String titleB) {
        return this.title =titleB;
    }

    @Override
    public String setAuthor(String author) {
        return this.author = author;
    }

    @Override
    public Genre setGenre(String genre) {
        return this.genre =Genre.getGenre(genre);
    }

    public String getISBN() {
        return ISBN;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Genre getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        return "Book{" +
                "ISBN='" + ISBN + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }
}
