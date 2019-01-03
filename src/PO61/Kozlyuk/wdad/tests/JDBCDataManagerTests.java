package PO61.Kozlyuk.wdad.tests;

import PO61.Kozlyuk.wdad.data.managers.JDBCDataManager;
import PO61.Kozlyuk.wdad.learn.xml.Book;
import PO61.Kozlyuk.wdad.learn.xml.Reader;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public class JDBCDataManagerTests {

    @Test
    public void negligentReadersTest () {
        JDBCDataManager dm = new JDBCDataManager();
        List<Reader> result = dm.negligentReaders();
        for (Reader i : result) {
            System.out.println(i);
        }
    }

    @Test
    public void removeBookTest () {}

    @Test
    public void addBookTest () {

    }

    @Test
    public void bookReturnDatesTest () {
        JDBCDataManager dm = new JDBCDataManager();
        Reader r = new Reader("FirstName", "SecondName");
        r.setId(4);
        HashMap<Book, LocalDate> result =  dm.bookReturnDates(r);
        result.forEach((book, date) -> {
            System.out.println(book + " " + date);
        });
    }

}
