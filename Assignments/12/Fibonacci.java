import java.math.*;
import java.util.*;

/*
 *
 * Name: Thomas Kent
 * Date: 02 February 2017
 * Course: CS 211
 *
 * Fibonacci class
 * Provides two different methods to calculate a fibonacci number.
 * One is very slow
 * The other is extremely fast
 *
 */
public class Fibonacci {

    // Program entry point
    public static void main(String[] args) {
        int test = 45; // I will limit my test code to passing int parameters
        BigInteger test2 = new BigInteger("45"); // only needed for overload
        System.out.println(theBigFib(test)); // a fast recursive version
        System.out.println(theBigFib(test2)); // overload, using same as above
        System.out.println(fibonacci(test)); // slow version in text

        System.out.println(theBigFib(new BigInteger("345"))); // testing the larger number from the assignment

        // Results:
        //
        // 45th Fibonacci number from theBigFib(45) is 1134903170
        //
        // 345th Fibonacci number from theBigFib(345) is 563963353180680437428706474693749258212475354428320807161115873039415970
    }

    // Public static methods
    // Slow inefficient way from the book
    public static int fibonacci(int n) {
        if (n <= 2) {
            return 1;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }

    // Takes a regular int, and calls into the BigInt overload
    public static BigInteger theBigFib(int n) {
        return theBigFib(new BigInteger("" + n));
    }

    // Takes a BigInt and returns the fibonacci number
    public static BigInteger theBigFib(BigInteger n) {
        return fibHelper(n, BigInteger.ONE, BigInteger.ZERO);
    }

    // Recursive helper method
    // Takes the current position in the countdown to the desired fibonacci sequence number, a running total of all the values so far, and the single previous value
    public static BigInteger fibHelper(BigInteger index, BigInteger val, BigInteger prev) {
        if (index == BigInteger.ZERO)
            // If zero, then the countdown is complete, and just return the previous value
            return prev;
        else
            // Otherwise, count down one sequence number, update the running total, and return a new call to this method with the new data
            return fibHelper(index.subtract(BigInteger.ONE), val.add(prev), val);
    }
}
