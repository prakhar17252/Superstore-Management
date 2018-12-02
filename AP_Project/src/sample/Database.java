package sample;

import java.util.TreeMap;

public class Database {
    public static Superuser superuser = new Superuser();
    public static WarehouseAdmin warehouseadmin = null;
    public static StoreAdmin storeadmin = null;
    public static TreeMap<String, WarehouseAdmin> warehouseadmins = new TreeMap<>();
    public static TreeMap<String, StoreAdmin> storeadmins = new TreeMap<>();
    public static TreeMap<String, Store> stores = new TreeMap<>();
    public static TreeMap<String, Warehouse> warehouses = new TreeMap<>();

    public static void addStore(Store s) {
        stores.put(s.getName(), s);
    }
    public static void addStoreAdmin(StoreAdmin s) {
        storeadmins.put(s.getUsername(), s);
    }
    public static void deleteStore(String s) {
        stores.remove(s);
    }
    public static void deleteStoreAdmin(String s) {
        storeadmins.remove(s);
    }

    public static void addWarehouse(Warehouse w) {
        warehouses.put(w.getName(), w);
    }

    public static void addWarehouseAdmin(WarehouseAdmin w) {
        warehouseadmins.put(w.getUsername(), w);
    }

    public static void deleteWarehouse(String w) {
        warehouses.remove(w);
    }

    public static void deleteWarehouseAdmin(String w) {
        warehouseadmins.remove(w);
    }

    public static boolean checkWarehouseAdminLogin(String username, String password) {
        getData();
        if(!warehouseadmins.containsKey(username)) return false;
        if(warehouseadmins.get(username).tryLogin(username, password)) {
            warehouseadmin = warehouseadmins.get(username);
            return true;
        }
        return false;
    }

    public static boolean checkStoreAdminLogin(String username, String password) {
        getData();
        if(!storeadmins.containsKey(username)) return false;
        if(storeadmins.get(username).tryLogin(username, password)) {
            storeadmin = storeadmins.get(username);
            return true;
        }
        return false;
    }

    public static boolean checkSuperUserLogin(String username, String password) {
        getData();
        return superuser.tryLogin(username, password);
    }

    public static TreeMap<String, Warehouse> getWarehouses() {
        return warehouses;
    }

    public static TreeMap<String, Store> getStores() {
        return stores;
    }

    public static void getData() {
        warehouseadmins = superuser.getWarehouseadmin();
        storeadmins = superuser.getStoreadmin();
        stores = superuser.getSuperstore().getStores();
        warehouses = superuser.getSuperstore().getWarehouses();
    }
}

