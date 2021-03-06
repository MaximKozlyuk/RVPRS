package PO61.Kozlyuk.wdad;

import PO61.Kozlyuk.wdad.learn.xml.TestXmlTask;
import PO61.Kozlyuk.wdad.learn.xml.XmlTask;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Application {

    static { }

    public static void main(String[] args) {

        System.out.println("I’m Maxim Kozlyuk, and I’m not a monkey\n");

        try {
            XmlTask task = new XmlTask("./src/PO61/Kozlyuk/wdad/learn/xml/myLib.xml");
            TestXmlTask test = new TestXmlTask(task);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}