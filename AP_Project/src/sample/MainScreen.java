package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 *  This is the main opening screen
 */
public class MainScreen extends Application {

    /**
     * This is the function which starts off the main stage.
     * @param primaryStage This is the main loaded stage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    /**
     * This is the event handler for opening the admin login screen
     * @param event Records click event
     * @throws IOException
     */
    @FXML
    protected void adminLogin(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AdminLoginScreen.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
        Database.superuser.serialize();
    }

    /**
     * This is the main function which starts off application
     * @param args This is array of string arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
