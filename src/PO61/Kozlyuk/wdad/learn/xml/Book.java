package PO61.Kozlyuk.wdad.learn.xml;

import java.io.Serializable;
import java.time.LocalDate;

public class Book implements Serializable {

    private long id;

    private Author author;
    private String name;
    private int printYear;
    private Genre genre;
    private LocalDate takeDate;

    public Book(String name, Author author, int printYear, Genre genre, LocalDate takeDate) {
        this.name = name;
        this.author = author;
        this.printYear = printYear;
        this.genre = genre;
        this.takeDate = takeDate;
    }

    public Author getAuthor() {
        return author;
    }

    public String getName() {
        return name;
    }

    int getPrintyear() {
        return printYear;
    }

    Genre getGenre() {
        return genre;
    }

    public LocalDate getTakeDate () {
        return takeDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder(author.toString());
        s.append(" - ").append(name).append(" ").append(printYear).append(", ").append(genre);
        s.append("; Take date: ").append(takeDate);
        return s.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) { return true; }
        if (!(obj instanceof Book)) { return false; }
        Book o = (Book) obj;
        return o.author.equals(author) && o.name.equals(name) && o.printYear == printYear &&
                o.genre == genre && o.takeDate.equals(takeDate);
    }
}
