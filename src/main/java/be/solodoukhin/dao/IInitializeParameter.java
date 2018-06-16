package be.solodoukhin.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author SOLODOUKHIN VIKTOR
 * <p>
 * date 16/06/18
 */
@FunctionalInterface
public interface IInitializeParameter {
    void setParameter(PreparedStatement query) throws SQLException;
}
