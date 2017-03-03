import java.util.*;

/*
 *
 * Name: Thomas Kent
 * Date: 27 January 2017
 * Course: CS 211
 *
 * Quiz11 class
 * Chapter 11 quiz
 * counts max number of occurences in a single lists, or from an intersection of two lists
 *
 */

// starter code:
public class Quiz11 {
    public static void main(String[] args) {
        ArrayList<Integer> list1 = new ArrayList<Integer>();
        list1.add(1);
        list1.add(1);
        list1.add(1);
        list1.add(2);
        list1.add(3);
        ArrayList<Integer> list2 = new ArrayList<Integer>();
        list2.add(3);
        list2.add(4);
        list2.add(5);
        System.out.println(Quiz11.maxOccurrences(list1)); // 3
        System.out.println(Quiz11.maxIntersections(list1, list2)); // 2
    }

    // solutions go below here:
    // A. Write a public static method maxOccurrences that accepts a list of Integers as a parameter and returns the number of times the most frequent occurring integer (the "mode") occurs in the list.
    // Solve this problem using a Map as auxiliary storage. If the list is empty, return 0.
    public static int maxOccurrences(List<Integer> list) {
        HashMap<Integer, Integer> occurences = new HashMap<Integer, Integer>();
        int maxOccurences = 0;

        // if the list is empty, the initial value of zero will be returned
        if (list.size() > 0)
            for (int i : list) {
                if (occurences.get(i) != null)
                    // if the element is present in the map, increment its count
                    occurences.put(i, occurences.get(i) + 1);
                else
                    // otherwise add a new count for that int starting at 1
                    occurences.put(i, 1);

                if (occurences.get(i) > maxOccurences)
                    // compare newly added count against the running maximum count, and replace it if necessary
                    maxOccurences = occurences.get(i);
            }

        return maxOccurences;
    }

    // B. Write a public static method maxIntersections that accepts two lists of Integers and returns the number of times the most frequent Integer occurs only when in both lists.
    // For example, for list1=[1,1,1,2,3] and list2=[3,4,5], then maxIntersections(list1,list2) would return two as the 3 is in both lists so seen twice.
    // If no elements are common to both lists, return 0.
    public static int maxIntersections(List<Integer> list1, List<Integer> list2) {
        HashMap<Integer, Integer> l1Helper = new HashMap<Integer, Integer>();
        HashMap<Integer, Integer> l2Helper = new HashMap<Integer, Integer>();
        int maxIntersections = 0;

        // populate both count maps
        for (int i : list1)
            if (l1Helper.get(i) != null)
                l1Helper.put(i, l1Helper.get(i) + 1);
            else
                l1Helper.put(i, 1);

        for (int i : list2)
            if (l2Helper.get(i) != null)
                l2Helper.put(i, l2Helper.get(i) + 1);
            else
                l2Helper.put(i, 1);

        // use the intersection of the keysets of both count maps to generate a set of ints that appear in both lists
        Set<Integer> intersection = new HashSet<Integer>(l1Helper.keySet());
        intersection.retainAll(l2Helper.keySet());

        // if the intersection set is empty, the initial value of zero will be returned
        for (int i : intersection)
            // get a total count of occurences from both count maps, for each element in the intersection set
            if (l1Helper.get(i) + l2Helper.get(i) > maxIntersections)
                // compare it against the running max count and replace if necessary
                maxIntersections = l1Helper.get(i) + l2Helper.get(i);

        return maxIntersections;
    }

}