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
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 *  This is the file for adding Item on Store screen
 *
 */
public class AddItemScreenStore extends Application implements Initializable {

    @FXML
    Button button;
    @FXML
    ComboBox quantity, category, subcategory, item, type;
    @FXML
    Text ctext, stext, itext, qtext;

    /**
     *  Main Method of File
     * @param args It is an array of String arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     *  This is the overrided Start Method
     * @param primaryStage This is the main stage which loads the initial setup
     * @throws IOException
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AddItemScreenStore.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    /**
     * Sets Quantity
     * @param t selects quantity by setting up respective quantity true or false
     */
    public void setQuantity(boolean t) {
        quantity.setVisible(t);
        qtext.setVisible(t);
    }

    /**
     * Sets Category
     * @param t selects category by setting up respective category true or false
     */
    public void setCategory(boolean t) {
        category.setVisible(t);
        ctext.setVisible(t);
    }

    /** selects subcategory
     * @param t selects subcategory by setting up respective sub category true or false
     */
    public void setSubcategory(boolean t) {
        subcategory.setVisible(t);
        stext.setVisible(t);
    }

    /** sets Item
     * @param t selects item by setting up respective item true or false
     */
    public void setItem(boolean t) {
        item.setVisible(t);
        itext.setVisible(t);
    }

    /**
     * @param actionEvent
     * @throws IOException
     */
    public void selectType(ActionEvent actionEvent) throws IOException {
        try {
            String opt = type.getValue().toString();
            setItem(false);
            setCategory(false);
            setSubcategory(false);
            setQuantity(false);
            if(opt.equals("Category")) {
                setCategory(true);
            } else if(opt.equals("Subcategory")) {
                setCategory(true);
                setSubcategory(true);
            } else if(opt.equals("Item")) {
                setCategory(true);
                setSubcategory(true);
                setItem(true);
                setQuantity(true);
            }
        } finally {
            category.getItems().clear();
            category.getItems().setAll(Database.storeadmin.getStore().getWarehouse().getCategories().keySet());
        }
    }

    public void selectCategory(ActionEvent actionEvent) throws IOException {
        try {
            String opt = category.getValue().toString();
            subcategory.getItems().clear();
            subcategory.getItems().setAll(Database.storeadmin.getStore().getWarehouse().getCategories().get(opt).getSubcategory().keySet());
        } catch (Exception e) {
        }
    }

    public void selectSubcategory(ActionEvent actionEvent) throws IOException {
        try {
            String cat = category.getValue().toString();
            String sub = subcategory.getValue().toString();
            item.getItems().clear();
            item.getItems().setAll(Database.storeadmin.getStore().getWarehouse().getCategories().get(cat).getSubcategory().get(sub).getItems().keySet());
        } catch (Exception e) {
        }
    }

    public void selectItem(ActionEvent actionEvent) throws IOException {
        try {
            String cat = category.getValue().toString();
            String sub = subcategory.getValue().toString();
            String itemname = item.getValue().toString();

            ArrayList<String> s = new ArrayList<>();
            Item i = Database.storeadmin.getStore().getWarehouse().getCategories().get(cat).getSubcategory().get(sub).getItems().get(itemname);
            int quan = i.getQuantity();
            for(int j = 0; j <= quan; j++) {
                s.add(Integer.toString(j));
            }
            quantity.getItems().setAll(s);
        } catch (Exception e) {
        }
    }

    public void buttonaction(ActionEvent actionEvent) throws IOException {
        try {
            String opt = type.getValue().toString();

            if(opt.equals("Category")) {
                String cat = category.getValue().toString();
                Database.storeadmin.addCategory(cat);
            } else if(opt.equals("Subcategory")) {
                String cat = category.getValue().toString();
                String sub = subcategory.getValue().toString();
                Database.storeadmin.addSubcategory(cat, sub);
            } else {
                String cat = category.getValue().toString();
                String sub = subcategory.getValue().toString();
                String itemname = item.getValue().toString();
                int q = Integer.parseInt(quantity.getValue().toString());
                Database.storeadmin.addItem(cat, sub, itemname, q);
            }
        } catch (ItemAlreadyExistsException e) {
            //
        } catch (SubcategoryAlreadyExistsException e) {
            //
        } catch (CategoryAlreadyExistsException e) {
            //
        } catch (Exception e) {
        }
        Database.superuser.serialize();
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setQuantity(false);
        setCategory(false);
        setItem(false);
        setSubcategory(false);
        type.getItems().setAll("Category", "Subcategory", "Item");
    }
}
