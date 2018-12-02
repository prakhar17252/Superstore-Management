package sample;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 *  Main class for Store
 */
public class Store extends Outlet implements Serializable {
    protected StoreAdmin admin;
    protected Warehouse warehouse;

    /** Constructor for instantiating a store
     * @param name Name of Store
     * @param admin Admin to be selected for store
     * @param superstore superstore reference inside store
     * @param warehouse warehouse to be linked
     */
    Store(String name, StoreAdmin admin, Superstore superstore, Warehouse warehouse) {
        super(name, superstore);
        this.admin = admin;
        this.warehouse = warehouse;
    }

    /**
     * Returns categories inside store
     * @return Tree map of Categories
     */
    public TreeMap<String, Category> getCategories() {
        return categories;
    }

    /**
     * Function for searching items inside store.
     * @param name Name of the item
     * @return Returns searched Item
     */
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

    /**
     *  Returns linked Warehouse
     * @return warehouse reference
     */
    public Warehouse getWarehouse() {
        return warehouse;
    }

    /**
     * Returns admin of store
     * @return admin reference
     */
    public StoreAdmin getAdmin() {
        return admin;
    }
}
