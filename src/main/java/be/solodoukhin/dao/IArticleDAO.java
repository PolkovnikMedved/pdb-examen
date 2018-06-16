package be.solodoukhin.dao;

import be.solodoukhin.model.Article;
import be.solodoukhin.model.Category;
import be.solodoukhin.model.enumeration.Step;

import java.util.List;

/**
 * @author SOLODOUKHIN VIKTOR
 * <p>
 * date 16/06/18
 */
public interface IArticleDAO extends ICrudDAO<Article, String> {

    List<Article> getArticlesSteps(Step step);
    List<Article> getArticlesCategories(Category category);
}
