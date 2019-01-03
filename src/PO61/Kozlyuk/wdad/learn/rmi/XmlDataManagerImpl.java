package PO61.Kozlyuk.wdad.learn.rmi;

import PO61.Kozlyuk.wdad.data.managers.DataManager;
import PO61.Kozlyuk.wdad.learn.xml.Reader;
import PO61.Kozlyuk.wdad.learn.xml.XmlTask;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;

import PO61.Kozlyuk.wdad.learn.xml.Book;
import java.io.IOException;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class XmlDataManagerImpl implements DataManager {

    private XmlTask xmlTask;

    public XmlDataManagerImpl(String path) throws RemoteException {
        try {
            xmlTask = new XmlTask(path);
        } catch (ParserConfigurationException e) {
            throw new RemoteException("ParserConfigurationException in XmlTask");
        } catch (SAXException e) {
            throw new RemoteException("SAXException in XmlTask");
        } catch (IOException e) {
            throw new RemoteException("IOException in XmlTask");
        }
    }

    @Override
    public List<Reader> negligentReaders() {
        return xmlTask.negligentReaders();
    }

    @Override
    public void removeBook(Reader reader, Book book) {
        xmlTask.removeBook(reader,book);
    }

    @Override
    public void addBook(Reader reader, Book book) {
        xmlTask.addBook(reader,book);
    }

    @Override // возвращает Hashmap для получения даты возврата книги заданного читателя
    public HashMap<Book, LocalDate> bookReturnDates(Reader reader) {
        HashMap<Book, LocalDate> map = new HashMap<>();
        ArrayList<Book> list = (ArrayList<Book>) xmlTask.debtBooks(reader);
        for (Book i : list) {
            map.put(i,i.getTakeDate());
        }
        return map;
    }

}
