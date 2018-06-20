package be.solodoukhin.controller;

import be.solodoukhin.dao.FireBirdDAOFactory;
import be.solodoukhin.dao.firebird.FireBirdArticleDAO;
import be.solodoukhin.dao.firebird.FireBirdCategoryDAO;
import be.solodoukhin.exception.RestaurantException;
import be.solodoukhin.model.Article;
import be.solodoukhin.model.Category;
import be.solodoukhin.model.enumeration.Step;
import be.solodoukhin.service.ConnectionSingleton;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author SOLODOUKHIN VIKTOR
 * <p>
 * date 20/06/18
 */
public class ArticleController {

    private FireBirdArticleDAO articleDAO;
    private Article currentArticle;

    @FXML
    private ComboBox<String> articlesList;

    @FXML
    private TextField articleCode;

    @FXML
    private TextField articleName;

    @FXML
    private TextField articlePrice;

    @FXML
    private TextField articleDesctipion;

    @FXML
    private TextField articleCalories;

    @FXML
    private ChoiceBox<Step> steps;

    @FXML
    private ChoiceBox<String> categories;

    @FXML
    private CheckBox isAvailable;

    @FXML
    private Button quitButton;

    public void initialize()
    {
        this.setUp();
    }

    void start(Stage window) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/articles.fxml"));
        Scene scene = new Scene(root, 400, 400);
        window.setScene(scene);
        window.setTitle("Articles");
        window.show();
    }

    private void setUp()
    {
        // Set Up connection
        this.articleDAO = new FireBirdArticleDAO(new FireBirdDAOFactory(ConnectionSingleton.getConnexion()));
        FireBirdCategoryDAO categoryDAO = new FireBirdCategoryDAO(new FireBirdDAOFactory(ConnectionSingleton.getConnexion()));

        // Set up choice boxes
        List<String> categoryNames = categoryDAO.getCategoryLeaves().stream().map(Category::getName).sorted().collect(Collectors.toList());
        this.steps.setItems(FXCollections.observableArrayList(Step.values()));
        this.categories.setItems(FXCollections.observableArrayList(categoryNames));

        // Set up article list
        List<String> articleCodes = this.articleDAO.getAll("").stream().map(Article::getCode).sorted().collect(Collectors.toList());
        this.articlesList.setItems(FXCollections.observableArrayList(articleCodes));

        // Change text field according to chosen article
        this.articlesList.setOnAction(event -> this.initializeWithSelectedArticle());
    }

    public void initializeWithSelectedArticle()
    {
        this.currentArticle = this.articleDAO.getById(articlesList.getSelectionModel().getSelectedItem());

        if(this.currentArticle != null)
        {
            this.articleCode.setText(this.currentArticle.getCode());
            this.articleName.setText(this.currentArticle.getName());
            this.articleDesctipion.setText(this.currentArticle.getDescription().orElse(""));
            this.articleCalories.setText(this.currentArticle.getCalories().toString());
            this.articlePrice.setText(this.currentArticle.getPrice().toString());
            this.isAvailable.setSelected(this.currentArticle.getAvailable());
            this.steps.setValue(this.currentArticle.getStep());
            this.categories.setValue(this.currentArticle.getCategory() != null ? this.currentArticle.getCategory().getName() : "");

        }
    }

    public void smartUpdateAction()
    {
        boolean updated = false;
        String oldArticleName = this.currentArticle.getName();
        String oldArticleDescription = this.currentArticle.getDescription().orElse("");
        boolean oldArticleAvailable = this.currentArticle.getAvailable();
        Integer oldArticleCalories = this.currentArticle.getCalories();
        Step oldArticleStep = this.currentArticle.getStep();
        Double oldArticlePrice = this.currentArticle.getPrice();

        try{
            if(!oldArticleName.equals(this.articleName.getText())) updated = true;
            if(!oldArticleDescription.equals(this.articleDesctipion.getText())) updated = true;
            if(!oldArticleCalories.equals(Integer.valueOf(this.articleCalories.getText()))) updated = true;
            if(!oldArticleAvailable == this.isAvailable.isSelected()) updated = true;
            if(!oldArticleStep.equals(this.steps.getValue())) updated = true;
            if(!oldArticlePrice.equals(Double.valueOf(this.articlePrice.getText()))) updated = true;

        } catch (Exception e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("An error occurred.");
            alert.setHeaderText("Could not update article.");
            alert.setContentText(e.getMessage());
            alert.show();
            e.printStackTrace();
            updated = false;
        }

        if(updated)
        {
            this.currentArticle.setName(this.articleName.getText());
            this.currentArticle.setDescription(this.articleDesctipion.getText());
            this.currentArticle.setAvailable(this.isAvailable.isSelected());
            this.currentArticle.setStep(this.steps.getValue());
            this.currentArticle.setPrice(Double.valueOf(this.articlePrice.getText()));
            this.currentArticle.setCalories(Integer.valueOf(articleCalories.getText()));
            try{
                this.articleDAO.update(this.currentArticle);
            } catch (RestaurantException e)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("An error occurred.");
                alert.setHeaderText("Could not update article.");
                alert.setContentText(e.getMessage());
                alert.show();
                e.printStackTrace();

                // reset database values
                this.initializeWithSelectedArticle();
            }
        }
    }

    public void exit()
    {
        try{
            RestaurantController restaurantController = new RestaurantController();
            restaurantController.start((Stage) this.quitButton.getScene().getWindow());
        }
        catch (Exception e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("An error occurred.");
            alert.setHeaderText("Could not continue.");
            alert.setContentText(e.getMessage());
            Optional<ButtonType> result = alert.showAndWait();
            e.printStackTrace();
            if(result.isPresent())
            {
                Platform.exit();
            }
        }
    }
}
