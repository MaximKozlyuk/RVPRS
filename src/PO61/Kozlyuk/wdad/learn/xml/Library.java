package PO61.Kozlyuk.wdad.learn.xml;

import java.util.ArrayList;

public class Library {

    private ArrayList<Reader> readers;

    Library () {
        readers = new ArrayList<>();
    }

    Library (ArrayList<Reader> list) {
        if (list == null) { throw new NullPointerException(); }
        this.readers = list;
    }

    Reader getReader (String firstName, String secondName) {
        for (int i = 0; i < readers.size(); i++) {
            if (readers.get(i).getFirstName().equals(firstName) && readers.get(i).getSecondName().equals(secondName)) {
                return readers.get(i);
            }
        }
        return null;
    }

    void addReader (Reader r) {
        if (r == null) { throw new NullPointerException(); }
        readers.add(r);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("Library, ");
        s.append(readers.size()).append(" reader(s):\n");
        for (int i = 0; i < readers.size(); i++) {
            s.append(readers.get(i).toString()).append("\n");
        }
        return s.toString();
    }
}
