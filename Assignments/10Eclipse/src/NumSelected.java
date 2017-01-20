
/*
 * 
 * Name: Thomas Kent
 * Date: 19 January 2017
 * Course: CS 211
 * 
 * NumSelected class
 * Represents a single item, in a given quantity in a shopping cart
 * 
 */
public class NumSelected {
    // Instance fields
    private Sku _sku;
    private int _quantity;

    // Constructors
    public NumSelected() {
        this(new Sku(), 0);
    }

    public NumSelected(Sku sku, int quantity) {
        _sku = sku;
        _quantity = quantity > 0 ? quantity : 0; // quantity shouldn't be negative
    }

    // Accessors
    public Sku getSku() {
        return _sku;
    }

    // Methods
    // Uses the Sku price calculation to determine the overall price for this item in the current quantity
    public double getPrice() {
        return _sku.priceFor(_quantity);
    }

    // Override Methods
    // Converts NumSelected object into human-readable string form
    public String toString() {
        return "sku: " + _sku.toString() + ", num:" + _quantity;
    }

}
