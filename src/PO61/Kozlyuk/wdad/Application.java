package PO61.Kozlyuk.wdad;

import PO61.Kozlyuk.wdad.learn.xml.XmlTask;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Application {

    static { }

    public static void main(String[] args) {

        System.out.println("I’m Maxim Kozlyuk, and I’m not a monkey\n");

//        Runtime rt = Runtime.getRuntime();
//        try {
//            rt.exec("pwd");
//        } catch (IOException e) {
//            System.out.println("runtime exec exp");
//        }

        String pwd = execShellCommand("pwd");
        //System.out.println(System.getProperty("user.dir"));

        try {
            XmlTask task = new XmlTask("./src/PO61/Kozlyuk/wdad/learn/xml/myLib.xml");
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static String execShellCommand(String command) {
        StringBuffer output = new StringBuffer();

        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
            p.waitFor();
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return output.toString();
    }

}