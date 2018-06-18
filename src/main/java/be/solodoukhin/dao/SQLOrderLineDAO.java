package be.solodoukhin.dao;

import be.solodoukhin.model.Article;
import be.solodoukhin.model.Order;
import be.solodoukhin.model.OrderLine;
import be.solodoukhin.model.embedded.OrderLineId;
import be.solodoukhin.model.enumeration.OrderLineState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.OperationNotSupportedException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author SOLODOUKHIN VIKTOR
 * <p>
 * date 16/06/18
 */
public abstract class SQLOrderLineDAO implements IOrderLineDAO {

    private static final String SQL_GET = "SELECT NUM_LIG, FKCOMMANDE_LIG, FKARTICLE_LIG, ETAT_LIG,PAYE_LIG,PRIX_LIG FROM TLIGNECMD";
    private static final String SQL_INSERT = "INSERT INTO TLIGNECMD(FKCOMMANDE_LIG, FKARTICLE_LIG, PRIX_LIG) VALUES(?,?,?) RETURNING NUM_LIG,ETAT_LIG, PAYE_LIG";
    private static final String SQL_DELETE = "DELETE FROM TLIGNECMD WHERE FKCOMMANDE_LIG = ? AND NUM_LIG = ?";
    private static final String SQL_DELETE_LINES = "DELETE FROM TLIGNECMD WHERE FKCOMMANDE_LIG = ?";

    private static final Logger logger = LoggerFactory.getLogger(SQLOrderDAO.class);
    private final SQLDAOFactory factory;

    public SQLOrderLineDAO(SQLDAOFactory factory) {
        this.factory = factory;
    }

    @Override
    public boolean deleteOrderLines(Order order) {
        try(PreparedStatement query = factory.getConnection().prepareStatement(SQL_DELETE_LINES))
        {
            query.setInt(1, order.getNumber());
            if(query.executeUpdate() != 0)
            {
                query.getConnection().commit();
                return true;
            } else {
                query.getConnection().rollback();
                return false;
            }
        }
        catch (SQLException e)
        {
            logger.error("Could not delete order lines for order :" + order, e);
            return false;
        }
    }

    @Override
    public OrderLine getById(OrderLineId id) {
        //TODO
        return null;
    }

    @Override
    public List<OrderLine> getAll(String regularExpression) {
        List<OrderLine> orderLines = new ArrayList<>();

        try(Statement query = factory.getConnection().createStatement())
        {
            ResultSet rs = query.executeQuery(SQL_GET + " " + regularExpression);

            while(rs.next())
            {
                Article article = factory.getArticleDAO().getById(rs.getString(3));
                orderLines.add(new OrderLine(new OrderLineId(rs.getInt(2), rs.getInt(1)),
                        article,
                        OrderLineState.valueOf(rs.getString(4).trim()),
                        rs.getBoolean(5),
                        rs.getDouble(6)));
            }
        }catch (SQLException e)
        {
            logger.error("Could not load command lines.", e);
        }

        return orderLines;
    }

    @Override
    public OrderLineId insert(OrderLine object) throws Exception {
        OrderLineId orderLineId = null;
        try(PreparedStatement query = factory.getConnection().prepareStatement(SQL_INSERT))
        {
            query.setInt(1, object.getId().getOrderId());
            query.setString(2, object.getArticle().getCode());
            query.setDouble(3, object.getPrice());
            query.executeQuery();
            query.getConnection().commit();

            ResultSet rs = query.getGeneratedKeys();
            if(rs.next())
            {
                object.setId(new OrderLineId(object.getId().getOrderId(), rs.getInt(1)));
                object.setState(OrderLineState.valueOf(rs.getString(2).trim()));
                object.setPaid(rs.getInt(3) == 1);
                object.setPrice(rs.getDouble(5));
                orderLineId = object.getId();
            }
        } catch (SQLException e)
        {
            logger.error("Could not insert order line.", e);
        }

        return orderLineId;
    }

    @Override
    public boolean delete(OrderLine object) throws Exception {
        try(PreparedStatement query = factory.getConnection().prepareStatement(SQL_DELETE))
        {
            query.setInt(1, object.getId().getOrderId());
            query.setInt(2, object.getId().getOrderLineId());
            if(query.executeUpdate() != 0)
            {
                query.getConnection().commit();
                return true;
            } else {
                query.getConnection().rollback();
                return false;
            }

        } catch (SQLException e)
        {
            logger.error("Could not delete order line:" + object, e);
            return false;
        }
    }

    @Override
    public boolean update(OrderLine object) throws OperationNotSupportedException {
        throw new OperationNotSupportedException();
    }
}
