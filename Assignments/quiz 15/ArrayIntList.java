import java.util.Arrays;
import java.util.Iterator;
import java.util.Collection;
import java.util.NoSuchElementException;

/*
 *
 * Name: Thomas Kent
 * Date: 24 Febuary 2017
 * Course: CS 211
 *
 * ArrayIntList class
 * Chapter 15 quiz
 * implement mirror, isPerfectMirror, and undoMirror
 * mirror to double a list by adding its reversed self to the end
 * isPerfectMirror to check if a list's second half is its first half in reverse
 * undoMirror to turn a perfect mirror list into a list half the size by removing the mirrored portion
 *
 */
public class ArrayIntList implements Iterable<Integer> {
    private int[] elementData;  // list of integers
    private int size = 0;       // current number of elements in the list

    public static final int DEFAULT_CAPACITY = 10;

    // YOUR CODE GOES HERE

    // A. isPerfectMirror(); returns true if this list is a mirror from mirror().
    public boolean isPerfectMirror() {
        if (size <= 1)
            // lists with one or zero elements don't have enough values to be a perfect mirror
            return false;

        int halfSize = size / 2;
        for (int i = 0, j = size - 1; i < halfSize; i++, j--)
            // compare the first and last elements, then walk inward
            if (this.elementData[i] != this.elementData[j])
                // if any of the pairs are not equal, the list is not a perfect mirror, no need to keep going
                return false;

        // if the code makes it this far, every pair from the outside in is a match, and the list is a perfect mirror
        return true;
    }

    // B. undoMirror(); a void method that will undo the mirror() operation only if this starts mirrored,
    // otherwise (!isPerfectMirror()) the undoMirror method leaves this unchanged..
    public void undoMirror() {
        if (!this.isPerfectMirror())
            return; // if the list is not a perfect mirror, take no action

        // otherwise cut the list in half.
        size /= 2;

        for (int i = size; i < this.elementData.length; i++)
            // blank out the rest of the list, just to be safe
            this.elementData[i] = 0;
    }

    public void mirror() {
        int[] temp = new int[this.size]; // temp array with just enough size to hold everything

        for (int i = size - 1, j = 0; i >= 0; i--, j++)
            // copy values in reverse order into a temp array
            temp[j] = this.elementData[i];

        int newSize = size * 2;
        // make sure the underlying array has enough room for the mirror
        this.ensureCapacity(newSize);

        for (int i = size, j = 0; i < newSize; i++, j++)
            // add the mirror from the reversed temp array
            this.elementData[i] = temp[j];

        // increase the size to include the mirror
        size = newSize;
    }

    // MY CODE ENDS

    // post: constructs an empty list of default capacity
    public ArrayIntList() {
        this(DEFAULT_CAPACITY);
    }

    // pre : capacity >= 0
    // post: constructs an empty list with the given capacity
    private ArrayIntList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity must be at least 0: " + capacity);
        }
        elementData = new int[capacity];
    }

    // for creating test cases more easily, Reges provided ... arg
    public ArrayIntList(int... elements) {
        this(Math.max(DEFAULT_CAPACITY, elements.length * 2));
        for (int n : elements) {
            add(n);
        }
    }

    // for creating test cases more easily (a dupe of the above constructor)
    public static ArrayIntList withValues(int... elements) {
        ArrayIntList list = new ArrayIntList(Math.max(DEFAULT_CAPACITY, elements.length * 2));
        for (int n : elements) {
            list.add(n);
        }
        return list;
    }

    // for creating test cases more easily
    public ArrayIntList(int element, boolean notCapacity) {
        this();
        add(element);
    }

    // for creating test cases more easily
    public ArrayIntList(Collection<Integer> elements) {
        this(Math.max(DEFAULT_CAPACITY, elements.size() * 2));
        for (int n : elements) {
            add(n);
        }
    }

    // copy constructor; for creating test cases more easily
    public ArrayIntList(ArrayIntList list) {
        this(Math.max(DEFAULT_CAPACITY, list.size() * 2));
        addAll(list);
    }

    // pre : size() < capacity (elementData.length)
    // post: appends the given value to the end of the list
    public void add(int value) {
        add(size, value);
    }

    // pre: size() < capacity (elementData.length) && 0 <= index <= size()
    // post: inserts the given value at the given index, shifting subsequent
    // values right
    public void add(int index, int value) {
        checkIndex(index, 0, size);
        ensureCapacity(size + 1);

        for (int i = size; i > index; i--) {
            elementData[i] = elementData[i - 1];
        }
        elementData[index] = value;
        size++;
    }

    // post: appends all values in the given list to the end of this list
    public void addAll(ArrayIntList other) {
        for (int i = 0; i < other.size; i++) {
            add(other.elementData[i]);
        }
    }

    // post: list is empty
    public void clear() {
        size = 0;
    }

    // post: returns true if the given value is contained in the list, false otherwise
    public boolean contains(int value) {
        return indexOf(value) != -1;
    }

    // post: ensures that the underlying array has the given capacity; if not,
    // the size is doubled (or more if given capacity is even larger)
    public void ensureCapacity(int capacity) {
        if (capacity > elementData.length) {
            int newCapacity = elementData.length * 2 + 1;
            if (capacity > newCapacity) {
                newCapacity = capacity;
            }
            int[] newList = new int[newCapacity];
            for (int i = 0; i < size; i++) {
                newList[i] = elementData[i];
            }
            elementData = newList;
        }
    }

    // returns true if o is an ArrayIntList with the same size and elements as this one
    public boolean equals(Object o) {
        if (!(o instanceof ArrayIntList)) {
            return false;
        }

        ArrayIntList other = (ArrayIntList) o;
        if (this.size != other.size) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            if (elementData[i] != other.elementData[i]) {
                return false;
            }
        }

        return true;
    }

    // pre : 0 <= index < size()
    // post: returns the integer at the given index in the list
    public int get(int index) {
        checkIndex(index);
        return elementData[index];
    }

    // post: returns capacity of this list's underlying array
    public int getCapacity() {
        return elementData.length;
    }

    // post : returns the position of the first occurence of the given
    // value (-1 if not found)
    public int indexOf(int value) {
        for (int i = 0; i < size; i++) {
            if (elementData[i] == value) {
                return i;
            }
        }
        return -1;
    }

    // post: returns true if list is empty, false otherwise
    public boolean isEmpty() {
        return size == 0;
    }

    // post: returns an iterator for this list
    public Iterator<Integer> iterator() {
        return new ArrayIntListIterator(this);
    }

    // pre : 0 <= index < size()
    // post: removes value at the given index, shifting subsequent values left
    public void remove(int index) {
        checkIndex(index);
        for (int i = index; i < size - 1; i++) {
            elementData[i] = elementData[i + 1];
        }
        size--;
    }

    // post: removes all occurrences of the values in the given list from this list
    public void removeAll(ArrayIntList other) {
        int newSize = 0;
        for (int i = 0; i < size; i++) {
            if (!other.contains(elementData[i])) {
                elementData[newSize] = elementData[i];
                newSize++;
            }
            size = newSize;
        }
    }

    // pre : 0 <= index < size()
    // post: replaces the integer at the given index with the given value
    public void set(int index, int value) {
        checkIndex(index);
        elementData[index] = value;
    }

    // post: returns the current number of elements in the list
    public int size() {
        return size;
    }

    // post: returns an array version of the contents of this list
    public int[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    // post: creates a comma-separated, bracketed version of the list
    public String toString() {
        String result = "[";
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                result += ", ";
            }
            if (i < elementData.length) {
                result += elementData[i];
            } else {
                // student's code is bogus; OOB
                result += "OOB!";
            }
        }
        result += "]";
        return result;
    }

    // helpers to make sure indexes do not fall out of bounds
    private void checkIndex(int index) {
        checkIndex(index, 0, size - 1);
    }

    private void checkIndex(int index, int min, int max) {
        if (!(min <= index && index <= max)) {
            throw new ArrayIndexOutOfBoundsException(
                    "Illegal index " + index + "; must be between " + min + " and " + max + "\n" + "list : " + toString() + " (size=" + size + ")\n" + "array: " + Arrays.toString(elementData) + " (capacity=" + elementData.length + ")");
        }
    }

    // Stuart Reges
    // 4/4/05
    //
    // The ArrayIntListIterator class provides a set of utilities for iterating
    // over an ArrayIntList and possibly removing values from the list.

    public static class ArrayIntListIterator implements Iterator<Integer> {
        private ArrayIntList list;    // list to iterate over
        private int position;          // current position within the list
        private boolean removeOK;      // whether it's okay to remove now

        // pre : list != null
        // post: constructs an iterator for the given list
        public ArrayIntListIterator(ArrayIntList list) {
            this.list = list;
            position = 0;
            removeOK = false;
        }

        // post: returns true if there are more elements left, false otherwise
        public boolean hasNext() {
            return position < list.size();
        }

        // pre : hasNext()
        // post: returns the next element in the iteration
        public Integer next() {
            if (!hasNext())
                throw new NoSuchElementException();
            int result = list.get(position);
            position++;
            removeOK = true;
            return result;
        }

        // pre : next() has been called without a call on remove (i.e., at most one
        // call per call on next)
        // post: removes the last element returned by the iterator
        public void remove() {
            if (!removeOK)
                throw new IllegalStateException();
            list.remove(position - 1);
            position--;
            removeOK = false;
        }
    }

}
