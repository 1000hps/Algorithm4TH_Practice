import java.util.ArrayList;

public class EdgeWeightedGraph {

    private ArrayList<Edge>[] adjList;
    private int V;
    private int E;

    /** build an edgeweightedgraph of V vertices. */
    public EdgeWeightedGraph(int V) {
        adjList = (ArrayList<Edge>[]) new ArrayList[V];
        this.V = V;
        for (int i = 0; i < V; i ++) {
            adjList[i] = new ArrayList<>();
        }
    }

    /** build an EWG from an input file. */
    public EdgeWeightedGraph(In in) {
        this(in.readInt());
        int edge_number = in.readInt();
        for (int i = 0; i < edge_number; i++) {
            int v = in.readInt();
            int w = in.readInt();
            double weight = in.readDouble();
            Edge curr_edge = new Edge(v, w, weight);
            addEdge(curr_edge);
        }
    }

    /** number of vertices. */
    public int V() {
        return V;
    }

    /** number of total edges. */
    public int E() {
        return E;
    }

    /** add edge e to the graph. */
    public void addEdge(Edge e) {
        int v = e.either();
        int w = e.other(v);
        adjList[v].add(0, e);
        adjList[w].add(0, e);
        E += 1;
    }

    /** all edges incident to v. */
    public Iterable<Edge> adj(int v) {
        return adjList[v];
    }

    /** all of this graph's edges. */
    public Iterable<Edge> edges() {
        ArrayList<Edge> allEdges = new ArrayList<>();
        for (int v = 0; v < V; v++) {
            for (Edge e: adjList[v]) {
                if (e.other(v) > v)
                    allEdges.add(e);
            }
        }
        return allEdges;
    }

    /** EWG string representation. */
    public String toString() {
        String s = "Number of vertices: " + V + "\n";
        s += "Number of edges: " + E + "\n";
        for (Edge e: edges())
            s += e.toString() + "\n";
        return s;
    }

    public static void main(String[] args) {
        EdgeWeightedGraph ewg = new EdgeWeightedGraph(new In(args[0]));
        System.out.println(ewg.toString());
    }


}
