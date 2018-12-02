package sample;

import javax.xml.crypto.Data;
import java.io.*;
import java.util.TreeMap;
import java.util.TreeSet;

public class Superuser extends User implements Serializable {
    protected Superstore superstore;
    protected TreeMap<String, WarehouseAdmin> warehouseadmin;
    protected TreeMap<String, StoreAdmin> storeadmin;
    protected TreeSet<String> usernames;

    Superuser() {
        super("lol", "lmao");
        try {
            deserialize();
            System.out.println("success");
            System.out.println(warehouseadmin.size());
            System.out.println(warehouseadmin.containsKey("a"));

            Database.warehouseadmins = warehouseadmin;
            Database.storeadmins = storeadmin;
            Database.stores = superstore.getStores();
            Database.warehouses = superstore.getWarehouses();

        } catch (Exception e) {
            superstore = new Superstore("superstore", this);
            warehouseadmin = new TreeMap<>();
            storeadmin = new TreeMap<>();
            usernames = new TreeSet<>();
        }
    }

    public void deserialize() throws IOException, ClassNotFoundException {
        {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("superstore.txt"));
            superstore = (Superstore) in.readObject();
        }

        {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("warehouseadmin.txt"));
            warehouseadmin = (TreeMap<String, WarehouseAdmin>) in.readObject();
        }

        {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("storeadmin.txt"));
            storeadmin = (TreeMap<String, StoreAdmin>) in.readObject();
        }

        {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("usernames.txt"));
            usernames = (TreeSet<String>) in.readObject();
        }
    }

    public void serialize() throws IOException {
        {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("superstore.txt"));
            out.writeObject(superstore);
        }

        {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("warehouseadmin.txt"));
            out.writeObject(warehouseadmin);
        }

        {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("storeadmin.txt"));
            out.writeObject(storeadmin);
        }

        {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("usernames.txt"));
            out.writeObject(usernames);
        }
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

    public TreeMap<String, StoreAdmin> getStoreadmin() {
        return storeadmin;
    }

    public TreeMap<String, WarehouseAdmin> getWarehouseadmin() {
        return warehouseadmin;
    }
}

