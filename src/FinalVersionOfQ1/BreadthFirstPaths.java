//-----------------------------------------------------
// Title: BreadthFirstPaths class
// Author: Ceyda Kuşçuoğlu

// Description: This class implements the breadth first search algorithm from the book
//-----------------------------------------------------
package FinalVersionOfQ1;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class BreadthFirstPaths {
    private boolean[] marked;
    private int[] edgeTo;
    int[] distTo;

    public BreadthFirstPaths(Digraph G) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        distTo = new int[G.V()];

    }

    public int[] bfs(Digraph G, int s) {
        // This method makes breath first search with queue and return in array type
        // This method returns distTo array
        ArrayQueue<Integer> q = new ArrayQueue<Integer>();
        q.add(s);
        marked[s] = true;
        distTo[s] = 0;
        while (!q.isEmpty()) {
            int v = q.remove();
            for (Node w : G.adj(v)) {
                if (!marked[w.getIndex()]) {
                    q.add(w.getIndex());
                    marked[w.getIndex()] = true;
                    edgeTo[w.getIndex()] = v;
                    distTo[w.getIndex()] = distTo[v] + 1;
                }
            }
        }
        return distTo;
    }

    public int distanceTo(int v) {
        return distTo[v];
    }

    public int[] originalDistTo(int s, Digraph G) {
        bfs(G, s);
        return distTo;

    }

    public class ArrayQueue<T> {
        // This class implements the queue structure
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

}