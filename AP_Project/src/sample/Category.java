package sample;

import java.io.Serializable;
import java.util.TreeMap;

/**
 * Category Class for
 */
public class Category implements Serializable {
    protected String name;
    protected TreeMap<String, Subcategory> subcategory;
    protected Outlet outlet;

    /**
     * Main Constructor for initialising a new Category
     * @param n This is the main name of constructor
     * @param outlet Outlet which contains it
     */
    Category(String n, Outlet outlet) {
        name = n;
        subcategory = new TreeMap<>();
        this.outlet = outlet;
    }

    /**
     * Helps to add a new Subcategory
     * @param name Name of the subcategory
     * @throws SubcategoryAlreadyExistsException Exception if SubCategory already exists
     */
    public void addSubcategory(String name) throws SubcategoryAlreadyExistsException {
        if(subcategory.containsKey(name)) throw new SubcategoryAlreadyExistsException("Subcategory alerady exists");
        Subcategory temp = new Subcategory(name, this);
        subcategory.put(name, temp);
    }

    /**
     * Deleting SubCategory inside Category
     * @param name name of subcategory
     */
    public void deleteSubcategory(String name) {
        subcategory.remove(name);
    }

    /** Function for returning subcategory
     * @returns subcategory
     */
    public TreeMap<String, Subcategory> getSubcategory() {
        return subcategory;
    }

    /**
     * Returns Outlet which contans it
     * @return outlet
     */
    public Outlet getOutlet() {
        return outlet;
    }

    /**
     * Setting name for category
     * @param name for category
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter Function
     * getting name of Category
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter Function
     * @param outlet setting outlet of category
     */
    public void setOutlet(Outlet outlet) {
        this.outlet = outlet;
    }

    /**
     * Sets subcategory
     * @param subcategory subcategory to be passed
     */
    public void setSubcategory(TreeMap<String, Subcategory> subcategory) {
        this.subcategory = subcategory;
    }
}
