import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;

/*
 *
 * Name: Thomas Kent
 * Date: 17 Febuary 2017
 * Course: CS 211
 *
 * Quiz14 class
 * Chapter 14 quiz
 * Implements two methods to mirror a stack and queue
 * ie: mirror([10,13,14]) returns [10,13,14,14,13,10], and leaves the parameter stack/queue in its original state
 *
 */
public class Quiz14 {
    // mirrors a stack<T>
    public static <T> Stack<T> mirror(Stack<T> incoming) {
        Stack<T> outgoing = new Stack<T>();
        Queue<T> aux = new LinkedList<T>();

        if (incoming.isEmpty())
            return outgoing; // if the stack to be mirrored is empty, the response should be as well

        // empty the incoming stack into auxilliary queue and into the outgoing stack
        while (!incoming.isEmpty()) {
            T temp = incoming.pop();
            aux.add(temp);
            outgoing.push(temp);
        }

        // the above will have the first half of the outgoing stack present, but in the wrong order. Use helper function to reverse the stack
        reverseStack(outgoing);

        // empty the auxilliary queue into the original stack and the outgoing stack
        while (aux.size() > 0) {
            T temp = aux.remove();
            incoming.push(temp);
            outgoing.push(temp);
        }

        // incoming stack will be in its original state, except backwards. Use helper fucntion to fix it
        reverseStack(incoming);

        return outgoing;
    }

    // mirrors a queue<T>
    public static <T> Queue<T> mirror(Queue<T> incoming) {
        Queue<T> outgoing = new LinkedList<T>();
        Stack<T> aux = new Stack<T>();

        if (incoming.size() == 0)
            return outgoing; // if the queue to be mirrored is empty, the response should be as well

        // empty the incoming queue into auxilliary stack and into the outgoing queue
        while (incoming.size() > 0) {
            T temp = incoming.remove();
            aux.push(temp);
            outgoing.add(temp);
        }

        // empty the auxilliary stack into the original queue and the outgoing queue
        while (!aux.isEmpty()) {
            T temp = aux.pop();
            incoming.add(temp);
            outgoing.add(temp);
        }

        // incoming queue will be in its original state, except backwards. Use helper function to fix it
        reverseQueue(incoming);

        return outgoing;
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

    // helper function to reverse a queue
    private static <T> Queue<T> reverseQueue(Queue<T> incoming) {
        Stack<T> aux = new Stack<T>();

        // empty the queue into a temp stack
        while (!incoming.isEmpty())
            aux.push(incoming.remove());

        // empty the stack back into the queue
        while (aux.size() > 0)
            incoming.add(aux.pop());

        // return the original queue, which is now in reverse order
        return incoming;
    }
}