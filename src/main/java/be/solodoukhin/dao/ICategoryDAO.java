package be.solodoukhin.dao;

import be.solodoukhin.model.Category;

import java.util.List;

/**
 * @author SOLODOUKHIN VIKTOR
 * <p>
 * date 16/06/18
 */
public interface ICategoryDAO extends ICrudDAO<Category, String> {

    List<Category> getCategoryLeaves();
}
