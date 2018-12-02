package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminLoginScreen extends Application implements Initializable {

    @FXML
    TextField username;
    @FXML
    PasswordField password;
    @FXML
    ComboBox options;
    @FXML
    Text selectoption;
    @FXML
    Text invalid;

    void setOptions(){
        options.getItems().addAll("SuperUser", "Warehouse Admin", "Store Admin");
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AdminLoginScreen.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    public void gotosuperuser(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SuperUserScreen.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
    }

    public void gotowarehouseadmin(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("WarehouseAdminScreen.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
    }

    public void gotostoreadmin(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StoreAdminScreen.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
    }



    public void logindetails(ActionEvent actionEvent) {
        selectoption.setVisible(false);
        String user = username.getText();
        String pass = password.getText();

        try {
            String op = options.getValue().toString();

            if(op.equals("SuperUser")) {
                if(Database.checkSuperUserLogin(user, pass)) {
                    // go to superuser screen
                    gotosuperuser(actionEvent);
                    return;
                }
                showInvalid();
            }

            else if(op.equals("Warehouse Admin")) {
                if(Database.checkWarehouseAdminLogin(user, pass)) {
                    // go to warehouse page
                    gotowarehouseadmin(actionEvent);
                    return;
                }
                showInvalid();
            }

            else {
                if(Database.checkStoreAdminLogin(user, pass)) {
                    // go to Storeadmin page
                    gotostoreadmin(actionEvent);
                    return;
                }
                showInvalid();
            }
            return;
        } catch (Exception e) {
            selectOption();
        }
    }

    public void showInvalid() {
        invalid.setVisible(true);
    }

    public void selectOption() {
        selectoption.setVisible(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setOptions();
    }
}
