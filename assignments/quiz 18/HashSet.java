// Implements a set of objects using a hash table.
// The hash table uses separate chaining to resolve collisions.
/*
 *
 * Name: Thomas Kent
 * Date: 17 March 2017
 * Course: CS 211
 *
 * HashSet<E> class
 * implement 2 methods
 * equals - checks two hashsets for equality
 * longestChain - checks the internal storage for the hashset for the longest chain of hashed values.
 * 
 */
public class HashSet<E> {
    private static final double MAX_LOAD_FACTOR = 0.75;
    private HashEntry<E>[] elementData;
    private int size;

    // Constructs an empty set.
    @SuppressWarnings("unchecked")
    public HashSet() {
        elementData = new HashEntry[10];
        size = 0;
    }

    // ADD METHODS HERE for exercise solutions:

    // builds a string that shows the internal structure of the hash set
    public String toString2() {
        int numRows = 0; // ignore the labels for now

        // all this just to count the total number of rows needed to display the deepest list of values
        for (int i = 0; i < this.elementData.length; i++) {
            int temp = 0;
            HashEntry<E> node = this.elementData[i];

            // iterate over each linked list in this array and find the longest one
            while (node != null) {
                temp++;
                node = node.next;
            }

            if (temp > numRows)
                numRows = temp;
        }
        numRows++; // now account for the labels

        // create the array that will store all the strings to be combined into output
        String[][] outputArray = new String[numRows][this.elementData.length];

        // populate the first row with [index] headers
        for (int i = 0; i < outputArray[0].length; i++)
            outputArray[0][i] = ("[" + i + "]          ").substring(0, 10);

        for (int i = 0; i < this.elementData.length; i++) {
            // left to right on the final output
            HashEntry<E> node = this.elementData[i];

            for (int j = 0; j < numRows - 1; j++) {
                // top down on the final output
                if (node != null) {
                    // if the node has data, add it, and enough filler space at the end to properly format the table
                    outputArray[j + 1][i] = (node.data.toString() + "         ").substring(0, 10);
                    node = node.next;
                } else
                    // otherwise just add ten blank spaces to keep the table formatted properly
                    outputArray[j + 1][i] = "          ";
            }
        }

        String outputString = "";

        // combine the array of stored strings into a single string with appropriate line breaks
        for (String[] i : outputArray) {
            for (String j : i)
                outputString += j;
            outputString += "\n";
        }

        return outputString;
    }

    // ASSIGNMENT CODE ENDS

    // QUIZ CODE STARTS

    // A. equals accepts another HashSet as a parameter and returns true if the other
    // HashSet contains exactly the same elements as this HashSet.
    // The internal hash table size and ordering of the elements does not matter, only the sets of elements themselves.
    public boolean equals(HashSet<E> other) {
        // first check that this set contains only values that are present in the other set
        for (int i = 0; i < this.elementData.length; i++) {
            HashEntry<E> node = this.elementData[i];

            // iterate through every link in this chain and make sure the value exists in other
            while (node != null) {
                if (!other.contains(node.data))
                    // if this value is not present in other, then the hashsets are different, so stop
                    return false;

                node = node.next;
            }
        }

        // then check the other way round - other set only has values that this set has
        for (int i = 0; i < other.elementData.length; i++) {
            HashEntry<E> node = other.elementData[i];

            // iterate through every link in this chain and make sure the value exists in other
            while (node != null) {
                if (!this.contains(node.data))
                    // if this value is not present in other, then the hashsets are different, so stop
                    return false;

                node = node.next;
            }
        }

        // if both of the above loops complete without detecting a missing element, then the two sets must be equal
        return true;
    }

    // B. longestChain an int method that returns the number of nodes in the longest linked list (chain) of this HashSet.
    // The empty set returns zero, most small sets return one, but sets where hash code collisions occur will have longer chains.
    public int longestChain() {
        int numRows = 0;

        for (int i = 0; i < this.elementData.length; i++) {
            int temp = 0;
            HashEntry<E> node = this.elementData[i];

            // iterate over each linked list in this array and find the longest one
            while (node != null) {
                temp++;
                node = node.next;
            }

            if (temp > numRows)
                numRows = temp;
        }

        return numRows;
    }

    // QUIZ CODE ENDS

    // Adds the given element to this set, if it was not already
    // contained in the set.
    public void add(E value) {
        if (!contains(value)) {
            if (loadFactor() >= MAX_LOAD_FACTOR) {
                rehash();
            }

            // insert new value at front of list
            int bucket = hashFunction(value);
            elementData[bucket] = new HashEntry<E>(value, elementData[bucket]);
            size++;
        }
    }

    // Removes all elements from the set.
    public void clear() {
        for (int i = 0; i < elementData.length; i++) {
            elementData[i] = null;
        }
        size = 0;
    }

    // Returns true if the given value is found in this set.
    public boolean contains(E value) {
        int bucket = hashFunction(value);
        HashEntry<E> current = elementData[bucket];
        while (current != null) {
            if (current.data.equals(value)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // Returns true if there are no elements in this queue.
    public boolean isEmpty() {
        return size == 0;
    }

    // Removes the given value if it is contained in the set.
    // If the set does not contain the value, has no effect.
    public void remove(E value) {
        int bucket = hashFunction(value);
        if (elementData[bucket] != null) {
            // check front of list
            if (elementData[bucket].data.equals(value)) {
                elementData[bucket] = elementData[bucket].next;
                size--;
            } else {
                // check rest of list
                HashEntry<E> current = elementData[bucket];
                while (current.next != null && !current.next.data.equals(value)) {
                    current = current.next;
                }

                // if the element is found, remove it
                if (current.next != null && current.next.data.equals(value)) {
                    current.next = current.next.next;
                    size--;
                }
            }
        }
    }

    // Returns the number of elements in the queue.
    public int size() {
        return size;
    }

    // Returns a string representation of this queue, such as "[10, 20, 30]";
    // The elements are not guaranteed to be listed in sorted order.
    public String toString() {
        String result = "[";
        boolean first = true;
        if (!isEmpty()) {
            for (int i = 0; i < elementData.length; i++) {
                HashEntry<E> current = elementData[i];
                while (current != null) {
                    if (!first) {
                        result += ", ";
                    }
                    result += current.data;
                    first = false;
                    current = current.next;
                }
            }
        }
        return result + "]";
    }

    // Returns the preferred hash bucket index for the given value.
    private int hashFunction(E value) {
        return Math.abs(value.hashCode()) % elementData.length;
    }

    private double loadFactor() {
        return (double) size / elementData.length;
    }

    // Resizes the hash table to twice its former size.
    @SuppressWarnings("unchecked")
    private void rehash() {
        // replace element data array with a larger empty version
        HashEntry<E>[] oldElementData = elementData;
        elementData = new HashEntry[2 * oldElementData.length];
        size = 0;

        // re-add all of the old data into the new array
        for (int i = 0; i < oldElementData.length; i++) {
            HashEntry<E> current = oldElementData[i];
            while (current != null) {
                add((E) current.data);
                current = current.next;
            }
        }
    }

    // Represents a single value in a chain stored in one hash bucket.
    @SuppressWarnings("hiding")
    private class HashEntry<E> {
        public E data;
        public HashEntry<E> next;

        @SuppressWarnings("unused")
        public HashEntry(E data) {
            this(data, null);
        }

        public HashEntry(E data, HashEntry<E> next) {
            this.data = data;
            this.next = next;
        }
    }
}
