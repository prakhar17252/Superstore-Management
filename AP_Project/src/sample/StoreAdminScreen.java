package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

/**
 *  Main class for store admin screen
 */
public class StoreAdminScreen extends Application {

    @FXML
    Button add, delete;

    /** Main function for launching App
     * @param args array of string arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /** Overriden Start function
     * @param primaryStage Main stage inside application
     * @throws IOException
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("StoreAdminScreen.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    /** Adding Item Event Handler
     * @param actionEvent Records event : Mouse Click
     * @throws IOException
     */
    public void addItem(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddItemScreenStore.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
    }

    /** Deleting Item Event Handler
     * @param actionEvent Event Recorded : Mouse Click
     * @throws IOException
     */
    public void deleteItem(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DeleteItemScreenStore.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
    }


}
