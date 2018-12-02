package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

public class AddItemScreenWarehouse extends Application implements Initializable {

    @FXML
    ComboBox addtype;
    @FXML
    ComboBox cattype;
    @FXML
    ComboBox subcattype;
    @FXML
    TextField name;
    @FXML
    TextField quantity;
    @FXML
    TextField D;
    @FXML
    TextField H;
    @FXML
    TextField K;
    @FXML
    Text dtext, qtext, htext, ktext, cat, subcat;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AddItemScreenWarehouse.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    public void selectType(ActionEvent actionEvent) {
        try {
            String opt = addtype.getValue().toString();
            System.out.println(opt);
            if(opt.equals("Category")) {
                cattype.setVisible(false);
                subcattype.setVisible(false);
                quantity.setVisible(false);
                D.setVisible(false);
                K.setVisible(false);
                H.setVisible(false);
                qtext.setVisible(false);
                dtext.setVisible(false);
                htext.setVisible(false);
                ktext.setVisible(false);
                cat.setVisible(false);
                subcat.setVisible(false);
            } else if(opt.equals("Subcategory")) {
                cattype.setVisible(true);
                quantity.setVisible(false);
                D.setVisible(false);
                K.setVisible(false);
                H.setVisible(false);
                qtext.setVisible(false);
                dtext.setVisible(false);
                htext.setVisible(false);
                ktext.setVisible(false);
                cat.setVisible(true);
                subcat.setVisible(false);
                subcattype.setVisible(false);
                cattype.getItems().clear();
                cattype.getItems().setAll(Database.warehouseadmin.getWarehouse().getCategories().keySet());
            } else if(opt.equals("Item")) {
                cattype.setVisible(true);
                subcattype.setVisible(true);
                cat.setVisible(true);
                subcat.setVisible(true);
                quantity.setVisible(true);
                D.setVisible(true);
                K.setVisible(true);
                H.setVisible(true);
                qtext.setVisible(true);
                dtext.setVisible(true);
                htext.setVisible(true);
                ktext.setVisible(true);
                cattype.getItems().clear();
                cattype.getItems().setAll(Database.warehouseadmin.getWarehouse().getCategories().keySet());
            }
        } catch (Exception e) {
        }
    }

    public void selectCat(ActionEvent actionEvent) {
        try {
            String opt = cattype.getValue().toString();
            subcattype.getItems().clear();
            Category cat = Database.warehouseadmin.getWarehouse().getCategories().get(opt);
            Collection<String> temp = Database.warehouseadmin.getWarehouse().getCategories().get(opt).getSubcategory().keySet();
            subcattype.getItems().setAll(Database.warehouseadmin.getWarehouse().getCategories().get(opt).getSubcategory().keySet());
        } catch (Exception e) {
        }
    }

    public void button_action(ActionEvent actionEvent) {
        try {
            String opt = addtype.getValue().toString();
            if(opt.equals("Category")) {
                Database.warehouseadmin.addCategory(name.getText());
            } else if(opt.equals("Subcategory")) {
                String cat = cattype.getValue().toString();
                Database.warehouseadmin.addSubcategory(cat, name.getText());
            } else {
                String cat = cattype.getValue().toString();
                String subcat = subcattype.getValue().toString();
                String n = name.getText();
                int q = Integer.parseInt(quantity.getText());
                double d = Double.parseDouble(D.getText());
                double k = Double.parseDouble(K.getText());
                double h = Double.parseDouble(H.getText());
                Database.warehouseadmin.addItem(cat, subcat, n, q, d, k, h);
            }
        }  catch (ItemAlreadyExistsException e) {
            //
        } catch (SubcategoryAlreadyExistsException e) {
            //
        } catch (CategoryAlreadyExistsException e) {

        } catch (Exception e) {
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addtype.getItems().addAll("Category", "Subcategory", "Item");
        cattype.setVisible(false);
        subcattype.setVisible(false);
        quantity.setVisible(false);
        D.setVisible(false);
        H.setVisible(false);
        K.setVisible(false);
        cat.setVisible(false);
        subcat.setVisible(false);
        qtext.setVisible(false);
        dtext.setVisible(false);
        htext.setVisible(false);
        ktext.setVisible(false);
    }
}
