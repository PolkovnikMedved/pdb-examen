package be.solodoukhin.dao.firebird;

import be.solodoukhin.GeneralTest;
import be.solodoukhin.dao.FireBirdDAOFactory;
import be.solodoukhin.model.Waiter;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

/**
 * @author SOLODOUKHIN VIKTOR
 * <p>
 * date 18/06/18
 */
public class FireBirdWaiterDAOTest extends GeneralTest{

    private FireBirdWaiterDAO dao;

    @BeforeMethod
    public void initializeDAO()
    {
        if(this.connection == null)
        {
            this.setConnection();
        }
        dao = new FireBirdWaiterDAO(new FireBirdDAOFactory(this.connection));
    }

    @Test
    public void testReadOne()
    {
        Waiter waiter = dao.getById("Phil");
        Assert.assertNotNull(waiter);
        System.out.println(waiter);
    }

    @Test
    public void testReadAll()
    {
        List<Waiter> waiters = dao.getAll("");
        Assert.assertTrue(waiters.size() > 0);
        waiters.forEach(System.out::println);
    }
}

