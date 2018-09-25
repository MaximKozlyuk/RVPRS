package PO61.Kozlyuk.wdad.learn.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

public class Library {

    private Document doc;
    private Element rootEl;
    private ArrayList<Reader> list;

    Library (Document doc, Element el) {
        this.doc = doc;
        this.rootEl = el;
        list = findAllReaders();
    }

    private ArrayList<Reader> findAllReaders () {
        ArrayList<Reader> readersList = new ArrayList<>();
        NodeList readers = rootEl.getElementsByTagName("reader");


        for (int i = 0; i < readers.getLength(); i++) {
            ((Element)readers.item(0)).getElementsByTagName("book");
            ((Element)readers.item(0)).getElementsByTagName("takedate");
        }

        return readersList;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("Library, ");
        s.append(list.size()).append(" reader(s)\n");
        for (int i = 0; i < list.size(); i++) {
            s.append(list.get(i).toString());
        }
        s.append("\n");
        return s.toString();
    }
}
