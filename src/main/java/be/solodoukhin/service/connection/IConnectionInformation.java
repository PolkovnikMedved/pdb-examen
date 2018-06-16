package be.solodoukhin.service.connection;

import java.util.Properties;

/**
 * @author SOLODOUKHIN VIKTOR
 * <p>
 * date 16/06/18
 */
@FunctionalInterface
public interface IConnectionInformation {

    /**
     * Retourne une map avec les infos de connexion précisant:
     * user
     * password
     * encoding
     * url
     * ...
     * @return Properties
     */
    Properties getProperties();
}
