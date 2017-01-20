import java.util.*;

/*
 * 
 * Name: Thomas Kent
 * Date: 19 January 2017
 * Course: CS 211
 * 
 * ShoppingCart class
 * Extends ArrayList to represent a collection of items being purchased
 * 
 */
@SuppressWarnings("serial")
public class ShoppingCart extends ArrayList<NumSelected> {
    // Instance fields
    private double _discount;
    private boolean _isDiscounted;

    // Constructor
    public ShoppingCart() {
        super();

        _discount = .9;
        _isDiscounted = false;
    }

    // Mutators
    public void setDiscount(boolean isDiscounted) {
        _isDiscounted = isDiscounted;
    }

    // Methods
    // Adds an item to the shopping cart. If the item is already present in the cart, the quantity is adjusted to reflect the new value
    public boolean add(NumSelected item) {
        int duplicateIndex = -1;

        for (int i = 0; i < this.size(); i++)
            if (this.get(i).getSku().equals(item.getSku()))
                duplicateIndex = i; // index of item if it already exists in the cart

        if (duplicateIndex >= 0)
            super.set(duplicateIndex, item); // item already in cart, replace it
        else
            super.add(item); // item not in cart, add it

        return true;
    }

    // Calculates the total value of every item in the cart
    public double getTotal() {
        double runningTotal = 0.0;
        for (NumSelected s : this)
            runningTotal += s.getPrice(); // add the price of each item to the running total

        // if the discount flag is set, apply the discount
        return _isDiscounted ? runningTotal * _discount : runningTotal;
    }

    // Override Methods
    // Converts ShoppingCart object into human-readable string form
    public String toString() {
        String wholeCart = "";

        for (NumSelected ns : this)
            wholeCart += ns.toString() + ", ";

        return wholeCart;
    }

}
