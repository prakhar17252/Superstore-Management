public class StoreAdmin extends User implements OutletAdmin {
    protected Store store;

    StoreAdmin(String name, String pass, String storename, Superstore superstore, Warehouse warehouse) {
        super(name, pass);
        this.store = new Store(storename, this, superstore, warehouse);
    }

    @Override
    public void addCategory(String name) throws CategoryAlreadyExistsException {
        store.addCategory(name);
    }

    @Override
    public void deleteCategory(String name) {
        store.deleteCategory(name);
    }

    @Override
    public void addSubcategory(String cat, String sub) throws SubcategoryAlreadyExistsException{
        store.getCategories().get(cat).addSubcategory(sub);
    }

    @Override
    public void deleteSubcategory(String cat, String sub) {
        store.getCategories().get(cat).deleteSubcategory(sub);
    }

    @Override
    public void addItem(String cat, String sub, String itemname, int q, double D, double K, double H) throws ItemAlreadyExistsException {
        Subcategory s = store.getCategories().get(cat).getSubcategory().get(sub);
        s.addItem(itemname, q, D, K, H);
    }

    @Override
    public void deleteItem(String cat, String sub, String itemname) {
        Subcategory s = store.getCategories().get(cat).getSubcategory().get(sub);
        s.deleteItem(itemname);
    }

    @Override
    public void editItem(Item i, String name, int quantity, double D, double K, double H) {
        i.setName(name);
        i.setQuantity(quantity);
        i.setD(D);
        i.setK(K);
        i.setH(H);
    }

    public Store getStore() {
        return store;
    }

    public void sendOrderToWarehouse(Item item, int quantity) throws NotEnoughQuantityException {
        String itemname = item.getName();
        boolean ans = store.getWarehouse().requestItems(itemname, quantity, store);
        if(!ans) throw new NotEnoughQuantityException("not enough quantities");
        item.editQuantity(quantity);
    }
}
