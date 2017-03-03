import java.util.Iterator;
import java.util.EmptyStackException;
import java.util.NoSuchElementException;


/**
 * @author thokent
 *
 */
public class ArrayIntStack {
    // private fields
    private int[] elementData;
    private int size;

    // public static fields
    public static final int MAX_CAPACITY = 20;

    /**
     * default constructor for custom int stack
     * 
     */
    public ArrayIntStack() {
        this.elementData = new int[MAX_CAPACITY];
        this.size = 0;
    }

    /**
     * @return
     */
    public boolean empty() {
        return size == 0;
    }

    /**
     * @return
     */
    public int peek() {
        if (size == 0)
            throw new EmptyStackException();
        return elementData[size - 1];
    }

    /**
     * @return
     */
    public int pop() {
        if (size == 0)
            throw new EmptyStackException();

        int returning = elementData[size - 1];
        elementData[size - 1] = 0;
        size--;

        return returning;
    }

    /**
     * @param item
     * @return
     */
    public int push(int item) {
        if (size == MAX_CAPACITY)
            throw new StackOverflowError();

        elementData[size] = item;
        size++;

        return item;
    }

    /**
     * @return
     */
    public IntStackIterator iterator() {
        return new IntStackIterator(new ArrayIntStack());
    }

    /**
     * @author thokent
     *
     */
    public static class IntStackIterator implements Iterator<Integer> {
        private ArrayIntStack list;
        private int position;

        /**
         * @param list
         */
        public IntStackIterator(ArrayIntStack list) {
            this.list = list;
            this.position = list.size;
        }

        /**
         * @return
         */
        public boolean hasNext() {
            return list.size > 0;
        }

        /**
         * @return
         */
        public Integer next() {
            if (!hasNext())
                throw new NoSuchElementException();

            int returning = list.pop();
            this.position--;
            return returning;
        }
    }
}
