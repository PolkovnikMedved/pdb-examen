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

    private static final String SQL_GET = "";
    private static final String SQL_INSERT = "";
    private static final String SQL_DELETE = "";

    private static final Logger logger = LoggerFactory.getLogger(SQLOrderDAO.class);
    private final SQLDAOFactory factory;

    public SQLOrderLineDAO(SQLDAOFactory factory) {
        this.factory = factory;
    }

    @Override
    public boolean deleteOrderLines(Order order) {
        //TODO
        return false;
    }

    @Override
    public OrderLine getById(OrderLineId id) {
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
                orderLines.add(new OrderLine(new OrderLineId(rs.getInt(1), rs.getInt(2)),
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
        //TODO
        return false;
    }

    @Override
    public boolean update(OrderLine object) throws Exception {
        throw new OperationNotSupportedException();
    }
}
