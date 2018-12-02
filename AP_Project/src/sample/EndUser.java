package sample;

import java.util.ArrayList;

/**
 *  This is the End User class for guest users who serve as customers to software
 */
public class EndUser {

    protected String name;
    protected Superstore superstore;
    protected ArrayList<Item> cart;

    /**
     * This is the main constructor for initialising the object
     * @param name Name of EndUser
     * @param s Links to SuperStore selected
     */
    EndUser(String name, Superstore s) {
        this.name = name;
        this.superstore = s;
    }

    /**
     * Searching Item inside Store
     * @param name name of item to be searched
     * @return item reference to be added to cart
     */
    public ArrayList<Item> searchItem(String name) {
        ArrayList<Item> items = new ArrayList<>();
        for(Store s: superstore.getStores().values()) {
            ArrayList<Item> temp = s.searchItem(name);
            items.addAll(items);
        }
        return items;
    }

    /**
     * Add to cart function
     * @param i Item to be added
     * @param q Quantity to be added
     */
    public void addtoCart(Item i, int q) {
        Item temp = i.clone();
        temp.setQuantity(q);
        boolean ans = i.editQuantity(-q);
        if(ans) {
            Store s = (Store) i.getSubcategory().getCategory().getOutlet();
            s.getWarehouse().requestItems(i.getName(), i.getEOQ(), s);
        }
        cart.add(temp);
    }

    /**
     * Check Out the existing items in cart
     * @return returns total cost of  items added to cart
     */
    public double checkOut() {
        double cost = 0;
        for(Item i: cart) {
            cost += i.getCost();
        }

        cart.clear();
        return cost;
    }

    /**
     *  Used to clear cart i.e. remove all items.
     */
    public void clearCart() {

        for(Item i: cart) {
            i.getSubcategory().getItems().get(i.getName()).editQuantity(i.getQuantity());
        }
        cart.clear();
    }
}
