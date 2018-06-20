package be.solodoukhin.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;

/**
 * @author SOLODOUKHIN VIKTOR
 * <p>
 * date 18/06/18
 */
public class RestaurantController{

    private ArticleController articleController;

    @FXML
    private Button getArticleButton;

    public void initialize() throws Exception
    {
        this.articleController = new ArticleController();
        getArticleButton.setOnAction(event -> this.showArticles());
    }

    private void showArticles()
    {
        try{
            Stage stage = (Stage) this.getArticleButton.getScene().getWindow();
            articleController.start(stage);
        } catch (Exception e)
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
