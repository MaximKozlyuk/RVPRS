package PO61.Kozlyuk.wdad.learn.rmi;

import PO61.Kozlyuk.wdad.data.managers.DataManager;
import PO61.Kozlyuk.wdad.data.managers.PreferencesManager;
import PO61.Kozlyuk.wdad.data.managers.PreferencesManagerException;
import PO61.Kozlyuk.wdad.utils.PreferencesManagerConstants;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * 1) получает ссылку на экземпляр класса PreferencesManager
 * 2) В зависимости от значений параметров (createregistry, registryaddress, registryport) создаёт
 * или подключается к реестру и регистрирует в нём удаленный объект – экземпляр класса XmlDataManagerImpl,
 * реализующего Remote интерфейс DataManager. Имя удаленного объекта – «DataManager».
 * После регистрации, сервер добавляет с помощью PreferencesManager в конфигурационный файл
 * информацию по добавленному объекту (тэг bindedobject, name – DataManager,
 * class – полное квалифицированное имя удаленного интерфейса).
 * При реализации XmlDataManagerImpl используется класс XmlTask, реализованный в рамках предыдущей
 * лабораторной работы. XmlDataManagerImpl делегирует выполнение операций с xml-файлами экземпляру
 * класса XmlTask. При необходимости добавьте методы в классе XmlTask.
 */

public class Server {

    private static DataManager xmlDataManager;
    static private String securityPolicyPath;
    static final private String EXECUTOR_NAME = "xmlDataManager";
    static final private String XML_DATA_MANAGER_PATH = "./src/PO61/Kozlyuk/wdad/learn/xml/myLib.xml";

    static private PreferencesManager preferencesManager;
    static private String codebaseUrl;
    static private int registryPort;
    static private String registryAddres;
    static private int executorPort = 1604; // Должен быть не занят
    static private String useCodebaseOnly;


    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, URISyntaxException, PreferencesManagerException {
        preferencesManager = PreferencesManager.getInstance(PreferencesManagerConstants.XML_APP_CONFIG_PATH);
        xmlDataManager = new XmlDataManagerImpl(XML_DATA_MANAGER_PATH);
        registryAddres = preferencesManager.getProperty(PreferencesManagerConstants.registryaddress);
        codebaseUrl = "file://".concat(Server.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());//получает путь до jar файла
        registryPort = Integer.parseInt(preferencesManager.getProperty(PreferencesManagerConstants.registryport));
        registryAddres = preferencesManager.getProperty(PreferencesManagerConstants.registryaddress);
        useCodebaseOnly = preferencesManager.getProperty(PreferencesManagerConstants.usecodebaseonly);
        securityPolicyPath = preferencesManager.getProperty(PreferencesManagerConstants.policypath);

        System.setProperty("java.rmi.server.codebase", codebaseUrl);
        System.setProperty("java.rmi.server.useCodebaseOnly", useCodebaseOnly); // искать классы в codebase, а не в class path
        System.setProperty("java.rmi.server.logCalls", "true"); // чтоб сервер в консоль выводил инку по коннекта - запросы на поиск объекта
        System.setProperty("java.security.policy", securityPolicyPath); // путь к файлу с правами доступа
        System.setProperty("java.rmi.server.hostname", registryAddres);

        Registry registry = null;

        try {
            if (preferencesManager.getProperty(PreferencesManagerConstants.createregistry).equals("yes"))
                registry = LocateRegistry.createRegistry(registryPort);
            else registry = LocateRegistry.getRegistry(registryPort);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        try {
            System.out.println("exporting object...");
            //UnicastRemoteObject.exportObject(xmlDataManager, executorPort); // экспорт объекта - необходимо, если класс, реализующий удаленный интерфейс, не наследуется от UnicastRemoteObject

            registry.rebind(EXECUTOR_NAME, xmlDataManager); // бинтом объект, чтоб клиент мог найти его по имени
            System.out.println("idl-ing");
        } catch (RemoteException re) {
            System.err.println("cant export or bind object");
            re.printStackTrace();
        }

        while (true) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }}


}
