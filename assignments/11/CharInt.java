import java.util.*;

/*
 *
 * Name: Thomas Kent
 * Date: 26 January 2017
 * Course: CS 211
 *
 * CharInt class
 * Stores a single key-value pair <char, int>
 *
 */
public class CharInt implements Comparable<CharInt>, Comparator<CharInt> {
    // Instance fields
    private char _character; // character being recorded
    private int _count; // how many times the characters is present

    // Constructors
    public CharInt(char character, int count) {
        _character = character;
        _count = count;
    }

    public CharInt() {
        this(' ', 0);
    }

    // Override Methods
    // Both implemented to sort by count from highest to lowest
    public int compareTo(CharInt other) {
        return other.getCount() - _count;
    }

    public int compare(CharInt o1, CharInt o2) {
        return o2.getCount() - o1.getCount();
    }

    public String toString() {
        return "{ '" + _character + "' = " + _count + " }";
    }

    // Accessor
    public int getCount() {
        return _count;
    }

    // Mutator
    public void setCount(int value) {
        _count = value;
    }
}