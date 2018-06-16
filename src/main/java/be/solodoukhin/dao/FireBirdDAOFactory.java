package be.solodoukhin.dao;

import be.solodoukhin.dao.firebird.*;

import java.sql.Connection;

/**
 * @author SOLODOUKHIN VIKTOR
 * <p>
 * date 16/06/18
 */
public class FireBirdDAOFactory extends SQLDAOFactory{


    public FireBirdDAOFactory(Connection connection) {
        super(connection);
    }

    @Override
    public IArticleDAO getArticleDAO() {
        return new FireBirdArticleDAO(this);
    }

    @Override
    public ICategoryDAO getCategoryDAO() {
        return new FireBirdCategoryDAO(this);
    }

    @Override
    public IWaiterDAO getWaiterDAO() {
        return new FireBirdWaiterDAO(this);
    }

    @Override
    public IOrderDAO getOrderDAO() {
        return new FireBirdOrderDAO(this);
    }

    @Override
    public IOrderLineDAO getOrderLineDAO() {
        return new FireBirdOrderLineDAO(this);
    }
}
