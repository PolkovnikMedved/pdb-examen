package be.solodoukhin.dao.firebird;

import be.solodoukhin.dao.SQLDAOFactory;
import be.solodoukhin.dao.SQLOrderDAO;

/**
 * @author SOLODOUKHIN VIKTOR
 * <p>
 * date 16/06/18
 */
public class FireBirdOrderDAO extends SQLOrderDAO{
    public FireBirdOrderDAO(SQLDAOFactory factory) {
        super(factory);
    }
}
