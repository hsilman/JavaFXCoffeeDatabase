/*
 * Written by Harry Silman
 */
package coffeedatabase;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

/**
 *
 * @author Silman
 */
public class CoffeeDatabaseController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private ObservableList<Coffee> coffeeData;
    @FXML
    private TableView<Coffee> tableView;
    @FXML
    private TableColumn<Coffee, String> Company;
    @FXML
    private TableColumn<Coffee, String> BeanName;
    @FXML
    private TableColumn<Coffee, String> RoastMethod;
    @FXML
    private TableColumn<Coffee, String> Weight;
    @FXML
    private TableColumn<Coffee, String> TimeTo1C;
    @FXML
    private TableColumn<Coffee, String> TimeTo2C;
    @FXML
    private TableColumn<Coffee, String> TRT;
    @FXML
    private TableColumn<Coffee, String> Flavors;
    @FXML
    private TableColumn<Coffee, String> Rating;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //create reference to FXML list for coffees
        coffeeData = FXCollections.observableArrayList();

        //initialize list of coffees from database
        try {
            Connection con = DBConnect.getConnection();
            initializeList(con);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CoffeeDatabaseController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //create Cell Value Factories and editable text fields
        tableView.setEditable(true);
        tableView.getSelectionModel().setCellSelectionEnabled(true);

        Company.setCellValueFactory(new PropertyValueFactory<>("company"));
        Company.setCellFactory(TextFieldTableCell.forTableColumn());
        BeanName.setCellValueFactory(new PropertyValueFactory<>("beanName"));
        BeanName.setCellFactory(TextFieldTableCell.forTableColumn());
        RoastMethod.setCellValueFactory(new PropertyValueFactory<>("roastMethod"));
        RoastMethod.setCellFactory(TextFieldTableCell.forTableColumn());
        Weight.setCellValueFactory(new PropertyValueFactory<>("weight"));
        Weight.setCellFactory(TextFieldTableCell.forTableColumn());
        TimeTo1C.setCellValueFactory(new PropertyValueFactory<>("timeToFirstCrack"));
        TimeTo1C.setCellFactory(TextFieldTableCell.forTableColumn());
        TimeTo2C.setCellValueFactory(new PropertyValueFactory<>("timeToSecondCrack"));
        TimeTo2C.setCellFactory(TextFieldTableCell.forTableColumn());
        TRT.setCellValueFactory(new PropertyValueFactory<>("totalRoastTime"));
        TRT.setCellFactory(TextFieldTableCell.forTableColumn());
        Flavors.setCellValueFactory(new PropertyValueFactory<>("flavors"));
        Flavors.setCellFactory(TextFieldTableCell.forTableColumn());
        Rating.setCellValueFactory(new PropertyValueFactory<>("rating"));
        Rating.setCellFactory(TextFieldTableCell.forTableColumn());


        /*
        Save cell edits to database
         */
        Company.setOnEditCommit(event -> {
            Coffee coffee = event.getRowValue();
            coffee.setCompany(event.getNewValue());
            try {
                editDatabase("Company", event.getNewValue(), coffee.idProperty().get());
            } catch (SQLException ex) {
                Logger.getLogger(CoffeeDatabaseController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CoffeeDatabaseController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        BeanName.setOnEditCommit(event -> {
            Coffee coffee = event.getRowValue();
            coffee.setBeanName(event.getNewValue());
            try {
                editDatabase("Bean_Name", event.getNewValue(), coffee.idProperty().get());
            } catch (SQLException ex) {
                Logger.getLogger(CoffeeDatabaseController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CoffeeDatabaseController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        RoastMethod.setOnEditCommit(event -> {
            Coffee coffee = event.getRowValue();
            coffee.setRoastMethod(event.getNewValue());
            try {
                editDatabase("Roast_Method", event.getNewValue(), coffee.idProperty().get());
            } catch (SQLException ex) {
                Logger.getLogger(CoffeeDatabaseController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CoffeeDatabaseController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        Weight.setOnEditCommit(event -> {
            Coffee coffee = event.getRowValue();
            coffee.setWeight(event.getNewValue());
            try {
                editDatabase("Weight", event.getNewValue(), coffee.idProperty().get());
            } catch (SQLException ex) {
                Logger.getLogger(CoffeeDatabaseController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CoffeeDatabaseController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        TimeTo1C.setOnEditCommit(event -> {
            Coffee coffee = event.getRowValue();
            coffee.setWeight(event.getNewValue());
            try {
                editDatabase("Time_To_First_Crack", event.getNewValue(), coffee.idProperty().get());
            } catch (SQLException ex) {
                Logger.getLogger(CoffeeDatabaseController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CoffeeDatabaseController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        TimeTo2C.setOnEditCommit(event -> {
            Coffee coffee = event.getRowValue();
            coffee.setWeight(event.getNewValue());
            try {
                editDatabase("Time_To_Second_Crack", event.getNewValue(), coffee.idProperty().get());
            } catch (SQLException ex) {
                Logger.getLogger(CoffeeDatabaseController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CoffeeDatabaseController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        TRT.setOnEditCommit(event -> {
            Coffee coffee = event.getRowValue();
            coffee.setWeight(event.getNewValue());
            try {
                editDatabase("Total_Roast_Time", event.getNewValue(), coffee.idProperty().get());
            } catch (SQLException ex) {
                Logger.getLogger(CoffeeDatabaseController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CoffeeDatabaseController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        Flavors.setOnEditCommit(event -> {
            Coffee coffee = event.getRowValue();
            coffee.setWeight(event.getNewValue());
            try {
                editDatabase("Flavors", event.getNewValue(), coffee.idProperty().get());
            } catch (SQLException ex) {
                Logger.getLogger(CoffeeDatabaseController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CoffeeDatabaseController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        Rating.setOnEditCommit(event -> {
            Coffee coffee = event.getRowValue();
            coffee.setRating(event.getNewValue());
            try {
                editDatabase("Rating", event.getNewValue(), coffee.idProperty().get());
            } catch (SQLException ex) {
                Logger.getLogger(CoffeeDatabaseController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CoffeeDatabaseController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        // populate tableview
        updateTable();

    }

    private void initializeList(Connection con) throws SQLException {

        Statement statement = null;
        String query = "select * from coffees";

        try {
            statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id");
                String company = rs.getString("Company");
                String beanName = rs.getString("Bean_Name");
                String roastMethod = rs.getString("Roast_Method");
                String weight = rs.getString("Weight");
                String t21c = rs.getString("Time_To_First_Crack");
                String t22c = rs.getString("Time_To_Second_Crack");
                String trt = rs.getString("Total_Roast_Time");
                String flavors = rs.getString("Flavors");
                String rating = rs.getString("Rating");

                Coffee coffee = new Coffee();
                coffee.setID(id);
                coffee.setCompany(company);
                coffee.setBeanName(beanName);
                coffee.setRoastMethod(roastMethod);
                coffee.setWeight(weight);
                coffee.setTimeToFirstCrack(t21c);
                coffee.setTimeToSecondCrack(t22c);
                coffee.setTotalRoastTime(trt);
                coffee.setFlavors(flavors);
                coffee.setRating(rating);

                coffeeData.add(coffee);
            }
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    private void updateTable() {
        tableView.getColumns().clear();
        tableView.setItems(coffeeData);
        tableView.getColumns().addAll(Company, BeanName, RoastMethod, Weight,
                TimeTo1C, TimeTo2C, TRT, Flavors, Rating);

    }

    @FXML
    protected void handleDeleteButtonAction(ActionEvent event) {

        Alert alert = new Alert(AlertType.CONFIRMATION, "Delete Selection?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            Coffee coffee = tableView.getSelectionModel().getSelectedItem();

            try {
                deleteCoffeeFromDatabase(coffee);
            } catch (SQLException ex) {
                Logger.getLogger(CoffeeDatabaseController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CoffeeDatabaseController.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                Connection con = DBConnect.getConnection();
                coffeeData.clear();
                initializeList(con);
                updateTable();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(CoffeeDatabaseController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    @FXML
    protected void handleNewButtonAction(ActionEvent event) throws IOException {
        
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("NewCoffee.fxml"));
          
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage stage = new Stage();
            stage.setTitle("New Coffee");
            stage.setScene(scene);
            stage.show();

            // updates the tableview after new coffee added to DB
            stage.setOnHiding((WindowEvent we) -> {
               
                    try {
                        Connection con = DBConnect.getConnection();
                        coffeeData.clear();
                        initializeList(con);
                        updateTable();
                    } catch (SQLException | ClassNotFoundException ex) {
                        Logger.getLogger(CoffeeDatabaseController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                
            });
        

    }

    // updates the database column based on unique ID
    private void editDatabase(String column, String newValue, int id) throws SQLException, ClassNotFoundException {

        try (
                Connection con = DBConnect.getConnection();
                PreparedStatement stmt = con.prepareStatement("UPDATE coffees SET " + column + " = ? WHERE id = ? ");) {

            stmt.setString(1, newValue);
            stmt.setInt(2, id);
            stmt.execute();
        } catch (SQLException ex) {
            System.err.println("Error");
            // if anything goes wrong, you will need the stack trace:
            ex.printStackTrace(System.err);
        }
    }

    // removes coffee from database based on unique ID
    private void deleteCoffeeFromDatabase(Coffee coffee) throws SQLException, ClassNotFoundException {
        try (
                Connection con = DBConnect.getConnection();
                PreparedStatement stmt = con.prepareStatement("DELETE FROM coffees WHERE id = ? ");) {

            stmt.setInt(1, coffee.idProperty().get());
            stmt.execute();
            System.out.println("Deleted " + coffee.beanNameProperty().get());
        } catch (SQLException ex) {
            System.err.println("Error");
            // if anything goes wrong, you will need the stack trace:
            ex.printStackTrace(System.err);
        }
    }

}
