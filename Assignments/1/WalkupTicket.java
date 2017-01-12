import page657.*;

/*
   Name: Thomas Kent
   Date: 9 January 2017
   Course: CS 211

   WalkupTicket class
   Represents a walk-up event ticket, which costs $50.
 */
public class WalkupTicket extends Ticket {
    // Constructors
    public WalkupTicket(int value) {
        super(value);
    }

    public WalkupTicket() {
        this(0);
    }

    // Accessors
    public double getPrice() {
        return 50.0; // tickets purchased as walk-up cost $50
    }
}
