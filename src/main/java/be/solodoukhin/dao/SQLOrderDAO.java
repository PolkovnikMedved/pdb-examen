package be.solodoukhin.dao;

import be.solodoukhin.model.Order;
import be.solodoukhin.model.OrderLine;
import be.solodoukhin.model.Waiter;
import be.solodoukhin.model.enumeration.OrderState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.OperationNotSupportedException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author SOLODOUKHIN VIKTOR
 * <p>
 * date 16/06/18
 */
public abstract class SQLOrderDAO implements IOrderDAO {

    private static final String SQL_BY_ID = "SELECT NUM_COM, MOMENTA_COM, MOMENTS_COM, ETAT_COM, TOTAL_COM, FKSERVEUR_COM FROM TCOMMANDE WHERE NUM_COM = ?";
    private static final String SQL_INSERT = "INSERT INTO TCOMMANDE(FKSERVEUR_COM) VALUES (?) RETURNING NUM_COM, MOMENTA_COM";
    private static final String SQL_DELETE = "DELETE FROM TCOMMANDE WHERE NUM_COM = ?";

    private static final Logger logger = LoggerFactory.getLogger(SQLOrderDAO.class);
    private final SQLDAOFactory factory;

    public SQLOrderDAO(SQLDAOFactory factory) {
        this.factory = factory;
    }

    @Override
    public Order getById(Integer id) {
        Order order = null;
        try(PreparedStatement query = factory.getConnection().prepareStatement(SQL_BY_ID))
        {
            query.setInt(1, id);
            ResultSet rs = query.executeQuery();
            if(rs.next())
            {
                Waiter waiter = factory.getWaiterDAO().getById(rs.getString(6));
                List<OrderLine> orderLines = factory.getOrderLineDAO().getAll(" WHERE FKCOMMANDE_LIG="+id);
                order = new Order(id,
                        rs.getTimestamp(2).toLocalDateTime(),
                        rs.getTimestamp(3) != null ? rs.getTimestamp(3).toLocalDateTime() : null,
                        OrderState.valueOf(rs.getString(4).trim()),
                        rs.getDouble(5),
                        waiter,
                        orderLines);
            }
        } catch (SQLException e)
        {
            logger.error("Could not find order with id = " + id, e);
        }

        return order;
    }

    @Override
    public List<Order> getAll(String regularExpression) {
        return null;
    }

    @Override
    public Integer insert(Order object) throws Exception {
        Integer out = null;
        ResultSet rs;

        if(object.getNumber() != null){
            update(object);
        } else {
            try(PreparedStatement query = factory.getConnection().prepareStatement(SQL_INSERT))
            {
                query.setString(1, object.getWaiter().getCode());
                rs = query.executeQuery();
                if(rs.next())
                {
                    out = rs.getInt(1);
                    object.setNumber(out);
                    object.setArrivedMoment(rs.getTimestamp(2).toLocalDateTime());
                }
                query.getConnection().commit();
                logger.info("A new order has been created with id = " + out);
            } catch (SQLException e)
            {
                logger.error("Could not insert order.", e);
                throw e;
            }
        }
        return out;
    }

    @Override
    public boolean delete(Order object) throws Exception {
        try(PreparedStatement query = factory.getConnection().prepareStatement(SQL_DELETE))
        {
            if(!object.getArticles().isEmpty())
            {
                factory.getOrderLineDAO().deleteOrderLines(object);
            }
            query.setInt(1, object.getNumber());
            if(query.executeUpdate() != 0)
            {
                query.getConnection().commit();
                return true;
            }

        } catch (SQLException e)
        {
            logger.error("Could not delete the order : " + object, e);
        }
        return false;
    }

    @Override
    public boolean update(Order object) throws Exception {
        throw new OperationNotSupportedException();
    }
}
