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
        superstore.addWarehouse(warehousename, temp.getWarehouse());
    }

    public void deleteWarehouseAdmin(String username) {
        Warehouse temp = warehouseadmin.get(username).getWarehouse();
        warehouseadmin.remove(username);
        usernames.remove(username);
        superstore.removeWarehouse(temp.getName());
    }

    public void addStoreAdmin(String username, String password, String storename, Warehouse warehouse) throws UserAlreadyExistsException, OutletAlreadyExistsException {
        if(usernames.contains(username)) throw new UserAlreadyExistsException("Username exists for Warehouse Admin");
        StoreAdmin temp = new StoreAdmin(username, password, storename, superstore, warehouse);
        storeadmin.put(storename, temp);
        superstore.addStore(storename, temp.getStore());
    }

    public void deleteStoreAdmin(String username) {
        Store temp = storeadmin.get(username).getStore();
        storeadmin.remove(username);
        usernames.remove(username);
        superstore.removeStore(temp.getName());
    }

}
