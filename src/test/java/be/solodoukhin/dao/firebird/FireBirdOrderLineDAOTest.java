package be.solodoukhin.dao.firebird;

import be.solodoukhin.GeneralTest;
import be.solodoukhin.dao.FireBirdDAOFactory;
import be.solodoukhin.model.OrderLine;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.naming.OperationNotSupportedException;

/**
 * @author SOLODOUKHIN VIKTOR
 * <p>
 * date 18/06/18
 */
public class FireBirdOrderLineDAOTest extends GeneralTest{

    private FireBirdOrderLineDAO dao;

    @BeforeMethod
    public void initializeDAO()
    {
        if(this.connection == null)
        {
            this.setConnection();
        }
        dao = new FireBirdOrderLineDAO(new FireBirdDAOFactory(this.connection));
    }

    @Test(expectedExceptions = OperationNotSupportedException.class)
    public void testUpdateException() throws OperationNotSupportedException
    {
        OrderLine orderLine = null;
        dao.update(orderLine);
    }
}
