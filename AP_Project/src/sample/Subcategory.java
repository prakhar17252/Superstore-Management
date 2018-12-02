package sample;

import java.util.TreeMap;

public class Subcategory {
    protected String name;
    protected TreeMap<String, Item> items;
    protected Category category;

    Subcategory(String name, Category cat) {
        this.name = name;
        items = new TreeMap<>();
        category = cat;
    }

    public void addItem(String name, int quantity, double D, double K, double H) throws ItemAlreadyExistsException {
        if(items.containsKey(name)) throw new ItemAlreadyExistsException("Item already exists");
        Item temp = new Item(name, quantity, D, K, H, this);
        items.put(name, temp);
    }

    public void deleteItem(String name) {
        items.remove(name);
    }

    public TreeMap<String, Item> getItems() {
        return items;
    }

    public Category getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setItems(TreeMap<String, Item> items) {
        this.items = items;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
