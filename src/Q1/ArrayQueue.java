//-----------------------------------------------------
// Title: Queue implementation class
// Author: Ceyda Kuşçuoğlu

// Description: This class implements the queue structure
//-----------------------------------------------------

package Q1;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class ArrayQueue<T> {

    private ArrayList<T> queue;

    public ArrayQueue() {
        queue = new ArrayList<T>();
    }

    public void add(T item) {
        queue.add(item);
    }

    public boolean isEmpty() {
        if (queue.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public T remove() {
        if (size() == 0) {
            throw new NoSuchElementException();
        }

        return queue.remove(0);
    }

    public T peek() {
        if (size() == 0) {
            throw new NoSuchElementException();
        }

        return queue.get(0);
    }

    public int size() {
        return queue.size();
    }

    public void printQueue() {
        for (T item : queue) {
            System.out.println(item);
        }
    }
}
