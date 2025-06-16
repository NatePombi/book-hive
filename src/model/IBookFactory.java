package model;

public interface IBookFactory {
    IBook createBook(String title, String author, String genre);

}
