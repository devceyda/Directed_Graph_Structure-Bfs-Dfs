package FinalVersionOfQ1;

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

    private int V;
    private Bag<Node>[] adj;
    private ArrayList<Node> verticies;

    public Digraph(File file) {
        int index = 0;
        verticies = new ArrayList<>(); // The list that holds the verticies in graph

        try (FileReader reader = new FileReader(file)) {
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                Node n;
                String[] cities = line.split(",");// It splits the line with "," and takes the items in array

                if (!contains(cities[0], verticies)) { // If verticies list not contains the vertex it creates node and
                                                       // adds to the vertex list
                    n = new Node(cities[0], index);
                    index++;
                    verticies.add(n);
                }
                if (!contains(cities[1], verticies)) { // If verticies list not contains the vertex it creates node and
                                                       // adds to the vertex list
                    n = new Node(cities[1], index);
                    index++;
                    verticies.add(n);
                }
            }

            bubbleSort(verticies); // Sorts the verticies according to their data and alphabetical order
            this.V = verticies.size();

            adj = (Bag<Node>[]) new Bag[V];
            for (int v = 0; v < V; v++) {
                adj[v] = new Bag<Node>();
            }
            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileReader reader = new FileReader(file)) {
            BufferedReader bufferedReader2 = new BufferedReader(reader);
            String newLine;

            while ((newLine = bufferedReader2.readLine()) != null) {
                // This loop adds the edge between veriticies
                String[] cities = newLine.split(",");
                String a = cities[0];
                String b = cities[1];

                addEdge(getNodeByIndex(getIndex(a)), getNodeByIndex(getIndex(b)));
            }
            bufferedReader2.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ArrayList<Node> getVerticies() {
        return verticies;
    }

    public int V() {
        return V;
    }

    public Node getNodeByIndex(int searchIndex) { // this method returns the node that has given index
        for (Node node : verticies) {
            if (node.getIndex() == searchIndex) {
                return node;
            }
        }
        return null;
    }

    public void addEdge(Node v, Node w) {
        adj[v.getIndex()].add(w);
    }

    public Iterable<Node> adj(int v) {
        return adj[v];
    }

    public boolean contains(String data, ArrayList<Node> list) {
        // This method returns if given list contains the wanted node
        Node node = new Node(data);
        for (Node n : list) {
            if (n.getData().equals(node.getData())) {
                return true;
            }
        }
        return false;
    }

    public int getIndex(String data) {
        // This method takes the data of node and find returns the index of that node
        for (int i = 0; i < verticies.size(); i++) {
            Node n = verticies.get(i);
            if (n.getData().equals(data)) {
                return i;
            }
        }
        return -1;
    }

    public String getDataAtIndex(int index) {
        // This method takes the index and returns the data of that node
        if (index >= 0 && index < verticies.size()) {
            Node n = verticies.get(index);
            return n.getData();
        } else {
            return null;
        }
    }

    public void bubbleSort(ArrayList<Node> nodes) {
        // This method for sorting the verticies according to their data
        for (int lastUnsortedIndex = nodes.size() - 1; lastUnsortedIndex > 0; lastUnsortedIndex--) {
            for (int i = 0; i < lastUnsortedIndex; i++) {
                if (nodes.get(i).getData().compareTo(nodes.get(i + 1).getData()) > 0) {
                    swap(nodes, i, i + 1);
                }
            }
        }
    }

    private void swap(ArrayList<Node> nodes, int i, int j) {
        if (i == j) {
            return;
        }

        Node temp = nodes.get(i);
        nodes.set(i, nodes.get(j));
        nodes.set(j, temp);
    }

}

class Node {

    // This class implements the node structure

    private String data;
    private int index;

    public Node() {

    }

    public Node(String data, int index) {
        this.data = data;
        this.index = index;
    }

    public Node(String data) {
        this.data = data;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public String getData() {
        return data;
    }

    @Override
    public String toString() {
        return data;
    }

}