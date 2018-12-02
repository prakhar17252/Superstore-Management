package sample;

import java.io.Serializable;

/**
 *  This is the Item class which is used to
 */
public class Item implements Cloneable, Serializable {

    protected String name;
    protected int quantity;
    protected double D;
    protected double K;
    protected double H;
    protected Subcategory subcategory;
    protected int EOQ;
    protected double cost;

    /**
     *  Constructor for instantiating a new Item
     * @param name Name of Item
     * @param quantity Quantity of Item
     * @param D Cost Per Quarter
     * @param K Demands for Items per Quarter
     * @param H Carrying Cost Per Unit Quarter
     * @param subcategory Reference(Association) to Sub Category which contains it
     */
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

    /**
     *  Method for calculating EOQ
     */
    private void calcEOQ() {
        this.EOQ = (int) Math.ceil(Math.sqrt((2.0 * D * K) / H));
    }

    /**
     * Getter Function
     * @return EOQ Calculated
     */
    public int getEOQ() {
        calcEOQ();
        return this.EOQ;
    }

    /**
     *  Getter Function
     * @return Quantity of Item
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Getter Function
     * @return Name of Item
     */
    public String getName() {
        return name;
    }

    /**
     * Method for editing Quantity
     * @param q New Quantity
     * @return If Quantity is changed or not
     */
    public boolean editQuantity(int q) {
        this.quantity += q;
        if(this.quantity == 0) return true;
        return false;
    }

    /**
     * Helps to Set Quantity
     * @param q New Quantity
     */
    public void setQuantity(int q) {
        quantity = q;
    }

    /**
     * Helps to change Name
     * @param name New Name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This is the method for setting D
     * @param d New D
     */
    public void setD(double d) {
        D = d;
        calcEOQ();
        calcCost();
    }

    /**
     * This is the method for setting H
     * @param h New H
     */
    public void setH(double h) {

        H = h;
        calcEOQ();
        calcCost();
    }

    /**
     * Setting K
     * @param k New K
     */
    public void setK(double k) {
        K = k;
        calcEOQ();
        calcCost();
    }

    /**
     * Method for Calculating Cost
     */
    public void calcCost() {
        cost = D + H;
    }

    /**
     * @return Method for returning cost
     */
    public double getCost() {
        return cost;
    }

    /**
     * @return subcategory of existing Item
     */
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
