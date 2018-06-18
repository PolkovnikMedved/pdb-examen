package be.solodoukhin;

import be.solodoukhin.service.ConnectionSingleton;
import be.solodoukhin.service.connection.ConnectionFromFile;
import be.solodoukhin.service.url.Databases;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.sql.Connection;

/**
 * @author SOLODOUKHIN VIKTOR
 * <p>
 * date 18/06/18
 */
public class GeneralTest {

    protected Connection connection;

    @BeforeMethod
    public void setConnection()
    {
        ConnectionSingleton.setConnectionInformation(new ConnectionFromFile("connectionRestoTest.properties", Databases.FIREBIRD));
        this.connection = ConnectionSingleton.getConnexion();
    }

    @AfterMethod
    public void releaseConnection()
    {
        ConnectionSingleton.releaseDatabaseConnection();
    }

}
