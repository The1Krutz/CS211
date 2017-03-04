// Class LinkedIntList can be used to store a list of integers.
/*
 *
 * Name: Thomas Kent
 * Date: 03 March 2017
 * Course: CS 211
 *
 * LinkedIntList class
 * Chapter 16 quiz
 * isPerfectStutter: checks if each element of this list occurs in adjacent matched pairs, ie: [1, 1, 5, 5, 2, 2]
 * removeStutter: removes the adjacent matched elements from a perfectly stuttered list (like above), ie: [1, 1, 5, 5, 2, 2] => [1, 5, 2]
 *
 */
public class LinkedIntList {
    private ListNode front; // first value in the list

    // post: constructs an empty list
    public LinkedIntList() {
        front = null;
    }

    // QUIZ CODE FOLLOWS \/\/\/

    // A. isPerfectStutter(); returns true if this list has exactly two int data throughout the list (like stutter() was just used on the list).
    // A. isPerfectStutter() is a boolean method that can tell if this list has two int data throughout.
    // Empty lists and lists of length 1 will return false, because it takes two or more int's to be stuttered.
    public boolean isPerfectStutter() {
        if (this.size() <= 1 || this.size() % 2 != 0)
            // automatically false if there are not enough elements to be a perfect stutter, or if the list has an odd number of elements
            return false;

        // start at the first node
        ListNode currentNode = this.front;

        // iterate through the list
        while (currentNode.next != null && currentNode.next.next != null) {
            if (currentNode.data != currentNode.next.data)
                return false; // if this node and the next node's data are not equal, this list is not stuttered, no point in continuing

            // skip ahead to the next pair
            currentNode = currentNode.next.next;
        }

        return true;
    }

    // B. removeStutter(); a void method that will undo the stutter() operation for a list where isPerfectStutter() is true.
    // B removeStutter() will undo the stutter effect created by the stutter() method,
    // and needs to first check if isPerfectStutter() is true,
    // because you cannot remove the stutter() signature if it's not present.
    public void removeStutter() {
        if (!this.isPerfectStutter())
            return; // nothing to do if the list isn't perfectly stuttered

        // begin with the first node
        ListNode currentNode = this.front;
        int initialSize = this.size();

        for (int i = 0; i < initialSize / 2; i++) {
            // reset the current node's link to skip the next element
            currentNode.next = currentNode.next.next;

            // move to the new next Node and continue the process
            currentNode = currentNode.next;
        }

    }

    // QUIZ CODE ENDS /\/\/\

    // post: returns the current number of elements in the list
    public int size() {
        int count = 0;
        ListNode current = front;
        while (current != null) {
            current = current.next;
            count++;
        }
        return count;
    }

    // pre : 0 <= index < size()
    // post: returns the integer at the given index in the list
    public int get(int index) {
        return nodeAt(index).data;
    }

    // post: creates a comma-separated, bracketed version of the list
    public String toString() {
        if (front == null) {
            return "[]";
        } else {
            String result = "[" + front.data;
            ListNode current = front.next;
            while (current != null) {
                result += ", " + current.data;
                current = current.next;
            }
            result += "]";
            return result;
        }
    }

    // post : returns the position of the first occurrence of the given
    // value (-1 if not found)
    public int indexOf(int value) {
        int index = 0;
        ListNode current = front;
        while (current != null) {
            if (current.data == value) {
                return index;
            }
            index++;
            current = current.next;
        }
        return -1;
    }

    // post: appends the given value to the end of the list
    public void add(int value) {
        if (front == null) {
            front = new ListNode(value);
        } else {
            ListNode current = front;
            while (current.next != null) {
                current = current.next;
            }
            current.next = new ListNode(value);
        }
    }

    // pre: 0 <= index <= size()
    // post: inserts the given value at the given index
    public void add(int index, int value) {
        if (index == 0) {
            front = new ListNode(value, front);
        } else {
            ListNode current = nodeAt(index - 1);
            current.next = new ListNode(value, current.next);
        }
    }

    // pre : 0 <= index < size()
    // post: removes value at the given index
    public void remove(int index) {
        if (index == 0) {
            front = front.next;
        } else {
            ListNode current = nodeAt(index - 1);
            current.next = current.next.next;
        }
    }

    // pre : 0 <= i < size()
    // post: returns a reference to the node at the given index
    private ListNode nodeAt(int index) {
        ListNode current = front;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    // list node class from the homework
    // ListNode is a class for storing a single node of a linked list. This node class is for a list of integer values.
    private class ListNode {
        public int data; // data stored in this node
        public ListNode next; // link to next node in the list

        // post: constructs a node with data 0 and null link
        public ListNode() {
            this(0, null);
        }

        // post: constructs a node with given data and null link
        public ListNode(int data) {
            this(data, null);
        }

        // post: constructs a node with given data and given link
        public ListNode(int data, ListNode next) {
            this.data = data;
            this.next = next;
        }
    }

}
