package be.solodoukhin.dao;

import be.solodoukhin.model.Order;
import be.solodoukhin.model.OrderLine;
import be.solodoukhin.model.embedded.OrderLineId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author SOLODOUKHIN VIKTOR
 * <p>
 * date 16/06/18
 */
public abstract class SQLOrderLineDAO implements IOrderLineDAO {

    private static final Logger logger = LoggerFactory.getLogger(SQLOrderDAO.class);
    private final SQLDAOFactory factory;

    public SQLOrderLineDAO(SQLDAOFactory factory) {
        this.factory = factory;
    }

    @Override
    public boolean deleteOrderLines(Order order) {
        return false;
    }

    @Override
    public OrderLine getById(OrderLineId id) {
        return null;
    }

    @Override
    public List<OrderLine> getAll(String regularExpression) {
        return null;
    }

    @Override
    public OrderLineId insert(OrderLine object) throws Exception {
        return null;
    }

    @Override
    public boolean delete(OrderLine object) throws Exception {
        return false;
    }

    @Override
    public boolean update(OrderLine object) throws Exception {
        return false;
    }
}
