package PO61.Kozlyuk.wdad.learn.xml;

import com.google.api.client.util.DateTime;

import java.time.LocalDate;
import java.time.Period;
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

    Reader getReader (int i) {
        return readers.get(i);
    }

    void addReader (Reader r) {
        if (r == null) { throw new NullPointerException(); }
        readers.add(r);
    }

    int readersAmount () {
        return readers.size();
    }

    boolean isDebtor (Reader r) {
        for (Book b : r.getDebtBooks()) {
            if (getBookUsageDays(b) > 14) {
                return true;
            }
        }
        return false;
    }

    private int getBookUsageDays (Book book) {
        int counter = 0;
        // todo end this
        return counter;
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
