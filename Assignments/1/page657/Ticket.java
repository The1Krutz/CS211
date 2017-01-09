package page657;
/**
 * Name: W.P. Iverson
 * Date: January 2017
 * Course: CS211
 * Assignment: #9
 * Ticket Exercises from text
 */

// DO NOT Change this code, it will be used as base for all grading
public abstract class Ticket {
	//INSTANCE VARIABLES
	private int number;  // The ticket number defined by constructor
	protected double price;  // Price determined only in sub-class
	
	// STATIC FIELDS: Only one for the whole Class
	private static int numSold = 0;
	public static int maxSold = 100;
	private static boolean[] sold = new boolean[maxSold];  
	// Tells us if that index (number) is sold	
	
	//CONSTRUCTORS
	public Ticket(int value) {
		numSold++;	
		while (sold[value]) {
			value++;  // find next available unique ticket number
		}
		sold[value] = true;
		number = value;  // Ticket constructor sets number
	}
	
	public Ticket() {
		this(0);
	}
	
	// ACCESSORS
	public abstract double getPrice();  // now you must make this
	
	// JAVA standard toString
    public String toString() {
        return "Number: " + this.number + ", Price: " + this.getPrice();
    }
}
