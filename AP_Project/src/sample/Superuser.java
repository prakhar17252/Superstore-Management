package sample;

import javax.xml.crypto.Data;
import java.util.TreeMap;
import java.util.TreeSet;

public class Superuser extends User {
    protected Superstore superstore;
    protected TreeMap<String, WarehouseAdmin> warehouseadmin;
    protected TreeMap<String, StoreAdmin> storeadmin;
    protected TreeSet<String> usernames;

    Superuser() {
        super("lol", "lmao");
        superstore = new Superstore("superstore", this);
        warehouseadmin = new TreeMap<>();
        storeadmin = new TreeMap<>();
        usernames = new TreeSet<>();
    }

    public void addWarehouseAdmin(String username, String password, String warehousename) throws UserAlreadyExistsException, OutletAlreadyExistsException {
        if(this.usernames.contains(username)) throw new UserAlreadyExistsException("Username exists for Warehouse Admin");
        WarehouseAdmin temp = new WarehouseAdmin(username, password, warehousename, superstore);
        warehouseadmin.put(warehousename, temp);
        Database.addWarehouseAdmin(temp);
        superstore.addWarehouse(warehousename, temp.getWarehouse());
        Database.addWarehouse(temp.getWarehouse());
    }

    public void deleteWarehouseAdmin(String username) {
        Warehouse temp = warehouseadmin.get(username).getWarehouse();
        for(Store s: temp.getStores().values()) {
            deleteStoreAdmin(s.getAdmin().getUsername());
        }
        Database.deleteWarehouse(temp.getName());
        superstore.removeWarehouse(temp.getName());
        warehouseadmin.remove(username);
        Database.deleteWarehouseAdmin(username);
        usernames.remove(username);
    }

    public void addStoreAdmin(String username, String password, String storename, Warehouse warehouse) throws UserAlreadyExistsException, OutletAlreadyExistsException {
        if(usernames.contains(username)) throw new UserAlreadyExistsException("Username exists for Warehouse Admin");
        StoreAdmin temp = new StoreAdmin(username, password, storename, superstore, warehouse);
        warehouse.addStore(temp.getStore());
        storeadmin.put(storename, temp);
        Database.addStoreAdmin(temp);
        superstore.addStore(storename, temp.getStore());
        Database.addStore(temp.getStore());
    }

    public void deleteStoreAdmin(String username) {
        Store temp = storeadmin.get(username).getStore();
        superstore.removeStore(temp.getName());
        Database.deleteStore(temp.getName());
        storeadmin.remove(username);
        Database.deleteStoreAdmin(username);
        usernames.remove(username);
    }

    public Superstore getSuperstore() {
        return superstore;
    }
}

