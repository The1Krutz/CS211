import page657.*;

/*
   Name: Thomas Kent
   Date: 9 January 2017
   Course: CS 211

   AdvanceTicket class
   Represents ticket purchased in advance.
 */
public class AdvanceTicket extends Ticket {
    // Instance variables
    private int days; // number of days in advance a ticket was purchased

    // Constructors
    public AdvanceTicket(int value, int daysAhead) {
        super(value);

        // days must be positive, or throw exception
        if (daysAhead > 0) {
            days = daysAhead;
        } else {
            throw new IllegalArgumentException("Days must be positive");
        }
    }

    public AdvanceTicket(int value) {
        this(value, 1);
    }

    public AdvanceTicket() {
        this(0);
    }

    // Accessors
    public double getPrice() {
        return days >= 10 ? 30.0 : 40.0; // tickets purchased 10 or more days in advance cost $30; those purchased 9 or fewer days in advance cost $40
    }

    public int getDays() {
        return days;
    }
}
