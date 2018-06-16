package be.solodoukhin.dao.firebird;

import be.solodoukhin.dao.SQLDAOFactory;
import be.solodoukhin.dao.SQLOrderLineDAO;

/**
 * @author SOLODOUKHIN VIKTOR
 * <p>
 * date 16/06/18
 */
public class FireBirdOrderLineDAO extends SQLOrderLineDAO{
    public FireBirdOrderLineDAO(SQLDAOFactory factory) {
        super(factory);
    }
}
