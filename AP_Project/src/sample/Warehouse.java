package sample;

import java.util.TreeMap;

public class Warehouse extends Outlet{
    protected WarehouseAdmin admin;
    protected TreeMap<String, Store> stores;

    Warehouse(String name, WarehouseAdmin admin, Superstore superstore) {
        super(name, superstore);
        this.admin = admin;
        stores = new TreeMap<>();
    }

    public boolean requestItems(String itemname, int quantity, Store store) {
        for(Category c: categories.values()) {
            for(Subcategory s: c.getSubcategory().values()) {
                Item temp = s.getItems().get(itemname);
                if(temp != null && temp.getQuantity() > quantity) {
                    boolean ans = temp.editQuantity(-quantity);
                    if(ans) admin.addItem_to_order(temp);
                    admin.addMessage("Item " + itemname + " sent to store " + store.getName());
                    return true;
                }
            }
        }

        boolean ans = superstore.requestItems(itemname, quantity, this);
        if(ans) {
            admin.addMessage("Ordered Item " + itemname);
            admin.addMessage("Item " + itemname + " sent to store " + store.getName());
            return true;
        }

        return false;
    }

    public WarehouseAdmin getAdmin() {
        return admin;
    }
    public TreeMap<String, Store> getStores() { return stores; }
    public void addStore(Store s) {
        stores.put(s.getName(), s);
    }
}
