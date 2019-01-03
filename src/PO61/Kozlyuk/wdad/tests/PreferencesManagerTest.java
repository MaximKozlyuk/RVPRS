package PO61.Kozlyuk.wdad.tests;

import PO61.Kozlyuk.wdad.data.managers.PreferencesManager;
import PO61.Kozlyuk.wdad.utils.PreferencesManagerConstants;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class PreferencesManagerTest {

    @Test
    public void setPropertyTest () {
        try {
            PreferencesManager mngr = PreferencesManager.getInstance("");
            mngr.setProperty("", "");
        } catch (IOException | SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getPropertyTest () {
        try {
            PreferencesManager mngr = PreferencesManager.getInstance("");
            //mngr.getProperty("");
        } catch (IOException | SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getDbParamsTest () {
        try {
            System.out.println(
                    PreferencesManager.getInstance(
                            PreferencesManagerConstants.XML_APP_CONFIG_PATH
                    ).getDriverType()
            );
            System.out.println(PreferencesManager.getInstance(
                    PreferencesManagerConstants.XML_APP_CONFIG_PATH
            ).getHostName());
            System.out.println(PreferencesManager.getInstance(
                    PreferencesManagerConstants.XML_APP_CONFIG_PATH
            ).getPort());
            System.out.println(PreferencesManager.getInstance(
                    PreferencesManagerConstants.XML_APP_CONFIG_PATH
            ).getDBName());
            System.out.println(PreferencesManager.getInstance(
                    PreferencesManagerConstants.XML_APP_CONFIG_PATH
            ).getUser());
            System.out.println(PreferencesManager.getInstance(
                    PreferencesManagerConstants.XML_APP_CONFIG_PATH
            ).getPass());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

}
