package PO61.Kozlyuk.wdad.learn.xml;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class XmlTask {

    private Library library;
    private Document doc;
    private String pathToXML;

    public XmlTask(String path) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        doc = builder.parse(path);
        pathToXML = path;

        library = new Library(parseAllReaders());
        System.out.println(library);
        System.out.println("DEBTORS");
        ArrayList<Reader> debt = (ArrayList<Reader>) negligentReaders();
        for (Reader d : debt) {
            System.out.println(d.toString());
        }
        //addBook(library.getReader(1), library.getReader(1).getDebtBooks().get(0));

        //removeBook(library.getReader(0), library.getReader(0).getDebtBooks().get(0));
    }

    // я слишком поздно узнал про jaxb...
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
                reader.addTakenBook(
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

    private Genre getGenre(String s) {
        for (Genre g : Genre.values()) {
            if (s.equalsIgnoreCase(g.toString())) {
                return g;
            }
        }
        return Genre.OTHER;
    }

    private NodeList getSubNodes(Node n, String s) {
        return ((Element) n).getElementsByTagName(s);
    }

    private LocalDate getTakeDate(Node n) {
        return LocalDate.of(
                Integer.parseInt(n.getAttributes().item(2).getTextContent()),
                Integer.parseInt(n.getAttributes().item(1).getTextContent()),
                Integer.parseInt(n.getAttributes().item(0).getTextContent())
        );
    }

    // возвращающий список читателей – должников (у которых книга на руках уже более 2-х недель).
    public List<Reader> negligentReaders() {
        Period p;
        ArrayList<Reader> r = new ArrayList<>();
        for (int i = 0; i < library.readersAmount(); i++) {
            if (library.isDebtor(library.getReader(i))) {
                r.add(library.getReader(i));
            }
        }
        return r;
    }

    // удаляющий запись о книге у заданного читателя. Записывает результат в этот же xml-документ.
    public void removeBook(Reader reader, Book book) {
        if (reader == null || book == null) {
            throw new NullPointerException();
        }
        library.getReader(
                reader.getFirstName(),
                reader.getSecondName()
        ).returnBook(book);

        Node r = getReaderFromDoc(reader.getFirstName(), reader.getSecondName());
        NodeList books = ((Element)r).getElementsByTagName("book");

        NodeList takeDates = ((Element)r).getElementsByTagName("takedate");
        for (int b = 0; b < books.getLength(); b++) {
            if (isNodeEqualBook((Element)books.item(b),book)) {
                books.item(b).getParentNode().removeChild(books.item(b));
                takeDates.item(b).getParentNode().removeChild(takeDates.item(b));
            }
        }
        try {
            saveXML();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    private boolean isNodeEqualBook (Element e, Book b) {
        NodeList author = e.getElementsByTagName("author");
        return ((Element)author.item(0)).getElementsByTagName("firstname").item(0).getTextContent().equals(b.getAuthor().getFirstName()) &&
                ((Element)author.item(0)).getElementsByTagName("secondname").item(0).getTextContent().equals(b.getAuthor().getSecondName()) &&
               e.getElementsByTagName("name").item(0).getTextContent().equals(b.getName()) &&
               (Integer.parseInt(e.getElementsByTagName("printyear").item(0).getTextContent()) == b.getPrintyear()) &&
                e.getElementsByTagName("genre").item(0).getAttributes().item(0).getTextContent().equalsIgnoreCase(b.getGenre().toString());
    }

    // добавляющий запись о книге заданному читателю. Записывает результат в этот же xml-документ.
    public void addBook(Reader reader, Book book) {
        // предположим что reader уже лежит в library
        if (reader == null || book == null) {
            throw new NullPointerException();
        }
        reader.addTakenBook(book);
        Element bookEl = doc.createElement("book");
        Element authorEl = doc.createElement("author");
        // creating author tag
        Element newEl = doc.createElement("firstname");
        newEl.setTextContent(book.getAuthor().getFirstName());
        authorEl.appendChild(newEl);
        newEl = doc.createElement("secondname");
        newEl.setTextContent(book.getAuthor().getSecondName());
        authorEl.appendChild(newEl);
        bookEl.appendChild(authorEl);
        // add rest book tags
        newEl = doc.createElement("name");
        newEl.setTextContent(book.getName());
        bookEl.appendChild(newEl);
        newEl = doc.createElement("printyear");
        newEl.setTextContent(Integer.toString(book.getPrintyear()));
        bookEl.appendChild(newEl);
        newEl = doc.createElement("genre");
        newEl.setAttribute("value", book.getGenre().toString().toLowerCase());
        bookEl.appendChild(newEl);
        // creating take date tag
        Element takedateEl = doc.createElement("takedate");
        takedateEl.setAttribute("day", Integer.toString(book.getTakeDate().getDayOfMonth()));
        takedateEl.setAttribute("month", Integer.toString(book.getTakeDate().getMonthValue()));
        takedateEl.setAttribute("year", Integer.toString(book.getTakeDate().getYear()));

        Node r = getReaderFromDoc(reader.getFirstName(), reader.getSecondName());
        r.appendChild(bookEl);
        r.appendChild(takedateEl);

        try {
            saveXML();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    private Node getReaderFromDoc (String firstName, String secondName) {
        NodeList readers = doc.getDocumentElement().getElementsByTagName("reader");
        for (int i = 0; i < readers.getLength(); i++) {
            if (
                    readers.item(i).getAttributes().item(0).getTextContent().equals(firstName) &&
                            readers.item(i).getAttributes().item(1).getTextContent().equals(secondName)
            ) { return readers.item(i); }
        }
        return null;
    }

    private void saveXML() throws TransformerException {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        Result output = new StreamResult(new File(pathToXML));
        Source input = new DOMSource(doc);
        transformer.transform(input, output);
    }

    // возвращает список книг заданного читателя, которые он должен был вернуть
    public List<Book> debtBooks(Reader reader) {
        if (reader == null) {
            throw new NullPointerException();
        }
        return reader.getDebtBooks();
    }

}
