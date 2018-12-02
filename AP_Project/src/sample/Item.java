package sample;

public class Item implements Cloneable {
    protected String name;
    protected int quantity;
    protected double D;
    protected double K;
    protected double H;
    protected Subcategory subcategory;
    protected int EOQ;
    protected double cost;

    Item(String name, int quantity, double D, double K, double H, Subcategory subcategory) {
        this.name = name;
        this.quantity = quantity;
        this.D = D;
        this.K = K;
        this.H = H;
        this.subcategory = subcategory;
        calcEOQ();
        calcCost();
    }

    private void calcEOQ() {
        this.EOQ = (int) Math.ceil(Math.sqrt((2.0 * D * K) / H));
    }

    public int getEOQ() {
        calcEOQ();
        return this.EOQ;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    public boolean editQuantity(int q) {
        this.quantity += q;
        if(this.quantity == 0) return true;
        return false;
    }

    public void setQuantity(int q) {
        quantity = q;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setD(double d) {
        D = d;
        calcEOQ();
        calcCost();
    }

    public void setH(double h) {
        H = h;
        calcEOQ();
        calcCost();
    }

    public void setK(double k) {
        K = k;
        calcEOQ();
        calcCost();
    }

    public void calcCost() {
        cost = D + H;
    }

    public double getCost() {
        return cost;
    }

    public Subcategory getSubcategory() {
        return subcategory;
    }

    public Item clone() {
        Item temp = new Item(name, quantity, D, K, H, subcategory);
        return temp;
    }

    public double getD() {
        return D;
    }

    public void setSubcategory(Subcategory subcategory) {
        this.subcategory = subcategory;
    }

    public double getH() {
        return H;
    }

    public double getK() {
        return K;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setEOQ(int EOQ) {
        this.EOQ = EOQ;
    }

}
