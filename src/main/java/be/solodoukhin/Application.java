package be.solodoukhin;

import be.solodoukhin.controller.RestaurantController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author SOLODOUKHIN VIKTOR
 * <p>
 * date 20/06/18
 */
public class Application extends javafx.application.Application{
    @Override
    public void start(Stage primaryStage) throws Exception {
        /*
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/restaurant.fxml"));
        primaryStage.setTitle("Restaurant ISFCE");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
        */
        RestaurantController restaurantController = new RestaurantController();
        restaurantController.start(primaryStage);
    }
}
