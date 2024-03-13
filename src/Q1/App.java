package Q1;

import java.io.File;

public class App {
    public static void main(String[] args) throws Exception {
        File file = new File("C:\\Users\\ceyda\\OneDrive\\Masaüstü\\Cmpe 224\\Assignment 2\\src\\Q1\\input.txt");
        Digraph G = new Digraph(file);
        G.printDigraph();

        for (Node string : G.getVerticies()) {
            System.out.println(string);
        }
        System.out.println();
       // BreadthFirstPaths bfs = new BreadthFirstPaths(G);
        //bfs.bfs(G, 1, 2);
        DirectedDFS dfs = new DirectedDFS(G, 1);
        dfs.dfs(G, 1, 2,1);
        dfs.printPaths(file);

        // for (Integer string : G.adj(0)) {
        //     System.out.println(string);

        // }
        // DirectedDFS dfs = new DirectedDFS(G, 0);

    }
}
