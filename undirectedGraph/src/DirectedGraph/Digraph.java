package DirectedGraph;

import edu.princeton.cs.algs4.In;

import java.util.*;

/** A class for directional graph.
 *  I use an array of ArrayList to represent the graph structure.
 */


public class Digraph {

    private ArrayList<Integer>[] adj;
    private int V;
    private int E;

    /** Build a directional graph of V vertices. */
    public Digraph(int V) {
        this.V = V;
        adj = (ArrayList<Integer>[]) new ArrayList[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    public Digraph(In in) {
        this(in.readInt());
        int num_of_edge = in.readInt();
        for (int i = 0; i < num_of_edge; i++) {
            int start = in.readInt();
            int end = in.readInt();
            addEdge(start, end);
        }
    }

    public int V() {
        return this.V;
    }

    public int E() {
        return this.E;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    public void addEdge(int v, int w) {
        if (this.adj[v].contains(w))
            return;
        this.adj[v].add(0, w);
        E += 1;
    }

    /** Get the reverse of the graph.
     *  Reverse the direction of each edge.
     */
    public Digraph reverse() {
        Digraph reversed_graph = new Digraph(V());
        for (int i = 0; i < V(); i++) {
            for (int j: adj(i)) {
                reversed_graph.addEdge(j, i);
            }
        }
        return reversed_graph;
    }

    /** String representation of digraph. */
    public String toString() {
        String str = "Number of Vertices: " + V + "\n";
        str += "Number of Edges: " + E + "\n";
        for (int i = 0; i < V; i++) {
            str = str + i + ": ";
            for (int j = 0; j < adj[i].size(); j++) {
                str = str + adj[i].get(j) + " ";
            }
            str += "\n";
        }
        return str;
    }

    public static void main(String[] arg) {
        Digraph dg = new Digraph(new In(arg[0]));
        System.out.println("Original DirectedGraph.Digraph: ");
        System.out.println(dg.toString());
        System.out.println("Reversed DirectedGraph.Digraph above: ");
        System.out.println(dg.reverse().toString());
    }
}
