package be.solodoukhin.dao;

import java.sql.Connection;

/**
 * @author SOLODOUKHIN VIKTOR
 * <p>
 * date 16/06/18
 */
public abstract class DAOFactory {

    public abstract IArticleDAO getArticleDAO();
    public abstract ICategoryDAO getCategoryDAO();
    public abstract IWaiterDAO getWaiterDAO();
    public abstract IOrderDAO getOrderDAO();
    public abstract IOrderLineDAO getOrderLineDAO();

    public static DAOFactory getDAOFactory(PersistenceType persistenceType, Connection connection) {
        switch (persistenceType)
        {
            case FIREBIRD:
                if(connection != null) return new FireBirdDAOFactory(connection);
            case H2:
                return null; //TODO Implement h2
            default:
                break;
        }
        return null;
    }
}
