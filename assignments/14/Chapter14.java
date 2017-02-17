import java.util.Queue;
import java.util.LinkedList;

/*
*
* Name: Thomas Kent
* Date: 16 February 2017
* Course: CS 211
*
* Chapter14 class
* Implements 4 solutions from the book
* 
* stutter: creates a duplicate of each element in the next index. ie: 1,2,3 -> 1,1,2,2,3,3
* equals: checks two stacks for equality
* isSorted: checks a stack for ascending order
* removeMin: removes every instance of the minimum value from a stack
* 
*/
public class Chapter14 {
    // 2. stutter, modify to return the stuttered Stack, and leave the original Stack unchanged.
    public static <T> Stack<T> stutter(Stack<T> incoming) {
        if (incoming.size() == 0)
            return incoming; // if the stack is empty, just return it back

        Queue<T> aux = new LinkedList<T>();
        Stack<T> outgoing = new Stack<T>();

        // empty the stack into a temp queue
        while (incoming.size() > 0)
            aux.add(incoming.pop());

        // empty the temp queue back into the stack, but double it up
        while (!aux.isEmpty()) {
            incoming.push(aux.peek());
            outgoing.push(aux.peek());
            outgoing.push(aux.remove());
        }

        // use helper function to get the stack in the right order
        reverseStack(outgoing);

        return outgoing;
    }

    // 5. equals, use the compareTo method for comparisons, leave the original Stacks unchanged.
    public static <T extends Comparable<T>> boolean equals(Stack<T> in1, Stack<T> in2) {
        if (in1 == in2)
            return true; // if the stacks are the same reference, they will be equal.

        if (in1.size() != in2.size())
            return false; // if the lengths aren't the same, the stacks can't be equal. Plus it doubles as error-prevention for the loop below

        if (in1.size() == 0)
            return true; // lengths are the same by this point, so if one stack is empty, the other must be too.

        Queue<T> aux1 = new LinkedList<T>();
        Queue<T> aux2 = new LinkedList<T>();
        boolean overallEquality = true; // equal until proven otherwise

        while (in1.size() > 0) {
            int comp = in1.peek().compareTo(in2.peek());

            if (comp != 0)
                // can't just return false, the original stacks are half empty
                overallEquality = false;

            // store the value so we can fix the originals later
            aux1.add(in1.pop());
            aux2.add(in2.pop());
        }

        // move the values from the temp queue back into the original stacks
        while (!aux1.isEmpty()) {
            in1.push(aux1.remove());
            in2.push(aux2.remove());
        }

        // use the helper function to get the stacks back in the original order
        reverseStack(in1);
        reverseStack(in2);

        return overallEquality;
    }

    // 15. isSorted, use compareTo to evaluate if sorted, and leave the original Stack unchanged.
    public static <T extends Comparable<T>> boolean isSorted(Stack<T> incoming) {
        // an empty or single-element stack is considered sorted
        if (incoming.size() <= 1)
            return true;

        Queue<T> aux = new LinkedList<T>();
        boolean sortedNess = true; // equal until proven otherwise

        while (incoming.size() > 1) {
            T next = incoming.pop();
            int comp = next.compareTo(incoming.peek());

            if (comp < 0)
                // can't just return false, the original stacks are half empty
                sortedNess = false;

            // store the value so we can fix the originals later
            aux.add(next);
        }
        // add the alst value that has only been peeked, to the queue
        aux.add(incoming.pop());

        // move the values from the temp queue back into the original stack
        while (!aux.isEmpty())
            incoming.push(aux.remove());

        // use the helper function to get the stack back in the original order
        reverseStack(incoming);

        return sortedNess;
    }

    // 19. removeMin, use compareTo for evaluating the minimum, if the original Stack is empty you can return null.
    public static <T extends Comparable<T>> T removeMin(Stack<T> incoming) {
        if (incoming.size() == 0)
            return null; // empty stack returns null
        if (incoming.size() == 1)
            return incoming.pop(); // stack with only one element has an obvious min element

        Queue<T> aux = new LinkedList<T>();
        T minSoFar = incoming.peek(); // start off with a minimum in the list

        while (incoming.size() > 0) {
            T temp = incoming.pop();
            aux.add(temp); // store the value to fix the stack later

            if (temp.compareTo(minSoFar) < 0)
                minSoFar = temp; // if the current value is less than the current minimum, replace it
        }

        while (!aux.isEmpty()) {
            T temp = aux.remove();
            // refill the stack, but only with values that are greater than the minimum
            if (temp.compareTo(minSoFar) > 0)
                incoming.push(temp);
        }

        // use helper function to fix the stack order
        reverseStack(incoming);

        return minSoFar;
    }

    // helper function to reverse a stack
    private static <T> Stack<T> reverseStack(Stack<T> incoming) {
        Queue<T> aux = new LinkedList<T>();

        // empty the stack in a temp queue
        while (incoming.size() > 0)
            aux.add(incoming.pop());

        // empty the queue back into the stack
        while (!aux.isEmpty())
            incoming.push(aux.remove());

        // return the original stack, which is now in reverse order
        return incoming;
    }
}
