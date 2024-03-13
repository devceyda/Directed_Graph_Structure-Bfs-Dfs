package Q1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Digraph {

    private int V;
    private int E;
    private Bag<Integer>[] adj;
    ArrayList<Node> verticies = new ArrayList<>();

    public Digraph(File file) {
        int index = 0;
        this.E = 0;

        try (FileReader reader = new FileReader(file)) {
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                Node n;
                String[] cities = line.split(",");

                if (!contains(cities[0], verticies)) {
                    n = new Node(cities[0]);
                    n.setIndex(index);
                    index++;
                    verticies.add(n);
                }
                if (!contains(cities[1], verticies)) {
                    n = new Node(cities[1]);
                    n.setIndex(index);
                    index++;
                    verticies.add(n);
                }
            }
            // Define a custom comparator based on the 'data' property
            Comparator<Node> nodeComparator = Comparator.comparing(Node::getData);

            // Sort the list using Collections.sort() and the custom comparator
            Collections.sort(verticies, nodeComparator);
            this.V = verticies.size();

            adj = (Bag<Integer>[]) new Bag[V];
            for (int v = 0; v < V; v++) {
                adj[v] = new Bag<Integer>();
            }
            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileReader reader = new FileReader(file)) {
            BufferedReader bufferedReader2 = new BufferedReader(reader);
            String newLine;

            while ((newLine = bufferedReader2.readLine()) != null) {

                String[] cities = newLine.split(",");
                String a = cities[0];
                String b = cities[1];

                addEdge(getIndex(a, verticies), getIndex(b, verticies));
            }
            bufferedReader2.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public int outDegree(int v) {
        System.out.println(v + " " + adj[v].size());
        return adj[v].size();
    }

    public ArrayList<Node> getVerticies() {
        return verticies;
    }

    public void printDigraph() {
        for (int v = 0; v < V; v++) {
            System.out.print(verticies.get(v).data + ": ");
            for (int w : adj[v]) {
                System.out.print(verticies.get(w).data + ", ");
            }
            System.out.println();
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
        E++;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    public boolean contains(String data, ArrayList<Node> list) {
        Node node = new Node(data);
        for (Node n : list) {
            if (n.getData().equals(node.getData())) {
                return true;
            }
        }
        return false;
    }

    public int getIndex(String data, ArrayList<Node> list) {
        for (int i = 0; i < list.size(); i++) {
            Node n = list.get(i);
            if (n.getData().equals(data)) {
                return i;
            }
        }
        return -1;
    }

    public String getDataAtIndex(int index) {
        if (index >= 0 && index < verticies.size()) {
            Node n = verticies.get(index);
            return n.getData();
        } else {
            return null; // or throw an exception, depending on your requirements
        }
    }

}

class Node {

    String data;
    private int index;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Node(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    @Override
    public String toString() {
        return data;
    }

}