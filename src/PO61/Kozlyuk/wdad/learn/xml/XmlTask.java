package PO61.Kozlyuk.wdad.learn.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class XmlTask {

    private Library library;
    private Document doc;

    public XmlTask(String path) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        doc = builder.parse(path);

        library = new Library(parseAllReaders());
        System.out.println(library);
    }


    private ArrayList<Reader> parseAllReaders() {
        ArrayList<Reader> readerList = new ArrayList<>();
        NodeList readerNodes = doc.getDocumentElement().getElementsByTagName("reader");
        NodeList books;
        NodeList takeDates;
        Reader reader;
        NodeList author;
        for (int r = 0; r < readerNodes.getLength(); r++) {

            reader = new Reader(readerNodes.item(r).getAttributes().item(0).getTextContent(),
                    readerNodes.item(r).getAttributes().item(1).getTextContent());

            books = getSubNodes(readerNodes.item(r), "book");
            takeDates = getSubNodes(readerNodes.item(r), "takedate");

            for (int b = 0; b < books.getLength(); b++) {
                author = getSubNodes(books.item(b), "author");
                reader.addTookedBook(
                        new Book(
                                getSubNodes(books.item(b), "name").item(0).getTextContent(),
                                new Author(
                                       getSubNodes(author.item(0), "firstname").item(0).getTextContent(),
                                        getSubNodes(author.item(0), "secondname").item(0).getTextContent()
                                ),
                                Integer.parseInt(
                                        getSubNodes(books.item(b), "printyear").item(0).getTextContent()
                                ),
                                getGenre(
                                        getSubNodes(books.item(b), "genre").item(0).getAttributes().item(0).getTextContent()
                                ),
                                getTakeDate(takeDates.item(b))
                        )
                );
            }
            readerList.add(reader);
        }
        return readerList;
    }

    private Genre getGenre (String s) {
        // todo this
        return Genre.ARTICLE;
    }

    private NodeList getSubNodes (Node n, String s) {
        return ((Element)n).getElementsByTagName(s);
    }

    private LocalDate getTakeDate (Node n) {
        return LocalDate.of(
                Integer.parseInt(n.getAttributes().item(2).getTextContent()),
                Integer.parseInt(n.getAttributes().item(1).getTextContent()),
                Integer.parseInt(n.getAttributes().item(0).getTextContent())
        );
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
