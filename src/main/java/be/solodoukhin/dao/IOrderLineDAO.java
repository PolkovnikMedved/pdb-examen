package be.solodoukhin.dao;

import be.solodoukhin.model.Order;
import be.solodoukhin.model.OrderLine;
import be.solodoukhin.model.embedded.OrderLineId;

/**
 * @author SOLODOUKHIN VIKTOR
 * <p>
 * date 16/06/18
 */
public interface IOrderLineDAO extends ICrudDAO<OrderLine, OrderLineId> {

    boolean deleteOrderLines(Order order);
}
