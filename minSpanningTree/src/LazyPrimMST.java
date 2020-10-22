import java.util.*;

public class LazyPrimMST {
    private boolean[] marked;
    private ArrayList<Edge> mst;
    private minHeapPQ<Edge> crossingEdge;

    /** Calculate MST based on the input edge-weighted graph.
     *  Assume the input graph is connected and all edge weights
     *  are different.
     */
    public LazyPrimMST(EdgeWeightedGraph G) {
        marked = new boolean[G.V()];
        mst = new ArrayList<>();
        crossingEdge = new minHeapPQ<>();

        relax(0, G);
        while (!crossingEdge.isEmpty()) {
            Edge minWeightEdge = crossingEdge.delMin();
            int v = minWeightEdge.either();
            int w = minWeightEdge.other(v);
            if (marked[v] && marked[w])
                continue;
            mst.add(minWeightEdge);
            if (!marked[v])
                relax(v, G);
            else
                relax(w, G);
        }
    }

    /** all of the MST edges. */
    public Iterable<Edge> edges() {
        return mst;
    }

    /** MST weight.  A LAZY approach. */
    public double weight() {
        double sum = 0.0;
        Iterable<Edge> edges = edges();
        for (Edge e: edges)
            sum += e.weight();
        return sum;
    }

    public static void main(String[] args) {
        EdgeWeightedGraph g = new EdgeWeightedGraph(new In(args[0]));
        LazyPrimMST mst = new LazyPrimMST(g);
        System.out.println(mst.edges().toString());
        System.out.println(mst.weight());
    }

    /** ---------------------------------------------------- */

    /** relax vertex k and add all edges from k to unmarked vertices into pq.
     *  This lazy approach may add ineligible edges to the pq, which is not
     *  very efficient. We leave these possible ineligible edges in the pq until
     *  we use delMin() to check if an edge is eligible or not (in the constructor).
     */
    private void relax(int k, EdgeWeightedGraph G) {
        marked[k] = true;
        for (Edge e: G.adj(k)) {
            int other = e.other(k);
            if (!marked[other])
                crossingEdge.insert(e);
        }
    }
}
