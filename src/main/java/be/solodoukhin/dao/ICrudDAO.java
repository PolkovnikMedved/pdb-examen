package be.solodoukhin.dao;

import java.util.List;

/**
 * @author SOLODOUKHIN VIKTOR
 * <p>
 * date 16/06/18
 */
public interface ICrudDAO<T, K> {

    T getById(K id);
    List<T> getAll(String regularExpression);
    K insert(T object) throws Exception;
    boolean delete(T object) throws Exception;
    boolean update(T object) throws Exception;
}
