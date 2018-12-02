package sample;

import java.util.ArrayList;

public class WarehouseAdmin extends User implements OutletAdmin {
    protected Warehouse warehouse;
    protected ArrayList<String> messages;
    protected ArrayList<Item> items_to_order;

    WarehouseAdmin(String name, String pass, String warehousename, Superstore superstore) {
        super(name, pass);
        this.warehouse = new Warehouse(warehousename, this, superstore);
        messages = new ArrayList<>();
        items_to_order = new ArrayList<>();
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    @Override
    public void addCategory(String name) throws CategoryAlreadyExistsException {
        warehouse.addCategory(name);
    }

    @Override
    public void deleteCategory(String name) {
        for (Store st: warehouse.getStores().values()) {
            st.getAdmin().deleteCategory(name);
        }
        warehouse.deleteCategory(name);
    }
    @Override
    public void addSubcategory(String cat, String sub) throws CategoryAlreadyExistsException, SubcategoryAlreadyExistsException{
        Category c = warehouse.getCategories().get(cat);
        c.addSubcategory(sub);
        return;
    }

    @Override
    public void deleteSubcategory(String cat, String sub) {
        for(Store st: warehouse.getStores().values()) {
            st.getAdmin().deleteSubcategory(cat, sub);
        }
        warehouse.getCategories().get(cat).deleteSubcategory(sub);
    }

    public void addItem(String cat, String sub, String itemname, int q, double D, double K, double H) throws ItemAlreadyExistsException {
        Subcategory s = warehouse.getCategories().get(cat).getSubcategory().get(sub);
        s.addItem(itemname, q, D, K, H);
    }

    @Override
    public void deleteItem(String cat, String sub, String itemname) {
        for(Store st: warehouse.getStores().values()) {
            st.getAdmin().deleteItem(cat, sub, itemname);
        }
        warehouse.getCategories().get(cat).getSubcategory().get(sub).deleteItem(itemname);
    }

    public void addMessage(String message) {
        messages.add(message);
    }

    public void editItem(Item i, int quantity, double D, double K, double H) {
        i.setQuantity(quantity);
        i.setD(D);
        i.setK(K);
        i.setH(H);
    }


    public void addItem_to_order(Item item) {
        items_to_order.add(item);
    }

    public ArrayList<Warehouse> getOtherWarehouses() {
        return new ArrayList<>(warehouse.getSuperstore().getWarehouses().values());
    }
}
