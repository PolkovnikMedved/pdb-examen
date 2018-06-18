package be.solodoukhin.dao.firebird;

import be.solodoukhin.GeneralTest;
import be.solodoukhin.dao.FireBirdDAOFactory;
import be.solodoukhin.exception.RestaurantException;
import be.solodoukhin.model.Article;
import be.solodoukhin.model.enumeration.Step;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

/**
 * @author SOLODOUKHIN VIKTOR
 * <p>
 * date 17/06/18
 */
public class FireBirdArticleDAOTest extends GeneralTest {

    private FireBirdArticleDAO dao;

    @BeforeMethod
    public void initializeDAO()
    {
        if(this.connection == null)
        {
            this.setConnection();
        }
        dao = new FireBirdArticleDAO(new FireBirdDAOFactory(this.connection));
    }

    @Test
    public void testReadById()
    {
        Article article = dao.getById("JAMSER");
        Assert.assertNotNull(article);
        System.out.println("Article = " + article.toString());
    }

    @Test
    public void testReadAll()
    {
        List<Article> articles = dao.getAll("");
        Assert.assertTrue(articles.size() > 0);
        articles.forEach(System.out::println);
    }

    @Test
    public void testCreateOne()
    {
        String out = null;
        Article article = new Article("TEST", "Test", "Test", 100, 15.50, true, Step.ENTREE, null);
        try{
            out = dao.insert(article);
        } catch (RestaurantException exception)
        {
            exception.printStackTrace();
        }
        Assert.assertNotNull(out);
    }

    @Test
    public void testEditOne()
    {
        Article article = dao.getById("TEST");
        Assert.assertNotNull(article);
        article.setName("updated");
        boolean updated = false;
        try{
             updated = dao.update(article);
        } catch (RestaurantException e)
        {
            e.printStackTrace();
        }
        Assert.assertTrue(updated);
        Assert.assertTrue(dao.getById("TEST").getName().equals("updated"));
    }

    @Test
    public void testDeleteOne()
    {
        Article article = dao.getById("TEST");
        Assert.assertNotNull(article);
        boolean deleted = false;
        try{
            deleted = dao.delete(article);
        } catch (RestaurantException e)
        {
            e.printStackTrace();
        }
        Assert.assertTrue(deleted);
    }
}
