package be.solodoukhin.dao;

import be.solodoukhin.exception.RestaurantDeleteException;
import be.solodoukhin.exception.RestaurantException;
import be.solodoukhin.exception.RestaurantInsertException;
import be.solodoukhin.model.Waiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author SOLODOUKHIN VIKTOR
 * <p>
 * date 16/06/18
 */
public abstract class SQLWaiterDAO implements IWaiterDAO {

    private static final String SQL_BY_ID = "SELECT CODE_SER, NOM_SER, PRENOM_SER, EMAIL_SER FROM TSERVEUR WHERE TRIM(CODE_SER) = ?";
    private static final String SQL_ALL = "SELECT CODE_SER, NOM_SER, PRENOM_SER, EMAIL_SER FROM TSERVEUR";
    private static final String SQL_INSERT = "INSERT INTO TSERVEUR(CODE_SER, NOM_SER, PRENOM_SER, EMAIL_SER) VALUES(?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE TSERVEUR SET PRENOM_SER = ?, NOM_SER = ?, EMAIL_SER = ? WHERE TRIM(CODE_SER) = ?";
    private static final String SQL_DELETE = "DELETE FROM TSERVEUR WHERE TRIM(CODE_SER) = ?";

    private final SQLDAOFactory factory;
    private static final Logger logger = LoggerFactory.getLogger(SQLWaiterDAO.class);

    public SQLWaiterDAO(SQLDAOFactory factory) {
        this.factory = factory;
    }

    @Override
    public Waiter getById(String id) {

        Waiter waiter = null;
        if(id == null) return null;

        try(PreparedStatement query = factory.getConnection().prepareStatement(SQL_BY_ID))
        {
            query.setString(1, id);
            ResultSet rs = query.executeQuery();
            if(rs.next())
            {
                waiter = new Waiter(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4));//TODO ADD PHONES
            }
        } catch (SQLException e)
        {
            logger.info("Could not load the waiter with id = " + id, e);
        }

        return waiter;
    }

    @Override
    public List<Waiter> getAll(String regularExpression) {

        List<Waiter> waiters = new ArrayList<>();
        try(PreparedStatement query = factory.getConnection().prepareStatement(SQL_ALL))
        {
            ResultSet rs = query.executeQuery();
            while(rs.next())
            {
                waiters.add(new Waiter(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
        } catch (SQLException e)
        {
            logger.error("Could not load waiters", e);
        }
        return waiters;
    }

    @Override
    public String insert(Waiter object) throws RestaurantInsertException {
        if(object == null) return null;

        try(PreparedStatement query = factory.getConnection().prepareStatement(SQL_INSERT))
        {
            query.setString(1, object.getCode());
            query.setString(2, object.getLastName());
            query.setString(3, object.getFirstName());
            query.setString(4, object.getEmail().orElse(null));

            query.execute();
            query.getConnection().commit();
            return object.getCode();
        } catch (SQLException e)
        {
            logger.error("Could not insert waiter: " + object, e);
            throw new RestaurantInsertException("Could not insert waiter:" + object, object);
        }
    }

    @Override
    public boolean delete(Waiter object) throws RestaurantDeleteException {

        PreparedStatement query;
        try{
            query = factory.getConnection().prepareStatement(SQL_DELETE);
            query.setString(1, object.getCode());
            query.execute();
            query.getConnection().commit();
        } catch (SQLException e)
        {
            logger.error("Could not delete waiter: " + object, e);
            throw new RestaurantDeleteException("Could not delete waiter:" + object, object);
        }

        return true;
    }

    @Override
    public boolean update(Waiter object) throws RestaurantException {
        PreparedStatement query;
        try{
            query = factory.getConnection().prepareStatement(SQL_UPDATE);
            query.setString(1, object.getFirstName());
            query.setString(2, object.getLastName());
            query.setString(3, object.getEmail().orElse(null));
            query.setString(4, object.getCode());
            query.execute();
            query.getConnection().commit();
        } catch (SQLException e)
        {
            logger.error("Could not update waiter: " + object, e);
            throw new RestaurantException("Could not update waiter:" + object);
        }
        return true;
    }
}
