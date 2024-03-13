//-----------------------------------------------------
// Title: BreadthFirstPaths class
// Author: Ceyda Kuşçuoğlu

// Description: This class implements the breadth first search algorithm from the book
//-----------------------------------------------------
package Q1;

import java.util.ArrayList;
import java.util.List;

public class BreadthFirstPaths {
    private boolean[] marked;
    private int[] edgeTo;
    int[] distTo;
    private List<List<Integer>> paths;
    // ArrayList<Bag<Node>> verticies = new ArrayList<>();

    public void reset() {
        for (int i = 0; i < marked.length; i++) {
            marked[i] = false;
        }
        for (int i = 0; i < distTo.length; i++) {
            distTo[i] = 0;
        }
        for (int i = 0; i < edgeTo.length; i++) {
            edgeTo[i] = 0;
        }
    }

    public BreadthFirstPaths(Digraph G) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        distTo = new int[G.V()];
        paths = new ArrayList<>();
    }

    private List<Integer> getPath(int source, int destination) {
        List<Integer> path = new ArrayList<>();
        for (int x = destination; x != source; x = edgeTo[x]) {
            path.add(0, x);
        }
        path.add(0, source);
        return path;
    }

    private void printPaths() {
        System.out.println("Paths with length equal to hop number:");
        for (List<Integer> path : paths) {
            System.out.println(path);
        }
    }

    // public void bfs(Digraph G, int s, int hopNumber) {
    //     ArrayQueue<Integer> q = new ArrayQueue<Integer>();
    //     bfs(G, s);
    //     q.add(s);
    //     // marked[s] = true;
    //     // distTo[s] = 0;
    //     while (!q.isEmpty()) {
    //         int v = q.remove();

    //          List<Integer> currentPath = getPath(s, v);

    //         // Check if the length of the current path equals the hop number
    //         if (currentPath.size() == hopNumber + 1) {
    //             paths.add(currentPath);
    //         }
           

    //         for (int w : G.adj(v)) {
    //             // if (!marked[w]) {
    //             // resetMarked();
    //             if (distanceTo(w,originalDistTo(s, G)) <= hopNumber) {
    //                 q.add(w);
    //             }

                
    //         }
    //     }
    //     printPaths();

    // }

    public int[] bfs(Digraph G, int s) {
        ArrayQueue<Integer> q = new ArrayQueue<Integer>();
        q.add(s);
        marked[s] = true;
        distTo[s] = 0;
        while (!q.isEmpty()) {
            int v = q.remove();
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    q.add(w);
                    marked[w] = true;
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                }
            }
        }
        return distTo;
    }

    public int distanceTo(int v) {
        if (!marked[v]) {
            return -1; // Vertex not reachable
        }
        return distTo[v];
    }

    public int distanceTo(int v, int[] originalDistTo) {
        if (!marked[v]) {
            return -1; // Vertex not reachable
        }
        return originalDistTo[v];
    }

    public int[] originalDistTo(int s, Digraph G) {
        bfs(G, s);
        return distTo;

    }

    public int distanceTo(int s, int v, Digraph G) {
        bfs(G, s);
        if (!marked[v]) {
            return -1; // Vertex not reachable
        }
        return distTo[v];

    }

    // private void printFlightPath(int source, int destination) {
    //     System.out.println("Flight path: " + constructPath(source, destination));
    // }

    // private String constructPath(int source, int destination) {
    //     StringBuilder path = new StringBuilder();
    //     for (int x = destination; x != source; x = edgeTo[x]) {
    //         path.insert(0, " -> " + x);
    //     }
    //     path.insert(0, source);
    //     return path.toString();
    // }
}