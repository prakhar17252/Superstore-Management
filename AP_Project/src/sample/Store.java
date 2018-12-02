package sample;

import java.util.ArrayList;
import java.util.TreeMap;

public class Store extends Outlet {
    protected StoreAdmin admin;
    protected Warehouse warehouse;

    Store(String name, StoreAdmin admin, Superstore superstore, Warehouse warehouse) {
        super(name, superstore);
        this.admin = admin;
        this.warehouse = warehouse;
    }

    public TreeMap<String, Category> getCategories() {
        return categories;
    }

    public ArrayList<Item> searchItem(String name) {
        ArrayList<Item> items = new ArrayList<>();
        for(Category c: categories.values()) {
            for(Subcategory sub: c.getSubcategory().values()) {
                for(Item i: sub.getItems().values()) {
                    if(i.getName().equals(name)) {
                        items.add(i);
                    }
                }
            }
        }
        return items;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public StoreAdmin getAdmin() {
        return admin;
    }
}
