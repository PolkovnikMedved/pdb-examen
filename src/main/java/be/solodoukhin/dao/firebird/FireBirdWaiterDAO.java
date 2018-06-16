package be.solodoukhin.dao.firebird;

import be.solodoukhin.dao.SQLDAOFactory;
import be.solodoukhin.dao.SQLWaiterDAO;

/**
 * @author SOLODOUKHIN VIKTOR
 * <p>
 * date 16/06/18
 */
public class FireBirdWaiterDAO extends SQLWaiterDAO{
    public FireBirdWaiterDAO(SQLDAOFactory factory) {
        super(factory);
    }
}
