package PO61.Kozlyuk.wdad.learn.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class XmlTask {

    /*

    public List<Reader> negligentReaders() –
    возвращающий список читателей – должников (у которых книга на руках уже более 2-х недель).

    public void removeBook (Reader reader, Book book) –
    удаляющий запись о книге у заданного читателя. Записывает результат в этот же xml-документ.

    public void addBook (Reader reader, Book book) –
     добавляющий запись о книге заданному читателю. Записывает результат в этот же xml-документ.

    public List<Book> debtBooks (Reader reader) –
    возвращает список книг заданного читателя, которые он должен был вернуть
     */

    public void XmlTask () {
        String path = new String("./src/PO61/Kozlyuk/wdad/learn/xml/myLib.xml");
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(path);
            NodeList tagEl = document.getDocumentElement().getElementsByTagName("reader");

        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException | ParserConfigurationException e) {
            e.printStackTrace();
        }

        /*
        this.path = path;
        this.xmlFile = Paths.get(path);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        factory.setIgnoringComments(true);
        factory.setIgnoringElementContentWhitespace(true);
        factory.setValidating(true);
        this.document = builder.parse(xmlFile.toFile());
         */

    }

    private Element find(NodeList nodeList, Predicate<Element> predicate) {
        Element element;
        for (int i = 0; i < nodeList.getLength(); i++) {
            element = (Element) nodeList.item(i);

            if (predicate.test(element)) return element;
        }
        return null;
    }


}
