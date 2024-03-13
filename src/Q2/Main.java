package Q2;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class Main {
    // -----------------------------------------------------
    // Title: Main class
    // Author: Ceyda Kuşçuoğlu
 
    // Description: This class runs the main method
    // -----------------------------------------------------
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String filename = scan.next();// Takes the file name from user
        File file = new File(filename);
        Digraph G = new Digraph(file);

        DirectedDFS dfs = new DirectedDFS(G, 0);
        System.out.println(dfs.getPaths().size() + " treasures are found.");// Prints how many paths have been found
        System.out.println();
        System.out.println("Paths are:");
        int i = 1;
        for (List<Integer> path : dfs.getPaths()) {// Prints the paths
            System.out.print(i + ") ");
            for (Integer string : path) {

                System.out.print(G.getDataAtIndex(string));
            }
            System.out.print("E");
            System.out.println();
            i++;
        }

    }

}
