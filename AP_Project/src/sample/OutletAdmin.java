package sample;

public interface OutletAdmin {
    void addCategory(String name) throws CategoryAlreadyExistsException;
    void deleteCategory(String name);
    void addSubcategory(String cat, String sub) throws CategoryAlreadyExistsException, SubcategoryAlreadyExistsException;
    void deleteSubcategory(String cat, String sub);
//    void addItem(String cat, String sub, String itemname, int q, double D, double K, double H) throws ItemAlreadyExistsException;
    void deleteItem(String cat, String sub, String itemname);
//    void editItem(Item i, String name, int quantity, double D, double K, double H);
}
