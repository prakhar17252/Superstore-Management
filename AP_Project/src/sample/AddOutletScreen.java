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
import javafx.stage.Window;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.TreeMap;

public class AddOutletScreen extends Application implements Initializable {

    @FXML
    TextField username;
    @FXML
    PasswordField password;
    @FXML
    TextField outletname;
    @FXML
    ComboBox options;
    @FXML
    Text incorrectusername;
    @FXML
    Text incorrectoutletname;
    @FXML
    Text selectoption;
    @FXML
    ComboBox selectedwarehouse;
    @FXML
    Text ww;

    public static void main(String[] args) {
        launch(args);
    }

    void setOptions(){
        options.getItems().addAll("Store", "Warehouse");
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AddOutletScreen.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    public void addWarehouse(ActionEvent actionEvent) {
        try {
            String user = username.getText();
            String pass = password.getText();
            String out = outletname.getText();
            Database.superuser.addWarehouseAdmin(user, pass, out);
        } catch (UserAlreadyExistsException e) {
            incorrectusername.setVisible(true);
        } catch (OutletAlreadyExistsException e) {
            incorrectoutletname.setVisible(true);
        }
    }

    public void addStore(ActionEvent actionEvent) {
        try {
            String user = username.getText();
            String pass = password.getText();
            String out = outletname.getText();
            String ware = selectedwarehouse.getValue().toString();
            Warehouse w = Database.getWarehouses().get(ware);
            Database.superuser.addStoreAdmin(user, pass, out, w);
        } catch (UserAlreadyExistsException e) {
            incorrectusername.setVisible(true);
        } catch (OutletAlreadyExistsException e) {
            incorrectoutletname.setVisible(true);
        } catch (Exception e) {
            return;
        }
    }

    public void selectOutlet(ActionEvent actionEvent) {
        try {
            String opt = options.getValue().toString();
            if(opt.equals("Store")) {
                selectedwarehouse.setVisible(true);
                ww.setVisible(true);
            } else if(opt.equals("Warehouse")) {
                selectedwarehouse.setVisible(false);
                ww.setVisible(false);
            }
        } catch (Exception e) {
            ww.setVisible(false);
            selectedwarehouse.setVisible(false);
        }
    }

    public void addOutlet(ActionEvent actionEvent) {
        try {
            String opt = options.getValue().toString();
            if(opt.equals("Store")) {
                addStore(actionEvent);
            }
            else if(opt.equals("Warehouse")) {
                addWarehouse(actionEvent);
            }
        } catch (Exception e) {
            return;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setOptions();
        TreeMap<String, Warehouse> warehouses = Database.getWarehouses();
        selectedwarehouse.getItems().addAll(warehouses.keySet());
        ww.setVisible(false);
        selectedwarehouse.setVisible(false);
    }

}
