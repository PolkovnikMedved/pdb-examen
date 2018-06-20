package be.solodoukhin.controller;

import be.solodoukhin.dao.FireBirdDAOFactory;
import be.solodoukhin.dao.firebird.FireBirdWaiterDAO;
import be.solodoukhin.exception.RestaurantException;
import be.solodoukhin.model.Waiter;
import be.solodoukhin.service.ConnectionSingleton;
import be.solodoukhin.service.connection.ConnectionFromFile;
import be.solodoukhin.service.url.Databases;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

import java.sql.Connection;
import java.util.Optional;

/**
 * @author SOLODOUKHIN VIKTOR
 * <p>
 * date 20/06/18
 */
public class WaiterController {

    private FireBirdWaiterDAO waiterDAO;

    @FXML
    private TableView<Waiter> waiterTableView;

    @FXML
    private TableColumn<Waiter, String> code;

    @FXML
    private TableColumn<Waiter, String> lastName;

    @FXML
    private TableColumn<Waiter, String> firstName;

    @FXML
    private TableColumn<Waiter, String> email;

    @FXML
    private Button quitButton;

    void start(Stage window) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/waiters.fxml"));
        Scene scene = new Scene(root, 800, 400);
        window.setScene(scene);
        window.setTitle("Waiters");
        window.show();
    }

    public void initialize() {
        this.setUp();
    }

    private void setUp()
    {
        // Set Up connection
        ConnectionSingleton.setConnectionInformation(new ConnectionFromFile("connectionRestoTest.properties", Databases.FIREBIRD));
        Connection connection = ConnectionSingleton.getConnexion();
        this.waiterDAO = new FireBirdWaiterDAO(new FireBirdDAOFactory(connection));

        // define column properties
        this.code.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getCode()));
        this.lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        this.lastName.setCellFactory(TextFieldTableCell.forTableColumn());
        this.lastName.setOnEditCommit(event -> {
            Waiter waiter = event.getRowValue();
            waiter.setLastName(event.getNewValue());
            this.updateWaiter(waiter);
        });

        this.firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        this.firstName.setCellFactory(TextFieldTableCell.forTableColumn());
        this.firstName.setOnEditCommit(event -> {
            Waiter waiter = event.getRowValue();
            waiter.setFirstName(event.getNewValue());
            this.updateWaiter(waiter);
        });

        this.email.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getEmail().orElse("")));
        this.email.setCellFactory(TextFieldTableCell.forTableColumn());
        this.email.setOnEditCommit(event -> {
            Waiter waiter = event.getRowValue();
            if (event.getNewValue().equals("")) // Empty string is not accepted by the database
            {
                waiter.setEmail(null);
            } else {
                waiter.setEmail(event.getNewValue());
            }
            this.updateWaiter(waiter);
        });

        // initialize table with database data
        this.waiterTableView.setItems(FXCollections.observableArrayList(waiterDAO.getAll("")));
    }

    private void updateWaiter(Waiter waiter)
    {
        try{
            this.waiterDAO.update(waiter);
            this.successAlert();
        } catch (RestaurantException e){
            e.printStackTrace();
            this.showError(e);
        }
    }

    public void reload()
    {
        this.setUp();
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

    private void successAlert()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Success");
        alert.setContentText("The waiter has been updated.");
        alert.show();
    }
}
