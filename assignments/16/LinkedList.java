import java.util.*;
/*
 *
 * Name: Thomas Kent
 * Date: 02 March 2017
 * Course: CS 211
 *
 * LinkedList<E> class
 * Implements 4 solutions from the book
 * 
 * min: returns the minimum element in the list
 * countDuplicates: returns a count of duplicate elements
 * stutter: doubles thes ize of a list by turning every element into a pair of elements. ie: [1,2,3] => [1,1,2,2,3,3]
 * removeAll: removes all occurences of a given value from the list
 * 
 */

// Class LinkedList<E> can be used to store a list of values of type E.
// from Buildingjavaprograms.com
// modified by W.P. Iverson, Bellevue College, January 2017
// added backwards() to check list in backwards order

public class LinkedList<E extends Comparable<E>> implements Iterable<E> {
    // removed implements List due to version differences of List
    private ListNode<E> front; // first value in the list
    private ListNode<E> back; // last value in the list
    private int size; // current number of elements

    // post: constructs an empty list
    public LinkedList() {
        front = new ListNode<E>(null);
        back = new ListNode<E>(null);
        clear();
    }

    // ADD MORE METHODS HERE (like for assigned CS211 work):

    // #2. min
    // returns the minimum value in the list. If the list is empty, will throw a NoSuchElementException
    public E min() {
        if (size == 0)
            // throw if the list is empty
            throw new NoSuchElementException();

        // set the initial minimum to the first real value in the list
        ListNode<E> currentNode = this.front.next;
        E currentMin = currentNode.data;

        // iterate through the list
        for (int i = 0; i < this.size; i++) {
            // compare the current min value to the next value
            if (currentNode.data.compareTo(currentMin) < 0)
                // and replace the current min if the next value is "lower"
                currentMin = currentNode.data;

            // move to the next node
            currentNode = currentNode.next;
        }

        // return lowest value
        return currentMin;
    }

    // #5. countDuplicates
    // counts the duplicates in a sorted list. List is assumed to be in sorted order.
    // ie: if list contains [1, 1, 1, 2, 3, 3], then the count of duplicates is 3 (2 duplicates of 1 and 1 duplicate of 3)
    public int countDuplicates() {
        if (size <= 1)
            // a list with 0 or 1 elements can't have duplicates
            return 0;

        // set the initial value to the second value in the list
        ListNode<E> currentNode = this.front.next.next;
        int runningDupeCount = 0;

        // iterate through the list, but -1 size because we started from the second element
        for (int i = 0; i < this.size - 1; i++) {
            // compare the current value to the previous value
            if (currentNode.data.compareTo(currentNode.prev.data) == 0)
                // increment the dupe count if this value is equal to the previous value
                runningDupeCount++;

            // move to the next node
            currentNode = currentNode.next;
        }

        // return duplicate count
        return runningDupeCount;
    }

    // #9. stutter
    // doubles the size of the list by replacing every value with two of that value
    // ie: [1, 2, 3] becomes [1, 1, 2, 2, 3, 3]
    public void stutter() {
        if (this.size == 0)
            // nothing to do if the list is empty
            return;

        // set the initial value to the second value in the list
        ListNode<E> currentNode = this.front.next;

        // iterate through the list
        do {
            // keep reference to original next node
            ListNode<E> tempNext = currentNode.next;
            // create node to insert, with a copy of this node's data, and references to it's next and previous nodes
            ListNode<E> inserting = new ListNode<E>(currentNode.data, tempNext, currentNode);

            // adjust references on existing nodes to point to inserted node
            tempNext.prev = inserting;
            currentNode.next = inserting;

            // skip the inserted node
            currentNode = currentNode.next.next;

            // carry on in this manner until we reach the tail node
        } while (currentNode.data != null);

    }

    // #14. removeAll
    // removes all occurences of a given element from anywhere in the list
    public void removeAll(E removing) {
        if (this.size == 0)
            // nothing to do if the list is empty
            return;

        // set the initial value to the second value in the list
        ListNode<E> currentNode = this.front.next;

        // iterate through the list
        while (currentNode.data != null) {
            if (currentNode.data.compareTo(removing) == 0) {
                // reset next and previous node's links to skip this node
                currentNode.prev.next = currentNode.next;
                currentNode.next.prev = currentNode.prev;
            }

            // move to the next node
            currentNode = currentNode.next;
        }
    }

    // ASSIGNMENT CODE STOPS HERE

    // post: returns the current number of elements in the list
    public int size() {
        return size;
    }

    // pre : 0 <= index < size() (throws IndexOutOfBoundsException if not)
    // post: returns the value at the given index in the list
    public E get(int index) {
        checkIndex(index);
        ListNode<E> current = nodeAt(index);
        return current.data;
    }

    // post: creates a comma-separated, bracketed version of the list
    public String toString() {
        if (size == 0) {
            return "[]";
        } else {
            String result = "[" + front.next.data;
            ListNode<E> current = front.next.next;
            while (current != back) {
                result += ", " + current.data;
                current = current.next;
            }
            result += "]";
            return result;
        }
    }

    // post: creates a comma-separated, bracketed version of the list
    // Iverson creation
    public String backwards() {
        if (size == 0) {
            return "[]";
        } else {
            String result = "[" + back.prev.data;
            ListNode<E> current = back.prev.prev;
            while (current != front) {
                result += ", " + current.data;
                current = current.prev;
            }
            result += "]";
            return result;
        }
    }

    // post : returns the position of the first occurrence of the given
    // value (-1 if not found)
    public int indexOf(E value) {
        int index = 0;
        ListNode<E> current = front.next;
        while (current != back) {
            if (current.data.equals(value)) {
                return index;
            }
            index++;
            current = current.next;
        }
        return -1;
    }

    // post: returns true if list is empty, false otherwise
    public boolean isEmpty() {
        return size == 0;
    }

    // post: returns true if the given value is contained in the list,
    // false otherwise
    public boolean contains(E value) {
        return indexOf(value) >= 0;
    }

    // post: appends the given value to the end of the list
    public void add(E value) {
        add(size, value);
    }

    // pre: 0 <= index <= size() (throws IndexOutOfBoundsException if not)
    // post: inserts the given value at the given index, shifting subsequent
    // values right
    public void add(int index, E value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index: " + index);
        }
        ListNode<E> current = nodeAt(index - 1);
        ListNode<E> newNode = new ListNode<E>(value, current.next, current);
        current.next = newNode;
        newNode.next.prev = newNode;
        size++;
    }

    // post: appends all values in the given list to the end of this list
    public void addAll(List<E> other) {
        for (E value : other) {
            add(value);
        }
    }

    // pre : 0 <= index < size() (throws IndexOutOfBoundsException if not)
    // post: removes value at the given index, shifting subsequent values left
    public void remove(int index) {
        checkIndex(index);
        ListNode<E> current = nodeAt(index - 1);
        current.next = current.next.next;
        current.next.prev = current;
        size--;
    }

    // pre : 0 <= index < size() (throws IndexOutOfBoundsException if not)
    // post: replaces the value at the given index with the given value
    // public void set(int index, E value) {
    // checkIndex(index);
    // ListNode<E> current = nodeAt(index);
    // current.data = value;
    // }

    // post: list is empty
    public void clear() {
        front.next = back;
        back.prev = front;
        size = 0;
    }

    // post: returns an iterator for this list
    public Iterator<E> iterator() {
        return new LinkedIterator();
    }

    // pre : 0 <= index < size()
    // post: returns the node at a specific index. Uses the fact that the list
    // is doubly-linked to start from the front or the back, whichever
    // is closer.
    private ListNode<E> nodeAt(int index) {
        ListNode<E> current;
        if (index < size / 2) {
            current = front;
            for (int i = 0; i < index + 1; i++) {
                current = current.next;
            }
        } else {
            current = back;
            for (int i = size; i >= index + 1; i--) {
                current = current.prev;
            }
        }
        return current;
    }

    // post: throws an IndexOutOfBoundsException if the given index is
    // not a legal index of the current list
    private void checkIndex(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("index: " + index);
        }
    }

    private static class ListNode<E> {
        public E data; // data stored in this node
        public ListNode<E> next; // link to next node in the list
        public ListNode<E> prev; // link to previous node in the list

        // post: constructs a node with given data and null links
        public ListNode(E data) {
            this(data, null, null);
        }

        // post: constructs a node with given data and given links
        public ListNode(E data, ListNode<E> next, ListNode<E> prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }

    private class LinkedIterator implements Iterator<E> {
        private ListNode<E> current; // location of next value to return
        private boolean removeOK; // whether it's okay to remove now

        // post: constructs an iterator for the given list
        public LinkedIterator() {
            current = front.next;
            removeOK = false;
        }

        // post: returns true if there are more elements left, false otherwise
        public boolean hasNext() {
            return current != back;
        }

        // pre : hasNext()
        // post: returns the next element in the iteration
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E result = current.data;
            current = current.next;
            removeOK = true;
            return result;
        }

        // pre : next() has been called without a call on remove (i.e., at most
        // one call per call on next)
        // post: removes the last element returned by the iterator
        public void remove() {
            if (!removeOK) {
                throw new IllegalStateException();
            }
            ListNode<E> prev2 = current.prev.prev;
            prev2.next = current;
            current.prev = prev2;
            size--;
            removeOK = false;
        }
    }
}