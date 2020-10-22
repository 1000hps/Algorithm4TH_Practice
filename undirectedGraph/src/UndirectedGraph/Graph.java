package UndirectedGraph;

import edu.princeton.cs.algs4.In;

import java.util.ArrayList;

public class Graph {

    private int V;
    private int E;
    private ArrayList<Integer>[] adj;

    // create a V-vertex graph with no edges
    public Graph(int V) {
        this.V = V;
        this.E = 0;
        adj = (ArrayList<Integer>[]) new ArrayList[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    // read a graph from input stream in
    public Graph(In in) {
        this(in.readInt());
        int E = in.readInt();
        for (int i = 0; i < E; i++) {
            addEdge(in.readInt(), in.readInt());
        }
    }

    // return the number of vertices
    public int V() {
        return V;
    }

    // return the number of edges
    public int E() {
        return E;
    }

    // add edge v-w to this graph
    public void addEdge(int v, int w) {
        adj[v].add(0, w);
        adj[w].add(0, v);
        E += 1;
    }

    // return vertices adjacent to v
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    // String representation
    public String toString() {
        String s = V + " vertices, " + E + " edges\n";
        for (int v = 0; v < V; v++) {
            s += v + ": ";
            for (int w: this.adj(v))
                s += w + " ";
            s += "\n";
        }
        return s;
    }

    public static void main(String[] args) {
        Graph g = new Graph(new In(args[0]));
        System.out.println(g.toString());
    }
}
