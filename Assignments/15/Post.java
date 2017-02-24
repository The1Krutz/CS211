import java.io.*;
import java.util.*;

/*
 * Winter 2017, CS211, W.P. Iverson, instructor and author
 * This is a start at testing code for Chapter 15 Assignment
 * Other tests can and will happen....
 */
public class Post {

    public static void main(String[] args) throws FileNotFoundException {

        // required:
        ArrayIntStack bag15 = new ArrayIntStack();
        try {
            bag15.pop();
        } catch (EmptyStackException e) {
            System.out.println("test" + e);
        }
        bag15.push(42);
        bag15.push(24);
        bag15.push(-33);

        // works without the generic Iterator from Oracle
        ArrayIntStack.IntStackIterator handle = bag15.iterator();
        while (handle.hasNext())
            System.out.println(handle.next());
        System.out.println(bag15.empty());
        System.out.println(bag15.push(42));
        System.out.println(bag15.pop());
        System.out.println(bag15.peek());

        // optional:
        // if you implement Iterable<Iterator>
        // for (Integer i : bag15) System.out.println(i);
    }

}
