import java.util.*;

/*
 *
 * Name: Thomas Kent
 * Date: 26 January 2017
 * Course: CS 211
 *
 * CharacterCounter class
 * Reads a Scanner and counts the number of occurrences of each distinct character
 *
 */
public class CharacterCounter {
    // Instance fields
    private ArrayList<CharInt> sortedCharCounts; // sorted list of character-count maps objects
    private HashMap<Character, Integer> charCountMap; // unsorted map of characters with their count

    // Constructors
    public CharacterCounter(Scanner data) {
        sortedCharCounts = new ArrayList<CharInt>();

        while (data.hasNextLine()) {
            if (charCountMap == null)
                // initialize the hashmap on the first iteration
                charCountMap = new HashMap<Character, Integer>();
            else
                // increment the count of new line characters on each subsequent iteration
                addOrIncrement(charCountMap, '\n');

            String s = data.nextLine();
            // read and process one line at a time
            for (int i = 0; i < s.length(); i++) {
                // ignore case
                char c = Character.toLowerCase(s.charAt(i));
                addOrIncrement(charCountMap, c);
            }
        }

        // after all characters have been counted in the map, add them to an Arraylist for sorting
        for (char c : charCountMap.keySet())
            sortedCharCounts.add(new CharInt(c, charCountMap.get(c)));

        // sort using CharInt custom sorting
        Collections.sort(sortedCharCounts);
    }

    // Private methods
    private void addOrIncrement(Map<Character, Integer> map, Character charAdding) {
        Integer i = map.get(charAdding);

        if (i == null)
            // element not present, add it with count of 1
            map.put(charAdding, 1);
        else
            // element present, increment its count by 1
            map.put(charAdding, i + 1);
    }

    // Public methods
    public int getCounts(char character) {
        character = Character.toLowerCase(character);

        if (charCountMap.containsKey(character))
            // character is present in the map, return its count
            return charCountMap.get(character);
        else
            // character not present, return 0
            return 0;
    }

    public ArrayList<CharInt> getCounts(int count) {
        ArrayList<CharInt> subset = new ArrayList<CharInt>();

        // if the requested count is greater than the size of the list (positive or negative), set it to the size of the list
        if (count >= sortedCharCounts.size() || (count * -1) >= sortedCharCounts.size())
            count = sortedCharCounts.size();

        if (count > 0)
            // i is positive, return elements from 0 to i
            for (int j = 0; j < count; j++)
                subset.add(sortedCharCounts.get(j));
        else if (count < 0)
            // i is negative, return elements from end-|i| to end
            for (int j = sortedCharCounts.size() + count; j < sortedCharCounts.size(); j++)
                subset.add(sortedCharCounts.get(j));

        // if i is zero, this will be empty, which is intended
        return subset;
    }

    public ArrayList<CharInt> getCounts() {
        // default, return the whole sorted list
        return sortedCharCounts;
    }
}