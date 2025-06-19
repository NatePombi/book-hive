package util;

import model.Genre;
import model.IBook;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FindBook {
    private final List<IBook> books;
    public FindBook(List<IBook> books){
        this.books = books;
    }

    public Optional<IBook> findByTitle(String title){
        return books.stream()
                .filter(b-> b.getTitle().equalsIgnoreCase(title))
                .findFirst();
    }


    public List<IBook> findByGenre(String genre){
        List<IBook> bk = new ArrayList<>();
        String gnr = genre.replaceAll("[^a-zA-Z]","");

        for(IBook b : books){
            if(b.getGenre().equals(Genre.getGenre(gnr))){
                bk.add(b);
            }
        }
        return bk;
    }
}
