package Q2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

//-----------------------------------------------------
// Title: Digraph implementation class
// Author: Ceyda Kuşçuoğlu

// Description: This class implements the directed graph structure
//-----------------------------------------------------

public class Digraph {

    private Node node;
    private int index;
    private ArrayList<Node> mazeVerticies; // List of verticies
    private boolean[] marked;
    private int V;
    private Bag<Node>[] adj;

    public Digraph(File file) {
        mazeVerticies = new ArrayList<>();
        index = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                char[] chars = line.toCharArray();// Turns the items into char and assigns to array
                for (char c : chars) {
                    node = new Node(c, index);// Creates node with taken char and assigns index to it
                    mazeVerticies.add(node);// Adds the new node to vertex list
                    index++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        marked = new boolean[mazeVerticies.size()];
        for (int i = 0; i < marked.length; i++) { // If the char of the node equals the wall marked value of it will be
                                                  // true
            if (mazeVerticies.get(i).getData() == '+' || mazeVerticies.get(i).getData() == '-'
                    || mazeVerticies.get(i).getData() == '|') {
                marked[mazeVerticies.get(i).getIndex()] = true;
            } else {
                marked[mazeVerticies.get(i).getIndex()] = false;
            }
        }

        this.V = mazeVerticies.size();
        adj = (Bag<Node>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Node>();
        }

        for (int i = 0; i < V(); i++) { // Adds the edges to verticies
            if ((i - 16) > 0 && i - 16 >= 0 && i - 16 < V()) { // For the above adjacent vertex
                addEdge(mazeVerticies.get(i), mazeVerticies.get(i - 16));
            }

            if ((i + 16) < V() && i + 16 >= 0 && i + 16 < V()) { // For the below adjacent vertex
                addEdge(mazeVerticies.get(i), mazeVerticies.get(i + 16));
            }

            if ((i + 1) < V() && i + 1 >= 0 && i + 1 < V()) { // For the right adjacent vertex
                addEdge(mazeVerticies.get(i), mazeVerticies.get(i + 1));
            }
            if ((i - 1) < V() && i - 1 >= 0 && i - 1 < V()) { // For the left adjacent vertex
                addEdge(mazeVerticies.get(i), mazeVerticies.get(i - 1));
            }

        }

    }

    public Node getNodeByIndex(int searchIndex) { // This method returns the node that has given index
        for (Node node : mazeVerticies) {
            if (node.getIndex() == searchIndex) {
                return node;
            }
        }
        return null;
    }

    public void addEdge(Node v, Node w) {
        adj[v.getIndex()].add(w);
    }

    public ArrayList<Node> getMazeVerticies() {
        return mazeVerticies;
    }

    public char getDataAtIndex(int index) {
        // This method takes the index and returns the data of that node
        if (index >= 0 && index < mazeVerticies.size()) {
            Node n = mazeVerticies.get(index);
            return n.getData();
        } else {
            return (Character) null;
        }
    }

    public boolean[] getMarked() {
        return marked;
    }

    public int V() {
        return mazeVerticies.size();
    }

    public Iterable<Node> adj(int v) {
        return adj[v];
    }

}

class Node {
    // This class implements the node structure
    private char data;
    private int index;

    public Node() {

    }

    public Node(char data, int index) {
        this.data = data;
        this.index = index;
    }

    public Node(char data) {
        this.data = data;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public char getData() {
        return data;
    }

}
