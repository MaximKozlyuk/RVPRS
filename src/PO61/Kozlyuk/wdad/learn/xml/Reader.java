package PO61.Kozlyuk.wdad.learn.xml;

import java.util.ArrayList;

public class Reader extends Human{

    private ArrayList<Book> debtBooks;

    public Reader(String firstName, String secondName) {
        super(firstName, secondName);
        debtBooks = new ArrayList<>();
    }

    Reader () {
        this(null, null);
    }

    void addTakenBook(Book book) {
        debtBooks.add(book);
    }

    boolean returnBook (Book book) {
//        if (debtBooks.contains(book)) {
//            debtBooks.remove(book);
//            return true;
//        } else {
//            return false;
//        }
        for (int i = 0; i < debtBooks.size(); i++) {
            if (debtBooks.get(i).equals(book)) {
                debtBooks.remove(i);
                return true;
            }
        }
        return false;
    }

    ArrayList<Book> getDebtBooks() {
        return debtBooks;
    }

    @Override
    String getFirstName() {
        return super.getFirstName();
    }

    @Override
    String getSecondName() {
        return super.getSecondName();
    }

    @Override
    void setFirstName(String firstName) {
        super.setFirstName(firstName);
    }

    @Override
    void setSecondName(String secondName) {
        super.setSecondName(secondName);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder(super.toString());
        if (debtBooks.isEmpty()) {
            s.append(", no debts");
        } else {
            s.append("\nbook(s) debt: ").append(debtBooks.size()).append("\n");
            for (int i = 0; i < debtBooks.size(); i++) {
                s.append(debtBooks.get(i).toString()).append("\n");
            }
        }
        return s.toString();
    }
}
