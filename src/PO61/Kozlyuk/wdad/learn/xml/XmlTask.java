package PO61.Kozlyuk.wdad.learn.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.IOException;
import java.util.List;

public class XmlTask {

    private Library library;

    public XmlTask(String path) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(path);
        Element el = doc.getDocumentElement();
        this.library = new Library(doc, el);

        System.out.println(library);
    }

    // возвращающий список читателей – должников (у которых книга на руках уже более 2-х недель).
    public List<Reader> negligentReaders() {
        return null;
    }

    // удаляющий запись о книге у заданного читателя. Записывает результат в этот же xml-документ.
    public void removeBook (Reader reader, Book book) {

    }

    // добавляющий запись о книге заданному читателю. Записывает результат в этот же xml-документ.
    public void addBook (Reader reader, Book book) {

    }

    // возвращает список книг заданного читателя, которые он должен был вернуть
    public List<Book> debtBooks (Reader reader) {
        return null;
    }



}
