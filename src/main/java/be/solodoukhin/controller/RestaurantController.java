package be.solodoukhin.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author SOLODOUKHIN VIKTOR
 * <p>
 * date 18/06/18
 */
public class RestaurantController extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/restaurant.fxml"));
        primaryStage.setTitle("Restaurant ISFCE");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }
}
