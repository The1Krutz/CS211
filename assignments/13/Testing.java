import java.util.*;
/*
*
* Name: Thomas Kent
* Date: 09 February 2017
* Course: CS 211
*
* Testing class
* Generates timing data for Collections.sort()
* 
*/
public class Testing {
    // Program entry point
    public static void main(String[] args) {
        // dataset sizes to be used
        int[] numElements = new int[] { 50000, 100000, 250000, 500000, 1000000, 1500000, 2000000, 2500000, 3000000, 3500000, 4000000 };
        ArrayList<Integer> toBeSorted = new ArrayList<Integer>();

        for (int thisNum : numElements) {
            // fills the array list with the specified number of random integers, between zero and the int max value
            for (int i = 0; i < thisNum; i++)
                toBeSorted.add((int) (Math.random() * Integer.MAX_VALUE));

            long start = System.currentTimeMillis();
            // execute the sort
            Collections.sort(toBeSorted);
            long total = System.currentTimeMillis() - start;

            // print list size and execution time to console
            System.out.println(toBeSorted.size() + " items in " + total + " ms");
            // clear list for next run
            toBeSorted.clear();
        }
    }
}