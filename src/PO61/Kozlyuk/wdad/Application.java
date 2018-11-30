package PO61.Kozlyuk.wdad;

import PO61.Kozlyuk.wdad.data.managers.PreferencesManager;
import PO61.Kozlyuk.wdad.data.managers.PreferencesManagerException;
import PO61.Kozlyuk.wdad.learn.xml.TestXmlTask;
import PO61.Kozlyuk.wdad.learn.xml.XmlTask;
import PO61.Kozlyuk.wdad.tests.PreferencesManagerTest;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Set;

public class Application {

    static {
        System.out.println(System.getProperty("user.dir"));
    }

    public static void main(String[] args) {

        System.out.println("I’m Maxim Kozlyuk, and I’m not a monkey\n");

        try {
            XmlTask task = new XmlTask("./src/PO61/Kozlyuk/wdad/learn/xml/myLib.xml");

            //TestXmlTask test = new TestXmlTask(task);

            PreferencesManager prefMnger = PreferencesManager.getInstance(
                    "./src/PO61/Kozlyuk/wdad/resources/configuration/appconfig.xml"
            );

//            System.out.println(
//                    prefMnger.getProperty("appconfig.rmi.client.policypath")
//            );

            //prefMnger.setProperty("appconfig.rmi.server.registry.registryaddress","46.0.193.38");

//            Hashtable props = prefMnger.getProperties();
//            Set keySet = props.keySet();
//            for (Object i: keySet) {
//                System.out.println();
//            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}