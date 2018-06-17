package be.solodoukhin.dao.firebird;

import be.solodoukhin.dao.FireBirdDAOFactory;
import be.solodoukhin.model.Article;
import be.solodoukhin.service.ConnectionSingleton;
import be.solodoukhin.service.connection.ConnectionFromFile;
import be.solodoukhin.service.url.Databases;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Connection;

/**
 * @author SOLODOUKHIN VIKTOR
 * <p>
 * date 17/06/18
 */
public class FireBirdArticleDAOTest {

    Connection connection;
    FireBirdArticleDAO dao;

    @BeforeClass
    public void setConnection()
    {
        ConnectionSingleton.setConnectionInformation(new ConnectionFromFile("connectionRestoTest.properties", Databases.FIREBIRD));
        this.connection = ConnectionSingleton.getConnexion();
        dao = new FireBirdArticleDAO(new FireBirdDAOFactory(this.connection));
    }

    @AfterClass
    public void releaseConnection()
    {
        ConnectionSingleton.releaseDatabaseConnection();
    }

    @Test
    public void testReadById()
    {
        Article article = dao.getById("JAMSER");
        Assert.assertNotNull(article);
        System.out.println("Article = " + article.toString());
    }
}
