package Q1;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DirectedDFS {
    private boolean[] marked;
    private int[] edgeTo;
    private final int s;
    BreadthFirstPaths bfs;
    int[] distTo;
    private List<List<Integer>> paths;

    public DirectedDFS(Digraph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        bfs = new BreadthFirstPaths(G);
        distTo = bfs.originalDistTo(s, G);
        paths = new ArrayList<>();
    }

    public void dfs(Digraph G, int v, int hopNumber, int s) {

        for (int w : G.adj(v)) {

            edgeTo[w] = v;
            List<Integer> currentPath = getPath(s, w);

            if (currentPath.size() == hopNumber + 1) {
                paths.add(currentPath);
            }
            if (distTo[w] == hopNumber) {
                continue;
            } else if (distTo[w] < hopNumber) {
                dfs(G, w, hopNumber, s);
            }

        }

    }

    public boolean marked(int v) {
        return marked[v];
    }

    private List<Integer> getPath(int source, int destination) {
        List<Integer> path = new ArrayList<>();
        for (int x = destination; x != source; x = edgeTo[x]) {
            path.add(0, x);
        }
        path.add(0, source);
        return path;
    }

    public void printPaths(File file) {
        Digraph g = new Digraph(file);

        System.out.println("Number of total routes:" + paths.size());
        System.out.println();
        System.out.println("Routes are:");
        System.out.println();
        for (List<Integer> path : paths) {
            for (Integer index : path) {
                System.out.print(g.getDataAtIndex(index) + " ");
            }
            System.out.println(); // Move to the next line for the next path
        }
    }

}
