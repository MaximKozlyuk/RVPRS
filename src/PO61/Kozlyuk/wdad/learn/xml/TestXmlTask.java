package PO61.Kozlyuk.wdad.learn.xml;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class TestXmlTask implements Serializable {

     public TestXmlTask (XmlTask task) {

         System.out.println(task.getLibrary() + "\n");

         Book b = new Book(
                 "The New Book",
                 new Author("NickName", "Sname"),
                 2000,
                 Genre.ESSAY,
                 LocalDate.of(2018,9,10)
         );

         /*
         task.addBook(task.getLibrary().getReader(0),b);

         task.removeBook(
                 task.getLibrary().getReader(0),
                 task.getLibrary().getReader(0).getDebtBooks().get(1)
         );
         */

         System.out.println("negligentReaders");
         ArrayList<Reader> r = (ArrayList<Reader>) task.negligentReaders();
         System.out.println("Debt:" + r.size());
         for (Reader i : r) {
             System.out.println(i);
         }

    }

}
