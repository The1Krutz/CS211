// This program reads two text files and compares the vocabulary used in each.
// W.P. Iverson
// Bellevue College CS211
// Sample from text, Chapter 11

import java.util.*;
import java.io.*;

public class Vocabulary3 {
    public static void main(String[] q) throws FileNotFoundException {
        Scanner console = new Scanner(System.in);
        giveIntro();

        System.out.print("file #1 name? ");
        Scanner input1 = new Scanner(new File(console.nextLine())); // moby.txt
        System.out.print("file #2 name? ");
        Scanner input2 = new Scanner(new File(console.nextLine())); // hamlet.txt
        System.out.println();

        double time1 = System.currentTimeMillis();
        ArrayList<String> list1 = getWords(input1);
        ArrayList<String> list2 = getWords(input2);
        double time2 = System.currentTimeMillis();
        System.out.println("File read time=" + (time2-time1)/1000 + " seconds");
        
        time1 = System.currentTimeMillis();
        ArrayList<String> overlap = getOverlap(list1, list2);
        reportResults(list1, list2, overlap);
        time2 = System.currentTimeMillis();
        System.out.println("Process time=" + (time2-time1) + " milliseconds");
    }

    // post: reads all words from the given Scanner, turning them to lowercase
    //       and returning a sorted list of the vocabulary of the file
    public static ArrayList<String> getWords(Scanner input) {
        // ignore all but alphabetic characters and apostrophes
        input.useDelimiter("[^a-zA-Z']");
        // read all words and sort
        ArrayList<String> words = new ArrayList<String>();
        while (input.hasNext()) {
            String next = input.next().toLowerCase();
            words.add(next);
        }
        Collections.sort(words);

        // add unique words to new list and return
        ArrayList<String> result = new ArrayList<String>();
        if (words.size() > 0) {
            result.add(words.get(0));
            for (int i = 1; i < words.size(); i++) {
                if (!words.get(i).equals(words.get(i - 1))) {
                    result.add(words.get(i));
                }
            }
        }
        return result;
    }

    // pre : list1 and list2 are sorted and contain no duplicates
    // post: constructs and returns an ArrayList containing the words 
    //       in common betweeen list1 and list2
    public static ArrayList<String> getOverlap(ArrayList<String> list1,
                                               ArrayList<String> list2) {
        ArrayList<String> result = new ArrayList<String>();
        int index1 = 0;
        int index2 = 0;
        while (index1 < list1.size() && index2 < list2.size()) {
            int comparison = list1.get(index1).compareTo(list2.get(index2));
            if (comparison == 0) {
                result.add(list1.get(index1));
                index1++;
                index2++;
            } else if (comparison < 0) {
                index1++;
            } else {  // comparison > 0
                index2++;
            }
        }
        return result;
    }

    // post: explains program to user
    public static void giveIntro() {
        System.out.println("This program compares the vocabulary of two");
        System.out.println("text files, reporting the number of words");
        System.out.println("in common and the percent of overlap.");
        System.out.println();
    }

    // pre : overlap contains the words in common between list1 and list2
    // post: reports statistics about two word lists and their overlap
    public static void reportResults(ArrayList<String> list1,
                                     ArrayList<String> list2,
                                     ArrayList<String> overlap) {
        System.out.println("file #1 words = " + list1.size());
        System.out.println("file #2 words = " + list2.size());
        System.out.println("common words  = " + overlap.size());

        double percent1 = 100.0 * overlap.size() / list1.size();
        double percent2 = 100.0 * overlap.size() / list2.size();
        System.out.println("% of file 1 in overlap = " + percent1);
        System.out.println("% of file 2 in overlap = " + percent2);
    }
}
