package PO61.Kozlyuk.wdad.learn.xml;

import java.time.LocalDate;

class Book {

    private Author author;
    private String name;
    private int printYear;
    private Genre genre;
    private LocalDate takeDate;

    Book(String name, Author author, int printYear, Genre genre, LocalDate takeDate) {
        this.name = name;
        this.author = author;
        this.printYear = printYear;
        this.genre = genre;
        this.takeDate = takeDate;
    }

    Author getAuthor() {
        return author;
    }

    String getName() {
        return name;
    }

    int getPrintyear() {
        return printYear;
    }

    Genre getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder(author.toString());
        s.append(" - ").append(name).append(" ").append(printYear).append(", ").append(genre);
        s.append("; Take date: ").append(takeDate);
        return s.toString();
    }
}
