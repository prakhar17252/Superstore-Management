package sample;

import java.io.Serializable;

public class StoreAdmin extends User implements OutletAdmin, Serializable {
    protected Store store;

    StoreAdmin(String name, String pass, String storename, Superstore superstore, Warehouse warehouse) {
        super(name, pass);
        this.store = new Store(storename, this, superstore, warehouse);
    }

    @Override
    public void addCategory(String name) throws CategoryAlreadyExistsException {
        store.addCategory(name);
    }

    @Override
    public void deleteCategory(String name) {
        store.deleteCategory(name);
    }

    @Override
    public void addSubcategory(String cat, String sub) throws CategoryAlreadyExistsException, SubcategoryAlreadyExistsException{
        if(!store.getCategories().containsKey(cat)) store.addCategory(cat);
        store.getCategories().get(cat).addSubcategory(sub);
    }

    @Override
    public void deleteSubcategory(String cat, String sub) {
        store.getCategories().get(cat).deleteSubcategory(sub);
    }

    public void addItem(String cat, String sub, String itemname, int q) throws CategoryAlreadyExistsException, SubcategoryAlreadyExistsException, ItemAlreadyExistsException {
        Item i = store.getWarehouse().getCategories().get(cat).getSubcategory().get(sub).getItems().get(itemname);
        if(!store.getCategories().containsKey(cat)) addCategory(cat);
        if(!store.getCategories().get(cat).getSubcategory().containsKey(sub)) store.getCategories().get(cat).addSubcategory(sub);
        Item to_add = new Item(itemname, q, i.getD(), i.getH(), i.getK(), store.getCategories().get(cat).getSubcategory().get(sub));
        store.getCategories().get(cat).getSubcategory().get(sub).addItem(itemname, q, i.getD(), i.getK(), i.getH());
        store.warehouse.getCategories().get(cat).getSubcategory().get(sub).getItems().get(itemname).editQuantity(-q);
    }

    @Override
    public void deleteItem(String cat, String sub, String itemname) {
        Subcategory s = store.getCategories().get(cat).getSubcategory().get(sub);
        s.deleteItem(itemname);
    }

    public void editItem(Item i, double D, double K, double H) {
        i.setD(D);
        i.setK(K);
        i.setH(H);
    }

    public Store getStore() {
        return store;
    }

    public void sendOrderToWarehouse(Item item, int quantity) throws NotEnoughQuantityException {
        String itemname = item.getName();
        boolean ans = store.getWarehouse().requestItems(itemname, quantity, store);
        if(!ans) throw new NotEnoughQuantityException("not enough quantities");
        item.editQuantity(quantity);
    }
}
