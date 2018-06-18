package be.solodoukhin.dao;

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

    private static String sqlFromId = "SELECT CODE_SER, NOM_SER, PRENOM_SER, EMAIL_SER FROM TSERVEUR WHERE TRIM(CODE_SER) = ?";
    private static String sqlWaiters = "SELECT CODE_SER, NOM_SER, PRENOM_SER, EMAIL_SER FROM TSERVEUR";
    private static String sqlInsert;
    private static String sqlUpdate;
    private static String sqlDelete;

    private final SQLDAOFactory factory;
    private static final Logger logger = LoggerFactory.getLogger(SQLWaiterDAO.class);

    public SQLWaiterDAO(SQLDAOFactory factory) {
        this.factory = factory;
    }

    @Override
    public Waiter getById(String id) {

        Waiter waiter = null;
        if(id == null) return null;

        try(PreparedStatement query = factory.getConnection().prepareStatement(sqlFromId))
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
        try(PreparedStatement query = factory.getConnection().prepareStatement(sqlWaiters))
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
    public String insert(Waiter object) throws Exception {
        if(object == null) return null;

        try(PreparedStatement query = factory.getConnection().prepareStatement(sqlInsert))
        {
            //TODO SET DATA
            query.executeUpdate();
            query.getConnection().commit();

        } catch (SQLException e)
        {
            logger.error("Could not insert waiter: " + object, e);
            return null;
        }

        return object.getCode();
    }

    @Override
    public boolean delete(Waiter object) throws Exception {

        //TODO

        return false;
    }

    @Override
    public boolean update(Waiter object) throws Exception {
        //TODO

        return false;
    }
}
