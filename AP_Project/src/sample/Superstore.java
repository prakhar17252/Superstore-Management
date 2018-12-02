package sample;

import java.io.Serializable;
import java.util.TreeMap;
import java.util.TreeSet;

public class Superstore implements Serializable {
    protected String name;
    protected Superuser superuser;
    protected TreeMap<String, Store> stores;
    protected TreeMap<String, Warehouse> warehouses;
    protected TreeSet<String> outletnames;

    Superstore(String name, Superuser superuser) {
        this.name = name;
        this.superuser = superuser;
        stores = new TreeMap<>();
        warehouses = new TreeMap<>();
        outletnames = new TreeSet<>();
    }

    public void addWarehouse(String name, Warehouse warehouse) throws OutletAlreadyExistsException {
        if(outletnames.contains(name)) throw new OutletAlreadyExistsException("Outlet Exists");
        warehouses.put(name, warehouse);
        outletnames.add(name);
    }

    public void removeWarehouse(String name) {
        warehouses.remove(name);
        outletnames.remove(name);
    }

    public void addStore(String name, Store store) throws OutletAlreadyExistsException {
        if(outletnames.contains(name)) throw new OutletAlreadyExistsException("Outlet Exists");
        stores.put(name, store);
        outletnames.add(name);
    }

    public void removeStore(String name) {
        stores.remove(name);
        outletnames.remove(name);
    }

    public boolean requestItems(String itemname, int quantity, Warehouse warehouse) {
        for(Warehouse w: warehouses.values()) {
            for(Category c: w.getCategories().values()) {
                for(Subcategory s: c.getSubcategory().values()) {
                    for(Item i: s.getItems().values()) {
                        if(!i.getName().equals(itemname)) continue;
                        if(i.getQuantity() > quantity) {
                            boolean ans = i.editQuantity(-quantity);
                            w.getAdmin().addMessage("Item " + itemname + " sent to warehouse " + warehouse.getName());
                            if(ans) w.getAdmin().addItem_to_order(i);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public TreeMap<String, Store> getStores() {
        return stores;
    }

    public TreeMap<String, Warehouse> getWarehouses() {
        return warehouses;
    }
}
