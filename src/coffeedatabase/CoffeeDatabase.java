/*
 * Written by Harry Silman
 */
package coffeedatabase;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


/**
 *
 * @author Silman
 */
public class CoffeeDatabase extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
       primaryStage.setTitle("Coffee Database");
       Pane myPane = (Pane)FXMLLoader.load(getClass().getResource
    ("coffee_database.fxml"));
       Scene myScene = new Scene(myPane);
       primaryStage.setScene(myScene);
       primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
