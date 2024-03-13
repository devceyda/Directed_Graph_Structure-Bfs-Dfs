//-----------------------------------------------------
// Title: DirectedDFS class
// Author: Ceyda Kuşçuoğlu

// Description: This class implements the depth first search algorithm from the book
//-----------------------------------------------------

package FinalVersionOfQ1;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DirectedDFS {
    private int[] edgeTo;
    private final int s;
    private BreadthFirstPaths bfs;
    private int[] distTo;
    private List<List<Integer>> paths; // The outer list holds the paths,the inner list holds the verticies in path

    public DirectedDFS(Digraph G, int s) {
        edgeTo = new int[G.V()];
        this.s = s;
        bfs = new BreadthFirstPaths(G);
        distTo = bfs.originalDistTo(s, G);
        paths = new ArrayList<>();
    }

    public void dfs(Digraph G, Node v, int hopNumber) {

        // This method makes dfs without nlooking if it's marked it just goes until the
        // paths size reaches the hopNumber

        for (Node w : G.adj(v.getIndex())) {
            edgeTo[w.getIndex()] = v.getIndex();
            List<Integer> currentPath = getPath(s, w.getIndex());

            if (currentPath.size() == hopNumber + 1) {
                paths.add(currentPath);
            }
            if (distTo[w.getIndex()] == hopNumber) {
                continue;
            } else if (distTo[w.getIndex()] < hopNumber) {
                dfs(G, w, hopNumber);
            }

        }

    }

    private List<Integer> getPath(int start, int end) {
        // Takes the verticies between start and end verticies
        List<Integer> path = new ArrayList<>();
        for (int x = end; x != start; x = edgeTo[x]) {
            path.add(0, x);
        }
        path.add(0, start);
        return path;
    }

    public void printPaths(File file) {
        // Prints the paths list
        Digraph g = new Digraph(file);

        System.out.println("Number of total routes:" + paths.size());

        System.out.println("Routes are:");

        for (int i = paths.size() - 1; i >= 0; i--) {
            for (int j = 0; j < paths.get(i).size(); j++) {
                System.out.print(g.getDataAtIndex(paths.get(i).get(j)));
                if (j < paths.get(i).size() - 1) {
                    System.out.print("-");
                }
            }
            System.out.println();
        }

    }

}
