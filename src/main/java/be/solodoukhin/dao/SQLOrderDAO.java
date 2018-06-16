package be.solodoukhin.dao;

import be.solodoukhin.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author SOLODOUKHIN VIKTOR
 * <p>
 * date 16/06/18
 */
public abstract class SQLOrderDAO implements IOrderDAO {

    private static final Logger logger = LoggerFactory.getLogger(SQLOrderDAO.class);
    private final SQLDAOFactory factory;

    public SQLOrderDAO(SQLDAOFactory factory) {
        this.factory = factory;
    }

    @Override
    public Order getById(Integer id) {
        return null;
    }

    @Override
    public List<Order> getAll(String regularExpression) {
        return null;
    }

    @Override
    public Integer insert(Order object) throws Exception {
        return null;
    }

    @Override
    public boolean delete(Order object) throws Exception {
        return false;
    }

    @Override
    public boolean update(Order object) throws Exception {
        return false;
    }
}
