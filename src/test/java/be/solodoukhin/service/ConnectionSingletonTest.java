package be.solodoukhin.service;

import be.solodoukhin.service.connection.ConnectionFromFile;
import be.solodoukhin.service.url.Databases;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author SOLODOUKHIN VIKTOR
 * <p>
 * date 17/06/18
 */
public class ConnectionSingletonTest {

    @Test
    public void testConnectionFromRestaurantFile()
    {
        ConnectionSingleton.setConnectionInformation(new ConnectionFromFile("connectionRestoTest.properties", Databases.FIREBIRD));
        Connection c = ConnectionSingleton.getConnexion();
        Assert.assertNotNull(c);
        try{
            PreparedStatement query = c.prepareStatement("SELECT count(*) from TCOMMANDE");
            ResultSet rs = query.executeQuery();
            Assert.assertTrue(rs.next());
            Assert.assertNotEquals(0, rs.getInt(1));
            System.out.println("Found '"+ rs.getInt(1) +"' orders in the database.");
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        ConnectionSingleton.releaseDatabaseConnection();
    }
}
