import java.text.*;

/*
 * 
 * Name: Thomas Kent
 * Date: 19 January 2017
 * Course: CS 211
 * 
 * Sku class
 * Represents a single item in a shop.
 * All items have a name and price
 * Some items also have a bulk price if purchased in certain quantities.
 * 
 */
public class Sku {
    // Instance fields
    private String _name; // name of the item
    private double _price; // price of a single item
    private int _bulkQuantity; // number of items to qualify for a bulk rate
    private double _bulkPrice; // price for a bulk number of items

    // Constructors
    public Sku() {
        this("default", 0.0);
    }

    public Sku(String name, double price) {
        this(name, price, 0, 0.0);
    }

    public Sku(String name, double price, int bulkQuantity, double bulkPrice) {
        if (price < 0 || bulkQuantity < 0 || bulkPrice < 0)
            throw new IllegalArgumentException(); // none of the numeric values here can be negative

        _name = name;
        _price = price;
        _bulkQuantity = bulkQuantity;
        _bulkPrice = bulkPrice;
    }

    // Accessor
    public String getName() {
        return _name;
    }

    // Methods
    // Calculates the price for a given quantity of this item. This calculation includes bulk pricing if applicable
    public double priceFor(int quantity) {
        if (quantity < 0)
            throw new IllegalArgumentException();

        double total = 0.0;
        if (_bulkQuantity > 0) {
            // if the item has a bulk price, use that for as many items as possible
            int numBulks = quantity / _bulkQuantity;
            quantity -= (int) (numBulks * _bulkQuantity); // subtract any items that qualify for bulk pricing

            total = (_bulkPrice * numBulks) + (_price * quantity);
        } else
            total = _price * quantity; // total price without any bulk pricing

        return total;
    }

    // Override Methods
    // Compares two Sku objects for equality based on name only. There should be only one price for a given item
    public boolean equals(Sku other) {
        return _name.equals(other.getName());
    }

    // Converts Sku object into human-readable string form
    public String toString() {
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        String skuString = _name + ", " + nf.format(_price);
        if (_bulkPrice > 0)
            skuString += " (" + _bulkQuantity + " for " + nf.format(_bulkPrice) + ")";

        return skuString;
    }

}
