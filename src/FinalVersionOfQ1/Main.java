package FinalVersionOfQ1;

import java.io.File;
import java.util.Scanner;

//-----------------------------------------------------
// Title: Main class
// Author: Ceyda Kuşçuoğlu

// Description: This class runs the main method
//-----------------------------------------------------

public class Main {
    public static void main(String[] args) throws Exception {

        Scanner scan = new Scanner(System.in);

        String fileName = scan.next(); // Takes the file name from user
        int hopNumber = scan.nextInt(); // Takes the hopNumber from user
        String sourceCity = scan.next(); // Takes the source city from user

        File file = new File(fileName);
        Digraph G = new Digraph(file);

        Node n = new Node(sourceCity);
        n.setIndex(G.getIndex(n.getData()));

        DirectedDFS dfs = new DirectedDFS(G, 1);
        dfs.dfs(G, n, hopNumber);
        dfs.printPaths(file);

    }
}
