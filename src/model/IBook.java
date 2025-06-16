package model;

public interface IBook {

    public String setTitle(String titleB);
    public String setAuthor(String author);
    public Genre setGenre(String genre);

    public String getISBN();

    public String getTitle();

    public String getAuthor();

    public Genre getGenre();

    @Override
    public boolean equals(Object o);

    @Override
    public int hashCode();


    @Override
    public String toString();
}
