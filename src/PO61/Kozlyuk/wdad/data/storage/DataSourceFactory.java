package PO61.Kozlyuk.wdad.data.storage;

import PO61.Kozlyuk.wdad.data.managers.PreferencesManager;
import PO61.Kozlyuk.wdad.utils.PreferencesManagerConstants;
import org.junit.jupiter.api.Test;
import org.postgresql.ds.PGSimpleDataSource;
import org.xml.sax.SAXException;

import javax.sql.DataSource;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.*;

public class DataSourceFactory {

    public static DataSource createDataSource() throws ClassNotFoundException {
        PreferencesManager pm = null;
        try {
            pm = PreferencesManager.getInstance(
                    PreferencesManagerConstants.XML_APP_CONFIG_PATH
            );
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return DataSourceFactory.createDataSource(
          pm.getClassName(), pm.getDriverType(), pm.getHostName(), pm.getPort(), pm.getDBName(), pm.getUser(), pm.getPass()
        );
    }

    public static DataSource createDataSource
            (String className, String driverType, String host, int port, String dbName, String user, String password) throws ClassNotFoundException {
        Class.forName(driverType);
        PGSimpleDataSource ds = new PGSimpleDataSource();
        ds.setServerName(host);
        ds.setPortNumber(port);
        ds.setDatabaseName(dbName);
        ds.setUser(user);
        ds.setPassword(password);
        return ds;
    }

    @Test
    public void testCreateDS () {
        try {
            DataSource ds = DataSourceFactory.createDataSource();
            Connection connection = ds.getConnection();
//            Connection connection = DriverManager.getConnection(
//                    "jdbc:postgresql://127.0.0.1:5432/library", "max", ""
//            );
            StringBuilder query = new StringBuilder("SELECT * FROM authors;");
            Statement stmt = connection.createStatement();
            ResultSet result = stmt.executeQuery(query.toString());

            while (result.next()) {
                System.out.println(
                        result.getInt("id") + " " + result.getString("first_name") +
                                " " + result.getString("second_name") + " " + result.getString("birth_date")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
