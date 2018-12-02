package sample;

import java.io.Serializable;
import java.util.TreeMap;

/**
 *  Main Subcategory class
 */
public class Subcategory implements Serializable {
    protected String name;
    protected TreeMap<String, Item> items;
    protected Category category;

    /** Main Constructor for instantiating a subcategory
     * @param name Name of Subcategory
     * @param cat Category which contains subcategory
     */
    Subcategory(String name, Category cat) {
        this.name = name;
        items = new TreeMap<>();
        category = cat;
    }

    /** Adding item inside subcategory
     * @param name Name of Item
     * @param quantity Quantity of Item
     * @param D fixed Cost per Quarter
     * @param K Demand in item of units per quarter
     * @param H Carrying cost per unit
     * @throws ItemAlreadyExistsException
     */
    public void addItem(String name, int quantity, double D, double K, double H) throws ItemAlreadyExistsException {
        if(items.containsKey(name)) throw new ItemAlreadyExistsException("Item already exists");
        Item temp = new Item(name, quantity, D, K, H, this);
        items.put(name, temp);
    }

    /** Deleting item inside subcategory
     * @param name Name of item to be removed
     */
    public void deleteItem(String name) {
        items.remove(name);
    }

    /** returning a reference to all items contained inside subcategory
     * @return Tree map of Sub Categories
     */
    public TreeMap<String, Item> getItems() {
        return items;
    }

    /** Returns Category of SubCategory
     * @return category reference
     */
    public Category getCategory() {
        return category;
    }

    /** Returns name of subcategory
     * @return string name
     */
    public String getName() {
        return name;
    }

    /** Sets name of Subcategory
     * @param name string name to be set
     */
    public void setName(String name) {
        this.name = name;
    }

    /** Helps to set items inside tree map
     * @param items tree map of items inside sub category
     */
    public void setItems(TreeMap<String, Item> items) {
        this.items = items;
    }

    /** Sets Category of Subcategory
     * @param category category reference
     */
    public void setCategory(Category category) {
        this.category = category;
    }
}
