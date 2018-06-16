package be.solodoukhin.service;

import be.solodoukhin.service.connection.IConnectionInformation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author SOLODOUKHIN VIKTOR
 * <p>
 * date 16/06/18
 */
public final class ConnectionSingleton {

    private static final Logger logger = LoggerFactory.getLogger(ConnectionSingleton.class);
    private static Connection connection;
    private static IConnectionInformation connectionInformation;

    public static Connection getConnexion() {
        if (connectionInformation == null){
            logger.error("We need connection information object.");
        }

        if (connection == null) {
            Properties props = connectionInformation.getProperties();
            logger.info("Loading connection properties: OK.");

            String url = props.getProperty("url");

            try {
                connection = DriverManager.getConnection(url, props);
                logger.info("Database connection: OK");
                connection.setAutoCommit(false);
            } catch (SQLException e) {
                logger.error("Could not open database connection.", e.getMessage());
                releaseDatabaseConnection();
            }
        }
        return connection;
    }

    public static void releaseDatabaseConnection() {
        if (connection != null) {
            try {
                connection.close();
                logger.info("Database connection closed: OK");
            } catch (SQLException e1) {
                logger.warn("An error occurred closing connection.", e1);
            }
            connection = null;
        }
        logger.info("Connection released: OK");
    }

    public static void setConnectionInformation(IConnectionInformation info) {
        connectionInformation = info;
        releaseDatabaseConnection();
    }
}
