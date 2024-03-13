package Q2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class DirectedDFS {
    // -----------------------------------------------------
    // Title: DirectedDFS class
    // Author: Ceyda Kuşçuoğlu
 
    // Description: This class implements the depth first search algorithm from the
    // book
    // -----------------------------------------------------
    private int[] edgeTo;
    private final int s;
    private List<List<Integer>> paths;
    private boolean[] marked;

    public DirectedDFS(Digraph G, int s) {
        edgeTo = new int[G.V()];
        this.s = s;
        paths = new ArrayList<>();
        marked = G.getMarked();
        dfs(G, G.getNodeByIndex(s), new ArrayList<>());
    }

    private void dfs(Digraph G, Node v, List<Integer> currentPath) {

        if (marked[v.getIndex()] == false) { // If the first node is wall it won't turn to true
            marked[v.getIndex()] = true;
            currentPath.add(v.getIndex());// Add the vertex to path
        }
        List<Node> sortedNodesList = new ArrayList<>();
        G.adj(v.getIndex()).forEach(sortedNodesList::add);

        PriorityQueue<Node> sortedNodes = new PriorityQueue<>(sortedNodesList.size(),
                Comparator.comparing(Node::getData));
        sortedNodes.addAll(sortedNodesList);// Takes the adjacent verticies into a priority queue and sorts them

        while (!sortedNodes.isEmpty()) {
            Node w = sortedNodes.poll();

            edgeTo[w.getIndex()] = v.getIndex();
            if (!marked[w.getIndex()]) {
                if (w.getData() == 'E') { // It makes dfs recursively until the 'E' is found
                    paths.add(new ArrayList<>(currentPath));
                    continue;
                } else {
                    dfs(G, w, currentPath);
                }
            }
        }
    }

    public List<List<Integer>> getPaths() {
        return paths;
    }

}
