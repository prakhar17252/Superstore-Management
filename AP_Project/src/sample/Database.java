package sample;

import java.util.TreeMap;

/**
 *  Main Database Class
 */
public class Database {
    public static Superuser superuser = new Superuser();
    public static WarehouseAdmin warehouseadmin = null;
    public static StoreAdmin storeadmin = null;
    public static TreeMap<String, WarehouseAdmin> warehouseadmins = new TreeMap<>();
    public static TreeMap<String, StoreAdmin> storeadmins = new TreeMap<>();
    public static TreeMap<String, Store> stores = new TreeMap<>();
    public static TreeMap<String, Warehouse> warehouses = new TreeMap<>();

    /**
     * Adding store inside database
     * @param s Store to be added
     */
    public static void addStore(Store s) {
        stores.put(s.getName(), s);
    }

    /**
     * Setting Admin for set
     * @param s store to be set admin for
     */
    public static void addStoreAdmin(StoreAdmin s) {
        storeadmins.put(s.getUsername(), s);
    }

    /**
     * Deleting Store
     * @param s name of store
     */
    public static void deleteStore(String s) {
        stores.remove(s);
    }

    /**
     * Deleting Store Admin
     * @param s name of the store admin
     */
    public static void deleteStoreAdmin(String s) {
        storeadmins.remove(s);
    }

    /**
     * Adding Warehouse
     * @param w Warehouse to be added
     */
    public static void addWarehouse(Warehouse w) {
        warehouses.put(w.getName(), w);
    }

    /**
     * adding Admin for Warehouses
     * @param w admin to be set
     */
    public static void addWarehouseAdmin(WarehouseAdmin w) {
        warehouseadmins.put(w.getUsername(), w);
    }

    /**
     * removing Warehouse
     * @param w Warehouse name
     */
    public static void deleteWarehouse(String w) {
        warehouses.remove(w);
    }

    /**
     * remaining warehouse admin
     * @param w string name of admin
     */
    public static void deleteWarehouseAdmin(String w) {
        warehouseadmins.remove(w);
    }

    /**
     * checking if warehouse admin username and password are correct or not
     * @param username Username of Warehouse Admin
     * @param password Username of Password Admin
     * @return returns True if username and password are correct
     */
    public static boolean checkWarehouseAdminLogin(String username, String password) {
        getData();
        if(!warehouseadmins.containsKey(username)) return false;
        if(warehouseadmins.get(username).tryLogin(username, password)) {
            warehouseadmin = warehouseadmins.get(username);
            return true;
        }
        return false;
    }

    /**
     * checks if store admin username and password are correct or not
     * @param username username of store admin
     * @param password password of store admin
     * @return true or false if username and password are correct or not
     */
    public static boolean checkStoreAdminLogin(String username, String password) {
        getData();
        if(!storeadmins.containsKey(username)) return false;
        if(storeadmins.get(username).tryLogin(username, password)) {
            storeadmin = storeadmins.get(username);
            return true;
        }
        return false;
    }

    /**
     * checks if superuser admin username and password are correct or not
     * @param username username of superuser admin
     * @param password password of superuser password
     * @return true or false if username and password are correct or not
     */
    public static boolean checkSuperUserLogin(String username, String password) {
        getData();
        return superuser.tryLogin(username, password);
    }

    /**
     * Obtains existing warehouses inside database
     * @return Tree map which contains all stored warehouses
     */
    public static TreeMap<String, Warehouse> getWarehouses() {
        return warehouses;
    }

    /**
     * Obtains existing stores inside database
     * @return Tree map which contains all stores
     */
    public static TreeMap<String, Store> getStores() {
        return stores;
    }

    /**
     * Initialises databases's variables with the existing tree maps
     */
    public static void getData() {
        warehouseadmins = superuser.getWarehouseadmin();
        storeadmins = superuser.getStoreadmin();
        stores = superuser.getSuperstore().getStores();
        warehouses = superuser.getSuperstore().getWarehouses();
    }
}

