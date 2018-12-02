package sample;

import java.io.Serializable;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 *  Main Super Store class
 */
public class Superstore implements Serializable {
    protected String name;
    protected Superuser superuser;
    protected TreeMap<String, Store> stores;
    protected TreeMap<String, Warehouse> warehouses;
    protected TreeSet<String> outletnames;

    /** Constructor for instantiating a superstore
     * @param name name of superstore
     * @param superuser superuser reference which controls superstore
     */
    Superstore(String name, Superuser superuser) {
        this.name = name;
        this.superuser = superuser;
        stores = new TreeMap<>();
        warehouses = new TreeMap<>();
        outletnames = new TreeSet<>();
    }

    /** adding warehouses
     * @param name name of the warehouse
     * @param warehouse reference of warehouse
     * @throws OutletAlreadyExistsException exception to show if it already exists
     */
    public void addWarehouse(String name, Warehouse warehouse) throws OutletAlreadyExistsException {
        if(outletnames.contains(name)) throw new OutletAlreadyExistsException("Outlet Exists");
        warehouses.put(name, warehouse);
        outletnames.add(name);
    }

    /** removes warehouse inside store
     * @param name name of warehouse
     */
    public void removeWarehouse(String name) {
        warehouses.remove(name);
        outletnames.remove(name);
    }

    /** adding store inside superstore
     * @param name name of superstore
     * @param store store reference to be added
     * @throws OutletAlreadyExistsException exception to show if outlet already exists
     */
    public void addStore(String name, Store store) throws OutletAlreadyExistsException {
        if(outletnames.contains(name)) throw new OutletAlreadyExistsException("Outlet Exists");
        stores.put(name, store);
        outletnames.add(name);
    }

    /** removes store inside superstore
     * @param name name of superstore
     */
    public void removeStore(String name) {
        stores.remove(name);
        outletnames.remove(name);
    }

    /**
     * requests Items from warehouse
     * @param itemname Name of item
     * @param quantity Quantity of Item
     * @param warehouse Warehouse to be requested from
     * @return True or false depending if order is complete or not
     */
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

    /** returns reference of all stores
     * @return tree map of all stores
     */
    public TreeMap<String, Store> getStores() {
        return stores;
    }

    /** returns reference of all warehouses
     * @return tree map of all warehouses
     */
    public TreeMap<String, Warehouse> getWarehouses() {
        return warehouses;
    }
}
