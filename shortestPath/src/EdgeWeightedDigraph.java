/** A digraph class with weighted and directed edges. */

import java.util.*;

public class EdgeWeightedDigraph {
    private int V; // number of vertices.
    private int E; // number of edges.
    private ArrayList<DirectedEdge>[] adj; // adjacency lists.

    // an initial graph with V vertices and no edges.
    public EdgeWeightedDigraph(int V) {
        this.V = V;
        this.adj = (ArrayList<DirectedEdge>[]) new ArrayList[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    // an initial graph from an input file.
    public EdgeWeightedDigraph(In in) {
        this(in.readInt());
        int num_of_edge = in.readInt(); // number of edges.
        for (int i = 0; i < num_of_edge; i++) {
            int v = in.readInt();
            int w = in.readInt();
            double weight = in.readDouble();
            this.addEdge(new DirectedEdge(v, w, weight));
        }
    }


    // number of vertices.
    public int V() {
        return V;
    }

    // number of edges.
    public int E() {
        return E;
    }

    // add directed weighted edge e to this digraph.
    public void addEdge(DirectedEdge e) {
        int start = e.from();
        adj[start].add(e);
        E += 1;
    }

    // edges pointing from v.
    public Iterable<DirectedEdge> adj(int v) {
        return adj[v];
    }

    // all edges in this digraph.
    public Iterable<DirectedEdge> edges() {
        ArrayList<DirectedEdge> all_edges = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            all_edges.addAll(adj[i]);
        }
        return all_edges;
    }

    // string representation.
    public String toString() {
        String s = "Number of vertices: " + V + "\n"
                + "Number of edges: " + E + "\n";

        for (DirectedEdge de: edges()) {
            int i = de.from();
            int j = de.to();
            double weight = de.weight();
            String sub = i + "->" + j + "  " + weight + "\n";
            s += sub;
        }
        return s;
    }

    public static void main(String[] arg) {
        EdgeWeightedDigraph ewd =
                new EdgeWeightedDigraph(new In(arg[0]));
        System.out.println(ewd.toString());


    }


}
