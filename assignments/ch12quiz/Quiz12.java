/*
 *
 * Name: Thomas Kent
 * Date: 03 Febuary 2017
 * Course: CS 211
 *
 * Quiz12 class
 * Chapter 12 quiz
 * Uses recursion to convert a given integer (assumed to be between 0 and MAX_INT) into a binary string
 *
 */
public class Quiz12 {
    // Program Entry Point
    public static void main(String[] args) {
        // Sample code
        System.out.println(Quiz12.toBinaryString(43)); // "101011"
        System.out.println(Quiz12.toBinaryString(8)); // "1000"
        System.out.println(Quiz12.toBinaryString(0)); // "0"
    }
    
    // Public static methods
    // Uses recursion to convert an integer into a binary string
    // calculates string from left to right
    public static String toBinaryString(int n) {
        if (n < 2)
            // base case, returns the unmodified value if it is 0 or 1
            return "" + n;
        else
            // recursive case, prepends a 0 or 1 for this place, then calls this function again to determine one place to the right, etc
            return "" + toBinaryString(n / 2) + (n % 2);
    }
}