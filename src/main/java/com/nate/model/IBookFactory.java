package com.nate.model;

public interface IBookFactory {
    IBook createBook(String title, String author, String genre);

}
