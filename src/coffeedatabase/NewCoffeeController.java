/*
 * Written by Harry Silman
 */
package coffeedatabase;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Silman
 */
public class NewCoffeeController implements Initializable {

    @FXML
    private TextField CompanyTextField;
    @FXML
    private TextField BeanNameTextField;
    @FXML
    private TextField RoastMethodTextField;
    @FXML
    private TextField WeightTextField;
    @FXML
    private TextField TimeToFirstCrackTextField;
    @FXML
    private TextField TimeToSecondCrackTextField;
    @FXML
    private TextField TotalRoastTimeTextField;
    @FXML
    private TextField RatingTextField;
    @FXML
    private TextField FlavorsTextField;
    @FXML
    private Button saveButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    protected void handleSaveButtonAction(ActionEvent event) throws ClassNotFoundException {

        SimpleStringProperty company = new SimpleStringProperty(CompanyTextField.getText());
        SimpleStringProperty beanName = new SimpleStringProperty(BeanNameTextField.getText());
        SimpleStringProperty roastMethod = new SimpleStringProperty(RoastMethodTextField.getText());
        SimpleStringProperty weight = new SimpleStringProperty(WeightTextField.getText());
        SimpleStringProperty t21c = new SimpleStringProperty(TimeToFirstCrackTextField.getText());
        SimpleStringProperty t22c = new SimpleStringProperty(TimeToSecondCrackTextField.getText());
        SimpleStringProperty trt = new SimpleStringProperty(TotalRoastTimeTextField.getText());
        SimpleStringProperty flavors = new SimpleStringProperty(FlavorsTextField.getText());
        SimpleStringProperty rating = new SimpleStringProperty(RatingTextField.getText());

        Coffee coffee = new Coffee(company, beanName, roastMethod, weight, t21c, t22c, trt,
                flavors, rating);

        try {
            addCoffeeToDatabaseTable(coffee);
        } catch (SQLException ex) {
            Logger.getLogger(NewCoffeeController.class.getName()).log(Level.SEVERE, null, ex);
        }

        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();

    }

    private void addCoffeeToDatabaseTable(Coffee coffee) throws SQLException, ClassNotFoundException {

        try (
                Connection con = DBConnect.connect();
                PreparedStatement stmt = con.prepareStatement("INSERT INTO coffees"
                        + " (Company, Bean_Name, Roast_Method, Weight,"
                        + " Time_To_First_Crack, Time_To_Second_Crack,"
                        + " Total_Roast_Time, Flavors, Rating)"
                        + " VALUES (?,?,?,?,?,?,?,?,?)");) {

            stmt.setString(1, coffee.companyProperty().get());
            stmt.setString(2, coffee.beanNameProperty().get());
            stmt.setString(3, coffee.roastMethodProperty().get());
            stmt.setString(4, coffee.weightProperty().get());
            stmt.setString(5, coffee.timeToFirstCrackProperty().get());
            stmt.setString(6, coffee.timeToSecondCrackProperty().get());
            stmt.setString(7, coffee.totalRoastTimeProperty().get());
            stmt.setString(8, coffee.flavorsProperty().get());
            stmt.setString(9, coffee.ratingProperty().get());

            stmt.execute();
            System.out.println("Added " + coffee.beanNameProperty().get());
        } catch (SQLException ex) {
            System.err.println("Error");
            // if anything goes wrong, you will need the stack trace:
            ex.printStackTrace(System.err);
        }

    }
}
