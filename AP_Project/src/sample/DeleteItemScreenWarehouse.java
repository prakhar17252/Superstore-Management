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
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DeleteItemScreenWarehouse extends Application implements Initializable {

    @FXML
    Button button;
    @FXML
    Text cattext, subtext, itemtext;
    @FXML
    ComboBox selectcategory, selectsubcategory, selectitem, selecttype;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("DeleteItemScreenWarehouse.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    public void selectType(ActionEvent actionEvent) throws IOException {
        try {
            String opt = selecttype.getValue().toString();
            setItem(false);
            setCategory(false);
            setSubcategory(false);
            if(opt.equals("Category")) {
                setCategory(true);
                selectcategory.getItems().clear();
                selectcategory.getItems().setAll(Database.warehouseadmin.getWarehouse().getCategories().keySet());
            } else if(opt.equals("Subcategory")) {
                setCategory(true);
                setSubcategory(true);
                selectcategory.getItems().clear();
                selectcategory.getItems().setAll(Database.warehouseadmin.getWarehouse().getCategories().keySet());
            } else if(opt.equals("Item")) {
                setCategory(true);
                setSubcategory(true);
                setItem(true);
                selectcategory.getItems().clear();
                selectcategory.getItems().setAll(Database.warehouseadmin.getWarehouse().getCategories().keySet());
            }
        } finally {

        }
    }

    public void selectCategory(ActionEvent actionEvent) throws IOException {
        try {
            String opt = selectcategory.getValue().toString();
            selectsubcategory.getItems().clear();
            selectsubcategory.getItems().setAll(Database.warehouseadmin.getWarehouse().getCategories().get(opt).getSubcategory().keySet());
        } catch (Exception e) {
        }
    }

    public void selectSubcategory(ActionEvent actionEvent) throws IOException {
        try {
            String cat = selectcategory.getValue().toString();
            String sub = selectsubcategory.getValue().toString();
            selectitem.getItems().clear();
            selectitem.getItems().setAll(Database.warehouseadmin.getWarehouse().getCategories().get(cat).getSubcategory().get(sub).getItems().keySet());
        } catch (Exception e) {
        }
    }

    public void buttonaction(ActionEvent actionEvent) throws IOException {
        try {
            String type = selecttype.getValue().toString();
            String cat = selectcategory.getValue().toString();
            if(type.equals("Category")) {
                Database.warehouseadmin.deleteCategory(cat);
            } else if(type.equals("Subcategory")) {
                String sub = selectsubcategory.getValue().toString();
                Database.warehouseadmin.deleteSubcategory(cat, sub);
            } else if(type.equals("Item")) {
                String sub = selectsubcategory.getValue().toString();
                String itemname = selectitem.getValue().toString();
                Database.warehouseadmin.deleteItem(cat, sub, itemname);
            }
        } catch (Exception e) {
        }
        Database.superuser.serialize();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        selecttype.getItems().setAll("Category", "Subcategory", "Item");
        setCategory(false);
        setSubcategory(false);
        setItem(false);
    }

    public void setCategory(boolean t) {
        cattext.setVisible(t);
        selectcategory.setVisible(t);
    }
    public void setSubcategory(boolean t) {
        subtext.setVisible(t);
        selectsubcategory.setVisible(t);
    }
    public void setItem(boolean t) {
        itemtext.setVisible(t);
        selectitem.setVisible(t);
    }

}
