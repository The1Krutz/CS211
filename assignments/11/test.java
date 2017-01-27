import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class test {

    public static void main(String[] args) throws FileNotFoundException {
         Scanner data = new Scanner(new File("mobydick.txt"));
//       Scanner data = new Scanner(new File("test.txt"));
         CharacterCounter working = new CharacterCounter(data); // reads data from file, all the code to read file
         System.out.println(working.getCounts(3)); // returns highest 3 counts e.g. =199250, e=115002, t=86488...
         // I counted 199250 space characters in moby.txt file, yes 'e' is the most common char as Wikipedia predicts.
         System.out.println(working.getCounts('a')); // returns int count of char 'a' (zero if not present)
         System.out.println(working.getCounts(-3)); // returns a Collection of lowest 3 counts, e.g. $=2, [=2, ]=2
         System.out.println(working.getCounts()); // returns the ENTIRE Collection like a Map (like e=9, etc.) crazy long...
         System.out.println(working.getCounts(30000)); // returns the ENTIRE Collection like a Map (like e=9, etc.) crazy long...
         System.out.println(working.getCounts(-30000)); // returns the ENTIRE Collection like a Map (like e=9, etc.) crazy long...
    }
}
