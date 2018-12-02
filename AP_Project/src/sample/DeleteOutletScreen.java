package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.AcceptPendingException;
import java.util.ResourceBundle;

public class DeleteOutletScreen extends Application implements Initializable {

    @FXML
    ComboBox name;
    @FXML
    ComboBox type;
    @FXML
    Button del;
//    Superstore sup;
//    Superuser supp;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
//        sup = Database.superuser.superstore;
//        supp = Database.superuser;
        Parent root = FXMLLoader.load(getClass().getResource("DeleteOutletScreen.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    public void selectType(ActionEvent actionEvent) {
        try {
            String opt = type.getValue().toString();
            if(opt.equals("Store")) {
                name.getItems().clear();
                name.getItems().addAll(Database.superuser.getSuperstore().getStores().keySet());
            } else {
                name.getItems().clear();
                name.getItems().addAll(Database.superuser.getSuperstore().getWarehouses().keySet());
            }
        } catch (Exception e) {
            return;
        }
    }

    public void deleteStore(ActionEvent actionEvent) {
        try {
            String n = name.getValue().toString();
            String username = Database.getStores().get(n).getAdmin().getUsername();
            Database.superuser.deleteStoreAdmin(username);
        } catch (Exception e) {
            return;
        }
    }

    public void deleteWarehouse(ActionEvent actionEvent) {
        try {
            String n = name.getValue().toString();
            String username = Database.getWarehouses().get(n).getAdmin().getUsername();
            Database.superuser.deleteWarehouseAdmin(username);
        } catch (Exception e) {
            return;
        }
    }

    public void delete(ActionEvent actionEvent) {
        try {
            String opt = type.getValue().toString();
            if(opt.equals("Store")) {
                deleteStore(actionEvent);
            } else if(opt.equals("Warehouse")) {
                deleteWarehouse(actionEvent);
            }
            System.out.println(Database.getStores().size());
            System.out.println(Database.getWarehouses().size());
            System.out.println(Database.storeadmins.size());
            System.out.println(Database.warehouseadmins.size());

        } catch (Exception e) {
            return;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        type.getItems().addAll("Store", "Warehouse");
    }
}
