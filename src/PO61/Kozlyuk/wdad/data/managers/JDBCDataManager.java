package PO61.Kozlyuk.wdad.data.managers;

import PO61.Kozlyuk.wdad.data.storage.DataSourceFactory;
import PO61.Kozlyuk.wdad.learn.xml.Author;
import PO61.Kozlyuk.wdad.learn.xml.Book;
import PO61.Kozlyuk.wdad.learn.xml.Genre;
import PO61.Kozlyuk.wdad.learn.xml.Reader;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JDBCDataManager implements DataManager {

    private DataSource ds = null;
    private Connection connection = null;

    public JDBCDataManager() {
        try {
            ds = DataSourceFactory.createDataSource();
            connection = ds.getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Reader> negligentReaders() {
        ArrayList<Reader> readers = new ArrayList<>();
        String query = "SELECT readers.id, first_name, second_name\n" +
                "FROM books_readers INNER JOIN readers ON books_readers.readers_id = readers.id\n" +
                "WHERE (to_char(now(), 'J')::integer - to_char(take_date, 'J')::integer) > 14;";
        Statement stmt;
        ResultSet resultSet;

        try {
            stmt = connection.createStatement();
            resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                readers.add(
                        new Reader(
                                resultSet.getString(2),
                                resultSet.getString(3)
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return readers;
    }


    @Override
    public void removeBook(Reader reader, Book book) {
        StringBuilder query = new StringBuilder("DELETE FROM books_readers WHERE books_dictionary_id = ");
        query.append(book.getId()).append(" AND readers_id = ").append(reader.getId()).append(";");
        try {
            Statement stmt = connection.createStatement();
            stmt.executeQuery(query.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addBook(Reader reader, Book book) {
        StringBuilder query = new StringBuilder("INSERT INTO public.books_readers (books_dictionary_id, readers_id, take_date, return_date) ");
        query.append("VALUES (").append(book.getId()).append(',').append(reader.getId()).append(", now(), null);");
        try {
            Statement stmt = connection.createStatement();
            stmt.executeQuery(query.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public HashMap<Book, LocalDate> bookReturnDates(Reader reader) {
        HashMap<Book, LocalDate> returnDates = new HashMap<>();
        Statement stmt;
        ResultSet resultSet;

        StringBuilder query = new StringBuilder(
                "SELECT to_char(return_date, 'YYYY MM DD'), to_char(take_date, 'YYYY MM DD') , books.id, name, description, " +
                "print_year FROM books_readers INNER JOIN books ON books.id = books_readers.books_dictionary_id " +
                "WHERE return_date IS NOT NULL AND readers_id = "
        );
        query.append(reader.getId()).append(";");

        /** как вариант, брать из reader коллекцию и возвращать даты его книг */
//        returnDates.put(
//                new Book(params),
//                reader.getDebtBooks().stream().filter(book -> {
//                    return book.getTakeDate().equals(local_date_From_db);
//                }).findFirst().get().getTakeDate();
//        );

        try {
            stmt = connection.createStatement();
            resultSet = stmt.executeQuery(query.toString());
            while (resultSet.next()) {
                returnDates.put(
                        new Book(
                                resultSet.getString(4),
                                new Author("AuthorName", "AuthorSecondName"),
                                resultSet.getInt(6),
                                Genre.OTHER,
                                parseDate(resultSet.getString(2))
                        ),
                        parseDate(resultSet.getString(1))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnDates;
    }

    private LocalDate parseDate (String dbData) {
        String[] data = dbData.split("\\s+");
        return LocalDate.of(
                Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2])
        );
    }

}
