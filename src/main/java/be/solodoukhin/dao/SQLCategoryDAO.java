package be.solodoukhin.dao;

import be.solodoukhin.model.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.OperationNotSupportedException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author SOLODOUKHIN VIKTOR
 * <p>
 * date 16/06/18
 */
public abstract class SQLCategoryDAO implements ICategoryDAO {

    private static String sqlFromId = "SELECT NOM_CAT, FKCATPRINC_CAT FROM TCATEGORIE WHERE CODE_CAT = ?";
    private static String sqlCategories = "SELECT CODE_CAT, NOM_CAT, FKCATPRINC_CAT FROM TCATEGORIE";
    private static String sqlInsert = "INSERT INTO TCATEGORIE(CODE_CAT, NOM_CAT, FKCATPRINC_CAT) VALUES(?,?,?)";
    private static String sqlDelete = "DELETE FROM TCATEGORIE WHERE CODE_CAT = ?";
    private static String sqlNumberOfChildren = "SELECT COUNT(CODE_CAT) FROM TCATEGORIE WHERE FKCATPRINC_CAT = ?";
    private static String sqlCategoryLeaves = "SELECT CODE_CAT, NOM_CAT, FKCATPRINC_CAT FROM TCATEGORIE WHERE CODE_CAT NOT IN (SELECT c.FKCATPRINC_CAT FROM TCATEGORIE c WHERE c.FKCATPRINC_CAT IS NOT NULL)";

    private final SQLDAOFactory factory;
    private static final Logger logger = LoggerFactory.getLogger(SQLCategoryDAO.class);

    public SQLCategoryDAO(SQLDAOFactory factory) {
        this.factory = factory;
    }

    @Override
    public List<Category> getCategoryLeaves() {

        List<Category> categories = new ArrayList<>();
        try(PreparedStatement query = factory.getConnection().prepareStatement(sqlCategoryLeaves))
        {
            ResultSet rs = query.executeQuery();
            while(rs.next())
            {
                categories.add(new Category(rs.getString(1), rs.getString(2)));
            }
        } catch (SQLException e)
        {
            logger.error("Could not get categories.", e);
        }
        return categories;
    }

    @Override
    public Category getById(String id) {

        Category category = null;
        Category childCategory;
        Category parentCategory;
        if(id != null)
        {
            id = id.trim();

            try(PreparedStatement query = factory.getConnection().prepareStatement(sqlFromId))
            {
                query.setString(1, id);
                ResultSet rs = query.executeQuery();

                if(rs.next())
                {
                    category = new Category(id, rs.getString(1));
                    logger.debug("Let us create child category.");

                    id = rs.getString(2);

                    childCategory = category;
                    while(id != null)
                    {
                        id = id.trim();
                        query.setString(1, id);
                        rs = query.executeQuery();
                        if(rs.next())
                        {
                            parentCategory = new Category(id, rs.getString(1));
                            logger.debug("Let us create parent category");
                            childCategory.setParentCategory(parentCategory);
                            id = rs.getString(2);
                            childCategory = parentCategory;
                        }
                    }
                }

            } catch (SQLException e){
                logger.error("Could not create category.", e);
            }
        }

        return category;
    }

    @Override
    public List<Category> getAll(String regularExpression) {
        List<Category> categories = new ArrayList<>();
        try(PreparedStatement query = factory.getConnection().prepareStatement(sqlCategories))
        {
            ResultSet rs = query.executeQuery();

            while(rs.next())
            {
                categories.add(new Category(rs.getString(1), rs.getString(2)));
            }

        } catch (SQLException e)
        {
            logger.error("Could not load categories.", e);
        }

        return categories;
    }

    @Override
    public String insert(Category object) throws Exception {
        ResultSet rs;
        if(object == null){
            return null;
        }

        // Let us create parent categories
        if(object.getParentCategory() != null)
        {
            try(PreparedStatement queryExists = factory.getConnection().prepareStatement(sqlFromId))
            {
                queryExists.setString(1, object.getParentCategory().getCode());
                rs = queryExists.executeQuery();
                if(!rs.next())
                {
                    insert(object.getParentCategory());
                }
            } catch (SQLException e)
            {
                logger.error("Could not insert parent category.", e);
            }
        }

        try(PreparedStatement query = factory.getConnection().prepareStatement(sqlInsert))
        {
            query.setString(1, object.getCode());
            query.setString(2, object.getName());
            query.setString(3, object.getParentCategory() != null ? object.getParentCategory().getCode() : null);

            query.executeUpdate();
            query.getConnection().commit();
        } catch (SQLException e)
        {
            logger.error("Could not insert category.", e);
            return null;
        }

        return object.getCode();
    }

    @Override
    public boolean delete(Category object) throws Exception {

        boolean done = false;
        if(object == null) return true;
        if(object.getCode() == null || object.getParentCategory() != null) return false;

        String code = object.getCode().trim();
        ResultSet rs;
        int cpt;
        try(PreparedStatement query = factory.getConnection().prepareStatement(sqlNumberOfChildren))
        {
            query.setString(1, code);
            rs = query.executeQuery();
            rs.next();

            if(rs.getInt(1) == 0)
            {
                try(PreparedStatement secondQuery = factory.getConnection().prepareCall(sqlDelete))
                {
                    secondQuery.setString(1, code);
                    cpt = secondQuery.executeUpdate();
                    done = (cpt != 0);
                    logger.debug("Leaf category has been deleted: ", code);
                    factory.getConnection().commit();

                } catch (SQLException e)
                {
                    logger.error("Could not delete category.", e);
                    factory.getConnection().rollback();
                }
            }

        } catch (Exception e)
        {
            logger.error("An error occurred.", e);
        }


        return done;
    }

    @Override
    public boolean update(Category object) throws Exception {
        throw new OperationNotSupportedException("Category is not updatable.");
    }
}
