import java.util.ArrayList;

public class EndUser {
    protected String name;
    protected Superstore superstore;
    protected ArrayList<Item> cart;

    EndUser(String name, Superstore s) {
        this.name = name;
        this.superstore = s;
    }

    public ArrayList<Item> searchItem(String name) {
        ArrayList<Item> items = new ArrayList<>();
        for(Store s: superstore.getStores().values()) {
            ArrayList<Item> temp = s.searchItem(name);
            items.addAll(items);
        }
        return items;
    }

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

    public double checkOut() {
        double cost = 0;
        for(Item i: cart) {
            cost += i.getCost();
        }

        cart.clear();
        return cost;
    }

    public void clearCart() {
        for(Item i: cart) {
            i.getSubcategory().getItems().get(i.getName()).editQuantity(i.getQuantity());
        }
        cart.clear();
    }
}
