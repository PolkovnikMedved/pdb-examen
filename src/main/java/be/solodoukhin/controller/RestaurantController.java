package be.solodoukhin.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    private WaiterController waiterController;

    @FXML
    private Button getArticleButton;

    @FXML
    private Button getWaitersButton;

    public void initialize() throws Exception
    {
        this.articleController = new ArticleController();
        getArticleButton.setOnAction(event -> this.showArticles());
        this.waiterController = new WaiterController();
        getWaitersButton.setOnAction(event -> this.showWaiters());
    }

    public void start(Stage window) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/restaurant.fxml"));
        Scene scene = new Scene(root, 600, 400);
        window.setScene(scene);
        window.setTitle("ISFCE Restaurant welcome");
        window.show();
    }

    private void showArticles()
    {
        try{
            Stage stage = (Stage) this.getArticleButton.getScene().getWindow();
            articleController.start(stage);
        } catch (Exception e)
        {
            this.showError(e);
        }
    }

    private void showWaiters()
    {
        try{
            Stage stage = (Stage) this.getWaitersButton.getScene().getWindow();
            waiterController.start(stage);
        } catch (Exception e)
        {
            this.showError(e);
        }
    }

    private void showError(Throwable error)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("An error occurred.");
        alert.setHeaderText("Could not continue.");
        alert.setContentText(error.getMessage());
        Optional<ButtonType> result = alert.showAndWait();
        error.printStackTrace();
        if(result.isPresent())
        {
            Platform.exit();
        }
    }
}
