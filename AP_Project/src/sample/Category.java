package sample;

import java.util.TreeMap;

public class Category {
    protected String name;
    protected TreeMap<String, Subcategory> subcategory;
    protected Outlet outlet;

    Category(String n, Outlet outlet) {
        name = n;
        subcategory = new TreeMap<>();
        this.outlet = outlet;
    }

    public void addSubcategory(String name) throws SubcategoryAlreadyExistsException {
        if(subcategory.containsKey(name)) throw new SubcategoryAlreadyExistsException("Subcategory alerady exists");
        Subcategory temp = new Subcategory(name, this);
        subcategory.put(name, temp);
    }

    public void deleteSubcategory(String name) {
        subcategory.remove(name);
    }

    public TreeMap<String, Subcategory> getSubcategory() {
        return subcategory;
    }

    public Outlet getOutlet() {
        return outlet;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setOutlet(Outlet outlet) {
        this.outlet = outlet;
    }

    public void setSubcategory(TreeMap<String, Subcategory> subcategory) {
        this.subcategory = subcategory;
    }
}
