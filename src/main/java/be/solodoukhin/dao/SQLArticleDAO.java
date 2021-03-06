package be.solodoukhin.dao;

import be.solodoukhin.exception.RestaurantException;
import be.solodoukhin.exception.RestaurantInsertException;
import be.solodoukhin.model.Article;
import be.solodoukhin.model.Category;
import be.solodoukhin.model.enumeration.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public abstract class SQLArticleDAO implements IArticleDAO{

    private static final String SQL_FROM_ID = "";
    private static final String SQL_GET = "";
    private static final String SQL_INSERT = "";
    private static final String SQL_DELETE = "";
    private static final String SQL_UPDATE = "";

    private final SQLDAOFactory factory;
    private static final Logger logger = LoggerFactory.getLogger(SQLArticleDAO.class);

    public SQLArticleDAO(SQLDAOFactory factory) {
        this.factory = factory;
    }

    @Override
    public List<Article> getArticlesSteps(Step step) {

        if(step == null) return getAll(" WHERE FKETAPE_ART IS NULL ORDER BY CODE_ART", null);

        return getAll(" WHERE FKETAPE_ART = ? ORDER BY CODE_ART", q -> q.setString(1, step.name().substring(0,1)));
    }

    @Override
    public List<Article> getArticlesCategories(Category category) {
        if(category == null) return getAll(" WHERE FKCATEGORIE_ART IS NULL ORDER BY CODE_ART", null);


        return getAll(" WHERE FKCATEGORIE_ART = ? ORDER BY CODE_ART", q -> q.setString(1, category.getCode()));
    }

    @Override
    public Article getById(String id) {

        Article a = null;
        try(PreparedStatement query = factory.getConnection().prepareStatement(SQL_FROM_ID))
        {

            query.setString(1, id);
            ResultSet rs = query.executeQuery();
            if(rs.next())
            {
                Category category = null;
                if(rs.getString(7) != null)
                {
                    category = factory.getCategoryDAO().getById(rs.getString(7));
                    a = new Article(id ,
                            rs.getString(1),
                            rs.getString(2),
                            rs.getInt(3),
                            rs.getDouble(4),
                            (rs.getInt(5) == 1),
                            Step.getFromCode(rs.getString(6)),
                            category);

                }
            }

        } catch (SQLException e)
        {
            logger.error("Could not read article with id = " + id, e);
            a = null;
        }

        return a;
    }

    private List<Article> getAll(String regularExpression, IInitializeParameter initializeParameter)
    {
        String sql = SQL_GET + regularExpression;
        ICategoryDAO categoryDAO = factory.getCategoryDAO();
        List<Article> articles = new ArrayList<>();

        try(PreparedStatement query = factory.getConnection().prepareStatement(sql))
        {
            if(initializeParameter != null) initializeParameter.setParameter(query);

            ResultSet rs = query.executeQuery();
            while(rs.next())
            {
                Category category = null;
                if(rs.getString(8) != null)
                {
                    category = categoryDAO.getById(rs.getString(8));
                }

                articles.add(new Article(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getDouble(5),
                        rs.getInt(6) == 1,
                        Step.getFromCode(rs.getString(7)),
                        category
                ));
            }

        } catch (SQLException e)
        {
            logger.error("Could not read articles.", e);
        }

        return articles;
    }

    @Override
    public List<Article> getAll(String regularExpression) {
        return this.getAll(regularExpression, null);
    }

    @Override
    public String insert(Article object) throws RestaurantException {

        try{

            PreparedStatement query = factory.getConnection().prepareStatement(SQL_INSERT);
            query.setString(1, object.getCode());
            query.setString(2, object.getName());
            query.setString(3, object.getDescription().orElse(""));
            query.setString(4, Step.getCode(object.getStep()));
            query.setDouble(5, object.getPrice());
            query.setInt(6, object.getAvailable() ? 1 : 0);
            query.setInt(7, object.getCalories());
            query.setString(8, object.getCategory() == null ? null : object.getCategory().getCode());
            query.execute();
            query.getConnection().commit();
            return object.getCode();
        }catch (SQLException e)
        {
            logger.error("Could not insert article : " + object, e);
            throw new RestaurantInsertException(e.getMessage(), object);
        }
    }

    @Override
    public boolean delete(Article object) throws RestaurantException {
        return false;
    }

    @Override
    public boolean update(Article object) throws RestaurantException {
        return false;
    }
}
