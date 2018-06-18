package be.solodoukhin.dao.firebird;

import be.solodoukhin.GeneralTest;
import be.solodoukhin.dao.FireBirdDAOFactory;
import be.solodoukhin.model.Category;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author SOLODOUKHIN VIKTOR
 * <p>
 * date 18/06/18
 */
public class FireBirdCategoryDAOTest extends GeneralTest{

    private FireBirdCategoryDAO dao;

    @BeforeMethod
    public void initializeDAO()
    {
        if(this.connection == null)
        {
            this.setConnection();
        }
        dao = new FireBirdCategoryDAO(new FireBirdDAOFactory(this.connection));
    }

    @Test
    public void testReadCategory()
    {
        Category category = dao.getById("B");
        Assert.assertNotNull(category);
    }
}
