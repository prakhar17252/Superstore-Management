package sample;

import java.io.Serializable;
import java.util.TreeMap;

public abstract class Outlet implements Serializable {
    protected String name;
    protected Superstore superstore;
    protected TreeMap<String, Category> categories;

    Outlet(String name, Superstore superstore) {
        this.superstore = superstore;
        this.name = name;
        categories = new TreeMap<>();
    }

    public void addCategory(String name) throws CategoryAlreadyExistsException {
        if(categories.containsKey(name)) throw new CategoryAlreadyExistsException("category exists");
        Category temp = new Category(name, this);
        categories.put(name, temp);
    }

    public void deleteCategory(String name){
        categories.remove(name);
    }

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
