import page657.*;

/*
   Name: Thomas Kent
   Date: 9 January 2017
   Course: CS 211

   StudentAdvanceTicket class
   Represents ticket purchased in advance by a student
 */
public class StudentAdvanceTicket extends AdvanceTicket {
    // Accessors
    public double getPrice() {
        return super.getPrice() / 2.0; // 50% student discount on advance tickets
    }

    // Methods
    // override toString to include the ID requirement
    public String toString() {
        return super.toString() + " (ID Required)";
    }
}
