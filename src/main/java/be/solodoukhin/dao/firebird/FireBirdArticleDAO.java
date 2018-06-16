package be.solodoukhin.dao.firebird;

import be.solodoukhin.dao.SQLArticleDAO;
import be.solodoukhin.dao.SQLDAOFactory;

/**
 * @author SOLODOUKHIN VIKTOR
 * <p>
 * date 16/06/18
 */
public class FireBirdArticleDAO extends SQLArticleDAO{

    public FireBirdArticleDAO(SQLDAOFactory factory) {
        super(factory);
    }
}
