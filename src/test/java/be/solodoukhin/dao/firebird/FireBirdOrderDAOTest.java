package be.solodoukhin.dao.firebird;

import be.solodoukhin.GeneralTest;
import be.solodoukhin.dao.FireBirdDAOFactory;
import be.solodoukhin.model.Order;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author SOLODOUKHIN VIKTOR
 * <p>
 * date 18/06/18
 */
public class FireBirdOrderDAOTest extends GeneralTest{

    private FireBirdOrderDAO dao;

    @BeforeMethod
    public void initializeDAO()
    {
        if(this.connection == null)
        {
            this.setConnection();
        }
        dao = new FireBirdOrderDAO(new FireBirdDAOFactory(this.connection));
    }

    @Test
    public void testReadOrder()
    {
        Order order = dao.getById(1);
        Assert.assertNotNull(order);
        Assert.assertTrue(order.getArticles().size() > 0);
        System.out.println(order);
    }
}
