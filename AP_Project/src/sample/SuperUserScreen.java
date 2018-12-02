package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

/**
 *  Super User Screen Main class
 */
public class SuperUserScreen extends Application {

    /** Main class for launching screen
     * @param args array of string arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /** Start function overriden for starting stage
     * @param primaryStage main stage for setting up
     * @throws IOException
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SuperUserScreen.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    /** Event Handler for handling add admin button
     * @param actionEvent recorded event : mouse click
     * @throws Exception
     */
    public void addAdmin(ActionEvent actionEvent) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddOutletScreen.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
    }

    /** deleting admin button event handler
     * @param actionEvent recorded event : mouse click
     * @throws Exception
     */
    public void deleteAdmin(ActionEvent actionEvent) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DeleteOutletScreen.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
    }

}
