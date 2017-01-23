import java.util.ArrayList;

/*
 *
 * Name: Thomas Kent
 * Date: 20 January 2017
 * Course: CS 211
 *
 * quiz10 class
 * Chapter 10 quiz
 * groups and ungroups lists of strings into and from pairs
 * 
 */

// starter code:
public class Quiz10 {
    public static void main(String[] a) {
        ArrayList<String> test = new ArrayList<String>();
        test.add("seven");
        test.add("years");
        test.add("ago");
        Quiz10.clump(test);
        Quiz10.undoClump(test);
        System.out.println(test); // back to original list
    }

    // solutions go below here:

    // A. Chapter 10, Exercise #16 from text: Write a method called clump that accepts an ArrayList<String> and replaces each pair of Strings
    // with a single String. For example, [seven, years, ago] is changed to [(seven years), ago]
    public static void clump(ArrayList<String> list) {

        for (int i = 0; i < list.size(); i++) {
            String temp = "(";

            if (i + 1 >= list.size())
                // odd string out, has no pair, gets added as the last element without parentheses
                temp = list.get(i);
            else {
                // paired strings get parentheses and added as a pair
                temp += list.get(i) + " " + list.get(i + 1) + ")";
                list.remove(i + 1);
            }

            // replace first word with word group
            list.set(i, temp);
        }

        // output to console
        System.out.println(list);
    }

    // B. Write a method called undoClump that accepts an ArrayList<String> and replaces all Stings containing paired parentheses, with each word inside
    // those parentheses, words identified by space ' ' separating the words. Also remove the parentheses.
    // For example, [(seven years), ago] is changed back to [seven, years, ago]
    private static void undoClump(ArrayList<String> list) {

        for (int i = 0; i < list.size(); i++) {
            String temp = list.get(i);

            // check if words have been grouped and splitting is necessary
            if (temp.startsWith("(") && temp.endsWith(")") && temp.contains(" ")) {

                temp = temp.substring(1, temp.length() - 1); // remove parentheses
                String[] splitWords = temp.split(" "); // separate the words on spaces
                list.set(i, splitWords[0]); // replace the word group with the first element

                for (int j = 1; j < splitWords.length; j++) {
                    // add the remainder of the elements, moving the outer loop index to follow
                    list.add(i + 1, splitWords[j]);
                    i++;
                }
            }
        }

        // output to console
        System.out.println(list);
    }
}
