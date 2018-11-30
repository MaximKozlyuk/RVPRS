package PO61.Kozlyuk.wdad.learn.rmi;

import PO61.Kozlyuk.wdad.data.managers.PreferencesManager;
import PO61.Kozlyuk.wdad.data.managers.PreferencesManagerException;
import PO61.Kozlyuk.wdad.learn.xml.Author;
import PO61.Kozlyuk.wdad.learn.xml.Book;
import PO61.Kozlyuk.wdad.learn.xml.Genre;
import PO61.Kozlyuk.wdad.learn.xml.Reader;
import PO61.Kozlyuk.wdad.utils.PreferencesManagerConstants;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Client {

    private static PreferencesManager preferencesManager;
    private static final String XML_APP_CONFIG_PATH = "./src/PO61/Kozlyuk/wdad/resources/configuration/appconfig.xml";

    private static int registryPort;
    static private String securityPolicyPath;
    static private String codebaseUrl;
    static final private String EXECUTOR_NAME = "xmlDataManager";

    static private String registryAddres;
    static private String useCodebaseOnly;

    public static void main(String[] args)
            throws ParserConfigurationException, SAXException, IOException, PreferencesManagerException, URISyntaxException {

        System.out.println("Init...");

        preferencesManager = PreferencesManager.getInstance(XML_APP_CONFIG_PATH);
        codebaseUrl = "file://".concat(Client.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
        registryPort = Integer.parseInt(preferencesManager.getProperty(PreferencesManagerConstants.registryport));
        registryAddres = preferencesManager.getProperty(PreferencesManagerConstants.registryaddress);
        //        codebaseUrl = "file://".concat(preferencesManager.getProperty(PreferencesManagerConstants.classprovider));//берет путь из конфига
        securityPolicyPath = preferencesManager.getProperty(PreferencesManagerConstants.policypath);
        useCodebaseOnly = preferencesManager.getProperty(PreferencesManagerConstants.usecodebaseonly);

        System.setProperty("java.rmi.server.codebase", codebaseUrl);
        System.setProperty("java.rmi.server.useCodebaseOnly", useCodebaseOnly);
        System.setProperty("java.security.policy", securityPolicyPath);
        System.setProperty("java.rmi.server.logCalls", "true");

        System.out.println("Ready for action");

        Registry registry = null;
        try {
            registry = LocateRegistry.getRegistry("localhost", registryPort);
        } catch (RemoteException e) {
            System.err.println("cant locate registry");
            e.printStackTrace();
        }

        System.out.println("refistry");

        if (registry != null) {
            try {
//                System.out.println("exporting object...");
//                XmlDataManagerImpl imp = new XmlDataManagerImpl();
//                UnicastRemoteObject.exportObject(rei, EXECUTOR_PORT);
//                registry.rebind(EXECUTOR_NAME, rei);
//                System.out.println("idl-ing");
                Remote remoute = registry.lookup(EXECUTOR_NAME);

                System.out.println("starting ui");

                startUI((XmlDataManager) remoute);
            } catch (RemoteException re) {
                System.err.println("cant export or bind object");
                re.printStackTrace();
            } catch (NotBoundException e) {
                e.printStackTrace();
            }
        }

    }

    public static void startUI(XmlDataManager mngr) {
        // test objs:
        Reader r = new Reader("Ivan", "Ivanovich");
        Book book = new Book(
                "JavaGuide",
                new Author("Blokh", "Joshua"),
                2005,
                Genre.ESSAY,
                LocalDate.of(2018,11,29)
                );

        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("Enter command:");
            switch (scan.nextLine()) {
                case "removeBook": {
                    mngr.removeBook(r,book);
                    break;
                }
                case "addBook": {
                    mngr.addBook(r,book);
                    break;
                }
                case "bookReturnDates": {
                    HashMap<Book, LocalDate> map = mngr.bookReturnDates(r);
                    Set<Book> keys =  map.keySet();
                    for (Book i : keys) {
                        System.out.println(map.get(i));
                    }
                    break;
                }
                case "negligentReaders": {
                    List<Reader> l = mngr.negligentReaders();
                    for (Reader i : l) {
                        System.out.println(i);
                    }
                    break;
                }
                case "help" : {
                    System.out.println("removeBook addBook bookReturnDates negligentReaders");
                }
                default: {
                    System.out.println("no such command");
                }
            }
        }

    }

}
