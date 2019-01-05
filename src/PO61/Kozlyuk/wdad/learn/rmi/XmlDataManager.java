package PO61.Kozlyuk.wdad.learn.rmi;

import PO61.Kozlyuk.wdad.learn.xml.Reader;

import PO61.Kozlyuk.wdad.learn.xml.Book;

import java.io.Serializable;
import java.rmi.Remote;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * public List<Reader> negligentReaders () – возвращающий список читателей – должников
 * (у которых книга на руках уже более 2-х недель).
 *
 * public void removeBook (Reader reader, Book book) –
 * удаляющий запись о книге у заданного читателя. Записывает результат в этот же xml- документ.
 *
 * public void addBook (Reader reader, Book book) –
 * добавляющий запись о книге заданному читателю. Записывает результат в этот же xml- документ.
 *
 * public HashMap<Book, Date> bookReturnDates (Reader reader) –
 * возвращает Hashmap для получения даты возврата книги заданного читателя
 */

public interface XmlDataManager extends Remote, Serializable {

    List<Reader> negligentReaders ();

    void removeBook (Reader reader, Book book);

    void addBook (Reader reader, Book book);

    HashMap<Book, LocalDate> bookReturnDates (Reader reader);

}
