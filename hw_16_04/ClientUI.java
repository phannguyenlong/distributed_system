package hw_16_04;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The first Frame of Application
 * @author Long Phan
 */
public class ClientUI extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("form.fxml"));
        Scene scene = new Scene(loader.load());
    
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    } 

    public static void main(String[] args) {
        launch(args);
    }
}