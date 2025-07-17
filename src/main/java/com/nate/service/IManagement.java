package com.nate.service;

import com.nate.model.IBook;

import java.util.List;

public interface IManagement {
    boolean removeBook(String title);
    boolean addBook(String title, String author, String genre);
    boolean updateDetails(String bookTitle,String detailToUpdate, String whatToUpdateItTo);
    List<IBook> searchBooksByGenre(String genre);
    List<IBook> viewAllBooks();

     IBook searchBook(String title);
}
