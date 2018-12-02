package sample;

import java.io.Serializable;
import java.util.TreeMap;

/**
 *  Common Outlet abstract which is implemented by Stores and Warehouses
 */
public abstract class Outlet implements Serializable {

    protected String name;
    protected Superstore superstore;
    protected TreeMap<String, Category> categories;

    /**
     * Main Constructor for instantiating outlet, which can't be instantiated.
     * @param name Name of the outlet
     * @param superstore SuperStore reference as association
     */
    Outlet(String name, Superstore superstore) {
        this.superstore = superstore;
        this.name = name;
        categories = new TreeMap<>();
    }

    /**
     * Adding Category to an outlet, (Common functionality of both Store and Warehouse)
     * @param name Name of Category
     * @throws CategoryAlreadyExistsException (Exception if Category already exists)
     */
    public void addCategory(String name) throws CategoryAlreadyExistsException {
        if(categories.containsKey(name)) throw new CategoryAlreadyExistsException("category exists");
        Category temp = new Category(name, this);
        categories.put(name, temp);
    }

    /**
     * Deleting SubCategory
     * @param name Name of  SubCategory
     */
    public void deleteCategory(String name){
        categories.remove(name);
    }

    /**
     * getting Super Store reference
     * @return super
     */
    public Superstore getSuperstore() {
        return superstore;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public TreeMap<String, Category> getCategories() {
        return categories;
    }

    public void setCategories(TreeMap<String, Category> categories) {
        this.categories = categories;
    }

    public void setSuperstore(Superstore superstore) {
        this.superstore = superstore;
    }
}
