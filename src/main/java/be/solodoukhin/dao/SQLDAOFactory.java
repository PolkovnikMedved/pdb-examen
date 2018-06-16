package be.solodoukhin.dao;

import java.sql.Connection;

/**
 * @author SOLODOUKHIN VIKTOR
 * <p>
 * date 16/06/18
 */
public abstract class SQLDAOFactory extends DAOFactory{

    private Connection connection;

    public SQLDAOFactory(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }
}
